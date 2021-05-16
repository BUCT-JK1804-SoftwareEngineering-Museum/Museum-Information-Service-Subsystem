package table;

public class Comment {
        private String com_id; //评论ID
        private String mus_id; //博物馆id
        private String com_grade; //评分
        private String user_id;  //用户id
        private String mus_name; //博物馆名称
        private String com_info; //评论内容
        private String com_time; //评论时间
        public void setCom_id(String com_id) {
            this.com_id = com_id;
        }
        public String getCom_id() {
            return com_id;
        }

        public void setMus_id(String mus_id) {
            this.mus_id = mus_id;
        }
        public String getMus_id() {
            return mus_id;
        }

        public void setCom_grade(String com_grade) {
            this.com_grade = com_grade;
        }
        public String getCom_grade() {
            return com_grade;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
        public String getUser_id() {
            return user_id;
        }

        public void setMus_name(String mus_name) {
            this.mus_name = mus_name;
        }
        public String getMus_name() {
            return mus_name;
        }

        public void setCom_info(String com_info) {
            this.com_info = com_info;
        }
        public String getCom_info() {
            return com_info;
        }

        public void setCom_time(String com_time) {
            this.com_time = com_time;
        }
        public String getCom_time() {
            return com_time;
        }

    @Override
    public String toString() {
        return "Comment{" +
                "com_id='" + com_id + '\'' +
                ", mus_id='" + mus_id + '\'' +
                ", com_grade='" + com_grade + '\'' +
                ", user_id='" + user_id + '\'' +
                ", mus_name='" + mus_name + '\'' +
                ", com_info='" + com_info + '\'' +
                ", com_time='" + com_time + '\'' +
                '}';
    }
}
