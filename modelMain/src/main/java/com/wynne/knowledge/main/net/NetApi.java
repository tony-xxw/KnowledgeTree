package com.wynne.knowledge.main.net;




import com.wynne.knowledge.base.model.BaseListModel;
import com.wynne.knowledge.base.model.BaseModel;
import com.wynne.knowledge.main.model.ArticleModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 接口封装类
 *
 * @author Wynne
 */
public interface NetApi {


    /***
     * 首页列表
     * @return
     */
    @GET("article/list/{page}/json")
    Observable<BaseModel<BaseListModel<ArticleModel>>> obtainMains(@Path("page") int page);
}
