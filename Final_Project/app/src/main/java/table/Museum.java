package table;

public class Museum {

    private String mus_id;
    private String mus_name;
    private String mus_picture;
    private String mus_grade;
    private String mus_time;
    private String mus_address;
    private String mus_remark;
    private String mus_phone;
    private String mus_master;
    public void setMus_id(String mus_id) {
        this.mus_id = mus_id;
    }
    public String getMus_id() {
        return mus_id;
    }

    public void setMus_name(String mus_name) {
        this.mus_name = mus_name;
    }
    public String getMus_name() {
        return mus_name;
    }

    public void setMus_picture(String mus_picture) {
        this.mus_picture = mus_picture;
    }
    public String getMus_picture() {
        return mus_picture;
    }

    public void setMus_grade(String mus_grade) {
        this.mus_grade = mus_grade;
    }
    public String getMus_grade() {
        return mus_grade;
    }

    public void setMus_time(String mus_time) {
        this.mus_time = mus_time;
    }
    public String getMus_time() {
        return mus_time;
    }

    public void setMus_address(String mus_address) {
        this.mus_address = mus_address;
    }
    public String getMus_address() {
        return mus_address;
    }

    public void setMus_remark(String mus_remark) {
        this.mus_remark = mus_remark;
    }
    public String getMus_remark() {
        return mus_remark;
    }

    public void setMus_phone(String mus_phone) {
        this.mus_phone = mus_phone;
    }
    public String getMus_phone() {
        return mus_phone;
    }

    public void setMus_master(String mus_master) {
        this.mus_master = mus_master;
    }
    public String getMus_master() {
        return mus_master;
    }

    Museum(String mus_name, String mus_id, String mus_picture, String mus_grade, String mus_time, String mus_address, String mus_remark, String mus_phone, String mus_master)
    {
        this.mus_name=mus_name;
        this.mus_id=mus_id;
        this.mus_picture=mus_picture;
        this.mus_grade=mus_grade;
        this.mus_time=mus_time;
        this.mus_address=mus_address;
        this.mus_remark=mus_remark;
        this.mus_phone=mus_phone;
        this.mus_master=mus_master;
    }
    public Museum(){};

    @Override
    public String toString() {
        return "Museum{" +
                "mus_id='" + mus_id + '\'' +
                ", mus_name='" + mus_name + '\'' +
                ", mus_picture='" + mus_picture + '\'' +
                ", mus_grade='" + mus_grade + '\'' +
                ", mus_time='" + mus_time + '\'' +
                ", mus_address='" + mus_address + '\'' +
                ", mus_remark='" + mus_remark + '\'' +
                ", mus_phone='" + mus_phone + '\'' +
                ", mus_master='" + mus_master + '\'' +
                '}';
    }
}
