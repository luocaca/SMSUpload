package sms.luocaca.smsupload.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/7/19 0019.
 */

public class Sms implements Serializable {


    private String address;
    private String person;
    private String body;
    private String date;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

//    int index_Address = cur.getColumnIndex("address");
//    int index_Person = cur.getColumnIndex("person");
//    int index_Body = cur.getColumnIndex("body");
//    int index_Date = cur.getColumnIndex("date");
//    int index_Type = cur.getColumnIndex("type");

}
