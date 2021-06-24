package digi.coders.mho.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import digi.coders.mho.Adapter.ShowRoomAdapter;
import digi.coders.mho.Fregments.RoomFragment;
import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Helper.PrefrenceManager;
import digi.coders.mho.Model.RoomModel;
import digi.coders.mho.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    TextView search;
    ImageView toolbar;
    EditText edittext_searchvalue;
    List<RoomModel> roomModelList ;
    RecyclerView view_room;

    /* access modifiers changed from: protected */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        this.toolbar = (ImageView) findViewById(R.id.toolbar);

        this.search = (TextView) findViewById(R.id.search);

        this.view_room = (RecyclerView) findViewById(R.id.search_recyclerview);

        this.edittext_searchvalue = (EditText) findViewById(R.id.edittext_searchvalue);

        this.toolbar.setOnClickListener(view -> SearchActivity.this.onBackPressed());

        this.search.setOnClickListener(view -> {

            if (edittext_searchvalue.getText().toString().trim().isEmpty()){
                edittext_searchvalue.setError("Required");
            }else {
                searchroom(edittext_searchvalue.getText().toString().trim());
            }
        });
    }
    public void searchroom(String searchkey){
        Constant.showprogressbar(this, 1);
        roomModelList = new ArrayList<>();
        Constant.StartConnection().SerachRoom(Constant.KEYVALUE,searchkey).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Constant.dismissdialog();

                Log.i("searchroom_response",response.body().toString());

                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response.body().toString());
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    if (jsonObject.getString("res").equalsIgnoreCase("success")) {
                        JSONArray jsonArray1 = jsonObject.getJSONArray("data");
                        int i = 0;
                        while (i < jsonArray1.length()) {
                            JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                            roomModelList.add(new RoomModel(jsonObject1.getString("id"), jsonObject1.getString("room_name"), jsonObject1.getString("created_id"), jsonObject1.getString("photo"), jsonObject1.getString("announcement"), jsonObject1.getString(NotificationCompat.CATEGORY_STATUS), jsonObject1.getString("date"), jsonObject1.getString("totaljoin_memebr"),jsonObject1.getString("tag")));
                            i++;
                            jsonArray = jsonArray;
                        }
                        ShowRoomAdapter showRoomAdapter = new ShowRoomAdapter(SearchActivity.this, roomModelList);
                        SearchActivity.this.view_room.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                        SearchActivity.this.view_room.setAdapter(showRoomAdapter);
                        return;
                    }else {
                        Toast.makeText(SearchActivity.this, ""+jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Constant.dismissdialog();

                Log.i("searchroom_error",t.toString());
            }
        });

    }

}
