package com.example.brainking.home.mathdetail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brainking.R;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.model.MathDetailModel;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MathDetailActivity extends BrainActivity<MathDetailPresenter> implements MathDetailView, View.OnClickListener {

    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl_judge)
    RelativeLayout rl_judge;
    @BindView(R.id.ll_completion)
    LinearLayout ll_completion;
    @BindView(R.id.ll_multiple)
    LinearLayout ll_multiple;
    @BindView(R.id.ll_single)
    LinearLayout ll_single;

    //填空题view
    @BindView(R.id.tv_0)
    TextView tv0;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.tv_5)
    TextView tv5;
    @BindView(R.id.tv_6)
    TextView tv6;
    @BindView(R.id.tv_7)
    TextView tv7;
    @BindView(R.id.tv_8)
    TextView tv8;
    @BindView(R.id.tv_9)
    TextView tv9;
    @BindView(R.id.tv_point)
    TextView tv_point;
    @BindView(R.id.rl_delete)
    RelativeLayout rl_delete;
    @BindView(R.id.tv_answer_completion)
    TextView tv_answer_completion;


    private List<String> completionList = new ArrayList<>();
    private String answer_complete;
    private boolean isAddPoint = true;


    @Override
    protected MathDetailPresenter createPresenter() {
        return new MathDetailPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathdetail);

        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();


        basePresenter.getMathDetail();
    }

    @Override
    public void getMathDetailSuccess(MathDetailModel model) {
        if (1 == model.getData().getType()) {
            //判断
            showJudgmentView(model);
            Log.d("xuwudi", "数据===" + model.getData().getAnswer().getSelected().get(0).getA());

        } else if (2 == model.getData().getType()) {
            //单选
            Log.d("xuwudi", "数据===" + model.getData().getAnswer().getSelected().get(0).getA());
            showSingleChoose(model);
        } else if (3 == model.getData().getType()) {
            //多选
            Log.d("xuwudi", "数据===" + model.getData().getAnswer().getSelected().get(0).getA());
            showMultipleChoose(model);
        } else if (4 == model.getData().getType()) {
            //填空
            showCompletionView(model);
        }
    }

    private void showMultipleChoose(MathDetailModel model) {
        ll_multiple.setVisibility(View.VISIBLE);
    }

    //展示单选题的界面
    private void showSingleChoose(MathDetailModel model) {
        ll_single.setVisibility(View.VISIBLE);
    }

    //展示填空题的界面
    private void showCompletionView(MathDetailModel model) {
        ll_completion.setVisibility(View.VISIBLE);
        ll_single.setVisibility(View.GONE);
        ll_multiple.setVisibility(View.GONE);
        rl_judge.setVisibility(View.GONE);

        tv0.setOnClickListener(this);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
        tv6.setOnClickListener(this);
        tv7.setOnClickListener(this);
        tv8.setOnClickListener(this);
        tv9.setOnClickListener(this);
        tv_point.setOnClickListener(this);
        rl_delete.setOnClickListener(this);

    }

    @Override
    public void getMathDetailFail(String msg) {

    }

    //判断题
    private void showJudgmentView(MathDetailModel model) {
        rl_judge.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_0) {
            tv_answer_completion.setText("0");
           /* //首位不能是0
            if (completionList.size() > 0 && completionList.get(0).equals("0")) {

            } else {
                completionList.add("0");
            }

            if (completionList.size() == 1) {
                tv_answer_completion.setText(completionList.get(0));
            } else if (completionList.size() > 1) {
                for (int i = 0; i < completionList.size(); i++) {
                    String s = completionList.get(i);
                    answer_complete = answer_complete + s;
                }
                tv_answer_completion.setText(answer_complete + "");
            }*/

        } else if (v.getId() == R.id.tv_1) {
            tv_answer_completion.setText("1");
           /* completionList.add("1");
            if (completionList.size() == 1) {
                tv_answer_completion.setText(completionList.get(0));
            } else if (completionList.size() > 1) {
                for (int i = 0; i < completionList.size(); i++) {
                    String s = completionList.get(i);
                    answer_complete = answer_complete + s;
                }
                tv_answer_completion.setText(answer_complete + "");
            }*/
        } else if (v.getId() == R.id.tv_2) {
            tv_answer_completion.setText("2");
           /* completionList.add("2");
            if (completionList.size() == 1) {
                tv_answer_completion.setText(completionList.get(0));
            } else if (completionList.size() > 1) {
                for (int i = 0; i < completionList.size(); i++) {
                    String s = completionList.get(i);
                    answer_complete = answer_complete + s;
                }
                tv_answer_completion.setText(answer_complete + "");
            }*/
        } else if (v.getId() == R.id.tv_3) {
            tv_answer_completion.setText("3");
            /*completionList.add("3");
            if (completionList.size() == 1) {
                tv_answer_completion.setText(completionList.get(0));
            } else if (completionList.size() > 1) {
                for (int i = 0; i < completionList.size(); i++) {
                    String s = completionList.get(i);
                    answer_complete = answer_complete + s;
                }
                tv_answer_completion.setText(answer_complete + "");
            }*/
        } else if (v.getId() == R.id.tv_4) {
            tv_answer_completion.setText("4");
           /* completionList.add("4");
            if (completionList.size() == 1) {
                tv_answer_completion.setText(completionList.get(0));
            } else if (completionList.size() > 1) {
                for (int i = 0; i < completionList.size(); i++) {
                    String s = completionList.get(i);
                    answer_complete = answer_complete + s;
                }
                tv_answer_completion.setText(answer_complete + "");
            }*/
        } else if (v.getId() == R.id.tv_5) {
            tv_answer_completion.setText("5");
           /* completionList.add("5");
            if (completionList.size() == 1) {
                tv_answer_completion.setText(completionList.get(0));
            } else if (completionList.size() > 1) {
                for (int i = 0; i < completionList.size(); i++) {
                    String s = completionList.get(i);
                    answer_complete = answer_complete + s;
                }
                tv_answer_completion.setText(answer_complete + "");
            }*/
        } else if (v.getId() == R.id.tv_6) {
            tv_answer_completion.setText("6");
           /* completionList.add("6");
            if (completionList.size() == 1) {
                tv_answer_completion.setText(completionList.get(0));
            } else if (completionList.size() > 1) {
                for (int i = 0; i < completionList.size(); i++) {
                    String s = completionList.get(i);
                    answer_complete = answer_complete + s;
                }
                tv_answer_completion.setText(answer_complete + "");
            }*/
        } else if (v.getId() == R.id.tv_7) {
            tv_answer_completion.setText("7");
           /* completionList.add("7");
            if (completionList.size() == 1) {
                tv_answer_completion.setText(completionList.get(0));
            } else if (completionList.size() > 1) {
                for (int i = 0; i < completionList.size(); i++) {
                    String s = completionList.get(i);
                    answer_complete = answer_complete + s;
                }
                tv_answer_completion.setText(answer_complete + "");
            }*/
        } else if (v.getId() == R.id.tv_8) {
            tv_answer_completion.setText("8");
            /*completionList.add("8");
            if (completionList.size() == 1) {
                tv_answer_completion.setText(completionList.get(0));
            } else if (completionList.size() > 1) {
                for (int i = 0; i < completionList.size(); i++) {
                    String s = completionList.get(i);
                    answer_complete = answer_complete + s;
                }
                tv_answer_completion.setText(answer_complete + "");
            }*/
        } else if (v.getId() == R.id.tv_9) {
            tv_answer_completion.setText("9");
            /*completionList.add("9");
            if (completionList.size() == 1) {
                tv_answer_completion.setText(completionList.get(0));
            } else if (completionList.size() > 1) {
                for (int i = 0; i < completionList.size(); i++) {
                    String s = completionList.get(i);
                    answer_complete = answer_complete + s;
                }
                tv_answer_completion.setText(answer_complete + "");
            }*/
        } else if (v.getId() == R.id.tv_point) {
            /*//首位不能是.
            if (completionList.size() == 0) {
                Toast.makeText(this, "首位不可以是.", Toast.LENGTH_SHORT);
                return;
            }
            completionList.add(".");

            for (int i = 0; i < completionList.size(); i++) {
                String s = completionList.get(i);
                answer_complete = answer_complete + s;
            }
            tv_answer_completion.setText(answer_complete + "");*/

        } else if (v.getId() == R.id.rl_delete) {
            tv_answer_completion.setText("");
           /* if (completionList.size() == 0) {
                Toast.makeText(this, "不能再删除了", Toast.LENGTH_SHORT).show();
                return;
            }
            completionList.clear();
            tv_answer_completion.setText("");*/
        }
    }
}
