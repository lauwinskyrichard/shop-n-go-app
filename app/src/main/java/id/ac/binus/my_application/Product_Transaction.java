package id.ac.binus.my_application;

public class Product_Transaction {

    private int transaction_id;
    private int user_id;
    private int transaction_nominal;
    private String transaction_date;

    public Product_Transaction(int transaction_id, int user_id, int transaction_nominal, String transaction_date) {
        this.transaction_id = transaction_id;
        this.user_id = user_id;
        this.transaction_nominal = transaction_nominal;
        this.transaction_date = transaction_date;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTransaction_nominal() {
        return transaction_nominal;
    }

    public void setTransaction_nominal(int transaction_nominal) {
        this.transaction_nominal = transaction_nominal;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }
}
