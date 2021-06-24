package digi.coders.mho.Fregments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.JsonArray;
import com.smarteist.autoimageslider.SliderView;
//import com.smarteist.autoimageslider.SliderView;
import digi.coders.mho.Adapter.ShowRoomAdapter;
import digi.coders.mho.Adapter.SliderAdapter;
import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Helper.PrefrenceManager;
import digi.coders.mho.Model.RoomModel;
import digi.coders.mho.Model.SliderModel;
import digi.coders.mho.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<SliderModel> image = new ArrayList();
    private String mParam1;
    private String mParam2;
    SliderView rec_hot;
    TabLayout tablayout;
    View view;
    RecyclerView view_room;
    LinearLayout noroomfound;

    public static RoomFragment newInstance(String param1, String param2) {
        RoomFragment fragment = new RoomFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mParam1 = getArguments().getString(ARG_PARAM1);
            this.mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_room, container, false);
        this.view = inflate;
        this.rec_hot = (SliderView) inflate.findViewById(R.id.rec_hot);
        this.view_room = (RecyclerView) this.view.findViewById(R.id.view_room);
        this.noroomfound = (LinearLayout) this.view.findViewById(R.id.noroomfound);
        TabLayout tabLayout = (TabLayout) this.view.findViewById(R.id.tablayout);

        this.tablayout = tabLayout;
        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.item_tabitem1));

        TabLayout tabLayout2 = this.tablayout;
        tabLayout2.addTab(tabLayout2.newTab().setCustomView(R.layout.item_tabitem2));

        TabLayout tabLayout3 = this.tablayout;
        tabLayout2.addTab(tabLayout3.newTab().setCustomView(R.layout.item_tabitem3));

        if (Constant.isNetwork(getContext())) {
            getSlider();
            getrooms("");
        }
        this.tablayout.setOnTabSelectedListener((TabLayout.OnTabSelectedListener) new TabLayout.OnTabSelectedListener() {
            /* class digi.coders.mho.Fregments.RoomFragment.AnonymousClass1 */
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i("TAB_POSITION", tab.getPosition() + "");
                if (tab.getPosition() == 0) {
                    RoomFragment.this.view_room.setAdapter(null);
                    RoomFragment.this.getrooms("");
                } else if (tab.getPosition() == 2) {
                    RoomFragment.this.view_room.setAdapter(null);
                    RoomFragment.this.getrooms(new PrefrenceManager(RoomFragment.this.getContext()).getuserdetails().getId());

                }else if (tab.getPosition() == 1) {
                    RoomFragment.this.view_room.setAdapter(null);
                    RoomFragment.this.getrooms("newroom");

                }
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        return this.view;
    }

    public void getSlider() {
        Constant.showprogressbar(getContext(), 1);
        Constant.StartConnection().getSlider(Constant.KEYVALUE).enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Fregments.RoomFragment.AnonymousClass2 */

            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Constant.dismissdialog();
                Log.i("getslider_response", response.body().toString());
                try {
                    JSONObject jsonObject = new JSONArray(response.body().toString()).getJSONObject(0);
                    if (jsonObject.getString("res").equalsIgnoreCase("success")) {
                        JSONArray jsonArray1 = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray1.length(); i++) {
                            JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                            RoomFragment.this.image.add(new SliderModel(jsonObject1.getString("id"), jsonObject1.getString("image"), jsonObject1.getString("date"), jsonObject1.getString(NotificationCompat.CATEGORY_STATUS)));
                            RoomFragment.this.rec_hot.setSliderAdapter(new SliderAdapter(RoomFragment.this.image, RoomFragment.this.getContext()));
                        }
                        return;
                    }
                    Toast.makeText(RoomFragment.this.getContext(), "" + jsonObject.getString("res"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Constant.dismissdialog();
                Log.i("getslider_error", t.toString());
            }
        });
    }

    public void getrooms(String userid) {
        if (Constant.isNetwork(getContext())) {
            final Dialog dialog = new Dialog(getContext());
            Context context = getContext();
            getContext();
            View aa = ((LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_progress_bar_layout, (ViewGroup) null, false);
            dialog.setContentView(aa);
            dialog.setCancelable(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            Sprite doubleBounce = new Wave();
            ((ProgressBar) aa.findViewById(R.id.progressas)).setIndeterminateDrawable(doubleBounce);
            doubleBounce.setColor(-1);
            dialog.show();
            final List<RoomModel> roomModelList = new ArrayList<>();
            Constant.StartConnection().ShowRoom(Constant.KEYVALUE, userid).enqueue(new Callback<JsonArray>() {
                /* class digi.coders.mho.Fregments.RoomFragment.AnonymousClass3 */

                @Override // retrofit2.Callback
                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                    Log.i("showroom_response", response.body().toString());
                    dialog.dismiss();
                    try {
                        JSONArray jsonArray = new JSONArray(response.body().toString());
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        if (jsonObject.getString("res").equalsIgnoreCase("success")) {
                            noroomfound.setVisibility(View.GONE);
                            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
                            int i = 0;
                            while (i < jsonArray1.length()) {
                                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                                roomModelList.add(new RoomModel(jsonObject1.getString("id"), jsonObject1.getString("room_name"), jsonObject1.getString("created_id"), jsonObject1.getString("photo"), jsonObject1.getString("announcement"), jsonObject1.getString(NotificationCompat.CATEGORY_STATUS), jsonObject1.getString("date"), jsonObject1.getString("totaljoin_memebr"), jsonObject1.getString("tag")));
                                i++;
                                jsonArray = jsonArray;
                            }
                            ShowRoomAdapter showRoomAdapter = new ShowRoomAdapter(RoomFragment.this.getContext(), roomModelList);
                            RoomFragment.this.view_room.setLayoutManager(new LinearLayoutManager(RoomFragment.this.getContext()));
                            RoomFragment.this.view_room.setAdapter(showRoomAdapter);
                            return;
                        }else {
                            noroomfound.setVisibility(View.VISIBLE);
                            Toast.makeText(context, ""+jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
//                        Toast.makeText(RoomFragment.this.getContext(), jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE), 0).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override // retrofit2.Callback
                public void onFailure(Call<JsonArray> call, Throwable t) {
                    Log.i("showroom_error", t.toString());
                    dialog.dismiss();
                }
            });
        }
    }


}
