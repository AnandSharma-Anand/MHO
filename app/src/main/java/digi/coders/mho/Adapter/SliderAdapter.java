package digi.coders.mho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
//import com.smarteist.autoimageslider.SliderViewAdapter;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;
import digi.coders.mho.Helper.Constant;
//import digi.coders.mho.Helper.ViewHolderz;
import digi.coders.mho.Helper.ViewHolderz;
import digi.coders.mho.Model.SliderModel;
import digi.coders.mho.R;
import java.util.List;

public class SliderAdapter
extends SliderViewAdapter<ViewHolderz> {
    Context context;
    List<SliderModel> image;

    public SliderAdapter(List<SliderModel> image2, Context context2) {
        this.image = image2;
        this.context = context2;
    }

    @Override // com.smarteist.autoimageslider.SliderViewAdapter
    public ViewHolderz onCreateViewHolder(ViewGroup parent) {
        return new ViewHolderz(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_image, (ViewGroup) null));
    }

    public void onBindViewHolder(ViewHolderz viewHolder, int position) {
        Picasso.get().load(Constant.Slider_Url + this.image.get(position).getImage()).into(viewHolder.img);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.image.size();
    }
}
