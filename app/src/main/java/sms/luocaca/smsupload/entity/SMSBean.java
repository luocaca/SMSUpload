package sms.luocaca.smsupload.entity;

import java.io.Serializable;

/**
 * 短信
 */
public class SMSBean implements Serializable {

    private String _id ;
    private String thread_id; // 线程id
    private String msg_count; // 消息个数
    private String msg_snippet; // 消息片段
    private String address; // 地址
    private Long date; // 日期
    private String read; // 已读
    private String person;

    public SMSBean(String threadId, String msgCount, String msgSnippet) {
        thread_id = threadId;
        msg_count = msgCount;
        msg_snippet = msgSnippet;
    }

    public SMSBean() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getThread_id() {
        return thread_id;
    }

    public void setThread_id(String threadId) {
        thread_id = threadId;
    }

    public String getMsg_count() {
        return msg_count;
    }

    public void setMsg_count(String msgCount) {
        msg_count = msgCount;
    }

    public String getMsg_snippet() {
        return msg_snippet;
    }

    public void setMsg_snippet(String msgSnippet) {
        msg_snippet = msgSnippet;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
