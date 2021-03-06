package digi.coders.mho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Helper.Refresh;
import digi.coders.mho.Model.GiftModel;
import digi.coders.mho.R;

public class GiftShowAdapter extends RecyclerView.Adapter<GiftShowAdapter.ViewHolder> {


    Context context;
    List<GiftModel> giftModelList;
    int checkedPosition;
    Animation animBlink;
    public static int selectedpos;

    public GiftShowAdapter(Context context, List<GiftModel> giftModelList) {
        this.context = context;
        this.giftModelList = giftModelList;
        this.checkedPosition=-1;
        this.animBlink = AnimationUtils.loadAnimation(context,
                R.anim.blink);
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

        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return giftModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

             ImageView gifticons;
             TextView giftcoin;
             LinearLayout gift_lyaout;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            gifticons= itemView.findViewById(R.id.gifticons);
            giftcoin= itemView.findViewById(R.id.giftcoin);
            gift_lyaout= itemView.findViewById(R.id.gift_lyaout);
        }
        public void bind(int pos){
            if(checkedPosition==-1){
                gift_lyaout.setBackground(null);
            }else{
                if (checkedPosition==getAdapterPosition()){
                    gift_lyaout.setBackground(context.getDrawable(R.drawable.square));
                    gifticons.startAnimation(animBlink);
                }else{
                    gift_lyaout.setBackground(null);
                }
            }
            gift_lyaout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gift_lyaout.setBackground(context.getDrawable(R.drawable.square));
                    gifticons.startAnimation(animBlink);
                    selectedpos=pos;
                    Toast.makeText(context, ""+pos, Toast.LENGTH_SHORT).show();
                    if(checkedPosition!=getAdapterPosition()){
                        notifyItemChanged(checkedPosition);
                        checkedPosition=getAdapterPosition();
                    }
                }
            });
        }

    }


}
