package net.com.parkingos.bean;

/**
 * @author ASUS
 * @date 2020/4/18
 */
public class Card {
    private String card_id;
    private String seat_id;
    private String user_name;
    private String user_gender;
    private String user_addr;
    private String car_num;

    public Card() {
    }

    public Card(String card_id, String seat_id, String user_name, String user_gender, String user_addr, String car_num) {
        this.card_id = card_id;
        this.seat_id = seat_id;
        this.user_name = user_name;
        this.user_gender = user_gender;
        this.user_addr = user_addr;
        this.car_num = car_num;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(String seat_id) {
        this.seat_id = seat_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_addr() {
        return user_addr;
    }

    public void setUser_addr(String user_addr) {
        this.user_addr = user_addr;
    }

    public String getCar_num() {
        return car_num;
    }

    public void setCar_num(String car_num) {
        this.car_num = car_num;
    }

    @Override
    public String toString() {
        return "Card{" +
                "card_id='" + card_id + '\'' +
                ", seat_id='" + seat_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_gender='" + user_gender + '\'' +
                ", user_addr='" + user_addr + '\'' +
                ", car_num='" + car_num + '\'' +
                '}';
    }
}
