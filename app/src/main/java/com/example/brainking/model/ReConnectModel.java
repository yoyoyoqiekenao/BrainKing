package com.example.brainking.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReConnectModel {
    @SerializedName("msg")
    private String msg;
    @SerializedName("code")
    private Integer code;
    @SerializedName("data")
    private DatoDTO data;

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

    public DatoDTO getData() {
        return data;
    }

    public void setData(DatoDTO data) {
        this.data = data;
    }

    public static class DatoDTO {
        @SerializedName("players")
        List<RoomPlayer> players;
        /**
         * 房间号
         */
        @SerializedName("roomId")
        private String roomId;
        /**
         * 题目信息
         */
        @SerializedName("subject")
        private Subject subject;
        /**
         * 当前分数
         */
        @SerializedName("myScore")
        private int myScore;
        /**
         * 当前是第几题
         */
        @SerializedName("currentIndex")
        private int currentIndex;

        public List<RoomPlayer> getPlayers() {
            return players;
        }

        public void setPlayers(List<RoomPlayer> players) {
            this.players = players;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public Subject getSubject() {
            return subject;
        }

        public void setSubject(Subject subject) {
            this.subject = subject;
        }

        public int getMyScore() {
            return myScore;
        }

        public void setMyScore(int myScore) {
            this.myScore = myScore;
        }

        public int getCurrentIndex() {
            return currentIndex;
        }

        public void setCurrentIndex(int currentIndex) {
            this.currentIndex = currentIndex;
        }

        public static class RoomPlayer {

            @SerializedName("totalScore")
            private int totalScore;
            @SerializedName("userId")
            private Long userId;
            @SerializedName("nickName")
            private String nickName;
            @SerializedName("avatar")
            private String avatar;

            public int getTotalScore() {
                return totalScore;
            }

            public void setTotalScore(int totalScore) {
                this.totalScore = totalScore;
            }

            public Long getUserId() {
                return userId;
            }

            public void setUserId(Long userId) {
                this.userId = userId;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }

        public static class Subject {
            @SerializedName("id")
            private String id;
            @SerializedName("quType")
            private Integer quType;
            @SerializedName("title")
            private String title;
            @SerializedName("option")
            private List<SubjectOption> option;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public Integer getQuType() {
                return quType;
            }

            public void setQuType(Integer quType) {
                this.quType = quType;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<SubjectOption> getOption() {
                return option;
            }

            public void setOption(List<SubjectOption> option) {
                this.option = option;
            }

            public static class SubjectOption {
                @SerializedName("id")
                private String id;
                @SerializedName("content")
                private String content;
                @SerializedName("isRight")
                private Boolean isRight;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public Boolean getRight() {
                    return isRight;
                }

                public void setRight(Boolean right) {
                    isRight = right;
                }
            }
        }
    }
}
