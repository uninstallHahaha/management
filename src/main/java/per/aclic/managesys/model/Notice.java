package per.aclic.managesys.model;

import java.util.Date;

public class Notice {
    private String id;

    private String title;

    private String content;

    private String cuserid;

    private Date ctime;

    public Notice() {
    }

    public Notice(String id, String title, String content, String cuserid) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.cuserid = cuserid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCuserid() {
        return cuserid;
    }

    public void setCuserid(String cuserid) {
        this.cuserid = cuserid == null ? null : cuserid.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}