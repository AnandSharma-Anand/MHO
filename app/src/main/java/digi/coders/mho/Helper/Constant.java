package digi.coders.mho.Helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import digi.coders.mho.R;

public class Constant {
    public static String BASEURL = "http://mho.codersadda.in/apis/V1/";
    public static String KEYVALUE = "123456";
    public static String PROFILE_Url = "http://mho.codersadda.in/uploads/User/";
    public static String RoomPROFILE_Url = "http://mho.codersadda.in/uploads/Room/";
    public static String Slider_Url = "http://mho.codersadda.in/uploads/Slider/";
    public static String countrycode = "+91";
    public static String countryname = "+91";
    public static Dialog dialog;
    public static String usertype = "Got Something Technical Issue. Try again later!";

    public static GetServices StartConnection() {
        return (GetServices) RetrofitConnection.getRetrofitConnection().create(GetServices.class);
    }

    public static void showprogressbar(Context context, int action) {
        dialog = new Dialog(context);
        View aa = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.item_progress_bar_layout, (ViewGroup) null, false);
        dialog.setContentView(aa);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        Sprite doubleBounce = new Wave();
        ((ProgressBar) aa.findViewById(R.id.progressas)).setIndeterminateDrawable(doubleBounce);
        doubleBounce.setColor(-1);
        dialog.show();
    }

    public static void dismissdialog() {
        dialog.cancel();
        dialog.dismiss();
    }

    public static void openchooser(Activity c) {
        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(c);
        View view1 = c.getLayoutInflater().inflate(R.layout.ask_choosen_type, (ViewGroup) null);
        mBottomSheetDialog.setContentView(view1);
        mBottomSheetDialog.show();
        ((ImageView) view1.findViewById(R.id.camera)).setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Helper.Constant.AnonymousClass1 */

            public void onClick(View view) {
            }
        });
        ((ImageView) view1.findViewById(R.id.gallery)).setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Helper.Constant.AnonymousClass2 */

            public void onClick(View view) {
            }
        });
    }

    public static boolean isNetwork(Context context) {
        NetworkInfo netInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        noInternetDialog(context);
        return false;
    }

    public static void noInternetDialog(Context a) {
        AlertDialog.Builder dialog2 = new AlertDialog.Builder(a);
        dialog2.setMessage("no internet connection");
        dialog2.setNeutralButton("Ok", (DialogInterface.OnClickListener) null);
        dialog2.create().show();
    }
}
