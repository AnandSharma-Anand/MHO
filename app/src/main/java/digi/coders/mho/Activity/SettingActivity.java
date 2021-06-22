package digi.coders.mho.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import digi.coders.mho.R;

public class SettingActivity extends AppCompatActivity {
    TextView aboutus;
    TextView accout;
    TextView logout;
    TextView noyification;
    TextView privecy;
    Toolbar toolbar;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        this.accout = (TextView) findViewById(R.id.accout);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.logout = (TextView) findViewById(R.id.logout);
        this.noyification = (TextView) findViewById(R.id.noyification);
        this.aboutus = (TextView) findViewById(R.id.aboutus);
        this.privecy = (TextView) findViewById(R.id.privecy);
        this.toolbar.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.SettingActivity.AnonymousClass1 */

            public void onClick(View view) {
                SettingActivity.this.onBackPressed();
            }
        });
        this.accout.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.SettingActivity.AnonymousClass2 */

            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, HomeActivity.class);
                intent.putExtra("restart", "restart");
                SettingActivity.this.startActivity(intent);
            }
        });
        this.logout.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.SettingActivity.AnonymousClass3 */

            public void onClick(View view) {
                SettingActivity.this.startActivity(new Intent(SettingActivity.this, LoginActivity.class));
                SettingActivity.this.finish();
            }
        });
        this.noyification.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.SettingActivity.AnonymousClass4 */

            public void onClick(View view) {
                SettingActivity.this.startActivity(new Intent(SettingActivity.this, PushNotificationActivity.class));
                SettingActivity.this.finish();
            }
        });
        this.aboutus.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.SettingActivity.AnonymousClass5 */

            public void onClick(View view) {
                SettingActivity.this.startActivity(new Intent(SettingActivity.this, AboutActivity.class));
                SettingActivity.this.finish();
            }
        });
        this.aboutus.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.SettingActivity.AnonymousClass6 */

            public void onClick(View view) {
                SettingActivity.this.startActivity(new Intent(SettingActivity.this, AboutActivity.class));
                SettingActivity.this.finish();
            }
        });
        this.privecy.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.SettingActivity.AnonymousClass7 */

            public void onClick(View view) {
                SettingActivity.this.startActivity(new Intent(SettingActivity.this, PrivacyActivity.class));
                SettingActivity.this.finish();
            }
        });
    }
}
