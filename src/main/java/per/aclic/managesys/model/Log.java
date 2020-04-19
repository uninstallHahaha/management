package per.aclic.managesys.model;

public class Log {
    private String id;

    private String userid;

    private String opdesc;

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

    public String getOpdesc() {
        return opdesc;
    }

    public void setOpdesc(String opdesc) {
        this.opdesc = opdesc == null ? null : opdesc.trim();
    }
}