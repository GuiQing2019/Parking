package net.com.parkingos.bean;

/**
 * @author ASUS
 * @date 2020/4/6
 */
public class User {

    private String user_id="";
    private String role_id="";
    private String user_name="";
    private String real_name="";
    private String user_pwd="";
    private String user_phone="";

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public User() {
    }

    public User(String user_id, String role_id, String user_name, String real_name, String user_pwd, String user_phone) {
        this.user_id = user_id;
        this.role_id = role_id;
        this.user_name = user_name;
        this.real_name = real_name;
        this.user_pwd = user_pwd;
        this.user_phone = user_phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", role_id='" + role_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", real_name='" + real_name + '\'' +
                ", user_pwd='" + user_pwd + '\'' +
                ", user_phone='" + user_phone + '\'' +
                '}';
    }
}
