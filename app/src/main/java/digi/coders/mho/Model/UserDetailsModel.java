package digi.coders.mho.Model;

public class UserDetailsModel {
    String bio;
    String country;
    String date;
    String dateofbirth;
    String gender_type;
    String id;
    String mobile;
    String name;
    String otp;
    String otp_status;
    String password;
    String photo;
    String status;
    String tag;
    String user_id;
    String user_name;

    public String getId() {
        return this.id;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public String getUser_name() {
        return this.user_name;
    }

    public String getName() {
        return this.name;
    }

    public String getGender_type() {
        return this.gender_type;
    }

    public String getDateofbirth() {
        return this.dateofbirth;
    }

    public String getCountry() {
        return this.country;
    }

    public String getTag() {
        return this.tag;
    }

    public String getBio() {
        return this.bio;
    }

    public String getMobile() {
        return this.mobile;
    }

    public String getOtp() {
        return this.otp;
    }

    public String getOtp_status() {
        return this.otp_status;
    }

    public String getStatus() {
        return this.status;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPhoto() {
        return this.photo;
    }

    public String getDate() {
        return this.date;
    }

    public UserDetailsModel(String id2, String user_id2, String user_name2, String name2, String gender_type2, String dateofbirth2, String country2, String tag2, String bio2, String mobile2, String otp2, String otp_status2, String status2, String password2, String photo2, String date2) {
        this.id = id2;
        this.user_id = user_id2;
        this.user_name = user_name2;
        this.name = name2;
        this.gender_type = gender_type2;
        this.dateofbirth = dateofbirth2;
        this.country = country2;
        this.tag = tag2;
        this.bio = bio2;
        this.mobile = mobile2;
        this.otp = otp2;
        this.otp_status = otp_status2;
        this.status = status2;
        this.password = password2;
        this.photo = photo2;
        this.date = date2;
    }
}
