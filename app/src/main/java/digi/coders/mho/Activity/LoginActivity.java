package digi.coders.mho.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
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

public class LoginActivity extends AppCompatActivity {
    Toolbar appbar;
    ElasticButton btn_login;
    ImageView clear_text;
    EditText mobileno;
    TextView spin_state;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.appbar = (Toolbar) findViewById(R.id.appbar);
        this.spin_state = (TextView) findViewById(R.id.spin_state);
        this.mobileno = (EditText) findViewById(R.id.mobileno);
        this.clear_text = (ImageView) findViewById(R.id.clear_text);
        this.btn_login = (ElasticButton) findViewById(R.id.btn_login);
        this.spin_state.setText(Constant.countrycode);
        setSupportActionBar(this.appbar);
        this.appbar.setNavigationOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.LoginActivity.AnonymousClass1 */

            public void onClick(View view) {
                LoginActivity.this.onBackPressed();
            }
        });
        this.spin_state.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.LoginActivity.AnonymousClass2 */

            public void onClick(View view) {
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, SelectCountryActivity.class));
            }
        });
        this.mobileno.addTextChangedListener(new TextWatcher() {
            /* class digi.coders.mho.Activity.LoginActivity.AnonymousClass3 */

            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(final Editable editable) {
                if (editable.toString().trim().length() != 0) {
                    LoginActivity.this.clear_text.setVisibility(0);
                    LoginActivity.this.clear_text.setOnClickListener(new View.OnClickListener() {
                        /* class digi.coders.mho.Activity.LoginActivity.AnonymousClass3.AnonymousClass1 */

                        public void onClick(View view) {
                            LoginActivity.this.mobileno.setText("");
                        }
                    });
                } else {
                    LoginActivity.this.clear_text.setVisibility(8);
                }
                if (editable.toString().trim().length() == 10) {
                    LoginActivity.this.btn_login.setBackground(LoginActivity.this.getDrawable(R.drawable.cornor_color_radius));
                    LoginActivity.this.btn_login.setOnClickListener(new View.OnClickListener() {
                        /* class digi.coders.mho.Activity.LoginActivity.AnonymousClass3.AnonymousClass2 */

                        public void onClick(View view) {
                            if (Constant.isNetwork(LoginActivity.this)) {
                                LoginActivity.this.checkuser(editable.toString().trim());
                            }
                        }
                    });
                    return;
                }
                LoginActivity.this.btn_login.setBackground(LoginActivity.this.getDrawable(R.drawable.corner_redius));
                LoginActivity.this.btn_login.setOnClickListener(new View.OnClickListener() {
                    /* class digi.coders.mho.Activity.LoginActivity.AnonymousClass3.AnonymousClass3 */

                    public void onClick(View view) {
                    }
                });
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        this.spin_state.setText(Constant.countrycode);
    }

    public void checkuser(final String mobile) {
        Constant.showprogressbar(this, 1);
        Constant.StartConnection().getregistration(Constant.KEYVALUE, mobile).enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Activity.LoginActivity.AnonymousClass4 */

            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Log.i("login_response", response.body().toString());
                Constant.dismissdialog();
                try {
                    JSONObject jsonObject = new JSONArray(response.body().toString()).getJSONObject(0);
                    if (!jsonObject.getString("res").equalsIgnoreCase("success")) {
                        Toast.makeText(LoginActivity.this, "" + jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE), 0).show();
                    } else if (jsonObject.getString("open").equalsIgnoreCase("otp")) {
                        Constant.usertype = "newuser";
                        Intent i = new Intent(LoginActivity.this, OTPAcitivity.class);
                        i.putExtra("mobile", mobile);
                        LoginActivity.this.startActivity(i);
                    } else if (jsonObject.getString("open").equalsIgnoreCase("Password")) {
                        Constant.usertype = "olduser";
                        Intent i2 = new Intent(LoginActivity.this, PasswordActivity.class);
                        i2.putExtra("mobile", mobile);
                        LoginActivity.this.startActivity(i2);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.i("login_error", t.toString());
                Constant.dismissdialog();
            }
        });
    }
}
