package digi.coders.mho.Model;

public class RoomShowChatModel {
    String date;
    String id;
    String msg;
    String name;
    String photo;
    String room_id;
    String user_id;

    public RoomShowChatModel(String id2, String room_id2, String user_id2, String msg2, String date2, String photo2, String name2) {
        this.id = id2;
        this.room_id = room_id2;
        this.user_id = user_id2;
        this.msg = msg2;
        this.date = date2;
        this.photo = photo2;
        this.name = name2;
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

    public String getMsg() {
        return this.msg;
    }

    public String getDate() {
        return this.date;
    }

    public String getPhoto() {
        return this.photo;
    }

    public String getName() {
        return this.name;
    }
}
