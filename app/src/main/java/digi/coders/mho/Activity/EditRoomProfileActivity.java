package digi.coders.mho.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.google.gson.JsonArray;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
//import com.theartofdev.edmodo.cropper.CropImage;
import digi.coders.mho.Helper.Constant;
import digi.coders.mho.R;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditRoomProfileActivity extends AppCompatActivity {
    TextView announcment;
    LinearLayout editphoto;
    LinearLayout editroomname;
    LinearLayout edittag;
    LinearLayout edt_announcment;
    Uri profileUri;
    ImageView profile_photo;
    TextView roomname;
    TextView tag;
    Toolbar toolbar;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room_profile);
        this.profile_photo = (ImageView) findViewById(R.id.profile_photo);
        this.roomname = (TextView) findViewById(R.id.roomname);
        this.announcment = (TextView) findViewById(R.id.announcment);
        this.tag = (TextView) findViewById(R.id.tag);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.editphoto = (LinearLayout) findViewById(R.id.editphoto);
        this.edt_announcment = (LinearLayout) findViewById(R.id.edt_announcment);
        this.editroomname = (LinearLayout) findViewById(R.id.editroomname);
        this.edittag = (LinearLayout) findViewById(R.id.edittag);
        Picasso.get().load(Constant.RoomPROFILE_Url + "" + RoomDetailsActivity.photo).placeholder(R.drawable.capture).error(R.drawable.capture).into(this.profile_photo);
        this.roomname.setText(RoomDetailsActivity.room_name);
        this.announcment.setText(RoomDetailsActivity.announcement);
        this.tag.setText(RoomDetailsActivity.tag);
        this.toolbar.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.EditRoomProfileActivity.AnonymousClass1 */

            public void onClick(View view) {
                EditRoomProfileActivity.this.onBackPressed();
            }
        });
        this.editphoto.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.EditRoomProfileActivity.AnonymousClass2 */

            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(EditRoomProfileActivity.this, "android.permission.READ_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(EditRoomProfileActivity.this, "android.permission.CAMERA") == 0 && ContextCompat.checkSelfPermission(EditRoomProfileActivity.this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                    EditRoomProfileActivity.this.profileUri = Uri.parse("");
                    CropImage.activity(EditRoomProfileActivity.this.profileUri).setAspectRatio(1, 1).start(EditRoomProfileActivity.this);
                    return;
                }
                ActivityCompat.requestPermissions(EditRoomProfileActivity.this, new String[]{"android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 500);
            }
        });
        this.editroomname.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.EditRoomProfileActivity.AnonymousClass3 */

            public void onClick(View view) {
                Intent intent = new Intent(EditRoomProfileActivity.this, UpdateProfileActivity.class);
                intent.putExtra("from", "roomname");
                intent.putExtra("roomid", EditRoomProfileActivity.this.getIntent().getStringExtra("roomid"));
                EditRoomProfileActivity.this.startActivity(intent);
            }
        });
        this.edt_announcment.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.EditRoomProfileActivity.AnonymousClass4 */

            public void onClick(View view) {
                Intent intent = new Intent(EditRoomProfileActivity.this, UpdateProfileActivity.class);
                intent.putExtra("from", "announcment");
                intent.putExtra("roomid", EditRoomProfileActivity.this.getIntent().getStringExtra("roomid"));
                EditRoomProfileActivity.this.startActivity(intent);
            }
        });
        this.edittag.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.EditRoomProfileActivity.AnonymousClass4 */

            public void onClick(View view) {
                Intent intent = new Intent(EditRoomProfileActivity.this, SelectTagActivity.class);
                intent.putExtra("from", "tag");
                intent.putExtra("roomid", EditRoomProfileActivity.this.getIntent().getStringExtra("roomid"));
                EditRoomProfileActivity.this.startActivity(intent);
            }
        });
    }

    @Override // androidx.activity.ComponentActivity
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        Picasso.get().load(Constant.RoomPROFILE_Url + "" + RoomDetailsActivity.photo).placeholder(R.drawable.capture).error(R.drawable.capture).into(this.profile_photo);
        this.roomname.setText(RoomDetailsActivity.room_name);
        this.announcment.setText(RoomDetailsActivity.announcement);
        this.tag.setText(RoomDetailsActivity.tag);
    }

    @Override // androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback, androidx.fragment.app.FragmentActivity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 500) {
            Uri parse = Uri.parse("");
            this.profileUri = parse;
            CropImage.activity(parse).setAspectRatio(1, 1).start(this);
        }
    }
//
//    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 203 && resultCode == -1 && data != null) {
            Uri uri = CropImage.getActivityResult(data).getUri();
            this.profileUri = uri;
            Log.e("img name", uri.toString());
            this.profile_photo.setImageURI(this.profileUri);
            try {
                String encodedImage = encodeImage(BitmapFactory.decodeStream(getContentResolver().openInputStream(this.profileUri)));
                if (Constant.isNetwork(this)) {
                    updateprofile(encodedImage);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return Base64.encodeToString(baos.toByteArray(), 0);
    }

    private void updateprofile(String encodedImage) {
        Constant.showprogressbar(this, 1);
        Constant.StartConnection().RoomProfilePhoto(Constant.KEYVALUE, getIntent().getStringExtra("roomid"), encodedImage).enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Activity.EditRoomProfileActivity.AnonymousClass5 */

            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Log.i("profilephot_response", response.body().toString());
                Constant.dismissdialog();
                try {
                    JSONObject jsonObject = new JSONArray(response.body().toString()).getJSONObject(0);
                    if (jsonObject.getString("res").equalsIgnoreCase("")) {
                        RoomDetailsActivity.photo = jsonObject.getJSONArray("data").getJSONObject(0).getString("photo");
                    } else {
                        Toast.makeText(EditRoomProfileActivity.this, "" + jsonObject.getString(NotificationCompat.CATEGORY_MESSAGE), 0).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.i("profilephot_edit", t.toString());
                Constant.dismissdialog();
            }
        });
    }
}
