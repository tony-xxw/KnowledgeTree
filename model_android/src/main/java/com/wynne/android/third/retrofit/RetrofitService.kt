package com.wynne.android.third.retrofit

import com.wynne.knowledge.base.base.BaseResp
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("wxarticle/chapters/json/")
    fun obtainDetail(): Observable<BaseResp<List<Article>>>

    @GET("banner/json/")
    fun obtainBanner(str: String): Observable<Response<Banner>>
}