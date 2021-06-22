package digi.coders.mho.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import digi.coders.mho.Helper.Constant;
import digi.coders.mho.R;
import java.util.ArrayList;
import java.util.List;

public class SelectCountryActivity extends AppCompatActivity {
    Toolbar appbar;
    List<String> countrycode = new ArrayList();
    List<String> countrylist = new ArrayList();
    ListView listview;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_country);
        this.appbar = (Toolbar) findViewById(R.id.appbar);
        this.listview = (ListView) findViewById(R.id.listview);
        setSupportActionBar(this.appbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.appbar.setNavigationOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.SelectCountryActivity.AnonymousClass1 */

            public void onClick(View view) {
                SelectCountryActivity.this.onBackPressed();
            }
        });
        this.countrylist.add("India");
        this.countrycode.add("+91 ");
        this.listview.setAdapter((ListAdapter) new ArrayAdapter(this, 17367050, this.countrylist));
        this.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* class digi.coders.mho.Activity.SelectCountryActivity.AnonymousClass2 */

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Constant.countrycode = SelectCountryActivity.this.countrycode.get(i);
                Constant.countryname = SelectCountryActivity.this.countrylist.get(i);
                SelectCountryActivity.this.finish();
            }
        });
    }
}
