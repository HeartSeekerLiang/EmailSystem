package cn.grandcloud.email.model;

import javax.persistence.*;

/**
 * Created by ÖÇ¿µ on 2015/6/24 0024.
 */
@Entity
@Table(name = "send_template", schema = "", catalog = "emaildb")
public class SendTemplateEntity {
    private int stid;
    private String templateName;
    private String invokeName;
    private String content;

    @Id
    @Column(name = "stid", nullable = false, insertable = true, updatable = true)
    public int getStid() {
        return stid;
    }

    public void setStid(int stid) {
        this.stid = stid;
    }

    @Basic
    @Column(name = "templateName", nullable = false, insertable = true, updatable = true, length = 100)
    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Basic
    @Column(name = "invokeName", nullable = false, insertable = true, updatable = true, length = 100)
    public String getInvokeName() {
        return invokeName;
    }

    public void setInvokeName(String invokeName) {
        this.invokeName = invokeName;
    }

    @Basic
    @Column(name = "content", nullable = false, insertable = true, updatable = true, length = 2147483647)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SendTemplateEntity that = (SendTemplateEntity) o;

        if (stid != that.stid) return false;
        if (templateName != null ? !templateName.equals(that.templateName) : that.templateName != null) return false;
        if (invokeName != null ? !invokeName.equals(that.invokeName) : that.invokeName != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stid;
        result = 31 * result + (templateName != null ? templateName.hashCode() : 0);
        result = 31 * result + (invokeName != null ? invokeName.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
