package digi.coders.mho.Model;

public class WalletCoinsModel {

    String id;
    String gold_coins;
    String amount;
    String status;

    public String getId() {
        return id;
    }

    public String getGold_coins() {
        return gold_coins;
    }

    public String getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    String date;

    public WalletCoinsModel(String id, String gold_coins, String amount, String status, String date) {
        this.id = id;
        this.gold_coins = gold_coins;
        this.amount = amount;
        this.status = status;
        this.date = date;
    }
}
