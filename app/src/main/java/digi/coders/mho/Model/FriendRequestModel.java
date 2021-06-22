package digi.coders.mho.Model;

public class FriendRequestModel {
    String authentication;
    String date;
    String id;
    String id1;
    String name;
    String photo;
    String request_id;
    String sender_id;
    String user_id;
    String user_name;

    public FriendRequestModel(String id2, String sender_id2, String request_id2, String authentication2, String id12, String user_name2, String user_id2, String photo2, String date2, String name2) {
        this.id = id2;
        this.sender_id = sender_id2;
        this.request_id = request_id2;
        this.authentication = authentication2;
        this.id1 = id12;
        this.user_name = user_name2;
        this.user_id = user_id2;
        this.photo = photo2;
        this.date = date2;
        this.name = name2;
    }

    public String getPhoto() {
        return this.photo;
    }

    public String getDate() {
        return this.date;
    }

    public String getId() {
        return this.id;
    }

    public String getSender_id() {
        return this.sender_id;
    }

    public String getRequest_id() {
        return this.request_id;
    }

    public String getAuthentication() {
        return this.authentication;
    }

    public String getId1() {
        return this.id1;
    }

    public String getUser_name() {
        return this.user_name;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public String getName() {
        return this.name;
    }
}
