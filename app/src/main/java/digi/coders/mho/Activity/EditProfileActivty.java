package digi.coders.mho.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.gson.JsonArray;
import com.skydoves.elasticviews.ElasticButton;
import com.theartofdev.edmodo.cropper.CropImage;
//import com.theartofdev.edmodo.cropper.CropImage;
import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Helper.PrefrenceManager;
import digi.coders.mho.Model.UserDetailsModel;
import digi.coders.mho.R;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivty extends AppCompatActivity {
    TextView bio;
    TextView country;
    TextView dob;
    TextView gender;
    CardView opencamera;
    PrefrenceManager prefrenceManager;
    Uri profileUri;
    ImageView profile_img;
    TextView tag;
    Toolbar toolbar;
    ElasticButton update;
    EditText username;
    int day,month,year;


    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_activty);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.opencamera = (CardView) findViewById(R.id.opencamera);
        this.profile_img = (ImageView) findViewById(R.id.profile_img);
        this.username = (EditText) findViewById(R.id.username);
        this.gender = (TextView) findViewById(R.id.gender);
        this.dob = (TextView) findViewById(R.id.dob);
        this.country = (TextView) findViewById(R.id.country);
        this.tag = (TextView) findViewById(R.id.tag);
        this.bio = (TextView) findViewById(R.id.bio);
        this.update = (ElasticButton) findViewById(R.id.update);
        PrefrenceManager prefrenceManager2 = new PrefrenceManager(this);
        this.prefrenceManager = prefrenceManager2;
        UserDetailsModel userDetailsModel = prefrenceManager2.getuserdetails();
        this.toolbar.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.EditProfileActivty.AnonymousClass1 */

            public void onClick(View view) {
                EditProfileActivty.this.onBackPressed();
            }
        });
        this.country.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.EditProfileActivty.AnonymousClass2 */

            public void onClick(View view) {
                EditProfileActivty.this.startActivity(new Intent(EditProfileActivty.this, SelectCountryActivity.class));
            }
        });
        this.username.setText(userDetailsModel.getUser_name());
        this.gender.setText(userDetailsModel.getGender_type());
        this.dob.setText(userDetailsModel.getDateofbirth());
        this.country.setText(userDetailsModel.getCountry());
        this.tag.setText(userDetailsModel.getTag());
        this.bio.setText(userDetailsModel.getBio());


        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(EditProfileActivty.this);
                View aa = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_selectgender_layout, (ViewGroup) null, false);
                dialog.setContentView(aa);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

                ((TextView) aa.findViewById(R.id.male)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gender.setText("Male");
                        dialog.dismiss();
                    }
                });
                ((TextView) aa.findViewById(R.id.female)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gender.setText("Female");
                        dialog.dismiss();
                    }
                });


                dialog.show();

            }
        });



        dob.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(EditProfileActivty.this);
                View aa = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_datepicker_layout, (ViewGroup) null, false);
                dialog.setContentView(aa);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
              //  DisplayMetrics displayMetrics = new DisplayMetrics();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//                lp.horizontalMargin=40;

//                lp.gravity = Gravity.CENTER;

                dialog.getWindow().setAttributes(lp);


                dialog.show();

                DatePicker simpleDatePicker = (DatePicker)aa.findViewById(R.id.simpleDatePicker); // initiate a date picker



                simpleDatePicker.setSpinnersShown(true);
                simpleDatePicker.setCalendarViewShown(false);

                simpleDatePicker.setMaxDate(new Date().getTime());
