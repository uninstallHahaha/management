package per.aclic.managesys.model;

import java.util.Date;

public class Log {
    private String id;

    private String userid;

    private String username;

    private Integer userrole;

    private String opobj;

    private Date ctime;

    public Log() {
    }

    public Log(String id, String userid, String username, Integer userrole, String opobj) {
        this.id = id;
        this.userid = userid;
        this.username = username;
        this.userrole = userrole;
        this.opobj = opobj;
    }

    public String getId() {
        return id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getUserrole() {
        return userrole;
    }

    public void setUserrole(Integer userrole) {
        this.userrole = userrole;
    }

    public String getOpobj() {
        return opobj;
    }

    public void setOpobj(String opobj) {
        this.opobj = opobj == null ? null : opobj.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}