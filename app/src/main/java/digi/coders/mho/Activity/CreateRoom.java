package digi.coders.mho.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import com.google.gson.JsonArray;
import com.skydoves.elasticviews.ElasticButton;
import com.theartofdev.edmodo.cropper.CropImage;

import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Helper.PrefrenceManager;
import digi.coders.mho.R;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateRoom extends AppCompatActivity {
    EditText ammounsment;
    ElasticButton createroom;
    ImageView opencamera;
    Uri profileUri;
    ImageView profile_img;
    EditText roomname;
    Bitmap selectedImage;
    Toolbar toolbar;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.roomname = (EditText) findViewById(R.id.roomname);
        this.ammounsment = (EditText) findViewById(R.id.ammounsment);
        this.opencamera = (ImageView) findViewById(R.id.opencamera);
        this.profile_img = (ImageView) findViewById(R.id.profile_img);
        this.createroom = (ElasticButton) findViewById(R.id.createroom);
        this.toolbar.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.CreateRoom.AnonymousClass1 */
            public void onClick(View view) {
                CreateRoom.this.onBackPressed();
            }
        });
        this.opencamera.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.CreateRoom.AnonymousClass2 */

            public void onClick(View view) {
                CreateRoom.this.profileUri = Uri.parse("");
                CropImage.activity(CreateRoom.this.profileUri).setAspectRatio(1, 1).start(CreateRoom.this);
            }
        });
        this.createroom.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.CreateRoom.AnonymousClass3 */

            public void onClick(View view) {
                if (Constant.isNetwork(CreateRoom.this) && CreateRoom.this.validation()) {
                    String roomnamea = CreateRoom.this.roomname.getText().toString().trim();
                    String announsment = CreateRoom.this.ammounsment.getText().toString().trim();
                    String encodedImage = "";
                    if (CreateRoom.this.selectedImage != null) {
                        CreateRoom createRoom = CreateRoom.this;
                        encodedImage = createRoom.encodeImage(createRoom.selectedImage);
                    }
                    CreateRoom.this.createroom(roomnamea, announsment, encodedImage);
                }
            }
        });
    }

//    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 203 && resultCode == -1 && data != null) {
            Uri uri = CropImage.getActivityResult(data).getUri();
            this.profileUri = uri;
            Log.e("img name", uri.toString());
            this.profile_img.setImageURI(this.profileUri);
            try {
                this.selectedImage = BitmapFactory.decodeStream(getContentResolver().openInputStream(this.profileUri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
//
//    /* access modifiers changed from: private */
//    /* access modifiers changed from: public */
    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return Base64.encodeToString(baos.toByteArray(), 0);
    }
//
//    /* access modifiers changed from: private */
//    /* access modifiers changed from: public */
    private boolean validation() {
        if (this.roomname.getText().toString().trim().isEmpty()) {
            this.roomname.setError("Required");
            this.roomname.requestFocus();
            return false;
        } else if (!this.ammounsment.getText().toString().trim().isEmpty()) {
            return true;
        } else {
            this.ammounsment.setError("Required");
            this.ammounsment.requestFocus();
            return false;
        }
    }
//
    public void createroom(String roomname2, String anouncement, String image) {
        Constant.showprogressbar(this, 1);
        Constant.StartConnection().CreateRoom(Constant.KEYVALUE, roomname2, new PrefrenceManager(this).getuserdetails().getId(), image, anouncement).enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Activity.CreateRoom.AnonymousClass4 */
            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Log.i("createroom_repsponse", response.body().toString());
                Constant.dismissdialog();
                try {
                    JSONObject jsonObject = new JSONArray(response.body().toString()).getJSONObject(0);
                    if (jsonObject.getString("res").equalsIgnoreCase("success")) {
                        Toast.makeText(CreateRoom.this, "" + jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE), Toast.LENGTH_SHORT).show();
                        CreateRoom.this.startActivity(new Intent(CreateRoom.this, HomeActivity.class));
                        CreateRoom.this.finish();
                        return;
                    }
                    Toast.makeText(CreateRoom.this, "" + jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.i("createroom_error", t.toString());
                Constant.dismissdialog();
            }
        });
    }
}
