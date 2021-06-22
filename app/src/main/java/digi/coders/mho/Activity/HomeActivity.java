package digi.coders.mho.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.gson.JsonArray;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import digi.coders.mho.Fregments.AccountFragment;
import digi.coders.mho.Fregments.ChatFregment;
import digi.coders.mho.Fregments.RoomFragment;
import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Helper.PrefrenceManager;
import digi.coders.mho.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    public static Activity activity;
    LinearLayout account;
    ImageView addfriend;
    LinearLayout chat;
    DrawerLayout drawerlayout;
    CardView floating;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    CircleImageView opendrawer;
    LinearLayout privecy;
    CircleImageView profile_pic;
    LinearLayout room;
    ImageView searchroom;
    LinearLayout setting;
    LinearLayout store;
    TextView tv_account;
    TextView tv_chat;
    TextView tv_room;
    TextView userid;
    TextView username;
    LinearLayout wallet;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        activity = this;
        this.room = (LinearLayout) findViewById(R.id.room);
        this.chat = (LinearLayout) findViewById(R.id.chat);
        this.account = (LinearLayout) findViewById(R.id.account);
        this.drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        this.opendrawer = (CircleImageView) findViewById(R.id.opendrawer);
        this.tv_room = (TextView) findViewById(R.id.tv_room);
        this.tv_chat = (TextView) findViewById(R.id.tv_message);
        this.tv_account = (TextView) findViewById(R.id.tv_account);
        this.floating = (CardView) findViewById(R.id.floating);
        this.profile_pic = (CircleImageView) findViewById(R.id.profile_pic);
        this.userid = (TextView) findViewById(R.id.userid);
        this.username = (TextView) findViewById(R.id.username);
        this.searchroom = (ImageView) findViewById(R.id.searchroom);
        this.addfriend = (ImageView) findViewById(R.id.addfriend);
        this.setting = (LinearLayout) findViewById(R.id.setting);
        this.wallet = (LinearLayout) findViewById(R.id.wallet);
        this.store = (LinearLayout) findViewById(R.id.store);
        this.privecy = (LinearLayout) findViewById(R.id.privecy);
        this.room.setBackground(getResources().getDrawable(R.color.navigation_color));
        this.account.setBackground(null);
        this.addfriend.setVisibility(8);
        this.searchroom.setVisibility(0);
        this.chat.setBackground(null);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        this.fragmentManager = supportFragmentManager;
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        this.fragmentTransaction = beginTransaction;
        beginTransaction.replace(R.id.relativelayout, new RoomFragment());
        this.fragmentTransaction.commit();
        try {
            if (getIntent().getStringExtra("restart").equalsIgnoreCase("restart")) {
                if (Constant.isNetwork(this)) {
                    getprofile(this);
                }
                this.account.setBackground(getResources().getDrawable(R.color.navigation_color));
                this.chat.setBackground(null);
                this.room.setBackground(null);
                this.addfriend.setVisibility(8);
                this.searchroom.setVisibility(0);
                FragmentManager supportFragmentManager2 = getSupportFragmentManager();
                this.fragmentManager = supportFragmentManager2;
                this.fragmentTransaction = null;
                FragmentTransaction beginTransaction2 = supportFragmentManager2.beginTransaction();
                this.fragmentTransaction = beginTransaction2;
                beginTransaction2.replace(R.id.relativelayout, new AccountFragment());
                this.fragmentTransaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.opendrawer.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.HomeActivity.AnonymousClass1 */

            public void onClick(View view) {
                HomeActivity.this.drawerlayout.openDrawer(GravityCompat.START);
            }
        });
        this.room.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.HomeActivity.AnonymousClass2 */

            public void onClick(View view) {
                HomeActivity.this.room.setBackground(HomeActivity.this.getResources().getDrawable(R.color.navigation_color));
                HomeActivity.this.account.setBackground(null);
                HomeActivity.this.chat.setBackground(null);
                HomeActivity.this.addfriend.setVisibility(8);
                HomeActivity.this.searchroom.setVisibility(0);
                HomeActivity homeActivity = HomeActivity.this;
                homeActivity.fragmentManager = homeActivity.getSupportFragmentManager();
                HomeActivity homeActivity2 = HomeActivity.this;
                homeActivity2.fragmentTransaction = homeActivity2.fragmentManager.beginTransaction();
                HomeActivity.this.fragmentTransaction.replace(R.id.relativelayout, new RoomFragment());
                HomeActivity.this.fragmentTransaction.commit();
            }
        });
        this.chat.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.HomeActivity.AnonymousClass3 */

            public void onClick(View view) {
                HomeActivity.this.chat.setBackground(HomeActivity.this.getResources().getDrawable(R.color.navigation_color));
                HomeActivity.this.account.setBackground(null);
                HomeActivity.this.room.setBackground(null);
                HomeActivity.this.addfriend.setVisibility(0);
                HomeActivity.this.searchroom.setVisibility(8);
                HomeActivity homeActivity = HomeActivity.this;
                homeActivity.fragmentManager = homeActivity.getSupportFragmentManager();
                HomeActivity homeActivity2 = HomeActivity.this;
                homeActivity2.fragmentTransaction = homeActivity2.fragmentManager.beginTransaction();
                HomeActivity.this.fragmentTransaction.replace(R.id.relativelayout, new ChatFregment());
                HomeActivity.this.fragmentTransaction.commit();
            }
        });
        this.account.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.HomeActivity.AnonymousClass4 */

            public void onClick(View view) {
                HomeActivity.this.account.setBackground(HomeActivity.this.getResources().getDrawable(R.color.navigation_color));
                HomeActivity.this.chat.setBackground(null);
                HomeActivity.this.room.setBackground(null);
                HomeActivity.this.addfriend.setVisibility(8);
                HomeActivity.this.searchroom.setVisibility(0);
                HomeActivity homeActivity = HomeActivity.this;
                homeActivity.fragmentManager = homeActivity.getSupportFragmentManager();
                HomeActivity homeActivity2 = HomeActivity.this;
                homeActivity2.fragmentTransaction = homeActivity2.fragmentManager.beginTransaction();
                HomeActivity.this.fragmentTransaction.replace(R.id.relativelayout, new AccountFragment());
                HomeActivity.this.fragmentTransaction.commit();
            }
        });
        this.floating.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.HomeActivity.AnonymousClass5 */

            public void onClick(View view) {
                HomeActivity.this.startActivity(new Intent(HomeActivity.this, CreateRoom.class));
            }
        });
        this.searchroom.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.HomeActivity.AnonymousClass6 */

            public void onClick(View view) {
                HomeActivity.this.startActivity(new Intent(HomeActivity.this, SearchActivity.class));
            }
        });
        this.addfriend.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.HomeActivity.AnonymousClass7 */

            public void onClick(View view) {
                HomeActivity.this.startActivity(new Intent(HomeActivity.this, SearchUserActivity.class));
            }
        });
        this.wallet.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.HomeActivity.AnonymousClass8 */

            public void onClick(View view) {
                HomeActivity.this.drawerlayout.closeDrawers();
                HomeActivity.this.startActivity(new Intent(HomeActivity.this, WalletActivity.class));
            }
        });
        this.setting.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.HomeActivity.AnonymousClass9 */

            public void onClick(View view) {
                HomeActivity.this.drawerlayout.closeDrawers();
                HomeActivity.this.startActivity(new Intent(HomeActivity.this, SettingActivity.class));
            }
        });
        this.store.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.HomeActivity.AnonymousClass10 */

            public void onClick(View view) {
                HomeActivity.this.drawerlayout.closeDrawers();
                HomeActivity.this.startActivity(new Intent(HomeActivity.this, StoreActivity.class));
            }
        });
        this.privecy.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.HomeActivity.AnonymousClass11 */

            public void onClick(View view) {
                HomeActivity.this.drawerlayout.closeDrawers();
                HomeActivity.this.startActivity(new Intent(HomeActivity.this, PrivacyActivity.class));
            }
        });
        if (Constant.isNetwork(this)) {
            getprofile(this);
        }
        this.userid.setText("ID : " + new PrefrenceManager(this).getuserdetails().getUser_id());
        this.username.setText(new PrefrenceManager(this).getuserdetails().getUser_name());
        Picasso.get().load(Constant.PROFILE_Url + "" + new PrefrenceManager(this).getuserdetails()).placeholder(R.drawable.profile).error(R.drawable.profile).into(this.profile_pic);
    }

    public void chatpage(View view) {
        startActivity(new Intent(this, ChatActivity.class));
    }

    public static void getprofile(final Context context) {
        Constant.StartConnection().Profile(Constant.KEYVALUE, new PrefrenceManager(context).getuserdetails().getId()).enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Activity.HomeActivity.AnonymousClass12 */

            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Log.i("allprofiledata_response", response.body().toString());
                Constant.dismissdialog();
                try {
                    JSONObject jsonObject = new JSONArray(response.body().toString()).getJSONObject(0);
                    if (jsonObject.getString("res").equalsIgnoreCase("success")) {
                        Toast.makeText(context, "" + jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE), 0).show();
                        Constant.dismissdialog();
                        new PrefrenceManager(context).setuserdetails(jsonObject.getJSONObject("data"));
                        return;
                    }
                    Constant.dismissdialog();
                    Toast.makeText(context, "" + jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE), 0).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("allprofiledata_catch", e.toString());
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.i("allprofiledata_response", t.toString());
                Constant.dismissdialog();
            }
        });
    }
}
