package digi.coders.mho.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.JsonArray;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import digi.coders.mho.Adapter.ActiveRoomMemberAdapter;
import digi.coders.mho.Adapter.JoinedUserAdapter;
import digi.coders.mho.Adapter.ShowRoomChatAdapter;
import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Helper.PrefrenceManager;
import digi.coders.mho.Model.RoomShowChatModel;
import digi.coders.mho.Model.ShowRoomJoinedUser;
import digi.coders.mho.Model.UserDetailsModel;
import digi.coders.mho.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomDetailsActivity extends AppCompatActivity {
    public static String announcement;
    public static String country;
    public static String created_id;
    public static String id;
    public static String membership_fee;
    public static String numberfomic;
    public static String photo;
    public static String room_name;
    public static String tag;
    public static String totaljoin_memebr;
    ImageView backe,setting_headr,head_roomprofile;
    RecyclerView chat_view;
    int count = 0;
    CountDownTimer countDownTimer;
    EditText et_msg;
    CardView gift;
    TextView head_roomname;
    TextView joined;
    RecyclerView joinseduser;
    TextView online;
    List<RoomShowChatModel> roomShowChatModelList;
    LinearLayout roomdetails;
    List<ShowRoomJoinedUser> showRoomJoinedUserList;
    List<ShowRoomJoinedUser> showRoomJoinedUserListsearch;
    int time = 0;
    ImageView share_room;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);
        this.gift = (CardView) findViewById(R.id.gift);
        this.backe = (ImageView) findViewById(R.id.backe);
        this.roomdetails = (LinearLayout) findViewById(R.id.roomdetails);
        this.et_msg = (EditText) findViewById(R.id.et_msg);
        this.chat_view = (RecyclerView) findViewById(R.id.chat_view);
        this.joinseduser = (RecyclerView) findViewById(R.id.joinseduser);
        this.joined = (TextView) findViewById(R.id.joined);
        this.online = (TextView) findViewById(R.id.online);
        this.head_roomname = (TextView) findViewById(R.id.head_roomname);
        this.share_room = (ImageView) findViewById(R.id.share_room);
        this.setting_headr = (ImageView) findViewById(R.id.setting_headr);
        this.head_roomprofile = (ImageView) findViewById(R.id.head_roomprofile);

        /* class digi.coders.mho.Activity.RoomDetailsActivity.AnonymousClass1 */
        this.gift.setOnClickListener(view -> {
            BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(RoomDetailsActivity.this);
            mBottomSheetDialog.setContentView(RoomDetailsActivity.this.getLayoutInflater().inflate(R.layout.send_gift, (ViewGroup) null));
            mBottomSheetDialog.show();
        });


        /* class digi.coders.mho.Activity.RoomDetailsActivity.AnonymousClass2 */
        this.backe.setOnClickListener(view -> RoomDetailsActivity.this.onBackPressed());
        /* class digi.coders.mho.Activity.RoomDetailsActivity.AnonymousClass3 */
        this.et_msg.setOnKeyListener((view, i, keyEvent) -> {
            if (i == 66) {
          //      Toast.makeText(RoomDetailsActivity.this, "as", Toast.LENGTH_SHORT).show();
                if (RoomDetailsActivity.this.count == 0) {
                    RoomDetailsActivity roomDetailsActivity = RoomDetailsActivity.this;
                    roomDetailsActivity.sendmsg(roomDetailsActivity.et_msg.getText().toString());
                    RoomDetailsActivity.this.count++;
                }
            }
            return false;
        });

        this.share_room.setOnClickListener(v -> Share_room());

        this.et_msg.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                RoomDetailsActivity.this.count = 0;
            }
        });

        this.roomdetails.setOnClickListener(view -> {
            openbottom();
        });


        joinroom();
        getRoomdetails(getIntent().getStringExtra("roomid"));
        JoinRoomShowUser("");

        setting_headr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoomDetailsActivity.this, EditRoomProfileActivity.class);
                intent.putExtra("roomid", RoomDetailsActivity.this.getIntent().getStringExtra("roomid"));
                RoomDetailsActivity.this.startActivity(intent);
            }
        });


        this.head_roomprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openbottom();
            }
        });

        CountDownTimer r7 = new CountDownTimer((long) 10000, 1000) {
            /* class digi.coders.mho.Activity.RoomDetailsActivity.AnonymousClass6 */

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int i = seconds / 60;
                int seconds2 = seconds % 60;
            }

            public void onFinish() {
                RoomDetailsActivity.this.JoinRoomShowUser("");
                RoomDetailsActivity.this.time++;
                RoomDetailsActivity.this.countDownTimer.start();
            }
        };
        this.countDownTimer = r7;
        r7.start();
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        getRoomdetails(getIntent().getStringExtra("roomid"));
    }

    public void openbottom(){
        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(RoomDetailsActivity.this);
        View view1 = RoomDetailsActivity.this.getLayoutInflater().inflate(R.layout.bottom_layout_member, (ViewGroup) null);
        mBottomSheetDialog.setContentView(view1);
        DisplayMetrics displayMetrics = RoomDetailsActivity.this.getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        double d = (double) displayMetrics.heightPixels;
        Double.isNaN(d);
        BottomSheetBehavior.from((View) view1.getParent()).setPeekHeight((int) (d * 0.88d));
        mBottomSheetDialog.show();
        TabLayout tabLayout = (TabLayout) view1.findViewById(R.id.tablayout);
        final LinearLayout room_member = (LinearLayout) view1.findViewById(R.id.room_member);
        final LinearLayout room_profile = (LinearLayout) view1.findViewById(R.id.room_profile);
        final EditText search_user = (EditText) view1.findViewById(R.id.search_user);
        final TextView edit_roomprofile = ((TextView) view1.findViewById(R.id.edit_roomprofile));
        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("Member"));
        room_profile.setVisibility(View.VISIBLE);
        room_member.setVisibility(View.GONE);

        if (created_id.equalsIgnoreCase(new PrefrenceManager(RoomDetailsActivity.this).getuserdetails().getId())){
            Log.i("asdasdasd if",created_id+" "+new PrefrenceManager(RoomDetailsActivity.this).getuserdetails().getId());
            edit_roomprofile.setVisibility(View.VISIBLE);

        }else {
            edit_roomprofile.setVisibility(View.GONE);
            Log.i("asdasdasd else",created_id+" "+new PrefrenceManager(RoomDetailsActivity.this).getuserdetails().getId());
        }

        tabLayout.setOnTabSelectedListener((TabLayout.OnTabSelectedListener) new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                RoomDetailsActivity.this.getSupportFragmentManager().beginTransaction();
                if (tab.getText().toString().equalsIgnoreCase("Profile")) {
                    room_profile.setVisibility(View.VISIBLE);
                    room_member.setVisibility(View.GONE);
                } else if (tab.getText().toString().equalsIgnoreCase("Member")) {
                    room_profile.setVisibility(View.GONE);
                    room_member.setVisibility(View.VISIBLE);
                }
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });



        RecyclerView recyclerview_member = (RecyclerView) view1.findViewById(R.id.recyclerview_member);
