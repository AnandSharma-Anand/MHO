package digi.coders.mho.Activity;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import digi.coders.mho.R;

public class StoreActivity extends AppCompatActivity {
    Toolbar toolbar;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar);
        this.toolbar = toolbar2;
        toolbar2.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.StoreActivity.AnonymousClass1 */

            public void onClick(View view) {
                StoreActivity.this.onBackPressed();
            }
        });
    }
}
