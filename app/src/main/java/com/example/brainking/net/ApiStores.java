package com.example.brainking.net;

import com.example.brainking.json.CollectJson;
import com.example.brainking.json.CreateBattleRoomJson;
import com.example.brainking.json.DeleteFriendJson;
import com.example.brainking.json.Friend_Pass_refuse_Json;
import com.example.brainking.json.LoginJson;
import com.example.brainking.json.MatchAnswerJson;
import com.example.brainking.json.MessageReadJson;
import com.example.brainking.json.QuestionJson;
import com.example.brainking.json.SendMsgJson;
import com.example.brainking.json.UpdateUserInfoJson;
import com.example.brainking.json.VerCodeJson;
import com.example.brainking.model.BattleListModel;
import com.example.brainking.model.BattleNormalModel;
import com.example.brainking.model.CancelRoomModel;
import com.example.brainking.model.CollectDetailModel;
import com.example.brainking.model.CollectListModel;
import com.example.brainking.model.CollectModel;
import com.example.brainking.model.CreateRoomModel;
import com.example.brainking.model.DelAllHistoryModel;
import com.example.brainking.model.DeleteFriendModel;
import com.example.brainking.model.FriendListModel;
import com.example.brainking.model.Friend_Pass_refuse_Model;
import com.example.brainking.model.JoinRoomModel;
import com.example.brainking.model.LearnListModel;
import com.example.brainking.model.LoginModel;
import com.example.brainking.model.LoginOutModel;
import com.example.brainking.model.MatchAnswerModel;
import com.example.brainking.model.MatchScoreModel;
import com.example.brainking.model.MatchStartModel;
import com.example.brainking.model.MathDetailModel;
import com.example.brainking.model.MessageListModel;
import com.example.brainking.model.MessageReadModel;
import com.example.brainking.model.NewDetailModel;
import com.example.brainking.model.PoemListModel;
import com.example.brainking.model.PoemsDetailModel;
import com.example.brainking.model.QuestionModel;
import com.example.brainking.model.ReConnectModel;
import com.example.brainking.model.SearchHistoryModel;
import com.example.brainking.model.SearchModel;
import com.example.brainking.model.SearchPoemDetailModel;
import com.example.brainking.model.SendMsgModel;
import com.example.brainking.model.UpdateUserInfoModel;
import com.example.brainking.model.UploadModel;
import com.example.brainking.model.UserInfoModel;
import com.example.brainking.model.VerCodeModel;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * @author : ?????????
 * date   : 2021/5/1817:06
 * desc   :
 */
public interface ApiStores {

    //public static final String BASE_URL = "http://42.192.234.149:8080/";
    public static final String BASE_URL = "https://www.wdsd66.cn/api/";
    //????????????
    //public static final String BASE_URL = "http://192.168.1.107:8080/";
    //public static final String BASE_URL = "http://devojiang.kmdns.net:8081/";


    /**
     * ???????????????
     *
     * @param
     * @return
     */

    @POST("social/sendSms")
    Observable<VerCodeModel> getVerCode(@Body VerCodeJson json);

    /**
     * ????????????
     *
     * @param json
     * @return
     */
    @POST("social/login/mobile")
    Observable<LoginModel> goLogin(@Body LoginJson json);

    /**
     * ????????????????????????
     *
     * @return
     */
    @GET("learn/category/list?pid=1")
    Observable<LearnListModel> getLearnList_math();

    /**
     * ??????????????????
     *
     * @return
     */
    @GET("learn/category/list?pid=2")
    Observable<LearnListModel> getLearnList_poems();

    /* *//**
     * ??????????????????
     *
     * @param pid
     * @return
     *//*
    @GET("learn/poetry/learn")
    Observable<PoemsDetailModel> getPoemsDetail(@Query("pid") int pid);*/

    /**
     * ????????????????????????
     *
     * @return
     */
    @GET("learn/math/learn")
    Observable<MathDetailModel> getMathDetail(@Query("pid") int pid);

    /**
     * ??????????????????
     *
     * @return
     */
    @GET("f/user/getUserInfo")
    Observable<UserInfoModel> getUserInfo();

    /**
     * ????????????
     *
     * @param key
     * @param pageNum
     * @return
     */
    @GET("learn/home/search")
    Observable<SearchModel> searchPoems(@Query("key") String key, @Query("pageNum") int pageNum);

    /**
     * ??????????????????
     *
     * @param id
     * @return
     */
    @GET("learn/home/search/details")
    Observable<SearchPoemDetailModel> getSearchPoemDetail(@Query("id") int id);

    /**
     * ????????????????????????
     *
     * @param pageNum
     * @return
     */
    @GET("message/list")
    Observable<MessageListModel> getMessageList(@Query("pageNum") int pageNum);

    /**
     * ????????????????????????
     *
     * @param pageNum
     * @param toId
     * @return
     */
    @GET("message/details")
    Observable<NewDetailModel> getNewDetail(@Query("pageNum") int pageNum, @Query("toId") int toId);

    /**
     * ????????????
     *
     * @param json
     * @return
     */
    @POST("message/send")
    Observable<SendMsgModel> sendMsg(@Body SendMsgJson json);

