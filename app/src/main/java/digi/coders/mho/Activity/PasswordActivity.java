package digi.coders.mho.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import com.google.gson.JsonArray;
import com.skydoves.elasticviews.ElasticButton;
import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Helper.PrefrenceManager;
import digi.coders.mho.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordActivity extends AppCompatActivity {
    Toolbar appbar;
    ElasticButton btn_password;
    EditText password;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        this.password = (EditText) findViewById(R.id.password);
        this.appbar = (Toolbar) findViewById(R.id.appbar);
        this.btn_password = (ElasticButton) findViewById(R.id.btn_password);
        setSupportActionBar(this.appbar);
        getSupportActionBar().setIcon(R.drawable.ic_baseline_arrow_back_24);
        this.appbar.setNavigationOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.PasswordActivity.AnonymousClass1 */

            public void onClick(View view) {
                PasswordActivity.this.onBackPressed();
            }
        });
        this.password.addTextChangedListener(new TextWatcher() {
            /* class digi.coders.mho.Activity.PasswordActivity.AnonymousClass2 */

            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(final Editable editable) {
                if (editable.toString().trim().length() >= 6) {
                    PasswordActivity.this.btn_password.setBackground(PasswordActivity.this.getDrawable(R.drawable.cornor_color_radius));
                    PasswordActivity.this.btn_password.setOnClickListener(new View.OnClickListener() {
                        /* class digi.coders.mho.Activity.PasswordActivity.AnonymousClass2.AnonymousClass1 */

                        public void onClick(View view) {
                            if (Constant.isNetwork(PasswordActivity.this)) {
                                PasswordActivity.this.savepassword(PasswordActivity.this.getIntent().getStringExtra("mobile"), editable.toString().trim());
                            }
                        }
                    });
                    return;
                }
                PasswordActivity.this.btn_password.setBackground(PasswordActivity.this.getDrawable(R.drawable.corner_redius));
                PasswordActivity.this.btn_password.setOnClickListener(new View.OnClickListener() {
                    /* class digi.coders.mho.Activity.PasswordActivity.AnonymousClass2.AnonymousClass2 */

                    public void onClick(View view) {
                    }
                });
            }
        });
    }

    public void savepassword(String mobile, String password2) {
        Constant.showprogressbar(this, 1);
        Call<JsonArray> call = null;
        if (Constant.usertype.equalsIgnoreCase("newuser")) {
            call = Constant.StartConnection().PasswordUpdate(Constant.KEYVALUE, mobile, password2);
        } else if (Constant.usertype.equalsIgnoreCase("olduser")) {
            call = Constant.StartConnection().PasswordVerifcation(Constant.KEYVALUE, mobile, password2);
        }
        call.enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Activity.PasswordActivity.AnonymousClass3 */

            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Log.i("password_response", response.body().toString());
                Constant.dismissdialog();
                try {
                    JSONObject jsonObject = new JSONArray(response.body().toString()).getJSONObject(0);
                    if (jsonObject.getString("res").equalsIgnoreCase("success")) {
                        Toast.makeText(PasswordActivity.this, "" + jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE), 0).show();
                        new PrefrenceManager(PasswordActivity.this).setuserdetails(jsonObject.getJSONArray("data").getJSONObject(0));
                        PasswordActivity.this.startActivity(new Intent(PasswordActivity.this, HomeActivity.class));
                        PasswordActivity.this.finish();
                        return;
                    }
                    Toast.makeText(PasswordActivity.this, "" + jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE), 0).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.i("password_error", t.toString());
                Constant.dismissdialog();
            }
        });
    }
}
