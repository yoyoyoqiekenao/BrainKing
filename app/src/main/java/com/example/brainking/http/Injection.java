package com.example.brainking.http;

/**
 * @author : 徐无敌
 * date   : 2021/5/616:20
 * desc   :注入全局的数据仓库
 */
public class Injection {
    public static LoginRepository getLoginRepository() {
        //网络API服务
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);

        //网络数据源
        HttpDataSource httpDataSource = HttpDataSourceImpl.getInstance(apiService);

        return LoginRepository.getInstance(httpDataSource);
    }


}
