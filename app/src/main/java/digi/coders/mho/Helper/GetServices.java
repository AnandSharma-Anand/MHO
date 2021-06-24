package digi.coders.mho.Helper;

import com.google.gson.JsonArray;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GetServices {
    @FormUrlEncoded
    @POST("Chat")
    Call<JsonArray> Chat(@Field("apikey") String str, @Field("sender_id") String str2, @Field("receiver_id") String str3, @Field("message") String str4, @Field("fri_id") String str5);

    @FormUrlEncoded
    @POST("CreateRoom")
    Call<JsonArray> CreateRoom(@Field("apikey") String str, @Field("room_name") String str2, @Field("created_id") String str3, @Field("photo") String str4, @Field("announcement") String str5);

    @FormUrlEncoded
    @POST("FriendRequest")
    Call<JsonArray> FriendRequest(@Field("apikey") String str, @Field("sender_id") String str2, @Field("request_id") String str3, @Field("authentication") String str4);

    @FormUrlEncoded
    @POST("FriendRequestAccept")
    Call<JsonArray> FriendRequestAccept(@Field("apikey") String str, @Field("friend_id") String str2);

    @FormUrlEncoded
    @POST("FriendRequestList")
    Call<JsonArray> FriendRequestList(@Field("apikey") String str, @Field("user_id") String str2);

    @FormUrlEncoded
    @POST("FriendRequestReject")
    Call<JsonArray> FriendRequestReject(@Field("apikey") String str, @Field("friend_id") String str2);

    @FormUrlEncoded
    @POST("JoinRoom")
    Call<JsonArray> JoinRoom(@Field("apikey") String str, @Field("user_id") String str2, @Field("room_id") String str3);

    @FormUrlEncoded
    @POST("JoinRoomShowUser")
    Call<JsonArray> JoinRoomShowUser(
            @Field("apikey") String str,
            @Field("room_id") String str2,
            @Field("search_value") String str3);

    @FormUrlEncoded
    @POST("Notification")
    Call<JsonArray> Notification(@Field("apikey") String str);

    @FormUrlEncoded
    @POST("OtpVerifcation")
    Call<JsonArray> OtpVerifcation(@Field("apikey") String str, @Field("mobile") String str2, @Field("otp") String str3);

    @FormUrlEncoded
    @POST("PasswordUpdate")
    Call<JsonArray> PasswordUpdate(@Field("apikey") String str, @Field("mobile") String str2, @Field("password") String str3);

    @FormUrlEncoded
    @POST("PasswordVerifcation")
    Call<JsonArray> PasswordVerifcation(@Field("apikey") String str, @Field("mobile") String str2, @Field("password") String str3);

    @FormUrlEncoded
    @POST("PendingFriendRequestList")
    Call<JsonArray> PendingFriendRequestList(@Field("apikey") String str, @Field("user_id") String str2);

    @FormUrlEncoded
    @POST("Profile")
    Call<JsonArray> Profile(@Field("apikey") String str, @Field("user_id") String str2);

    @FormUrlEncoded
    @POST("ProfilePhoto")
    Call<JsonArray> ProfilePhoto(@Field("apikey") String str, @Field("user_id") String str2, @Field("photo") String str3);

    @FormUrlEncoded
    @POST("ProfileUpdate")
    Call<JsonArray> ProfileUpdate(@Field("apikey") String str, @Field("user_id") String str2, @Field("user_name") String str3, @Field("dateofbirth") String str4, @Field("country") String str5, @Field("tag") String str6, @Field("bio") String str7, @Field("gender_type") String str8);

    @FormUrlEncoded
    @POST("Resend")
    Call<JsonArray> Resend(@Field("apikey") String str, @Field("mobile") String str2);

    @FormUrlEncoded
    @POST("RoomChat")
    Call<JsonArray> RoomChat(@Field("apikey") String str, @Field("user_id") String str2, @Field("room_id") String str3, @Field("msg") String str4);

    @FormUrlEncoded
    @POST("RoomExit")
    Call<JsonArray> RoomExit(@Field("apikey") String str, @Field("user_id") String str2, @Field("room_id") String str3);

    @FormUrlEncoded
    @POST("RoomProfilePhoto")
    Call<JsonArray> RoomProfilePhoto(@Field("apikey") String str, @Field("room_id") String str2, @Field("photo") String str3);

    @FormUrlEncoded
    @POST("SearchUser")
    Call<JsonArray> SearchUser(@Field("apikey") String str, @Field("usernameorid") String str2);

    @FormUrlEncoded
    @POST("ShowChat")
    Call<JsonArray> ShowChat(@Field("apikey") String str, @Field("fri_id") String str2);

    @FormUrlEncoded
    @POST("ShowRoom")
    Call<JsonArray> ShowRoom(@Field("apikey") String str, @Field("user_id") String str2);

    @FormUrlEncoded
    @POST("ShowRoomChat")
    Call<JsonArray> ShowRoomChat(@Field("apikey") String str, @Field("room_id") String str2);

    @FormUrlEncoded
    @POST("UpdateRoom")
    Call<JsonArray> UpdateRoomannouncment(@Field("apikey") String str, @Field("room_id") String str2, @Field("announcement") String str3);

    @FormUrlEncoded
    @POST("UpdateRoom")
    Call<JsonArray> UpdateRoomroom_name(@Field("apikey") String str, @Field("room_id") String str2, @Field("room_name") String str3);

    @FormUrlEncoded
    @POST("UpdateRoom")
    Call<JsonArray> UpdateRoomTag(@Field("apikey") String str, @Field("room_id") String str2, @Field("tag") String str3);

    @FormUrlEncoded
    @POST("Slider")
    Call<JsonArray> getSlider(@Field("apikey") String str);

    @FormUrlEncoded
    @POST("registration")
    Call<JsonArray> getregistration(@Field("apikey") String str, @Field("mobile") String str2);

    @FormUrlEncoded
    @POST("ShowRoom")
    Call<JsonArray> roomdetails(@Field("apikey") String str, @Field("room_id") String str2);


    @FormUrlEncoded
    @POST("Gift")
    Call<JsonArray> Gift(@Field("apikey") String str2);

    @FormUrlEncoded
    @POST("SerachRoom")
    Call<JsonArray> SerachRoom(@Field("apikey") String str, @Field("search_value") String str2);

    @FormUrlEncoded
    @POST("PurchaseRule")
    Call<JsonArray> PurchaseRule(@Field("apikey") String str);

    @FormUrlEncoded
    @POST("GenerateToken")
    Call<JsonArray> GenerateToken(
            @Field("apikey") String str,
            @Field("user_id") String user_id,
            @Field("purchaserule_id") String purchaserule_id,
            @Field("orderCurrency") String str1,
            @Field("orderAmount") String str2,
            @Field("orderId") String str3
            );

    @FormUrlEncoded
    @POST("UpdateWallet")
    Call<JsonArray> UpdateWallet(
            @Field("apikey") String str,
            @Field("user_id") String user_id,
            @Field("orderId") String orderId,
            @Field("txt_response") String txt_response
            );

    @FormUrlEncoded
    @POST("GetWallet")
    Call<JsonArray> GetWallet(
            @Field("apikey") String str,
            @Field("user_id") String user_id

            );
}
