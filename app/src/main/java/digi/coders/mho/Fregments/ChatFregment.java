package digi.coders.mho.Fregments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.JsonArray;
import digi.coders.mho.Activity.FriendRequestListActivity;
import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Helper.PrefrenceManager;
import digi.coders.mho.Model.FriendRequestModel;
import digi.coders.mho.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatFregment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView friendlist;
    LinearLayout friendrequest;
    private String mParam1;
    private String mParam2;
    List<FriendRequestModel> requestModelList = new ArrayList();
    View v;

    public static ChatFregment newInstance(String param1, String param2) {
        ChatFregment fragment = new ChatFregment();
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
        View inflate = inflater.inflate(R.layout.fragment_chat_fregment, container, false);
        this.v = inflate;
        this.friendrequest = (LinearLayout) inflate.findViewById(R.id.friendrequest);
        this.friendlist = (RecyclerView) this.v.findViewById(R.id.friendlist);
        this.friendrequest.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Fregments.ChatFregment.AnonymousClass1 */

            public void onClick(View view) {
                ChatFregment.this.startActivity(new Intent(ChatFregment.this.getContext(), FriendRequestListActivity.class));
            }
        });
        getfriendlist();
        return this.v;
    }

    public void getfriendlist() {
        this.requestModelList.clear();
        Constant.showprogressbar(getContext(), 1);
        Constant.StartConnection().FriendRequestList(Constant.KEYVALUE, new PrefrenceManager(getContext()).getuserdetails().getId()).enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Fregments.ChatFregment.AnonymousClass2 */

            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                String str = "id";
                Constant.dismissdialog();
                Log.i(" FriendRequest_response", response.body().toString());
                try {
                    JSONObject jsonObject = new JSONArray(response.body().toString()).getJSONObject(0);
                    if (jsonObject.getString("res").equalsIgnoreCase("error")) {
                        Toast.makeText(ChatFregment.this.getContext(), jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE), 0).show();
                        return;
                    }
                    JSONArray jsonArray1 = jsonObject.getJSONArray("data");
                    int i = 0;
                    while (i < jsonArray1.length()) {
                        JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                        JSONObject userobject = jsonObject1.getJSONObject("user_details");
                        ChatFregment.this.requestModelList.add(new FriendRequestModel(jsonObject1.getString(str), jsonObject1.getString("sender_id"), jsonObject1.getString("request_id"), jsonObject1.getString("authentication"), userobject.getString(str), userobject.getString("user_name"), userobject.getString("user_id"), "", userobject.getString("date"), userobject.getString("name")));
                        FriendListAdapter friendRequestListAdapter = new FriendListAdapter(ChatFregment.this.getContext(), ChatFregment.this.requestModelList);
                        ChatFregment.this.friendlist.setLayoutManager(new LinearLayoutManager(ChatFregment.this.getContext()));
                        ChatFregment.this.friendlist.setAdapter(friendRequestListAdapter);
                        i++;
                        str = str;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Constant.dismissdialog();
                Log.i(" FriendRequest_error", t.toString());
            }
        });
    }
}
