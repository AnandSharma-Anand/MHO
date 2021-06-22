package digi.coders.mho.Activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import digi.coders.mho.R;

public class AboutActivity extends AppCompatActivity {
    Toolbar toolbar;
    String url = "https://www.google.com/";
    WebView webview;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        this.webview = (WebView) findViewById(R.id.webview);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.webview.loadUrl(this.url);
        this.webview.getSettings().setJavaScriptEnabled(true);
        this.toolbar.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.AboutActivity.AnonymousClass1 */

            public void onClick(View view) {
                AboutActivity.this.onBackPressed();
            }
        });
    }
}
