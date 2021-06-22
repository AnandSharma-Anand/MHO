package digi.coders.mho.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import digi.coders.mho.Helper.Constant;
import digi.coders.mho.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectTagActivity extends AppCompatActivity {

    public List<String> taglist=new ArrayList<>();
    ListView taglistitem;
    Toolbar toolbar;
    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_tag);

        taglist.add("Singing");
        taglist.add("Love");
        taglist.add("Friends");
        taglist.add("DJ");
        taglist.add("Gossip");
        taglist.add("Football");
        taglist.add("Family");
        taglist.add("Game");
        taglist.add("Girls Only");
        taglist.add("Competitions");
        taglist.add("Language Study");


        taglistitem=(ListView) findViewById(R.id.taglistitem);
        toolbar=(Toolbar) findViewById(R.id.toolbar);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        taglistitem.setAdapter(new ArrayAdapter(SelectTagActivity.this, android.R.layout.simple_dropdown_item_1line,taglist));


        taglistitem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(SelectTagActivity.this, ""+taglist.get(position), Toast.LENGTH_SHORT).show();
                updatedata(taglist.get(position));
            }
        });


    }



    public void updatedata(final String type) {

        Call<JsonArray> call = Constant.StartConnection().UpdateRoomTag(Constant.KEYVALUE, getIntent().getStringExtra("roomid"), type);
        call.enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Activity.UpdateProfileActivity.AnonymousClass7 */
            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Log.i("alksjjklsd", response.body().toString());
                try {
                    JSONObject jsonObject = new JSONArray(response.body().toString()).getJSONObject(0);
                    if (jsonObject.getString("res").equalsIgnoreCase("success")) {

                        RoomDetailsActivity.tag = type;
                        finish();

                        return;
                    }
                    Toast.makeText(SelectTagActivity.this, "" + jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE), Toast.LENGTH_SHORT).show();
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
