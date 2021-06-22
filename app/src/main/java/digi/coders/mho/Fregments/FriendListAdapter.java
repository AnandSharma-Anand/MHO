package digi.coders.mho.Fregments;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import digi.coders.mho.Activity.ChatActivity;
import digi.coders.mho.Model.FriendRequestModel;
import digi.coders.mho.R;
import java.util.List;

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.ViewHolder> {
    Context context;
    List<FriendRequestModel> friendRequestModelList;

    public FriendListAdapter(Context context2, List<FriendRequestModel> friendRequestModelList2) {
        this.context = context2;
        this.friendRequestModelList = friendRequestModelList2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(((LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_message_list, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.auth_msg.setText(this.friendRequestModelList.get(position).getAuthentication());
        holder.date.setText(this.friendRequestModelList.get(position).getDate());
        holder.username.setText(this.friendRequestModelList.get(position).getUser_name());
        holder.chat.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Fregments.FriendListAdapter.AnonymousClass1 */

            public void onClick(View view) {
                Intent intent = new Intent(FriendListAdapter.this.context, ChatActivity.class);
                intent.putExtra("username", FriendListAdapter.this.friendRequestModelList.get(position).getUser_name());
                intent.putExtra("friend_id", FriendListAdapter.this.friendRequestModelList.get(position).getId());
                intent.putExtra("reciver_id", FriendListAdapter.this.friendRequestModelList.get(position).getId1());
                FriendListAdapter.this.context.startActivity(intent);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.friendRequestModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView auth_msg;
        LinearLayout chat;
        TextView date;
        ImageView profile;
        TextView username;

        public ViewHolder(View itemView) {
            super(itemView);
            this.auth_msg = (TextView) itemView.findViewById(R.id.auth_msg);
            this.date = (TextView) itemView.findViewById(R.id.date);
            this.profile = (ImageView) itemView.findViewById(R.id.profile);
            this.username = (TextView) itemView.findViewById(R.id.username);
            this.chat = (LinearLayout) itemView.findViewById(R.id.chat);
        }
    }
}
