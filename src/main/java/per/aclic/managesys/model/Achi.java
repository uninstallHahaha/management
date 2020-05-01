package per.aclic.managesys.model;

import java.util.Date;

public class Achi {
    private String id;

    private String name;

    private String userid;

    private Integer type;

    private String detail;

    private String pic;

    private Date ctime;

    public Achi() {
    }

    public Achi(String id, String name, String userid, Integer type, String detail) {
        this.id = id;
        this.name = name;
        this.userid = userid;
        this.type = type;
        this.detail = detail;
    }

    public Achi(String id, String name, String userid, Integer type, String detail, Date ctime) {
        this.id = id;
        this.name = name;
        this.userid = userid;
        this.type = type;
        this.detail = detail;
        this.ctime = ctime;
    }

    public Achi(String id, String name, String userid, Integer type, String detail, String pic, Date ctime) {
        this.id = id;
        this.name = name;
        this.userid = userid;
        this.type = type;
        this.detail = detail;
        this.pic = pic;
        this.ctime = ctime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}