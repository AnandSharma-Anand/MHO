package digi.coders.mho.Activity;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import com.google.gson.JsonArray;
import com.skydoves.elasticviews.ElasticButton;

import digi.coders.mho.Adapter.ActiveRoomMemberAdapter;
import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Helper.PrefrenceManager;
import digi.coders.mho.Model.UserDetailsModel;
import digi.coders.mho.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFriendActivity extends AppCompatActivity {
    TextView country;
    TextView dateofbirth;
    TextView daycount;
    Dialog dialog;
    TextView language;
    int position;
    ElasticButton sendrequest;
    TextView tag;
    UserDetailsModel userDetailsModel2;
    UserDetailsModel userDetailsModel;
    TextView userid;
    TextView username;


    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        this.dateofbirth = (TextView) findViewById(R.id.dateofbirth);
        this.language = (TextView) findViewById(R.id.language);
        this.tag = (TextView) findViewById(R.id.tag);
        this.daycount = (TextView) findViewById(R.id.daycount);
        this.country = (TextView) findViewById(R.id.country);
        this.username = (TextView) findViewById(R.id.username);
        this.userid = (TextView) findViewById(R.id.userid);
        this.sendrequest = (ElasticButton) findViewById(R.id.sendrequest);
        this.position = Integer.parseInt(getIntent().getStringExtra("pos"));

        if (SearchUserActivity.userDetailsModels.size()!=0) {
             userDetailsModel2 = SearchUserActivity.userDetailsModels.get(this.position);
            sendrequest.setVisibility(View.VISIBLE);

        }else {
             userDetailsModel2 = ActiveRoomMemberAdapter.showRoomJoinedUserList.get(position).getUserDetailsModel();
            sendrequest.setVisibility(View.GONE);

        }

        this.userDetailsModel = userDetailsModel2;
        this.username.setText(userDetailsModel2.getUser_name());
        this.dateofbirth.setText(this.userDetailsModel.getDateofbirth());
        this.tag.setText(this.userDetailsModel.getTag());
        this.daycount.setText("6 Days");
        this.country.setText(this.userDetailsModel.getCountry());
        this.userid.setText(this.userDetailsModel.getUser_id());
        this.sendrequest.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.AddFriendActivity.AnonymousClass1 */

            public void onClick(View view) {
                AddFriendActivity.this.showpopup();
            }
        });

        this.userid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CopyText(userid.getText().toString().trim());
            }
        });

    }

    public void showpopup() {
        this.dialog = new Dialog(this);
        View v = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.authontication_info_layout, (ViewGroup) null, false);
        this.dialog.setContentView(v);
        this.dialog.setCancelable(false);
        this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.dialog.show();
        final EditText auth_msg = (EditText) v.findViewById(R.id.auth_msg);
        final ImageView clear_text = (ImageView) v.findViewById(R.id.clear_text);
        auth_msg.setText("I'm " + this.userDetailsModel.getUser_name());
        clear_text.setVisibility(View.VISIBLE);
        clear_text.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.AddFriendActivity.AnonymousClass2 */

            public void onClick(View view) {
                AddFriendActivity.this.username.setText("");
            }
        });
        ((ImageView) v.findViewById(R.id.close_dialog)).setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.AddFriendActivity.AnonymousClass3 */

            public void onClick(View view) {
                AddFriendActivity.this.dialog.dismiss();
            }
        });
        auth_msg.addTextChangedListener(new TextWatcher() {
            /* class digi.coders.mho.Activity.AddFriendActivity.AnonymousClass4 */

            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().length() != 0) {
                    clear_text.setVisibility(View.VISIBLE);
                    clear_text.setOnClickListener(new View.OnClickListener() {
                        /* class digi.coders.mho.Activity.AddFriendActivity.AnonymousClass4.AnonymousClass1 */

                        public void onClick(View view) {
                            AddFriendActivity.this.username.setText("");
                        }
                    });
                    return;
                }
                clear_text.setVisibility(View.GONE);
            }
        });
        ((ElasticButton) v.findViewById(R.id.confirm_button)).setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.AddFriendActivity.AnonymousClass5 */

            public void onClick(View view) {
                if (auth_msg.getText().toString().trim().isEmpty()) {
                    auth_msg.requestFocus();
                    auth_msg.setError("Required");
                    return;
                }
                AddFriendActivity addFriendActivity = AddFriendActivity.this;
                addFriendActivity.FriendRequest(addFriendActivity.userDetailsModel.getId(), auth_msg.getText().toString());
            }
        });
    }

    public void FriendRequest(String request_id, String auth_msg) {
        Constant.showprogressbar(this, 1);
        Constant.StartConnection().FriendRequest(Constant.KEYVALUE, new PrefrenceManager(this).getuserdetails().getId(), request_id, auth_msg).enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Activity.AddFriendActivity.AnonymousClass6 */

            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Constant.dismissdialog();
                Log.i(" FriendRequest_response", response.body().toString());
                try {
                    JSONObject jsonObject = new JSONArray(response.body().toString()).getJSONObject(0);
                    if (jsonObject.getString("res").equalsIgnoreCase("error")) {
                        Toast.makeText(AddFriendActivity.this, jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE), Toast.LENGTH_SHORT).show();
                        AddFriendActivity.this.dialog.dismiss();
                        return;
                    }
                    Toast.makeText(AddFriendActivity.this, jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE), Toast.LENGTH_SHORT).show();
                    AddFriendActivity.this.dialog.dismiss();
                } catch (JSONException e) {
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Constant.dismissdialog();
                Log.i(" FriendRequest_error", t.toString());
            }
        });
    }

    public void CopyText(String text) {
        String text1 = text;
        if (!text1.isEmpty()) {
            ClipboardManager  clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("key", text);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(getApplicationContext(), "Copied", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "No text to be copied", Toast.LENGTH_SHORT).show();
        }
    }
}