//                simpleDatePicker.setform("dmy");


                simpleDatePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year1, int monthOfYear, int dayOfMonth) {


                         day = dayOfMonth;
                         month = monthOfYear;
                         year = year1;


                    }
                });


                ((ElasticButton) aa.findViewById(R.id.ok)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dob.setText(day+"/"+month+"/"+year);
                        dialog.dismiss();
                    }
                });

            }
        });



        this.opencamera.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.EditProfileActivty.AnonymousClass3 */

            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(EditProfileActivty.this, "android.permission.READ_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(EditProfileActivty.this, "android.permission.CAMERA") == 0 && ContextCompat.checkSelfPermission(EditProfileActivty.this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                    EditProfileActivty.this.profileUri = Uri.parse("");
                    CropImage.activity(EditProfileActivty.this.profileUri).setAspectRatio(1, 1).start(EditProfileActivty.this);
                    return;
                }
                ActivityCompat.requestPermissions(EditProfileActivty.this, new String[]{"android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 500);
            }
        });
        this.update.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Activity.EditProfileActivty.AnonymousClass4 */

            public void onClick(View view) {
                if (EditProfileActivty.this.validateform()) {
                    String usernameq = EditProfileActivty.this.username.getText().toString().trim();
                    String gendergender = EditProfileActivty.this.gender.getText().toString().trim();
                    EditProfileActivty.this.updateprofiledata(usernameq, EditProfileActivty.this.dob.getText().toString().trim(), EditProfileActivty.this.country.getText().toString().trim(), EditProfileActivty.this.tag.getText().toString().trim(), EditProfileActivty.this.bio.getText().toString().trim(), gendergender);
                }
            }
        });
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

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 203 && resultCode == -1 && data != null) {
            Uri uri = CropImage.getActivityResult(data).getUri();
            this.profileUri = uri;
            Log.e("img name", uri.toString());
            this.profile_img.setImageURI(this.profileUri);
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

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean validateform() {
        if (this.username.getText().toString().trim().isEmpty()) {
            this.username.setError("Required");
            this.username.requestFocus();
            return false;
        } else if (this.gender.getText().toString().trim().isEmpty()) {
            this.gender.setError("Required");
            this.gender.requestFocus();
            return false;
        } else if (this.dob.getText().toString().trim().isEmpty()) {
            this.dob.setError("Required");
            this.dob.requestFocus();
            return false;
        } else if (this.country.getText().toString().trim().isEmpty()) {
            this.country.setError("Required");
            this.country.requestFocus();
            return false;
        } else if (this.tag.getText().toString().trim().isEmpty()) {
            this.tag.setError("Required");
            this.tag.requestFocus();
            return false;
        } else if (!this.bio.getText().toString().trim().isEmpty()) {
            return true;
        } else {
            this.bio.setError("Required");
            this.bio.requestFocus();
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        this.country.setText(Constant.countryname);
    }

    public void updateprofile(String image) {
        Constant.showprogressbar(this, 1);
        Constant.StartConnection().ProfilePhoto(Constant.KEYVALUE, this.prefrenceManager.getuserdetails().getId(), image).enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Activity.EditProfileActivty.AnonymousClass5 */

            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Log.i("profilephot_response", response.body().toString());
                Constant.dismissdialog();
                HomeActivity.getprofile(EditProfileActivty.this);
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.i("profilephot_edit", t.toString());
                Constant.dismissdialog();
            }
        });
    }

    public void updateprofiledata(String user_name, String dateofbirth, String country2, String tag2, String bio2, String gender_type) {
        Constant.showprogressbar(this, 1);
        Constant.StartConnection().ProfileUpdate(Constant.KEYVALUE, this.prefrenceManager.getuserdetails().getId(), user_name, dateofbirth, country2, tag2, bio2, gender_type).enqueue(new Callback<JsonArray>() {
            /* class digi.coders.mho.Activity.EditProfileActivty.AnonymousClass6 */

            @Override // retrofit2.Callback
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Log.i("profiledata_response", response.body().toString());
                Constant.dismissdialog();
                HomeActivity.getprofile(EditProfileActivty.this);
                HomeActivity.activity.finish();
                Intent intent = new Intent(EditProfileActivty.this, HomeActivity.class);
                intent.putExtra("restart", "restart");
                EditProfileActivty.this.startActivity(intent);
                EditProfileActivty.this.finish();
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.i("profiledata_error", t.toString());
                Constant.dismissdialog();
            }
        });
    }
}
