package table;

public class Exhibition {
        private String exh_id; //展览id
        private String exh_name; //展览名称
        private String mus_id;  //博物馆id
        private String mus_name; //博物馆名称
        private String exh_info; //展览介绍
        private String exh_picture; //展览图片
        private String exh_time;  //展览时间
        public void setExh_id(String exh_id) {
            this.exh_id = exh_id;
        }
        public String getExh_id() {
            return exh_id;
        }

        public void setExh_name(String exh_name) {
            this.exh_name = exh_name;
        }
        public String getExh_name() {
            return exh_name;
        }

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

        public void setExh_info(String exh_info) {
            this.exh_info = exh_info;
        }
        public String getExh_info() {
            return exh_info;
        }

        public void setExh_picture(String exh_picture) {
            this.exh_picture = exh_picture;
        }
        public String getExh_picture() {
            return exh_picture;
        }

        public void setExh_time(String exh_time) {
            this.exh_time = exh_time;
        }
        public String getExh_time() {
            return exh_time;
        }

    @Override
    public String toString() {
        return "Exhibition{" +
                "exh_id='" + exh_id + '\'' +
                ", exh_name='" + exh_name + '\'' +
                ", mus_id='" + mus_id + '\'' +
                ", mus_name='" + mus_name + '\'' +
                ", exh_info='" + exh_info + '\'' +
                ", exh_picture='" + exh_picture + '\'' +
                ", exh_time='" + exh_time + '\'' +
                '}';
    }
}
