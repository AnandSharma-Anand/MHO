package digi.coders.mho.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import digi.coders.mho.Adapter.ShowRoomAdapter;
import digi.coders.mho.Adapter.WalletPurchaseCoinsAdapter;
import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Helper.PrefrenceManager;
import digi.coders.mho.Model.RoomModel;
import digi.coders.mho.Model.WalletCoinsModel;
import digi.coders.mho.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;





public class WalletActivity extends AppCompatActivity {
    Toolbar toolbar;
    List<WalletCoinsModel> walletCoinsModels;
    RecyclerView coins_list;

    public static Activity WalletActivity;
    TextView transastion_history,walletamount;


    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar);
         coins_list = (RecyclerView) findViewById(R.id.coins_list);

        walletamount = (TextView) findViewById(R.id.walletamount);

        WalletActivity=WalletActivity.this;

        this.toolbar = toolbar2;
        toolbar2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WalletActivity.this.onBackPressed();
            }
        });

//        ((TextView) findViewById(R.id.transastion_history)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent());
//            }
//        });


        getwalletamount();
        getcoinsdetails();

    }

    public void getcoinsdetails(){

//        Constant.showprogressbar(this, 1);
        walletCoinsModels=new ArrayList<>();
        Constant.StartConnection().PurchaseRule(Constant.KEYVALUE).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Constant.dismissdialog();
                Log.i("getcoinsdetails_res",response.body().toString());


                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response.body().toString());
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    if (jsonObject.getString("res").equalsIgnoreCase("success")) {

                        JSONArray jsonArray1=jsonObject.getJSONArray("data");
                        for (int i=0;i<jsonArray1.length();i++) {
                            JSONObject jsonObject1=jsonArray1.getJSONObject(i);
                            walletCoinsModels.add(new WalletCoinsModel(
                                    jsonObject1.getString("id"),
                                    jsonObject1.getString("gold_coins"),
                                    jsonObject1.getString("amount"),
                                    jsonObject1.getString("status"),
                                    jsonObject1.getString("date")
                            ));
                        }

                        WalletPurchaseCoinsAdapter walletPurchaseCoinsAdapter=new WalletPurchaseCoinsAdapter(WalletActivity.this,walletCoinsModels);
                        coins_list.setLayoutManager(new GridLayoutManager(WalletActivity.this,2,RecyclerView.VERTICAL,false));
                        coins_list.setAdapter(walletPurchaseCoinsAdapter);
                    }else {
                        Toast.makeText(WalletActivity.this, ""+jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Constant.dismissdialog();
                Log.i("getcoinsdetails_error",t.toString());

            }
        });

    }

    public void getwalletamount(){
        Constant.showprogressbar(this, 1);
        Constant.StartConnection().GetWallet(Constant.KEYVALUE, new PrefrenceManager(WalletActivity.this).getuserdetails().getId()).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
            Constant.dismissdialog();
                Log.i("getwalletamount_res",response.body().toString());


                try {
                    JSONArray jsonArray=new JSONArray(response.body().toString());
                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                    if (jsonObject.getString("res").equalsIgnoreCase("success")){

                        String wallet=jsonObject.getString("wallet");
                        walletamount.setText(wallet);



                    }else {
                        Toast.makeText(WalletActivity.this, ""+jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Constant.dismissdialog();
                Log.i("getwalletamount_error",t.toString());
            }
        });

    }



}
