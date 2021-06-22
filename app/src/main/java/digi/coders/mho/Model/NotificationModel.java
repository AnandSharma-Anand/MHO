package digi.coders.mho.Model;

public class NotificationModel {
    String date;
    String description;
    String id;
    String tittle;

    public String getId() {
        return this.id;
    }

    public String getTittle() {
        return this.tittle;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDate() {
        return this.date;
    }

    public NotificationModel(String id2, String tittle2, String description2, String date2) {
        this.id = id2;
        this.tittle = tittle2;
        this.description = description2;
        this.date = date2;
    }
}
