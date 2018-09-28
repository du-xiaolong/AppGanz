package com.dxl.appganz.presenter;

import com.dxl.appganz.interf.IHomeFragmentPresenter;
import com.dxl.appganz.interf.IHomeFragmentView;
import com.dxl.appganz.model.CategoryResult;
import com.dxl.appganz.util.NetUtil;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dxl on 2018/9/28 22:13.
 */
public class HomeFragmentPresenter implements IHomeFragmentPresenter {

    private Subscription mSubscription;

    public HomeFragmentPresenter(IHomeFragmentView homeFragmentView) {
        mHomeFragmentView = homeFragmentView;
    }

    IHomeFragmentView mHomeFragmentView;

    @Override
    public void getCategoryItemsData() {
        mSubscription = NetUtil.getGankApi().getCategoryResult("Android", 10, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CategoryResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mHomeFragmentView.getCategoryItemFail(e.getMessage());
                    }

                    @Override
                    public void onNext(CategoryResult categoryResult) {
                        mHomeFragmentView.setCategoryItems(categoryResult.results);
                    }
                });
    }

    @Override
    public void subscribe() {
        getCategoryItemsData();
    }

    @Override
    public void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
