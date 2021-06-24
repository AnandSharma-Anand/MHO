package digi.coders.mho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Model.ShowRoomJoinedUser;
import digi.coders.mho.R;

public class SelectUserAdapter extends BaseAdapter {

    List<ShowRoomJoinedUser> showRoomJoinedUserList;
    Context context;

    public SelectUserAdapter(List<ShowRoomJoinedUser> showRoomJoinedUserList, Context context) {
        this.showRoomJoinedUserList = showRoomJoinedUserList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return showRoomJoinedUserList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView= inflater.inflate(R.layout.item_spinnerlayout_layout, parent, false);

        CircleImageView circular_image=convertView.findViewById(R.id.circular_image);
        TextView text_username=convertView.findViewById(R.id.text_username);

        text_username.setText(showRoomJoinedUserList.get(position).getUserDetailsModel().getUser_name());
        try {
            Picasso.get().load(Constant.PROFILE_Url+""+showRoomJoinedUserList.get(position).getUserDetailsModel().getPhoto()).placeholder(R.drawable.profile).error(R.drawable.profile).into(circular_image);
        }catch (Exception e){
            e.printStackTrace();
        }

        return convertView;
    }
}
