package digi.coders.mho.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.gson.JsonArray;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import digi.coders.mho.Activity.PaymentActicity;
import digi.coders.mho.Activity.WalletActivity;
import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Helper.PrefrenceManager;
import digi.coders.mho.Model.WalletCoinsModel;
import digi.coders.mho.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletPurchaseCoinsAdapter extends RecyclerView.Adapter<WalletPurchaseCoinsAdapter.ViewHolder> {

    Context context;
    List<WalletCoinsModel> walletCoinsModels;

    public WalletPurchaseCoinsAdapter(Context context, List<WalletCoinsModel> walletCoinsModels) {
        this.context = context;
        this.walletCoinsModels = walletCoinsModels;
    }


    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new WalletPurchaseCoinsAdapter.ViewHolder(((LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_wallet, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        holder.coins.setText(walletCoinsModels.get(position).getGold_coins());
        holder.amount.setText("INR "+walletCoinsModels.get(position).getAmount());



        holder.amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gettoken(walletCoinsModels.get(position).getAmount(),walletCoinsModels.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return walletCoinsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView coins,amount;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            coins=(TextView) itemView.findViewById(R.id.coins);
            amount=(TextView) itemView.findViewById(R.id.amount);

        }
    }

    public void gettoken(String amount,String purchaseid){
        Constant.showprogressbar(context, 1);
        String orderid=System.currentTimeMillis()+"";
        Constant.StartConnection().GenerateToken(Constant.KEYVALUE,new PrefrenceManager(context).getuserdetails().getId(),purchaseid,"INR",amount,orderid).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Constant.dismissdialog();
                Log.i("generate_token_res",response.body().toString());

                try {
                    JSONArray jsonArray=new JSONArray(response.body().toString());
                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                    if (jsonObject.getString("res").equalsIgnoreCase("success")){
                        JSONObject jsonObject1=jsonObject.getJSONObject("data");
                        String token=jsonObject1.getString("cftoken");
                        Intent intent=new Intent(context, PaymentActicity.class);
                        intent.putExtra("token",token);
                        intent.putExtra("orderid",orderid);
                        intent.putExtra("orderamount",amount);
                        Log.i("token",token);
                        context.startActivity(intent);

                    }else {
                        Toast.makeText(context, ""+jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Constant.dismissdialog();
                Log.i("generate_token_err",t.toString());
            }
        });
    }
}
