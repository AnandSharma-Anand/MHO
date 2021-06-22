package digi.coders.mho.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import digi.coders.mho.Helper.PrefrenceManager;
import digi.coders.mho.R;

public class SplashScreenActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        new Handler().postDelayed(new Runnable() {
            /* class digi.coders.mho.Activity.SplashScreenActivity.AnonymousClass1 */

            public void run() {
                if (new PrefrenceManager(SplashScreenActivity.this).getuserdetails().getId().equalsIgnoreCase("id")) {
                    SplashScreenActivity.this.startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                    SplashScreenActivity.this.finish();
                    return;
                }
                SplashScreenActivity.this.startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                SplashScreenActivity.this.finish();
            }
        }, 3000);
    }
}
