package digi.coders.mho.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.JsonArray;
import com.squareup.picasso.Picasso;

import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Helper.Refresh;
import digi.coders.mho.Model.FriendRequestModel;
import digi.coders.mho.R;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendRequestListAdapter extends RecyclerView.Adapter<FriendRequestListAdapter.ViewHolder> {
    Context context;
    List<FriendRequestModel> friendRequestModelList;
    Refresh refresh;

    public FriendRequestListAdapter(Context context2, List<FriendRequestModel> friendRequestModelList2, Refresh refresh2) {
        this.context = context2;
        this.friendRequestModelList = friendRequestModelList2;
        this.refresh = refresh2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(((LayoutInflater) this.context
                .getSystemService(context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.item_friendrequest_list, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.reject.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Adapter.FriendRequestListAdapter.AnonymousClass1 */

            public void onClick(View view) {
                FriendRequestListAdapter friendRequestListAdapter = FriendRequestListAdapter.this;
                friendRequestListAdapter.acceptandreject(friendRequestListAdapter.friendRequestModelList.get(position).getId(), "reject");
            }
        });
        holder.accept.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Adapter.FriendRequestListAdapter.AnonymousClass2 */

            public void onClick(View view) {
                FriendRequestListAdapter friendRequestListAdapter = FriendRequestListAdapter.this;
                friendRequestListAdapter.acceptandreject(friendRequestListAdapter.friendRequestModelList.get(position).getId(), "accept");
            }
        });
        holder.friendname.setText(this.friendRequestModelList.get(position).getUser_name());
        holder.auth.setText(this.friendRequestModelList.get(position).getAuthentication());
        holder.request_date.setText(this.friendRequestModelList.get(position).getDate());

//        holder.

        Picasso.get().load(Constant.PROFILE_Url+""+friendRequestModelList.get(position).getPhoto()).placeholder(R.drawable.profile).error(R.drawable.profile).into(holder.profile);


    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.friendRequestModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView accept;
        TextView auth;
        TextView friendname,request_date;
        ImageView profile;
        TextView reject;

        public ViewHolder(View itemView) {
            super(itemView);
            this.profile = (ImageView) itemView.findViewById(R.id.profile);
            this.reject = (TextView) itemView.findViewById(R.id.reject);
            this.accept = (TextView) itemView.findViewById(R.id.accept);
            this.friendname = (TextView) itemView.findViewById(R.id.friendname);
            this.auth = (TextView) itemView.findViewById(R.id.auth);
            this.request_date = (TextView) itemView.findViewById(R.id.request_date);
        }
    }

    public void acceptandreject(String friend_id, final String status) {
        Constant.showprogressbar(this.context, 1);
        Call<JsonArray> call = null;
        if (status.equalsIgnoreCase("reject")) {
            call = Constant.StartConnection().FriendRequestReject(Constant.KEYVALUE, friend_id);
        } else if (status.equalsIgnoreCase("accept")) {
            call = Constant.StartConnection().FriendRequestAccept(Constant.KEYVALUE, friend_id);
        }
        call.enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Adapter.FriendRequestListAdapter.AnonymousClass3 */

            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Constant.dismissdialog();
                Log.i("accept_reject_response", response.body().toString() + "  " + status);
                FriendRequestListAdapter.this.refresh.refresh();
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Constant.dismissdialog();
                Log.i(" accept_reject_response", t.toString() + " " + status);
            }
        });
    }
}
