package digi.coders.mho.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.JsonArray;
import digi.coders.mho.Adapter.NotificationAdapter;
import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Model.NotificationModel;
import digi.coders.mho.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PushNotificationActivity extends AppCompatActivity {
    List<NotificationModel> notificationModelList = new ArrayList();
    RecyclerView notification_view;
    Toolbar toolbar;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notification);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.notification_view = (RecyclerView) findViewById(R.id.notification_view);
        this.toolbar.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.PushNotificationActivity.AnonymousClass1 */

            public void onClick(View view) {
                PushNotificationActivity.this.onBackPressed();
            }
        });
        getNotification();
    }

    public void getNotification() {
        Constant.showprogressbar(this, 1);
        Constant.StartConnection().Notification(Constant.KEYVALUE).enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Activity.PushNotificationActivity.AnonymousClass2 */

            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Constant.dismissdialog();
                Log.i("Notification_responseq", response.body().toString());
                try {
                    JSONObject jsonObject = new JSONArray(response.body().toString()).getJSONObject(0);
                    if (jsonObject.getString("res").equalsIgnoreCase("error")) {
                        Toast.makeText(PushNotificationActivity.this, jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE), 0).show();
                        return;
                    }
                    JSONArray jsonArray1 = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray1.length(); i++) {
                        JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                        PushNotificationActivity.this.notificationModelList.add(new NotificationModel(jsonObject1.getString("id"), jsonObject1.getString("tittle"), jsonObject1.getString("description"), jsonObject1.getString("date")));
                    }
                    NotificationAdapter notificationAdapter = new NotificationAdapter(PushNotificationActivity.this.notificationModelList, PushNotificationActivity.this);
                    PushNotificationActivity.this.notification_view.setLayoutManager(new LinearLayoutManager(PushNotificationActivity.this));
                    PushNotificationActivity.this.notification_view.setAdapter(notificationAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.i("Notification_errorq", t.toString());
                Constant.dismissdialog();
            }
        });
    }
}
