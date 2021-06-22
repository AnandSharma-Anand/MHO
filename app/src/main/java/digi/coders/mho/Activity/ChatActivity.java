package digi.coders.mho.Activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import com.google.gson.JsonArray;
import digi.coders.mho.Adapter.MessagingAdapter;
import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Helper.PrefrenceManager;
import digi.coders.mho.Model.MessegingModel;
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

public class ChatActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    ListView listview;
    EditText message_et;
    List<MessegingModel> messegingModelList = new ArrayList();
    ImageView send_msg;
    int time = 0;
    Toolbar toolbar;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.listview = (ListView) findViewById(R.id.listview);
        final String friend_id = getIntent().getStringExtra("friend_id");
        final String reciver_id = getIntent().getStringExtra("reciver_id");
        String username = getIntent().getStringExtra("username");
        showchat(friend_id);
        CountDownTimer r11 = new CountDownTimer((long) 10000, 1000) {
            /* class digi.coders.mho.Activity.ChatActivity.AnonymousClass1 */

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int i = seconds / 60;
                int seconds2 = seconds % 60;
            }

            public void onFinish() {
                ChatActivity.this.showchat(friend_id);
                ChatActivity.this.time++;
                ChatActivity.this.countDownTimer.start();
            }
        };
        this.countDownTimer = r11;
        r11.start();
        this.toolbar.setTitle(username);
        this.toolbar.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.ChatActivity.AnonymousClass2 */

            public void onClick(View view) {
                ChatActivity.this.onBackPressed();
            }
        });
        this.message_et = (EditText) findViewById(R.id.message_et);
        ImageView imageView = (ImageView) findViewById(R.id.send_msg);
        this.send_msg = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.ChatActivity.AnonymousClass3 */

            public void onClick(View view) {
                if (!ChatActivity.this.message_et.getText().toString().trim().isEmpty()) {
                    ChatActivity chatActivity = ChatActivity.this;
                    chatActivity.setSend_msg(reciver_id, chatActivity.message_et.getText().toString().trim(), friend_id);
                    return;
                }
                Toast.makeText(ChatActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showchat(String friend_id) {
        this.messegingModelList.clear();
        Constant.StartConnection().ShowChat(Constant.KEYVALUE, friend_id).enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Activity.ChatActivity.AnonymousClass4 */

            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                String status;
                String str = "sender_id";
                Constant.dismissdialog();
                Log.i("chatactivity_responseq", response.body().toString());
                try {
                    JSONArray jsonArray = new JSONArray(response.body().toString());
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    if (jsonObject.getString("res").equalsIgnoreCase("success")) {
                        UserDetailsModel userDetailsModel = new PrefrenceManager(ChatActivity.this).getuserdetails();
                        JSONArray jsonArray1 = jsonObject.getJSONArray("data");
                        int i = 0;
                        while (i < jsonArray1.length()) {
                            JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                            if (userDetailsModel.getId().equalsIgnoreCase(jsonObject1.getString(str))) {
                                status = "me";
                            } else {
                                status = "they";
                            }
                            ChatActivity.this.messegingModelList.add(new MessegingModel(jsonObject1.getString("id"), jsonObject1.getString(str), jsonObject1.getString("receiver_id"), jsonObject1.getString("message"), jsonObject1.getString("fri_id"), jsonObject1.getString("date"), status));
                            ChatActivity.this.listview.setAdapter((ListAdapter) new MessagingAdapter(ChatActivity.this.messegingModelList, ChatActivity.this));
                            i++;
                            str = str;
                            jsonArray = jsonArray;
                        }
                        return;
                    }
                    Toast.makeText(ChatActivity.this, "" + jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.i("chatactivity_errorq", t.toString());
                Constant.dismissdialog();
            }
        });
    }

    public void setSend_msg(String receiver_id, String message, String friend_id) {
        Constant.StartConnection().Chat(Constant.KEYVALUE, new PrefrenceManager(this).getuserdetails().getId(), receiver_id, message, friend_id).enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Activity.ChatActivity.AnonymousClass5 */

            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Constant.dismissdialog();
                Log.i("chatactivity_response", response.body().toString());
                try {
                    JSONObject jsonObject = new JSONArray(response.body().toString()).getJSONObject(0);
                    if (jsonObject.getString("res").equalsIgnoreCase("success")) {
                        ChatActivity chatActivity = ChatActivity.this;
                        chatActivity.showchat(chatActivity.getIntent().getStringExtra("friend_id"));
                        return;
                    }
                    Toast.makeText(ChatActivity.this, jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.i("chatactivity_error", t.toString());
                Constant.dismissdialog();
            }
        });
    }
}
