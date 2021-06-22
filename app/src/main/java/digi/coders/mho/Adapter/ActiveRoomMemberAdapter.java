package digi.coders.mho.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Model.ShowRoomJoinedUser;
import digi.coders.mho.R;
import java.util.List;

public class ActiveRoomMemberAdapter extends RecyclerView.Adapter<ActiveRoomMemberAdapter.ViewHolder> {
    Context context;
    List<ShowRoomJoinedUser> showRoomJoinedUserList;

    public ActiveRoomMemberAdapter(List<ShowRoomJoinedUser> showRoomJoinedUserList2, Context context2) {
        this.showRoomJoinedUserList = showRoomJoinedUserList2;
        this.context = context2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(((LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_active_user, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            holder.personname.setText(this.showRoomJoinedUserList.get(position).getUserDetailsModel().getUser_name());
            holder.jointime.setText(this.showRoomJoinedUserList.get(position).getDate());
            if (this.showRoomJoinedUserList.get(position).getUserDetailsModel().getGender_type().equalsIgnoreCase("MALE")) {
                holder.gendertype.setImageDrawable(this.context.getDrawable(R.drawable.male));
            } else {
                holder.gendertype.setImageDrawable(this.context.getDrawable(R.drawable.female));
            }
            Picasso.get().load(Constant.PROFILE_Url + "" + this.showRoomJoinedUserList.get(position).getUserDetailsModel().getPhoto()).placeholder(R.drawable.profile).error(R.drawable.profile).into(holder.profile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.showRoomJoinedUserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView gendertype;
        TextView jointime;
        TextView personname;
        CircleImageView profile;

        public ViewHolder(View itemView) {
            super(itemView);
            this.profile = (CircleImageView) itemView.findViewById(R.id.profile);
            this.personname = (TextView) itemView.findViewById(R.id.personname);
        }
    }
}
