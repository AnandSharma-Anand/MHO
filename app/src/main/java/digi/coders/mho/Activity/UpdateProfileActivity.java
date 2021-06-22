package digi.coders.mho.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import com.google.gson.JsonArray;
import com.skydoves.elasticviews.ElasticButton;
import digi.coders.mho.Helper.Constant;
import digi.coders.mho.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileActivity extends AppCompatActivity {
    ImageView clear;
    EditText mobileno;
    TextView textcount;
    Toolbar toolbar;
    ElasticButton update;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        this.mobileno = (EditText) findViewById(R.id.mobileno);
        this.clear = (ImageView) findViewById(R.id.clear_text);
        this.textcount = (TextView) findViewById(R.id.textcount);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.update = (ElasticButton) findViewById(R.id.update);
        String string = getIntent().getStringExtra("from");
        if (string.equalsIgnoreCase("roomname")) {
            roomnamesetting();
        } else if (string.equalsIgnoreCase("announcment")) {
            announcmentsetting();
        }
        this.toolbar.setOnClickListener(view -> UpdateProfileActivity.this.onBackPressed());
        if (this.mobileno.getText().toString().trim().length() != 0) {
            this.clear.setVisibility(View.VISIBLE);
        } else {
            this.clear.setVisibility(View.GONE);
        }
        /* class digi.coders.mho.Activity.UpdateProfileActivity.AnonymousClass2 */
        this.clear.setOnClickListener(view -> UpdateProfileActivity.this.mobileno.setText(""));
    }

    public void roomnamesetting() {
        this.mobileno.setHint("Enter room name");
        this.textcount.setText("24");
        this.toolbar.setTitle("Room Name");
        this.mobileno.setText(RoomDetailsActivity.room_name);
        mobileno.setFilters(new InputFilter[] { new InputFilter.LengthFilter(24) });

        this.update.setOnClickListener(view -> {
            if (!mobileno.getText().toString().trim().isEmpty()) {
                UpdateProfileActivity.this.updatedata("name");
            }else {
                mobileno.setError("Required");
            }
        });


        this.mobileno.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                UpdateProfileActivity.this.textcount.setText((24 - editable.toString().length()) + "");
                if (editable.toString().trim().length() != 0) {
                    UpdateProfileActivity.this.clear.setVisibility(View.VISIBLE);
                } else {
                    UpdateProfileActivity.this.clear.setVisibility(View.GONE);
                }
            }
        });
    }

    public void announcmentsetting() {
        this.mobileno.setHint("Enter Announcement");
        this.textcount.setText("120");
        this.toolbar.setTitle("Announcement");
        this.mobileno.setText(RoomDetailsActivity.announcement);
        mobileno.setFilters(new InputFilter[] { new InputFilter.LengthFilter(120) });

        this.update.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.UpdateProfileActivity.AnonymousClass5 */

            public void onClick(View view)
            {
                if (!mobileno.getText().toString().trim().isEmpty()) {

                    UpdateProfileActivity.this.updatedata("ano");

                }else {
                    mobileno.setError("Required");
                }
            }
        });
        this.mobileno.addTextChangedListener(new TextWatcher() {
            /* class digi.coders.mho.Activity.UpdateProfileActivity.AnonymousClass6 */

            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                UpdateProfileActivity.this.textcount.setText((120 - editable.toString().length()) + "");
                if (editable.toString().trim().length() != 0) {
                    UpdateProfileActivity.this.clear.setVisibility(View.VISIBLE);
                } else {
                    UpdateProfileActivity.this.clear.setVisibility(View.GONE);
                }
            }
        });
    }

    public void updatedata(final String type) {
        Call<JsonArray> call = null;
        if (type.equalsIgnoreCase("name")) {
            call = Constant.StartConnection().UpdateRoomroom_name(Constant.KEYVALUE, getIntent().getStringExtra("roomid"), this.mobileno.getText().toString().trim());
        } else if (type.equalsIgnoreCase("ano")) {
            call = Constant.StartConnection().UpdateRoomannouncment(Constant.KEYVALUE, getIntent().getStringExtra("roomid"), this.mobileno.getText().toString().trim());
        }
        call.enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Activity.UpdateProfileActivity.AnonymousClass7 */

            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Log.i("alksjjklsd", response.body().toString());
                try {
                    JSONObject jsonObject = new JSONArray(response.body().toString()).getJSONObject(0);
                    if (jsonObject.getString("res").equalsIgnoreCase("success")) {
                        if (type.equalsIgnoreCase("name")) {
                            RoomDetailsActivity.room_name = UpdateProfileActivity.this.mobileno.getText().toString();
                        } else if (type.equalsIgnoreCase("ano")) {
                            RoomDetailsActivity.announcement = UpdateProfileActivity.this.mobileno.getText().toString();
                        }
                        UpdateProfileActivity.this.finish();
                        return;
                    }
                    Toast.makeText(UpdateProfileActivity.this, "" + jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.i("alksjjklsd", t.toString());
            }
        });
    }
}
