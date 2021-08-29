package com.example.brainking.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CollectListModel {

    @SerializedName("total")
    public String total;
    @SerializedName("code")
    public Integer code;
    @SerializedName("msg")
    public String msg;
    @SerializedName("rows")
    public List<RowsDTO> rows;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RowsDTO> getRows() {
        return rows;
    }

    public void setRows(List<RowsDTO> rows) {
        this.rows = rows;
    }

    public static class RowsDTO {
        @SerializedName("id")
        public String id;
        @SerializedName("subjectId")
        public String subjectId;
        @SerializedName("title")
        public String title;
        @SerializedName("type")
        public String type;
        @SerializedName("author")
        public String author;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(String subjectId) {
            this.subjectId = subjectId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
    }
}
