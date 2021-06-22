package digi.coders.mho.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.JsonArray;
import digi.coders.mho.Adapter.FriendRequestListAdapter;
import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Helper.PrefrenceManager;
import digi.coders.mho.Helper.Refresh;
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

public class FriendRequestListActivity extends AppCompatActivity implements Refresh {

    LinearLayout empty;
    RecyclerView recyclerview_request;
    List<FriendRequestModel> requestModelList = new ArrayList();
    Toolbar toolbar;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request_list);
        this.recyclerview_request = (RecyclerView) findViewById(R.id.recyclerview_request);
        this.empty = (LinearLayout) findViewById(R.id.empty);
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar);
        this.toolbar = toolbar2;
        toolbar2.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.FriendRequestListActivity.AnonymousClass1 */

            public void onClick(View view) {
                FriendRequestListActivity.this.onBackPressed();
            }
        });
        getrequestlist();
    }

    public void getrequestlist() {
        Constant.showprogressbar(this, 1);
        Constant.StartConnection().PendingFriendRequestList(Constant.KEYVALUE, new PrefrenceManager(this).getuserdetails().getId()).enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Activity.FriendRequestListActivity.AnonymousClass2 */

            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                String str = "id";
                Constant.dismissdialog();
                Log.i(" FriendRequest_response", response.body().toString());
                try {
                    JSONObject jsonObject = new JSONArray(response.body().toString()).getJSONObject(0);
                    if (jsonObject.getString("res").equalsIgnoreCase("error")) {
                        Toast.makeText(FriendRequestListActivity.this, jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE), Toast.LENGTH_SHORT).show();
                        FriendRequestListActivity.this.empty.setVisibility(View.VISIBLE);
                        FriendRequestListActivity.this.recyclerview_request.setAdapter(null);
                        return;
                    }
                    FriendRequestListActivity.this.empty.setVisibility(View.GONE);
                    Toast.makeText(FriendRequestListActivity.this, jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE), Toast.LENGTH_SHORT).show();
                    JSONArray jsonArray1 = jsonObject.getJSONArray("data");
                    int i = 0;
                    while (i < jsonArray1.length()) {
                        JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                        JSONObject userobject = jsonObject1.getJSONObject("user_details");
                        FriendRequestListActivity.this.requestModelList.add(new FriendRequestModel(
                                jsonObject1.getString(str),
                                jsonObject1.getString("sender_id"),
                                jsonObject1.getString("request_id"),
                                jsonObject1.getString("authentication"),
                                userobject.getString(str),
                                userobject.getString("user_name"),
                                userobject.getString("user_id"),
                                userobject.getString("photo"),
                                jsonObject1.getString("date"),
                                userobject.getString("name")));

                        FriendRequestListActivity friendRequestListActivity = FriendRequestListActivity.this;
                        FriendRequestListAdapter friendRequestListAdapter = new FriendRequestListAdapter(friendRequestListActivity, friendRequestListActivity.requestModelList, FriendRequestListActivity.this);
                        FriendRequestListActivity.this.recyclerview_request.setLayoutManager(new LinearLayoutManager(FriendRequestListActivity.this));
                        FriendRequestListActivity.this.recyclerview_request.setAdapter(friendRequestListAdapter);
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

    @Override // digi.coders.mho.Helper.Refresh
    public void refresh() {
        getrequestlist();
    }
}
