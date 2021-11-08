package id.ac.binus.my_application;

public class User {

    private int user_id;
    private  String email;
    private String username;
    private  String password;
    private String phone;
    private int wallet;
    private  String dob;
    private String alamat;

    public User(int user_id, String email, String username, String password, String phone, int wallet, String dob,String alamat) {
        this.user_id = user_id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.wallet = wallet;
        this.dob = dob;
        this.alamat = alamat;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }





}
