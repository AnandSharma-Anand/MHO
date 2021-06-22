package digi.coders.mho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Model.RoomShowChatModel;
import digi.coders.mho.R;
import java.util.List;

public class ShowRoomChatAdapter extends RecyclerView.Adapter<ShowRoomChatAdapter.ViewHolder> {
    Context context;
    List<RoomShowChatModel> roomShowChatModelList;

    public ShowRoomChatAdapter(List<RoomShowChatModel> roomShowChatModelList2, Context context2) {
        this.roomShowChatModelList = roomShowChatModelList2;
        this.context = context2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.item_room_msg_layout, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            holder.name.setText(this.roomShowChatModelList.get(position).getName());
            holder.message.setText(this.roomShowChatModelList.get(position).getMsg());
            Picasso.get().load(Constant.PROFILE_Url + "" + this.roomShowChatModelList.get(position).getPhoto()).placeholder(R.drawable.profile).error(R.drawable.profile).into(holder.profile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.roomShowChatModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView message;
        TextView name;
        CircleImageView profile;

        public ViewHolder(View itemView) {
            super(itemView);
            this.message = (TextView) itemView.findViewById(R.id.message);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.profile = (CircleImageView) itemView.findViewById(R.id.profile);
        }
    }
}
