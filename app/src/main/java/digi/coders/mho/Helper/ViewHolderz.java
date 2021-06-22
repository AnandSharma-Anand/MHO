package digi.coders.mho.Helper;
//
import android.view.View;
import android.widget.ImageView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import digi.coders.mho.R;

public class ViewHolderz extends SliderViewAdapter.ViewHolder {
    public ImageView img;

    public ViewHolderz(View view) {
        super(view);
        this.img = (ImageView) view.findViewById(R.id.card_logo);
    }
}
