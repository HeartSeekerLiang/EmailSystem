package cn.grandcloud.email.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ÖÇ¿µ on 2015/6/23 0023.
 */
@Entity
@Table(name = "send_normal", schema = "", catalog = "emaildb")
public class SendNormalEntity {
    private int snid;
    private String sender;
    private String reciver;
    private Timestamp sendTime;
    private EmailContentEntity emailContentByEid;

    @Id
    @Column(name = "snid", nullable = false, insertable = true, updatable = true)
    public int getSnid() {
        return snid;
    }

    public void setSnid(int snid) {
        this.snid = snid;
    }

    @Basic
    @Column(name = "sender", nullable = false, insertable = true, updatable = true, length = 100)
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Basic
    @Column(name = "reciver", nullable = false, insertable = true, updatable = true, length = 2147483647)
    public String getReciver() {
        return reciver;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }

    @Basic
    @Column(name = "sendTime", nullable = false, insertable = true, updatable = true)
    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SendNormalEntity that = (SendNormalEntity) o;

        if (snid != that.snid) return false;
        if (sender != null ? !sender.equals(that.sender) : that.sender != null) return false;
        if (reciver != null ? !reciver.equals(that.reciver) : that.reciver != null) return false;
        if (sendTime != null ? !sendTime.equals(that.sendTime) : that.sendTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = snid;
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (reciver != null ? reciver.hashCode() : 0);
        result = 31 * result + (sendTime != null ? sendTime.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "eid", referencedColumnName = "eid", nullable = false)
    public EmailContentEntity getEmailContentByEid() {
        return emailContentByEid;
    }

    public void setEmailContentByEid(EmailContentEntity emailContentByEid) {
        this.emailContentByEid = emailContentByEid;
    }
}
