package digi.coders.mho.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import com.gocashfree.cashfreesdk.CFPaymentService;
import com.cashfree.pg.CFPaymentService;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Helper.PrefrenceManager;
import digi.coders.mho.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static com.cashfree.pg.CFPaymentService.*;


public class PaymentActicity extends AppCompatActivity {

    String stage = "TEST";
    String token;

    String appId = "74203d9ea5a1b04858452415130247";
//    String orderId;
//    String orderAmount = "1.0;
//    String orderNote = "Coins Purchase";
//    String customerName = "MHOAPP";
//    String customerPhone = "6396873834";
//    String customerEmail = "anand@gmail.com";

    String orderId = "Order0001";
    String orderAmount = "1";
    String orderNote = "Test Order";
    String customerName = "John Doe";
    String customerPhone = "9900012345";
    String customerEmail = "test@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_payment_acticity);


        token=getIntent().getStringExtra("token");
        orderId="asas";


         orderId = getIntent().getStringExtra("orderid");
         orderAmount = getIntent().getStringExtra("orderamount");
//        String orderNote = "Test Order";
         customerName = new PrefrenceManager(PaymentActicity.this).getuserdetails().getUser_name();
         customerPhone = new PrefrenceManager(PaymentActicity.this).getuserdetails().getMobile();
         customerEmail = "mho@gmail.com";

        Log.i("aksghsjfd",token+" "+orderId);

        CFPaymentService cfPaymentService = CFPaymentService.getCFPaymentServiceInstance();
        cfPaymentService.setOrientation(0);
        cfPaymentService.doPayment(PaymentActicity.this, getInputParams(), token, stage, "#ff018180", "#FFFFFF", false);


    }

    private Map<String, String> getInputParams() {
        Map<String, String> params = new HashMap<>();
        params.put(PARAM_APP_ID, appId);
        params.put(PARAM_ORDER_ID, orderId);
        params.put(PARAM_ORDER_AMOUNT, orderAmount);
        params.put(PARAM_ORDER_NOTE, orderNote);
        params.put(PARAM_CUSTOMER_NAME, customerName);
        params.put(PARAM_CUSTOMER_PHONE, customerPhone);
        params.put(PARAM_CUSTOMER_EMAIL, customerEmail);
        params.put(PARAM_ORDER_CURRENCY, "INR");
        return params;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //
          Log.d("asasass", "ReqCode : " + CFPaymentService.REQ_CODE);
        Log.d("asasass", "API Response : ");
        if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null)
                Toast.makeText(this, ""+bundle.getString("txMsg"), Toast.LENGTH_SHORT).show();
//                  for (String key : bundle.keySet()) {
//                    if (bundle.getString(key) != null) {
//                        Log.d("asasass values", key + " : " + bundle.getString(key));
//                    }
//                }
            updatewalletamount(bundle);
            Log.d("asasasa",bundle+"");
        }
    }


    public void openrecipet(String msg){
        Dialog   dialog = new Dialog(PaymentActicity.this);
        View aa = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.dialog_ransaction_succes, (ViewGroup) null, false);
        dialog.setContentView(aa);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        ((TextView)  aa.findViewById(R.id.purchaseid)).setText(Html.fromHtml("<p>Your purchase ID : <b><font Color=Blue>#'"+orderId+"'</font></b></p>"));
        ((TextView)  aa.findViewById(R.id.orderamount)).setText("Rs."+orderAmount);
        ((TextView)  aa.findViewById(R.id.tnxstatus)).setText(msg);
        ((TextView)  aa.findViewById(R.id.elasticbutton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WalletActivity.WalletActivity.finish();
                startActivity(new Intent(PaymentActicity.this,WalletActivity.class));
                PaymentActicity.this.finish();
            }
        });

        dialog.show();



    }


    public void updatewalletamount(Bundle txtresponse) {
        Constant.showprogressbar(this, 1);
        Constant.StartConnection().UpdateWallet(Constant.KEYVALUE, new PrefrenceManager(PaymentActicity.this).getuserdetails().getId(), orderId, txtresponse+"").enqueue(
                new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                        Constant.dismissdialog();
                        Log.i("updatewallet_res",response.body().toString());
                        try {
                            JSONArray jsonArray=new JSONArray(response.body().toString());
                            JSONObject jsonObject=jsonArray.getJSONObject(0);
                            if (jsonObject.getString("res").equalsIgnoreCase("success")){
                                openrecipet(txtresponse.getString("txMsg"));
                            }else {
                                Toast.makeText(PaymentActicity.this, ""+jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Constant.dismissdialog();
                        Log.i("updatewallet_error",t.toString());
                    }
                }
        );
    }
}