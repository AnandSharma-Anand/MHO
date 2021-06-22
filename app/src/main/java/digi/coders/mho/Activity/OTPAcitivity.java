package digi.coders.mho.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import com.google.gson.JsonArray;
import com.skydoves.elasticviews.ElasticButton;
import digi.coders.mho.Helper.Constant;
import digi.coders.mho.R;
import in.aabhasjindal.otptextview.OTPListener;
import in.aabhasjindal.otptextview.OtpTextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPAcitivity extends AppCompatActivity {
    Toolbar appbar;
    TextView mobile;
    OtpTextView otptextview;
    TextView resendotp;
    ElasticButton verify;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p_acitivity);
        this.appbar =  findViewById(R.id.appbar);
        this.mobile =  findViewById(R.id.mobile);
        this.otptextview =  findViewById(R.id.otptextview);
        this.resendotp =  findViewById(R.id.resendotp);
        this.mobile.setText("+91-" + getIntent().getStringExtra("mobile"));
        setSupportActionBar(this.appbar);
        getSupportActionBar().setIcon(R.drawable.ic_baseline_arrow_drop_down_24);
        this.appbar.setNavigationOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.OTPAcitivity.AnonymousClass1 */

            public void onClick(View view) {
                OTPAcitivity.this.onBackPressed();
            }
        });
        this.otptextview.setOtpListener(new OTPListener() {
            /* class digi.coders.mho.Activity.OTPAcitivity.AnonymousClass2 */

            @Override // in.aabhasjindal.otptextview.OTPListener
            public void onInteractionListener() {
            }

            @Override // in.aabhasjindal.otptextview.OTPListener
            public void onOTPComplete(String otp) {
                if (Constant.isNetwork(OTPAcitivity.this)) {
                    OTPAcitivity oTPAcitivity = OTPAcitivity.this;
                    oTPAcitivity.verifyotp(oTPAcitivity.getIntent().getStringExtra("mobile"), otp);
                }
            }
        });
        this.resendotp.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.OTPAcitivity.AnonymousClass3 */

            public void onClick(View view) {
                if (Constant.isNetwork(OTPAcitivity.this)) {
                    OTPAcitivity oTPAcitivity = OTPAcitivity.this;
                    oTPAcitivity.setResendotp(oTPAcitivity.getIntent().getStringExtra("mobile"));
                }
            }
        });
    }

    public void verifyotp(final String mobile2, String otp) {
        Constant.showprogressbar(this, 1);
        Constant.StartConnection().OtpVerifcation(Constant.KEYVALUE, mobile2, otp).enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Activity.OTPAcitivity.AnonymousClass4 */

            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Log.i("otp_response", response.body().toString());
                Constant.dismissdialog();
                try {
                    JSONObject jsonObject = new JSONArray(response.body().toString()).getJSONObject(0);
                    if (jsonObject.getString("res").equalsIgnoreCase("success")) {
                        Intent i = new Intent(OTPAcitivity.this, PasswordActivity.class);
                        i.putExtra("mobile", mobile2);
                        OTPAcitivity.this.startActivity(i);
                        return;
                    }
                    Toast.makeText(OTPAcitivity.this, "" + jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.i("otp_error", t.toString());
                Constant.dismissdialog();
            }
        });
    }

    public void setResendotp(String mobile2) {
        Constant.showprogressbar(this, 1);
        Constant.StartConnection().Resend(Constant.KEYVALUE, mobile2).enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Activity.OTPAcitivity.AnonymousClass5 */

            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Log.i("setResendotp_response", response.body().toString());
                Constant.dismissdialog();
                try {
                    JSONObject jsonObject = new JSONArray(response.body().toString()).getJSONObject(0);
                    if (jsonObject.getString("res").equalsIgnoreCase("success")) {
                        Toast.makeText(OTPAcitivity.this, "" + jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(OTPAcitivity.this, "" + jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.i("setResendotp_error", t.toString());
                Constant.dismissdialog();
            }
        });
    }
}
