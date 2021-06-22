package digi.coders.mho.Model;

public class ShowRoomJoinedUser {
    String date;
    String id;
    String room_id;
    UserDetailsModel userDetailsModel;
    String user_id;

    public ShowRoomJoinedUser(String id2, String room_id2, String user_id2, String date2, UserDetailsModel userDetailsModel2) {
        this.id = id2;
        this.room_id = room_id2;
        this.user_id = user_id2;
        this.date = date2;
        this.userDetailsModel = userDetailsModel2;
    }

    public String getId() {
        return this.id;
    }

    public String getRoom_id() {
        return this.room_id;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public String getDate() {
        return this.date;
    }

    public UserDetailsModel getUserDetailsModel() {
        return this.userDetailsModel;
    }
}
