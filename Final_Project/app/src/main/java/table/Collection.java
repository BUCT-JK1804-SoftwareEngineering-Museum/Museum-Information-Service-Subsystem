package table;

public class Collection {

    private String col_id; //藏品ID
    private String mus_id;  //博物馆ID
    private String col_name; //藏品名称
    private String col_era;  // 藏品年代
    private String col_info;  //藏品介绍
    private String mus_name;  //博物馆名称
    private String col_picture; //藏品图片
    public void setCol_id(String col_id) {
        this.col_id = col_id;
    }
    public String getCol_id() {
        return col_id;
    }

    public void setMus_id(String mus_id) {
        this.mus_id = mus_id;
    }
    public String getMus_id() {
        return mus_id;
    }

    public void setCol_name(String col_name) {
        this.col_name = col_name;
    }
    public String getCol_name() {
        return col_name;
    }

    public void setCol_era(String col_era) {
        this.col_era = col_era;
    }
    public String getCol_era() {
        return col_era;
    }

    public void setCol_info(String col_info) {
        this.col_info = col_info;
    }
    public String getCol_info() {
        return col_info;
    }

    public void setMus_name(String mus_name) {
        this.mus_name = mus_name;
    }
    public String getMus_name() {
        return mus_name;
    }

    public void setCol_picture(String col_picture) {
        this.col_picture = col_picture;
    }
    public String getCol_picture() {
        return col_picture;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "col_id='" + col_id + '\'' +
                ", mus_id='" + mus_id + '\'' +
                ", col_name='" + col_name + '\'' +
                ", col_era='" + col_era + '\'' +
                ", col_info='" + col_info + '\'' +
                ", mus_name='" + mus_name + '\'' +
                ", col_picture='" + col_picture + '\'' +
                '}';
    }
}
