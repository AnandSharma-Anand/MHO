package digi.coders.mho.Model;

public class MessegingModel {
    String date;
    String fri_id;
    String id;
    String message;
    String receiver_id;
    String sender_id;
    String status;

    public MessegingModel(String id2, String sender_id2, String receiver_id2, String message2, String fri_id2, String date2, String status2) {
        this.id = id2;
        this.sender_id = sender_id2;
        this.receiver_id = receiver_id2;
        this.message = message2;
        this.fri_id = fri_id2;
        this.date = date2;
        this.status = status2;
    }

    public String getId() {
        return this.id;
    }

    public String getSender_id() {
        return this.sender_id;
    }

    public String getReceiver_id() {
        return this.receiver_id;
    }

    public String getMessage() {
        return this.message;
    }

    public String getFri_id() {
        return this.fri_id;
    }

    public String getDate() {
        return this.date;
    }

    public String getStatus() {
        return this.status;
    }
}
