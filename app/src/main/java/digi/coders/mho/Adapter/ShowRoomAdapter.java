package digi.coders.mho.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import digi.coders.mho.Activity.RoomDetailsActivity;
import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Model.RoomModel;
import digi.coders.mho.R;
import java.util.List;

public class ShowRoomAdapter extends RecyclerView.Adapter<ShowRoomAdapter.ViewHolder> {
    Context context;
    List<RoomModel> roomModelList;

    public ShowRoomAdapter(Context context2, List<RoomModel> roomModelList2) {
        this.context = context2;
        this.roomModelList = roomModelList2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(((LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_room_list, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        try {
            holder.announcment.setText(this.roomModelList.get(position).getAnnouncement());
            holder.room_name.setText(this.roomModelList.get(position).getRoom_name());
            holder.joined.setText(this.roomModelList.get(position).getTotaljoin_memebr());
            holder.room_card.setOnClickListener(new View.OnClickListener() {
                /* class digi.coders.mho.Adapter.ShowRoomAdapter.AnonymousClass1 */

                public void onClick(View view) {
                    Intent intent = new Intent(ShowRoomAdapter.this.context, RoomDetailsActivity.class);
                    intent.putExtra("roomid", ShowRoomAdapter.this.roomModelList.get(position).getId());
                    ShowRoomAdapter.this.context.startActivity(intent);
                }
            });
            Picasso.get().load(Constant.RoomPROFILE_Url + "" + this.roomModelList.get(position).getPhoto()).error(R.drawable.capture).placeholder(R.drawable.capture).into(holder.room_img);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.roomModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView announcment;
        TextView joined;
        CardView room_card;
        ImageView room_img;
        TextView room_name;

        public ViewHolder(View itemView) {
            super(itemView);
            this.room_img = (ImageView) itemView.findViewById(R.id.room_img);
            this.room_name = (TextView) itemView.findViewById(R.id.room_name);
            this.announcment = (TextView) itemView.findViewById(R.id.announcment);
            this.joined = (TextView) itemView.findViewById(R.id.joined);
            this.room_card = (CardView) itemView.findViewById(R.id.room_card);
        }
    }
}
