package com.example.brainking.entity;

/**
 * @author : 徐无敌
 * date   : 2021/5/1817:11
 * desc   :
 */
public class PwdLoginEntity {

    /**
     * access_token : 5e82b6d1-1a9c-483f-86d9-5951c3834789
     * token_type : bearer
     * refresh_token : 56894d22-8b9b-4546-b8b5-da137d086166
     * expires_in : 2591638
     * scope : app
     * name : 母女俩OK你密集恐惧空间咯咯你咯木空你咯
     * userType : 1
     * makeBy : sunshine-cloud
     * storeId : 26
     * userId : 1880487284586135917
     */

    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private String name;
    private int userType;
    private String makeBy;
    private int storeId;
    private long userId;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getMakeBy() {
        return makeBy;
    }

    public void setMakeBy(String makeBy) {
        this.makeBy = makeBy;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
