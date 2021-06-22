package digi.coders.mho.Model;

public class RoomModel {
    String announcement;
    String created_id;
    String date;
    String id;
    String photo;
    String room_name;
    String status;
    String totaljoin_memebr;

    public String getId() {
        return this.id;
    }

    public String getRoom_name() {
        return this.room_name;
    }

    public String getCreated_id() {
        return this.created_id;
    }

    public String getPhoto() {
        return this.photo;
    }

    public String getAnnouncement() {
        return this.announcement;
    }

    public String getStatus() {
        return this.status;
    }

    public String getDate() {
        return this.date;
    }

    public RoomModel(String id2, String room_name2, String created_id2, String photo2, String announcement2, String status2, String date2, String totaljoin_memebr2) {
        this.id = id2;
        this.room_name = room_name2;
        this.created_id = created_id2;
        this.photo = photo2;
        this.announcement = announcement2;
        this.status = status2;
        this.date = date2;
        this.totaljoin_memebr = totaljoin_memebr2;
    }

    public String getTotaljoin_memebr() {
        return this.totaljoin_memebr;
    }
}
