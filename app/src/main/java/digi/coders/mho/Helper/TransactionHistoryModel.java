package digi.coders.mho.Helper;

public class TransactionHistoryModel {

    String id;
    String user_id;
    String purchaserule_id;
    String amount;
    String type;
    String msg;
    String txt_response;
    String order_id;
    String status;

    public String getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getPurchaserule_id() {
        return purchaserule_id;
    }

    public String getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }

    public String getTxt_response() {
        return txt_response;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    String date;

    public TransactionHistoryModel(String id, String user_id, String purchaserule_id, String amount, String type, String msg, String txt_response, String order_id, String status, String date) {
        this.id = id;
        this.user_id = user_id;
        this.purchaserule_id = purchaserule_id;
        this.amount = amount;
        this.type = type;
        this.msg = msg;
        this.txt_response = txt_response;
        this.order_id = order_id;
        this.status = status;
        this.date = date;
    }



}
