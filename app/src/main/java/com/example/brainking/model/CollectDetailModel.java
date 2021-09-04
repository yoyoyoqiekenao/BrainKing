package com.example.brainking.model;

import com.google.gson.annotations.SerializedName;

public class CollectDetailModel {

    @SerializedName("msg")
    public String msg;
    @SerializedName("code")
    public Integer code;
    @SerializedName("data")
    public DataDTO data;

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
        @SerializedName("pId")
        public String pId;
        @SerializedName("title")
        public String title;
        @SerializedName("author")
        public String author;
        @SerializedName("dynasty")
        public String dynasty;
        @SerializedName("content")
        public String content;
        @SerializedName("annotation")
        public String annotation;
        @SerializedName("translation")
        public String translation;
        @SerializedName("autherIntro")
        public String autherIntro;
        @SerializedName("appreciate")
        public String appreciate;
        @SerializedName("audioUrl")
        public String audioUrl;
        @SerializedName("id")
        public String id;
        @SerializedName("version")
        public String version;
        @SerializedName("gmtCreate")
        public String gmtCreate;
        @SerializedName("gmtModified")
        public String gmtModified;
        @SerializedName("del")
        public Boolean del;

        public String getpId() {
            return pId;
        }

        public void setpId(String pId) {
            this.pId = pId;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
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

        public Boolean getDel() {
            return del;
        }

        public void setDel(Boolean del) {
            this.del = del;
        }
    }
}
