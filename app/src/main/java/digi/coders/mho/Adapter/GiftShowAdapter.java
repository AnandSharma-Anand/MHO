package digi.coders.mho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Model.GiftModel;
import digi.coders.mho.R;

public class GiftShowAdapter extends RecyclerView.Adapter<GiftShowAdapter.ViewHolder> {


    Context context;
    List<GiftModel> giftModelList;

    public GiftShowAdapter(Context context, List<GiftModel> giftModelList) {
        this.context = context;
        this.giftModelList = giftModelList;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new GiftShowAdapter.ViewHolder(((LayoutInflater)
                this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_gift_layotu, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {


        holder.giftcoin.setText(giftModelList.get(position).getGift_coins());
        Picasso.get().load(Constant.GIFTICON_URL+""+giftModelList.get(position).getIcon()).placeholder(R.drawable.giftbox).into(holder.gifticons);


    }

    @Override
    public int getItemCount() {
        return giftModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView gifticons;
              TextView giftcoin;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            gifticons= itemView.findViewById(R.id.gifticons);
            giftcoin= itemView.findViewById(R.id.giftcoin);
        }
    }
}
