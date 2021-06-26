package com.example.brainking.model;

import com.google.gson.annotations.SerializedName;

public class PoemsDetailModel {

    @SerializedName("msg")
    private String msg;
    @SerializedName("code")
    private Integer code;
    @SerializedName("data")
    private DataDTO data;

    @Override
    public String toString() {
        return "PoemsDetailModel{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO {
        @SerializedName("id")
        private Integer id;
        @SerializedName("title")
        private String title;
        @SerializedName("author")
        private String author;
        @SerializedName("dynasty")
        private String dynasty;
        @SerializedName("content")
        private String content;
        @SerializedName("annotation")
        private String annotation;
        @SerializedName("translation")
        private String translation;
        @SerializedName("autherIntro")
        private String autherIntro;
        @SerializedName("appreciate")
        private String appreciate;
        @SerializedName("audioUrl")
        private String audioUrl;
        @SerializedName("del")
        private Integer del;
        @SerializedName("version")
        private Integer version;
        @SerializedName("gmtCreate")
        private String gmtCreate;
        @SerializedName("gmtModified")
        private String gmtModified;
        @SerializedName("pid")
        private Integer pid;

        @Override
        public String toString() {
            return "DataDTO{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", author='" + author + '\'' +
                    ", dynasty='" + dynasty + '\'' +
                    ", content='" + content + '\'' +
                    ", annotation='" + annotation + '\'' +
                    ", translation='" + translation + '\'' +
                    ", autherIntro='" + autherIntro + '\'' +
                    ", appreciate='" + appreciate + '\'' +
                    ", audioUrl='" + audioUrl + '\'' +
                    ", del=" + del +
                    ", version=" + version +
                    ", gmtCreate='" + gmtCreate + '\'' +
                    ", gmtModified='" + gmtModified + '\'' +
                    ", pid=" + pid +
                    '}';
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getDynasty() {
            return dynasty;
        }

        public void setDynasty(String dynasty) {
            this.dynasty = dynasty;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAnnotation() {
            return annotation;
        }

        public void setAnnotation(String annotation) {
            this.annotation = annotation;
        }

        public String getTranslation() {
            return translation;
        }

        public void setTranslation(String translation) {
            this.translation = translation;
        }

        public String getAutherIntro() {
            return autherIntro;
        }

        public void setAutherIntro(String autherIntro) {
            this.autherIntro = autherIntro;
        }

        public String getAppreciate() {
            return appreciate;
        }

        public void setAppreciate(String appreciate) {
            this.appreciate = appreciate;
        }

        public String getAudioUrl() {
            return audioUrl;
        }

        public void setAudioUrl(String audioUrl) {
            this.audioUrl = audioUrl;
        }

        public Integer getDel() {
            return del;
        }

        public void setDel(Integer del) {
            this.del = del;
        }

        public Integer getVersion() {
            return version;
        }

        public void setVersion(Integer version) {
            this.version = version;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public String getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(String gmtModified) {
            this.gmtModified = gmtModified;
        }

        public Integer getPid() {
            return pid;
        }

        public void setPid(Integer pid) {
            this.pid = pid;
        }
    }
}
