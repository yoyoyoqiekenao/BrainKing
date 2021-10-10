package com.example.brainking.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchHistoryModel {
    @SerializedName("total")
    public Integer total;
    @SerializedName("rows")
    public List<RowsDTO> rows;
    @SerializedName("code")
    public Integer code;
    @SerializedName("msg")
    public String msg;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<RowsDTO> getRows() {
        return rows;
    }

    public void setRows(List<RowsDTO> rows) {
        this.rows = rows;
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

    public static class RowsDTO {
        @SerializedName("id")
        public Integer id;
        @SerializedName("userId")
        public Integer userId;
        @SerializedName("searchKey")
        public String searchKey;
        @SerializedName("version")
        public Integer version;
        @SerializedName("del")
        public Integer del;
        @SerializedName("gmtCreate")
        public String gmtCreate;
        @SerializedName("gmtModified")
        public String gmtModified;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getSearchKey() {
            return searchKey;
        }

        public void setSearchKey(String searchKey) {
            this.searchKey = searchKey;
        }

        public Integer getVersion() {
            return version;
        }

        public void setVersion(Integer version) {
            this.version = version;
        }

        public Integer getDel() {
            return del;
        }

        public void setDel(Integer del) {
            this.del = del;
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
    }
}
