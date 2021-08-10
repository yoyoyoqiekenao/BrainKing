package com.example.brainking.net;

import com.example.brainking.json.CollectJson;
import com.example.brainking.json.CreateBattleRoomJson;
import com.example.brainking.json.LoginJson;
import com.example.brainking.json.MatchAnswerJson;
import com.example.brainking.json.SendMsgJson;
import com.example.brainking.json.VerCodeJson;
import com.example.brainking.model.BattleListModel;
import com.example.brainking.model.CollectModel;
import com.example.brainking.model.CreateRoomModel;
import com.example.brainking.model.LearnListModel;
import com.example.brainking.model.LoginModel;
import com.example.brainking.model.MatchAnswerModel;
import com.example.brainking.model.MatchStartModel;
import com.example.brainking.model.MathDetailModel;
import com.example.brainking.model.MessageListModel;
import com.example.brainking.model.NewDetailModel;
import com.example.brainking.model.PoemsDetailModel;
import com.example.brainking.model.SearchModel;
import com.example.brainking.model.SearchPoemDetailModel;
import com.example.brainking.model.SendMsgModel;
import com.example.brainking.model.UserInfoModel;
import com.example.brainking.model.VerCodeModel;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author : 徐无敌
 * date   : 2021/5/1817:06
 * desc   :
 */
public interface ApiStores {

    public static final String BASE_URL = "http://42.192.234.149:8080/";
    //测试环境
    //public static final String BASE_URL = "http://devojiang.kmdns.net:8081/";


    /**
     * 获取验证码
     *
     * @param
     * @return
     */

    @POST("social/sendSms")
    Observable<VerCodeModel> getVerCode(@Body VerCodeJson json);

    /**
     * 登录接口
     *
     * @param json
     * @return
     */
    @POST("social/login/mobile")
    Observable<LoginModel> goLogin(@Body LoginJson json);

    /**
     * 获取小学数学列表
     *
     * @return
     */
    @GET("learn/category/list?pid=1")
    Observable<LearnListModel> getLearnList_math();

    /**
     * 获取诗词列表
     *
     * @return
     */
    @GET("learn/category/list?pid=2")
    Observable<LearnListModel> getLearnList_poems();

    /**
     * 获取诗词详情
     *
     * @param pid
     * @return
     */
    @GET("learn/poetry/learn")
    Observable<PoemsDetailModel> getPoemsDetail(@Query("pid") int pid);

    /**
     * 获取小学数学详情
     *
     * @return
     */
    @GET("learn/math/learn?pid=3")
    Observable<MathDetailModel> getMathDetail();

    /**
     * 获取用户信息
     *
     * @return
     */
    @GET("f/user/getUserInfo")
    Observable<UserInfoModel> getUserInfo();

    /**
     * 搜索结果
     *
     * @param key
     * @param pageNum
     * @return
     */
    @GET("learn/home/search")
    Observable<SearchModel> searchPoems(@Query("key") String key, @Query("pageNum") int pageNum);

    /**
     * 搜索结果详情
     *
     * @param id
     * @return
     */
    @GET("learn/home/search/details")
    Observable<SearchPoemDetailModel> getSearchPoemDetail(@Query("id") int id);

    /**
     * 获取好友消息列表
     *
     * @param pageNum
     * @return
     */
    @GET("message/list")
    Observable<MessageListModel> getMessageList(@Query("pageNum") int pageNum);

    /**
     * 获取好友聊天记录
     *
     * @param pageNum
     * @param toId
     * @return
     */
    @GET("message/details")
    Observable<NewDetailModel> getNewDetail(@Query("pageNum") int pageNum, @Query("toId") int toId);

    /**
     * 发送消息
     *
     * @param json
     * @return
     */
    @POST("message/send")
    Observable<SendMsgModel> sendMsg(@Body SendMsgJson json);

    /**
     * 收藏诗词
     *
     * @param json
     * @return
     */
    @POST("learn/collect")
    Observable<CollectModel> collectPoem(@Body CollectJson json);


    /**
     * 开始匹配(自动创建房间)
     *
     * @return
     */
    @GET("match/start")
    Observable<MatchStartModel> matchStart();


    /**
     * 准备
     *
     * @param pageNum
     * @return
     */
    @GET("match/ready")
    Observable<MatchStartModel> matchReady(@Query("roomId") String pageNum);


    /**
     * 退出房间
     *
     * @param roomId
     * @return
     */
    @GET("match/exit")
    Observable<MatchStartModel> matchExit(@Query("roomId") String roomId);

    /**
     * 答题
     *
     * @param json
     * @return
     */
    @POST("match/answer")
    Observable<MatchAnswerModel> matchAnswer(@Body MatchAnswerJson json);

    /**
     * 获取房间列表
     */
    @GET("match/battle/list")
    Observable<BattleListModel> getBattleList();

    /**
     * 创建好友房间
     *
     * @param json
     * @return
     */
    @POST("match/create")
    Observable<CreateRoomModel> createBattleRoom(@Body CreateBattleRoomJson json);
}

