package net.com.parkingos.bean;

/**
 * @author ASUS
 * @date 2020/4/15
 */
public class Seat {
    private String seat_id;
    private String seat_num;
    private String seat_section;
    private Integer seat_state;
    private String seat_tag;

    public String getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(String seat_id) {
        this.seat_id = seat_id;
    }

    public String getSeat_num() {
        return seat_num;
    }

    public void setSeat_num(String seat_num) {
        this.seat_num = seat_num;
    }

    public String getSeat_section() {
        return seat_section;
    }

    public void setSeat_section(String seat_section) {
        this.seat_section = seat_section;
    }

    public Integer getSeat_state() {
        return seat_state;
    }

    public void setSeat_state(Integer seat_state) {
        this.seat_state = seat_state;
    }

    public String getSeat_tag() {
        return seat_tag;
    }

    public void setSeat_tag(String seat_tag) {
        this.seat_tag = seat_tag;
    }

    public Seat() {
    }

    public Seat(String seat_id, String seat_num, String seat_section, Integer seat_state, String seat_tag) {
        this.seat_id = seat_id;
        this.seat_num = seat_num;
        this.seat_section = seat_section;
        this.seat_state = seat_state;
        this.seat_tag = seat_tag;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seat_id='" + seat_id + '\'' +
                ", seat_num='" + seat_num + '\'' +
                ", seat_section='" + seat_section + '\'' +
                ", seat_state=" + seat_state +
                ", seat_tag='" + seat_tag + '\'' +
                '}';
    }
}
