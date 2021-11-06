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

        @SerializedName("currentIndex")
        public Integer currentIndex;
        @SerializedName("myScore")
        public Integer myScore;
        @SerializedName("roomId")
        public String roomId;
        @SerializedName("subject")
        public SubjectDTO subject;
        @SerializedName("players")
        public List<PlayersDTO> players;

        public Integer getCurrentIndex() {
            return currentIndex;
        }

        public void setCurrentIndex(Integer currentIndex) {
            this.currentIndex = currentIndex;
        }

        public Integer getMyScore() {
            return myScore;
        }

        public void setMyScore(Integer myScore) {
            this.myScore = myScore;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public SubjectDTO getSubject() {
            return subject;
        }

        public void setSubject(SubjectDTO subject) {
            this.subject = subject;
        }

        public List<PlayersDTO> getPlayers() {
            return players;
        }

        public void setPlayers(List<PlayersDTO> players) {
            this.players = players;
        }

        public static class SubjectDTO {
            @SerializedName("id")
            public String id;
            @SerializedName("quType")
            public Integer quType;
            @SerializedName("title")
            public String title;
            @SerializedName("type")
            public String type;
            @SerializedName("option")
            public List<OptionDTO> option;

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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<OptionDTO> getOption() {
                return option;
            }

            public void setOption(List<OptionDTO> option) {
                this.option = option;
            }

            public static class OptionDTO {
                @SerializedName("content")
                public String content;
                @SerializedName("id")
                public String id;
                @SerializedName("isRight")
                public Boolean isRight;

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public Boolean getRight() {
                    return isRight;
                }

                public void setRight(Boolean right) {
                    isRight = right;
                }
            }
        }

        public static class PlayersDTO {
            @SerializedName("avatar")
            public String avatar;
            @SerializedName("diff")
            public Integer diff;
            @SerializedName("fees")
            public Integer fees;
            @SerializedName("lastAddTime")
            public Integer lastAddTime;
            @SerializedName("lastTime")
            public Long lastTime;
            @SerializedName("nickName")
            public String nickName;
            @SerializedName("openid")
            public String openid;
            @SerializedName("priority")
            public Integer priority;
            @SerializedName("ready")
            public Boolean ready;
            @SerializedName("roomId")
            public Integer roomId;
            @SerializedName("score")
            public Integer score;
            @SerializedName("totalScore")
            public Integer totalScore;
            @SerializedName("userId")
            public Long userId;
            @SerializedName("waitTime")
            public Integer waitTime;
            @SerializedName("email")
            public String email;
            @SerializedName("phonenumber")
            public String phonenumber;
            @SerializedName("remark")
            public String remark;
            @SerializedName("sex")
            public String sex;
            @SerializedName("userName")
            public String userName;
            @SerializedName("userType")
            public Integer userType;
            @SerializedName("answers")
            public List<AnswersDTO> answers;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public Integer getDiff() {
                return diff;
            }

            public void setDiff(Integer diff) {
                this.diff = diff;
            }

            public Integer getFees() {
                return fees;
            }

            public void setFees(Integer fees) {
                this.fees = fees;
            }

            public Integer getLastAddTime() {
                return lastAddTime;
            }

            public void setLastAddTime(Integer lastAddTime) {
                this.lastAddTime = lastAddTime;
            }

            public Long getLastTime() {
                return lastTime;
            }

            public void setLastTime(Long lastTime) {
                this.lastTime = lastTime;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public Integer getPriority() {
                return priority;
            }

            public void setPriority(Integer priority) {
                this.priority = priority;
            }

            public Boolean getReady() {
                return ready;
            }

            public void setReady(Boolean ready) {
                this.ready = ready;
            }

            public Integer getRoomId() {
                return roomId;
            }

            public void setRoomId(Integer roomId) {
                this.roomId = roomId;
            }

            public Integer getScore() {
                return score;
            }

            public void setScore(Integer score) {
                this.score = score;
            }

            public Integer getTotalScore() {
                return totalScore;
            }

            public void setTotalScore(Integer totalScore) {
                this.totalScore = totalScore;
            }

            public Long getUserId() {
                return userId;
            }

            public void setUserId(Long userId) {
                this.userId = userId;
            }

            public Integer getWaitTime() {
                return waitTime;
            }

            public void setWaitTime(Integer waitTime) {
                this.waitTime = waitTime;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getPhonenumber() {
                return phonenumber;
            }

            public void setPhonenumber(String phonenumber) {
                this.phonenumber = phonenumber;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public Integer getUserType() {
                return userType;
            }

            public void setUserType(Integer userType) {
                this.userType = userType;
            }

            public List<AnswersDTO> getAnswers() {
                return answers;
            }

            public void setAnswers(List<AnswersDTO> answers) {
                this.answers = answers;
            }

            public static class AnswersDTO {
                @SerializedName("canAnswer")
                public Boolean canAnswer;
                @SerializedName("choiceFlag")
                public Integer choiceFlag;
                @SerializedName("endTimestamp")
                public Long endTimestamp;
                @SerializedName("quType")
                public Integer quType;
                @SerializedName("right")
                public Boolean right;
                @SerializedName("roomId")
                public Long roomId;
                @SerializedName("score")
                public Integer score;
                @SerializedName("startTimestamp")
                public Long startTimestamp;
                @SerializedName("subjectId")
                public String subjectId;
                @SerializedName("time")
                public Integer time;
                @SerializedName("userId")
                public Long userId;
                @SerializedName("userScore")
                public Integer userScore;
                @SerializedName("answer")
                public List<String> answer;
                @SerializedName("choice")
                public List<String> choice;

                public Boolean getCanAnswer() {
                    return canAnswer;
                }

                public void setCanAnswer(Boolean canAnswer) {
                    this.canAnswer = canAnswer;
                }

                public Integer getChoiceFlag() {
                    return choiceFlag;
                }

                public void setChoiceFlag(Integer choiceFlag) {
                    this.choiceFlag = choiceFlag;
                }

                public Long getEndTimestamp() {
                    return endTimestamp;
                }

                public void setEndTimestamp(Long endTimestamp) {
                    this.endTimestamp = endTimestamp;
                }

                public Integer getQuType() {
                    return quType;
                }

                public void setQuType(Integer quType) {
                    this.quType = quType;
                }

                public Boolean getRight() {
                    return right;
                }

                public void setRight(Boolean right) {
                    this.right = right;
                }

                public Long getRoomId() {
                    return roomId;
                }

                public void setRoomId(Long roomId) {
                    this.roomId = roomId;
                }

                public Integer getScore() {
                    return score;
                }

                public void setScore(Integer score) {
                    this.score = score;
                }

                public Long getStartTimestamp() {
                    return startTimestamp;
                }

                public void setStartTimestamp(Long startTimestamp) {
                    this.startTimestamp = startTimestamp;
                }

                public String getSubjectId() {
                    return subjectId;
                }

                public void setSubjectId(String subjectId) {
                    this.subjectId = subjectId;
                }

                public Integer getTime() {
                    return time;
                }

                public void setTime(Integer time) {
                    this.time = time;
                }

                public Long getUserId() {
                    return userId;
                }

                public void setUserId(Long userId) {
                    this.userId = userId;
                }

                public Integer getUserScore() {
                    return userScore;
                }

                public void setUserScore(Integer userScore) {
                    this.userScore = userScore;
                }

                public List<String> getAnswer() {
                    return answer;
                }

                public void setAnswer(List<String> answer) {
                    this.answer = answer;
                }

                public List<String> getChoice() {
                    return choice;
                }

                public void setChoice(List<String> choice) {
                    this.choice = choice;
                }
            }
        }
    }
}