//        ((TextView) view1.findViewById(R.id.number_member)).setText("Member: " + RoomDetailsActivity.this.showRoomJoinedUserList.size() + "/" + RoomDetailsActivity.numberfomic);
        ((TextView) view1.findViewById(R.id.number_member)).setText("Member: " + RoomDetailsActivity.this.showRoomJoinedUserList.size() );
        ((TextView) view1.findViewById(R.id.memss_count)).setText(RoomDetailsActivity.this.showRoomJoinedUserList.size() + "");
        ((TextView) view1.findViewById(R.id.country)).setText(RoomDetailsActivity.country);
        ((TextView) view1.findViewById(R.id.tagtv)).setText(RoomDetailsActivity.tag);
        ((TextView) view1.findViewById(R.id.announcementtv)).setText(RoomDetailsActivity.announcement);
        ((TextView) view1.findViewById(R.id.roomname)).setText(RoomDetailsActivity.room_name);
        Picasso.get().load(Constant.RoomPROFILE_Url + "" + RoomDetailsActivity.photo).placeholder(R.drawable.profile).error(R.drawable.profile).into((CircleImageView) view1.findViewById(R.id.porfile));
        Log.i("profilepgoto", Constant.PROFILE_Url + "" + RoomDetailsActivity.photo);
        edit_roomprofile.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.RoomDetailsActivity.AnonymousClass5.AnonymousClass2 */

            public void onClick(View view) {
                Intent intent = new Intent(RoomDetailsActivity.this, EditRoomProfileActivity.class);
                intent.putExtra("roomid", RoomDetailsActivity.this.getIntent().getStringExtra("roomid"));
                RoomDetailsActivity.this.startActivity(intent);
                mBottomSheetDialog.dismiss();
            }
        });


        search_user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()!=0) {
                    JoinRoomShowUsersearch(s.toString(), recyclerview_member);
                }else {
                    recyclerview_member.setLayoutManager(new LinearLayoutManager(RoomDetailsActivity.this));
                    recyclerview_member.setAdapter(new ActiveRoomMemberAdapter(RoomDetailsActivity.this.showRoomJoinedUserList, RoomDetailsActivity.this));
                }
            }
        });

        recyclerview_member.setLayoutManager(new LinearLayoutManager(RoomDetailsActivity.this));
        recyclerview_member.setAdapter(new ActiveRoomMemberAdapter(RoomDetailsActivity.this.showRoomJoinedUserList, RoomDetailsActivity.this));

    }

    public void joinroom() {
        Constant.showprogressbar(this, 1);
        Constant.StartConnection().JoinRoom(Constant.KEYVALUE, new PrefrenceManager(getApplicationContext()).getuserdetails().getId(), getIntent().getStringExtra("roomid")).enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Activity.RoomDetailsActivity.AnonymousClass7 */

            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Constant.dismissdialog();
                Log.i("joinroom_response", response.body().toString());
                RoomDetailsActivity.this.ShowRoomChat();
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.i("joinroom_response", t.toString());
                Constant.dismissdialog();
            }
        });
    }

    public void Share_room(){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "This is my room code :- *"+getIntent().getStringExtra("roomid")+"* join me" ;
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    public void ShowRoomChat() {
        Constant.showprogressbar(this, 1);
        this.roomShowChatModelList = new ArrayList();
        Constant.StartConnection().ShowRoomChat(Constant.KEYVALUE, getIntent().getStringExtra("roomid")).enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Activity.RoomDetailsActivity.AnonymousClass8 */

            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Log.i("showroomchat_response", response.body().toString());
                Constant.dismissdialog();
                try {
                    JSONObject jsonObject = new JSONArray(response.body().toString()).getJSONObject(0);
                    if (jsonObject.getString("res").equalsIgnoreCase("success")) {
                        JSONArray jsonArray1 = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray1.length(); i++) {
                            JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                            RoomDetailsActivity.this.roomShowChatModelList.add(new RoomShowChatModel(jsonObject1.getString("id"), jsonObject1.getString("room_id"), jsonObject1.getString("user_id"), jsonObject1.getString(NotificationCompat.CATEGORY_MESSAGE), jsonObject1.getString("date"), jsonObject1.getString("photo"), jsonObject1.getString("name")));
                        }
                        ShowRoomChatAdapter showRoomChatAdapter = new ShowRoomChatAdapter(RoomDetailsActivity.this.roomShowChatModelList, RoomDetailsActivity.this);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RoomDetailsActivity.this);
                        RoomDetailsActivity.this.chat_view.scrollToPosition(RoomDetailsActivity.this.roomShowChatModelList.size() - 1);
                        RoomDetailsActivity.this.chat_view.setLayoutManager(linearLayoutManager);
                        linearLayoutManager.setStackFromEnd(true);
                        RoomDetailsActivity.this.chat_view.setAdapter(showRoomChatAdapter);
                        RoomDetailsActivity.this.chat_view.smoothScrollToPosition(RoomDetailsActivity.this.chat_view.getAdapter().getItemCount() - 1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.i("showroomchat_error", t.toString());
                Constant.dismissdialog();
            }
        });
    }

    public void sendmsg(String msg) {
        Constant.showprogressbar(this, 1);
        Constant.StartConnection().RoomChat(Constant.KEYVALUE, new PrefrenceManager(this).getuserdetails().getId(), getIntent().getStringExtra("roomid"), msg).enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Activity.RoomDetailsActivity.AnonymousClass9 */

            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Log.i("roomchat_response", response.body().toString());
                Constant.dismissdialog();
                RoomDetailsActivity.this.ShowRoomChat();
                RoomDetailsActivity.this.et_msg.setText("");
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.i("roomchat_error", t.toString());
                Constant.dismissdialog();
            }
        });
    }

    public void JoinRoomShowUser(String search) {
        this.showRoomJoinedUserList = new ArrayList();
        //this.showRoomJoinedUserListsearch = new ArrayList();
        Constant.StartConnection().JoinRoomShowUser(Constant.KEYVALUE, getIntent().getStringExtra("roomid"),search).enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Activity.RoomDetailsActivity.AnonymousClass10 */

            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Constant.dismissdialog();
                Log.i("JoinRoomShowUser_res", response.body().toString());
             //   Toast.makeText(RoomDetailsActivity.this, ""+response.body().toString(), Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = new JSONArray(response.body().toString()).getJSONObject(0);
                    if (jsonObject.getString("res").equalsIgnoreCase("success")) {
                        JSONArray jsonArray1 = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray1.length(); i++) {
                            JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                            JSONObject userobject = jsonObject1.getJSONObject("user_details");

                            RoomDetailsActivity.this.showRoomJoinedUserList.add(new ShowRoomJoinedUser(jsonObject1.getString("id"), jsonObject1.getString("room_id"), jsonObject1.getString("user_id"), jsonObject1.getString("date"), new UserDetailsModel(userobject.getString("id"), userobject.getString("user_id"), userobject.getString("user_name"), userobject.getString("name"), userobject.getString("gender_type"), userobject.getString("dateofbirth"), userobject.getString("country"), userobject.getString("tag"), userobject.getString("bio"), userobject.getString("mobile"), userobject.getString("otp"), userobject.getString("otp_status"), userobject.getString(NotificationCompat.CATEGORY_STATUS), userobject.getString("password"), userobject.getString("photo"), userobject.getString("date"))));

                        }
                        RoomDetailsActivity.this.joinseduser.setLayoutManager(new LinearLayoutManager(RoomDetailsActivity.this, RecyclerView.HORIZONTAL, false));
                        RoomDetailsActivity.this.joinseduser.setAdapter(new JoinedUserAdapter(RoomDetailsActivity.this.showRoomJoinedUserList, RoomDetailsActivity.this));
                        RoomDetailsActivity.this.joined.setText(RoomDetailsActivity.this.showRoomJoinedUserList.size() + "");
                        RoomDetailsActivity.this.online.setText("Online: " + RoomDetailsActivity.this.showRoomJoinedUserList.size() + "");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Constant.dismissdialog();
                Log.i("JoinRoomShowUser_error", t.toString());
            }
        });
    }

    public void JoinRoomShowUsersearch(String search ,RecyclerView recyclerview_member) {
        showRoomJoinedUserListsearch = new ArrayList();
        Constant.StartConnection().JoinRoomShowUser(Constant.KEYVALUE, getIntent().getStringExtra("roomid"),search).enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Activity.RoomDetailsActivity.AnonymousClass10 */

            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Constant.dismissdialog();
                Log.i("ShowUsersearch_res", response.body().toString());
             //   Toast.makeText(RoomDetailsActivity.this, ""+response.body().toString(), Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = new JSONArray(response.body().toString()).getJSONObject(0);
                    if (jsonObject.getString("res").equalsIgnoreCase("success")) {
                        JSONArray jsonArray1 = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray1.length(); i++) {
                            JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                            JSONObject userobject = jsonObject1.getJSONObject("user_details");
//                            //if (search.equalsIgnoreCase("")) {
                                RoomDetailsActivity.this.showRoomJoinedUserListsearch.add(new ShowRoomJoinedUser(jsonObject1.getString("id"), jsonObject1.getString("room_id"), jsonObject1.getString("user_id"), jsonObject1.getString("date"), new UserDetailsModel(userobject.getString("id"), userobject.getString("user_id"), userobject.getString("user_name"), userobject.getString("name"), userobject.getString("gender_type"), userobject.getString("dateofbirth"), userobject.getString("country"), userobject.getString("tag"), userobject.getString("bio"), userobject.getString("mobile"), userobject.getString("otp"), userobject.getString("otp_status"), userobject.getString(NotificationCompat.CATEGORY_STATUS), userobject.getString("password"), userobject.getString("photo"), userobject.getString("date"))));
//
                        }
                        recyclerview_member.setLayoutManager(new LinearLayoutManager(RoomDetailsActivity.this, RecyclerView.VERTICAL, false));
                        recyclerview_member.setAdapter(new ActiveRoomMemberAdapter(RoomDetailsActivity.this.showRoomJoinedUserListsearch, RoomDetailsActivity.this));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Constant.dismissdialog();
                Log.i("ShowUsersearch_error", t.toString());
            }
        });
    }

    public void getRoomdetails(String userid) {
        if (Constant.isNetwork(this)) {
            final Dialog dialog = new Dialog(this);
            View aa = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_progress_bar_layout, (ViewGroup) null, false);
            dialog.setContentView(aa);
            dialog.setCancelable(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            Sprite doubleBounce = new Wave();
            ((ProgressBar) aa.findViewById(R.id.progressas)).setIndeterminateDrawable(doubleBounce);
            doubleBounce.setColor(-1);
            dialog.show();
            new ArrayList();
            Constant.StartConnection().roomdetails(Constant.KEYVALUE, userid).enqueue(new Callback<JsonArray>() {
                /* class digi.coders.mho.Activity.RoomDetailsActivity.AnonymousClass11 */

                @Override // retrofit2.Callback
                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                    Log.i("showroom_response", response.body().toString());
                    dialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONArray(response.body().toString()).getJSONObject(0);
                        if (jsonObject.getString("res").equalsIgnoreCase("success")) {
                            JSONObject jsonObject1 = jsonObject.getJSONArray("data").getJSONObject(0);
                            RoomDetailsActivity.id = jsonObject1.getString("id");
                            RoomDetailsActivity.room_name = jsonObject1.getString("room_name");
                            RoomDetailsActivity.created_id = jsonObject1.getString("created_id");
                            RoomDetailsActivity.photo = jsonObject1.getString("photo");
                            RoomDetailsActivity.announcement = jsonObject1.getString("announcement");
                            RoomDetailsActivity.tag = jsonObject1.getString("tag");
                            RoomDetailsActivity.membership_fee = jsonObject1.getString("membership_fee");
                            RoomDetailsActivity.numberfomic = jsonObject1.getString("numberfomic");
                            RoomDetailsActivity.totaljoin_memebr = jsonObject1.getString("totaljoin_memebr");
                            RoomDetailsActivity.country = jsonObject1.getString("country");
                            RoomDetailsActivity.this.head_roomname.setText(RoomDetailsActivity.room_name);
                            return;
                        }
                        Toast.makeText(RoomDetailsActivity.this, jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE), Toast.LENGTH_SHORT).show();
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

    public void askexit() {
        final Dialog dialog = new Dialog(this);
        View aa = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_exit_layout, (ViewGroup) null, false);
        dialog.setContentView(aa);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        ((LinearLayout) aa.findViewById(R.id.keep)).setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.RoomDetailsActivity.AnonymousClass12 */

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        ((LinearLayout) aa.findViewById(R.id.exit)).setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.RoomDetailsActivity.AnonymousClass13 */

            public void onClick(View view) {
                RoomDetailsActivity.this.exitroom();
            }
        });
        dialog.show();
    }

    @Override // androidx.activity.ComponentActivity
    public void onBackPressed() {
        askexit();
    }

    public void exitroom() {
        Constant.showprogressbar(this, 1);
        Constant.StartConnection().RoomExit(Constant.KEYVALUE, new PrefrenceManager(getApplicationContext()).getuserdetails().getId(), getIntent().getStringExtra("roomid")).enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Activity.RoomDetailsActivity.AnonymousClass14 */

            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Constant.dismissdialog();
                Log.i("exitroom_response", response.body().toString());
                RoomDetailsActivity.this.finish();
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.i("exitroom_response", t.toString());
                Constant.dismissdialog();
            }
        });
    }
}