    /**
     * ????????????
     *
     * @param json
     * @return
     */
    @POST("collect")
    Observable<CollectModel> collectPoem(@Body CollectJson json);


    /**
     * ????????????(??????????????????)
     *
     * @return
     */
    @GET("match/start")
    Observable<MatchStartModel> matchStart(@Query("battleType") String battleType);


    /**
     * ??????
     *
     * @param pageNum
     * @return
     */
    @GET("match/ready")
    Observable<MatchStartModel> matchReady(@Query("roomId") String pageNum);


    /**
     * ????????????
     *
     * @param roomId
     * @return
     */
    @GET("match/exit")
    Observable<MatchStartModel> matchExit(@Query("roomId") String roomId);

    /**
     * ??????
     *
     * @param json
     * @return
     */
    @POST("match/answer")
    Observable<MatchAnswerModel> matchAnswer(@Body MatchAnswerJson json);

    /**
     * ??????????????????
     */
    @GET("match/battle/list")
    Observable<BattleListModel> getBattleList();

    /**
     * ??????????????????
     *
     * @param json
     * @return
     */
    @POST("match/create")
    Observable<CreateRoomModel> createBattleRoom(@Body CreateBattleRoomJson json);

    /**
     * ??????????????????
     *
     * @return
     */
    @GET("match/multi/ready")
    Observable<CreateRoomModel> multiReady(@Query("roomId") String roomId);

    /**
     * ??????????????????
     *
     * @param roomId
     * @return
     */
    @GET("match/join")
    Observable<JoinRoomModel> joinRoom(@Query("roomId") String roomId);

    /**
     * ????????????
     */
    @GET("match/cancelRoom")
    Observable<CancelRoomModel> cancelRoom(@Query("roomId") String roomId);

    /**
     * ????????????
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GET("friend/list")
    Observable<FriendListModel> getFriendList(@Query("pageNum") String pageNum, @Query("pageSize") String pageSize);

    /**
     * ??????????????????
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GET("friend/checkApply")
    Observable<FriendListModel> getFriendApplyList(@Query("pageNum") String pageNum, @Query("pageSize") String pageSize);

    /**
     * ????????????
     *
     * @return
     */
    @POST("friend/passAdd")
    Observable<Friend_Pass_refuse_Model> passAdd(@Body Friend_Pass_refuse_Json json);

    /**
     * ????????????
     *
     * @return
     */
    @POST("friend/refuseApply")
    Observable<Friend_Pass_refuse_Model> passRefuse(@Body Friend_Pass_refuse_Json json);

    /**
     * ??????????????????
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GET("collect/list")
    Observable<CollectListModel> getCollectList(@Query("pageNum") String pageNum, @Query("pageSize") String pageSize);

    /**
     * ??????????????????
     *
     * @param subjectId
     * @param type
     * @return
     */
    @GET("collect/details")
    Observable<CollectDetailModel> getCollectDetail(@Query("subjectId") String subjectId, @Query("type") String type);

    /**
     * ????????????
     *
     * @return
     */
    @POST("logout")
    Observable<LoginOutModel> logOut();

    /**
     * ????????????
     *
     * @param json
     * @return
     */
    @POST("friend/deleteFriend")
    Observable<DeleteFriendModel> deleteFriend(@Body DeleteFriendJson json);

    /**
     * ????????????
     *
     * @param json
     * @return
     */
    @POST("message/read")
    Observable<MessageReadModel> messageRead(@Body MessageReadJson json);

    /**
     * ????????????(???)
     *
     * @param pageNum
     * @param pageSize
     * @param pid
     * @return
     */
    @GET("learn/poetry/list")
    Observable<PoemListModel> getPoemList(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize, @Query("pid") int pid);

    /**
     * ??????????????????
     *
     * @param id
     * @return
     */
    @GET("learn/poetry/details")
    Observable<PoemsDetailModel> getPoemsDetail(@Query("id") int id);

    /**
     * ??????
     *
     * @param json
     * @return
     */
    @POST("question/userQuestion")
    Observable<QuestionModel> putQuestion(@Body QuestionJson json);

    /**
     * ??????????????????
     *
     * @param json
     * @return
     */
    @POST("f/user/updateUserInfo")
    Observable<UpdateUserInfoModel> updateUserInfo(@Body UpdateUserInfoJson json);

    /**
     * ??????????????????
     *
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    @GET("user/history/list")
    Observable<SearchHistoryModel> getSearchHistory(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize, @Query("userId") String userId);

    /**
     * ??????????????????????????????
     *
     * @return
     */
    @GET("user/history/delAll")
    Observable<DelAllHistoryModel> delAllHistory();

    /**
     * ??????File
     *
     * @param
     * @return
     */
    @Multipart
    @POST("common/upload")
    Observable<UploadModel> getFile(@Part MultipartBody.Part file);

    /**
     * ????????????
     *
     * @return
     */
    @GET("match/reconnect")
    Observable<ReConnectModel> reConnect();

    /**
     * ??????????????????????????????
     *
     * @return
     */
    @GET("match/double/battle/init")
    Observable<MatchScoreModel> getMatchScore();

    /**
     * ????????????
     *
     * @return
     */
    @POST("logoff")
    Observable<LoginOutModel> logOff();
}

