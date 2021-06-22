package digi.coders.mho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import digi.coders.mho.Model.MessegingModel;
import digi.coders.mho.R;
import java.util.List;

public class MessagingAdapter extends BaseAdapter {
    Context context;
    List<MessegingModel> messegingModelList;

    public MessagingAdapter(List<MessegingModel> messegingModelList2, Context context2) {
        this.messegingModelList = messegingModelList2;
        this.context = context2;
    }

    public int getCount() {
        return this.messegingModelList.size();
    }

    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.item_messeginf_layout, viewGroup, false);
        LinearLayout me_layout = (LinearLayout) view2.findViewById(R.id.me_layout);
        LinearLayout they_layout = (LinearLayout) view2.findViewById(R.id.they_layout);
        TextView they_msg = (TextView) view2.findViewById(R.id.they_msg);
        TextView me_messege = (TextView) view2.findViewById(R.id.me_messege);
        if (this.messegingModelList.get(i).getStatus().equalsIgnoreCase("me")) {
            me_layout.setVisibility(0);
            they_layout.setVisibility(8);
            me_messege.setText(this.messegingModelList.get(i).getMessage());
        } else {
            me_layout.setVisibility(8);
            they_msg.setVisibility(0);
            me_messege.setText(this.messegingModelList.get(i).getMessage());
        }
        return view2;
    }
}
