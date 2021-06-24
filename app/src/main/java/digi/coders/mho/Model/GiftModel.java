package digi.coders.mho.Model;

public class GiftModel {

    public String getId() {
        return id;
    }

    public String getGift_coins() {
        return gift_coins;
    }

    public String getIcon() {
        return icon;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    String id;
    String gift_coins;
    String icon;
    String status;
    String date;

    public GiftModel(String id, String gift_coins, String icon, String status, String date) {
        this.id = id;
        this.gift_coins = gift_coins;
        this.icon = icon;
        this.status = status;
        this.date = date;
    }
}
