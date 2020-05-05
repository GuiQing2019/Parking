package net.com.parkingos.bean;

/**
 * @author ASUS
 * @date 2020/4/7
 * 提示信息回传
 */
public class ResultData {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultData(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultData() {
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
