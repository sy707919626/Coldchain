package com.bfby.coldchain.common.rx;


import com.bfby.coldchain.Bean.BaseBean;
import com.bfby.coldchain.common.exception.ApiException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @description:统一封装结果预处理
 */
public class RxHttpResponseCompatTests {

    public static <T> ObservableTransformer<BaseBean<T>, T> compatResult() {
        return new ObservableTransformer<BaseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseBean<T>> upstream) {
                return upstream.flatMap(new Function<BaseBean<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(final BaseBean<T> baseBean) throws Exception {

                        if (baseBean.getCode()== 0 ) {

                            return Observable.just(baseBean.getData());
                        } else {
                            String msg = baseBean.getMessage();
                            return Observable.error(new ApiException(Integer.valueOf(baseBean.getCode()), msg));
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
            }
        };
    }
}








