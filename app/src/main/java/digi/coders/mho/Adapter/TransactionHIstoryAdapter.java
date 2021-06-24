package digi.coders.mho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import digi.coders.mho.R;

public class TransactionHIstoryAdapter extends RecyclerView.Adapter<TransactionHIstoryAdapter.ViewHolder> {

    Context context;


    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new TransactionHIstoryAdapter.ViewHolder(((LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_transcationhistory_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }

    }
}
