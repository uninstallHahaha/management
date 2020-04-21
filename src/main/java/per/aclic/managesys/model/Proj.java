package per.aclic.managesys.model;

import java.util.Date;

public class Proj {
    private String id;

    private String userid;

    private String name;

    private Integer state;

    private Integer count;

    private String detail;

    private Integer type;

    private Date ctime;

    public String getId() {
        return id;
    }

    public Proj() {
    }

    public Proj(String id, String userid, String name, Integer state, Integer count, String detail, Integer type, Date ctime) {
        this.id = id;
        this.userid = userid;
        this.name = name;
        this.state = state;
        this.count = count;
        this.detail = detail;
        this.type = type;
        this.ctime = ctime;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}