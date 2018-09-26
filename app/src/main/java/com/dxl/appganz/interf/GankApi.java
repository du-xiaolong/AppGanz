package com.dxl.appganz.interf;

import com.dxl.appganz.model.CategoryResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by dxl on 2018/9/26 22:50.
 */
public interface GankApi {
    @GET("{type}/{count}/{page}")
    Observable<CategoryResult> getCategoryResult(@Path("type") String type, @Path("count") int count, @Path("page") int page);

}
