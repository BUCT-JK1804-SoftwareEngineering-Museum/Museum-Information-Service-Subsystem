package table;

public class New {
        private String new_id;  //新闻id
        private String mus_id;  //博物馆id
        private String new_publisher; //发布人
        private String new_time; //发布时间
        private String new_title; //新闻标题
        private String new_content; //新闻内容
        private String new_pic; //新闻图片
        private String new_source; //爬取源
        private String new_level; //新闻类型
        public void setNew_id(String new_id) {
            this.new_id = new_id;
        }
        public String getNew_id() {
            return new_id;
        }

        public void setMus_id(String mus_id) {
            this.mus_id = mus_id;
        }
        public String getMus_id() {
            return mus_id;
        }

        public void setNew_publisher(String new_publisher) {
            this.new_publisher = new_publisher;
        }
        public String getNew_publisher() {
            return new_publisher;
        }

        public void setNew_time(String new_time) {
            this.new_time = new_time;
        }
        public String getNew_time() {
            return new_time;
        }

        public void setNew_title(String new_title) {
            this.new_title = new_title;
        }
        public String getNew_title() {
            return new_title;
        }

        public void setNew_content(String new_content) {
            this.new_content = new_content;
        }
        public String getNew_content() {
            return new_content;
        }

        public void setNew_pic(String new_pic) {
            this.new_pic = new_pic;
        }
        public String getNew_pic() {
            return new_pic;
        }

        public void setNew_source(String new_source) {
            this.new_source = new_source;
        }
        public String getNew_source() {
            return new_source;
        }

        public void setNew_level(String new_level) {
            this.new_level = new_level;
        }
        public String getNew_level() {
            return new_level;
        }

    @Override
    public String toString() {
        return "New{" +
                "new_id='" + new_id + '\'' +
                ", mus_id='" + mus_id + '\'' +
                ", new_publisher='" + new_publisher + '\'' +
                ", new_time='" + new_time + '\'' +
                ", new_title='" + new_title + '\'' +
                ", new_content='" + new_content + '\'' +
                ", new_pic='" + new_pic + '\'' +
                ", new_source='" + new_source + '\'' +
                ", new_level='" + new_level + '\'' +
                '}';
    }
}
