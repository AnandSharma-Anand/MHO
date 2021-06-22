package digi.coders.mho.Model;

public class SliderModel {
    String date;
    String id;
    String image;
    String status;

    public SliderModel(String id2, String image2, String date2, String status2) {
        this.id = id2;
        this.image = image2;
        this.date = date2;
        this.status = status2;
    }

    public String getId() {
        return this.id;
    }

    public String getImage() {
        return this.image;
    }

    public String getDate() {
        return this.date;
    }

    public String getStatus() {
        return this.status;
    }
}
