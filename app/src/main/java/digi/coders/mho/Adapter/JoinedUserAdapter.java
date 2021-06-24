package digi.coders.mho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Model.ShowRoomJoinedUser;
import digi.coders.mho.R;
import java.util.List;

public class JoinedUserAdapter extends RecyclerView.Adapter<JoinedUserAdapter.ViewHolder> {
    Context context;
    List<ShowRoomJoinedUser> showRoomJoinedUserList;

    public JoinedUserAdapter(List<ShowRoomJoinedUser> showRoomJoinedUserList2, Context context2) {
        this.showRoomJoinedUserList = showRoomJoinedUserList2;
        this.context = context2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(((LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_joineduser_layout, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {

        try
        {
            Picasso.get().load(Constant.PROFILE_Url+""+showRoomJoinedUserList.get(position).getUserDetailsModel().getPhoto()).placeholder(R.drawable.profile).into(holder.profile);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.showRoomJoinedUserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView profile;

        public ViewHolder(View itemView) {
            super(itemView);

            profile=itemView.findViewById(R.id.profile);

        }
    }
}
