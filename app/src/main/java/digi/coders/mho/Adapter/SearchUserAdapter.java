package digi.coders.mho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Model.UserDetailsModel;
import digi.coders.mho.R;
import java.util.List;

public class SearchUserAdapter extends BaseAdapter {
    Context context;
    List<UserDetailsModel> userDetailsModels;

    public SearchUserAdapter(List<UserDetailsModel> userDetailsModels2, Context context2) {
        this.userDetailsModels = userDetailsModels2;
        this.context = context2;
    }

    public int getCount() {
        return this.userDetailsModels.size();
    }

    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = ((LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_friendlist, viewGroup, false);
        ((TextView) view2.findViewById(R.id.friendname)).setText(this.userDetailsModels.get(i).getUser_name());
        Picasso.get().load(Constant.PROFILE_Url + this.userDetailsModels.get(i).getPhoto()).placeholder(R.drawable.profile).error(R.drawable.profile).into((ImageView) view2.findViewById(R.id.profile));
        return view2;
    }
}
