package com.example.brainking.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MathDetailModel {

    @SerializedName("msg")
    private String msg;
    @SerializedName("code")
    private Integer code;
    @SerializedName("data")
    private DataDTO data;

    @Override
    public String toString() {
        return "MathDetailModel{" +
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
        @SerializedName("answer")
        private AnswerDTO answer;
        @SerializedName("analysis")
        private String analysis;
        @SerializedName("type")
        private Integer type;

        @Override
        public String toString() {
            return "DataDTO{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", answer=" + answer +
                    ", analysis='" + analysis + '\'' +
                    ", type=" + type +
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

        public AnswerDTO getAnswer() {
            return answer;
        }

        public void setAnswer(AnswerDTO answer) {
            this.answer = answer;
        }

        public String getAnalysis() {
            return analysis;
        }

        public void setAnalysis(String analysis) {
            this.analysis = analysis;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public static class AnswerDTO {
            @SerializedName("answer")
            private String answer;
            @SerializedName("selected")
            private List<SelectedDTO> selected;

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }

            public List<SelectedDTO> getSelected() {
                return selected;
            }

            public void setSelected(List<SelectedDTO> selected) {
                this.selected = selected;
            }

            public static class SelectedDTO {
                @SerializedName("A")
                private String A;
                @SerializedName("B")
                private String B;
                @SerializedName("C")
                private String C;
                @SerializedName("D")
                private String D;

                public String getA() {
                    return A;
                }

                public void setA(String A) {
                    this.A = A;
                }

                public String getB() {
                    return B;
                }

                public void setB(String B) {
                    this.B = B;
                }

                public String getC() {
                    return C;
                }

                public void setC(String C) {
                    this.C = C;
                }

                public String getD() {
                    return D;
                }

                public void setD(String D) {
                    this.D = D;
                }
            }
        }
    }
}
