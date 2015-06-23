package cn.grandcloud.email.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ÖÇ¿µ on 2015/6/23 0023.
 */
@Entity
@Table(name = "emailContent", schema = "", catalog = "emaildb")
public class EmailContentEntity {
    private int eid;
    private String subject;
    private String content;
    private Timestamp pubDate;

    @Id
    @Column(name = "eid", nullable = false, insertable = true, updatable = true)
    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    @Basic
    @Column(name = "subject", nullable = false, insertable = true, updatable = true, length = 100)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "content", nullable = false, insertable = true, updatable = true, length = 16777215)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "pubDate", nullable = false, insertable = true, updatable = true)
    public Timestamp getPubDate() {
        return pubDate;
    }

    public void setPubDate(Timestamp pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailContentEntity that = (EmailContentEntity) o;

        if (eid != that.eid) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (pubDate != null ? !pubDate.equals(that.pubDate) : that.pubDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eid;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (pubDate != null ? pubDate.hashCode() : 0);
        return result;
    }
}
