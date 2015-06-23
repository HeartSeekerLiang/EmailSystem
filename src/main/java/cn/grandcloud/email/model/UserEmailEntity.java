package cn.grandcloud.email.model;

import javax.persistence.*;

/**
 * Created by ÖÇ¿µ on 2015/6/23 0023.
 */
@Entity
@Table(name = "userEmail", schema = "", catalog = "emaildb")
public class UserEmailEntity {
    private int uid;
    private String userEmail;
    private boolean ifSend;

    @Id
    @Column(name = "uid", nullable = false, insertable = true, updatable = true)
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "userEmail", nullable = false, insertable = true, updatable = true, length = 100)
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Basic
    @Column(name = "ifSend", nullable = false, insertable = true, updatable = true)
    public boolean isIfSend() {
        return ifSend;
    }

    public void setIfSend(boolean ifSend) {
        this.ifSend = ifSend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEmailEntity that = (UserEmailEntity) o;

        if (uid != that.uid) return false;
        if (ifSend != that.ifSend) return false;
        if (userEmail != null ? !userEmail.equals(that.userEmail) : that.userEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        result = 31 * result + (ifSend ? 1 : 0);
        return result;
    }
}
