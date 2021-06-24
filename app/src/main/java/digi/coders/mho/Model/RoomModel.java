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

    public String getRoom_tag() {
        return room_tag;
    }

    public RoomModel(String id, String room_name, String created_id, String photo, String announcement, String status, String date, String totaljoin_memebr, String room_tag) {
        this.announcement = announcement;
        this.created_id = created_id;
        this.date = date;
        this.id = id;
        this.photo = photo;
        this.room_name = room_name;
        this.status = status;
        this.totaljoin_memebr = totaljoin_memebr;
        this.room_tag = room_tag;
    }

    String room_tag;

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



    public String getTotaljoin_memebr() {
        return this.totaljoin_memebr;
    }
}
