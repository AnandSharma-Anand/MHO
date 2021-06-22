package digi.coders.mho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import digi.coders.mho.Model.NotificationModel;
import digi.coders.mho.R;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    Context context;
    List<NotificationModel> notificationModelList;

    public NotificationAdapter(List<NotificationModel> notificationModelList2, Context context2) {
        this.notificationModelList = notificationModelList2;
        this.context = context2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(((LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_notification_layout, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.noti_date.setText(this.notificationModelList.get(position).getDate());
        holder.noti_msg.setText(this.notificationModelList.get(position).getDescription());
        holder.notificationtitle.setText(this.notificationModelList.get(position).getTittle());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.notificationModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView noti_date;
        TextView noti_msg;
        TextView notificationtitle;

        public ViewHolder(View itemView) {
            super(itemView);
            this.notificationtitle = (TextView) itemView.findViewById(R.id.notificationtitle);
            this.noti_msg = (TextView) itemView.findViewById(R.id.noti_msg);
            this.noti_date = (TextView) itemView.findViewById(R.id.noti_date);
        }
    }
}
