package com.dxl.appganz.presenter;

import com.dxl.appganz.interf.IHomePresenter;
import com.dxl.appganz.interf.IHomeView;
import com.dxl.appganz.model.CategoryResult;
import com.dxl.appganz.util.NetUtil;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by dxl on 2018/9/26 22:50.
 */
public class HomePresenter implements IHomePresenter {

    Subscription mSubscription;

    public HomePresenter(IHomeView IHomeView) {
        mIHomeView = IHomeView;
    }

    IHomeView mIHomeView;

    @Override
    public void getBannerData() {
        mSubscription = NetUtil.getGankApi().getCategoryResult("福利", 10, 1)
                .flatMap(new Func1<CategoryResult, Observable<CategoryResult.ResultsBean>>() {
                    @Override
                    public Observable<CategoryResult.ResultsBean> call(CategoryResult categoryResult) {
                        return Observable.from(categoryResult.results);
                    }
                })
                .map(new Func1<CategoryResult.ResultsBean, String>() {
                    @Override
                    public String call(CategoryResult.ResultsBean resultsBean) {
                        return resultsBean.url;
                    }
                }).toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mIHomeView.loadBannerFail(e.getMessage());
                    }

                    @Override
                    public void onNext(List<String> strings) {
                        mIHomeView.setBanner(strings);
                    }
                });
    }

    @Override
    public void subscribe() {
        getBannerData();
    }

    @Override
    public void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
    }
}
