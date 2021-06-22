package digi.coders.mho.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import digi.coders.mho.Model.UserDetailsModel;
import org.json.JSONException;
import org.json.JSONObject;

public class PrefrenceManager {
    Context context;
    SharedPreferences.Editor editor;
    SharedPreferences saSharedPreferences;

    public PrefrenceManager(Context context2) {
        this.context = context2;
        SharedPreferences sharedPreferences = context2.getSharedPreferences("MHO_Prefrence", 0);
        this.saSharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }

    public void setuserdetails(JSONObject jsonObject) {
        try {
            this.editor.putString("id", jsonObject.getString("id"));
            this.editor.putString("user_id", jsonObject.getString("user_id"));
            this.editor.putString("user_name", jsonObject.getString("user_name"));
            this.editor.putString("name", jsonObject.getString("name"));
            this.editor.putString("gender_type", jsonObject.getString("gender_type"));
            this.editor.putString("dateofbirth", jsonObject.getString("dateofbirth"));
            this.editor.putString("country", jsonObject.getString("country"));
            this.editor.putString("tag", jsonObject.getString("tag"));
            this.editor.putString("bio", jsonObject.getString("bio"));
            this.editor.putString("mobile", jsonObject.getString("mobile"));
            this.editor.putString("otp", jsonObject.getString("otp"));
            this.editor.putString("otp_status", jsonObject.getString("otp_status"));
            this.editor.putString(NotificationCompat.CATEGORY_STATUS, jsonObject.getString(NotificationCompat.CATEGORY_STATUS));
            this.editor.putString("password", jsonObject.getString("password"));
            this.editor.putString("photo", jsonObject.getString("photo"));
            this.editor.putString("date", jsonObject.getString("date"));
            this.editor.commit();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("shareed_catch", e.getMessage());
        }
    }

    public UserDetailsModel getuserdetails() {
        return new UserDetailsModel(this.saSharedPreferences.getString("id", "id"), this.saSharedPreferences.getString("user_id", "user_id"), this.saSharedPreferences.getString("user_name", "user_name"), this.saSharedPreferences.getString("name", "name"), this.saSharedPreferences.getString("gender_type", "gender_type"), this.saSharedPreferences.getString("dateofbirth", "dateofbirth"), this.saSharedPreferences.getString("country", "country"), this.saSharedPreferences.getString("tag", "tag"), this.saSharedPreferences.getString("bio", "bio"), this.saSharedPreferences.getString("mobile", "mobile"), this.saSharedPreferences.getString("otp", "otp"), this.saSharedPreferences.getString("otp_status", "otp_status"), this.saSharedPreferences.getString(NotificationCompat.CATEGORY_STATUS, NotificationCompat.CATEGORY_STATUS), this.saSharedPreferences.getString("password", "password"), this.saSharedPreferences.getString("photo", "photo"), this.saSharedPreferences.getString("date", "date"));
    }
}
