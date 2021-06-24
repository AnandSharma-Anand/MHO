package digi.coders.mho.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import com.google.gson.JsonArray;
import digi.coders.mho.Adapter.SearchUserAdapter;
import digi.coders.mho.Helper.Constant;
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

public class SearchUserActivity extends AppCompatActivity {
    public static List<UserDetailsModel> userDetailsModels;
    ImageView clear_text;
    ListView listview;
    EditText username;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);
        this.username = (EditText) findViewById(R.id.username);
        this.clear_text = (ImageView) findViewById(R.id.clear_text);
        this.listview = (ListView) findViewById(R.id.listview);
        this.username.addTextChangedListener(new TextWatcher() {
            /* class digi.coders.mho.Activity.SearchUserActivity.AnonymousClass1 */

            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().length() != 0) {
                    SearchUserActivity.this.clear_text.setVisibility(View.VISIBLE);
                    SearchUserActivity.this.clear_text.setOnClickListener(new View.OnClickListener() {
                        /* class digi.coders.mho.Activity.SearchUserActivity.AnonymousClass1.AnonymousClass1 */

                        public void onClick(View view) {
                            SearchUserActivity.this.username.setText("");
                        }
                    });
                    SearchUserActivity.this.serachuser(editable.toString().trim());
                    return;
                }
                SearchUserActivity.this.clear_text.setVisibility(View.GONE);
                SearchUserActivity.this.listview.setAdapter((ListAdapter) null);
            }
        });
    }

    public void serachuser(String usernameorid) {
        userDetailsModels = new ArrayList();
        this.listview.setAdapter((ListAdapter) null);
        Constant.StartConnection().SearchUser(Constant.KEYVALUE, usernameorid).enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Activity.SearchUserActivity.AnonymousClass2 */

            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Log.i("serachuser_response", response.body().toString());
                try {
                    JSONObject jsonObject = new JSONArray(response.body().toString()).getJSONObject(0);
                    if (jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE).equalsIgnoreCase("User Found")) {
                        int i = 0;
                        for (JSONArray jsonArray1 = jsonObject.getJSONArray("data"); i < jsonArray1.length(); jsonArray1 = jsonArray1) {
                            JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                            SearchUserActivity.userDetailsModels.add(new UserDetailsModel(jsonObject1.getString("id"), jsonObject1.getString("user_id"), jsonObject1.getString("user_name"), jsonObject1.getString("name"), jsonObject1.getString("gender_type"), jsonObject1.getString("dateofbirth"), jsonObject1.getString("country"), jsonObject1.getString("tag"), jsonObject1.getString("bio"), jsonObject1.getString("mobile"), jsonObject1.getString("otp"), jsonObject1.getString("otp_status"), jsonObject1.getString(NotificationCompat.CATEGORY_STATUS), jsonObject1.getString("password"), jsonObject1.getString("photo"), jsonObject1.getString("date")));
                            i++;
                        }
                        SearchUserActivity.this.listview.setAdapter((ListAdapter) new SearchUserAdapter(SearchUserActivity.userDetailsModels, SearchUserActivity.this));
                        SearchUserActivity.this.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            /* class digi.coders.mho.Activity.SearchUserActivity.AnonymousClass2.AnonymousClass1 */

                            @Override // android.widget.AdapterView.OnItemClickListener
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Intent intent = new Intent(SearchUserActivity.this, AddFriendActivity.class);
                                intent.putExtra("pos", i + "");
                                SearchUserActivity.this.startActivity(intent);
                            }
                        });
                        return;
                    }
                    Toast.makeText(SearchUserActivity.this, jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.i("serachuser_error", t.toString());
            }
        });
    }
}
