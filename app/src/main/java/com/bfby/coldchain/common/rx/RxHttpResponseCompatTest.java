package com.bfby.coldchain.common.rx;

import com.bfby.coldchain.Bean.BaseBean;
import com.bfby.coldchain.Bean.BaseBeans;
import com.bfby.coldchain.common.exception.ApiException;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @description:统一封装结果预处理
 */
public class RxHttpResponseCompatTest {

    public static <T> ObservableTransformer<BaseBeans<T>, List<T>> compatResult() {
        return new ObservableTransformer<BaseBeans<T>, List<T>>() {
            @Override
            public ObservableSource<List<T>> apply(Observable<BaseBeans<T>> upstream) {
                return upstream.flatMap(new Function<BaseBeans<T>, ObservableSource<List<T>>>() {
                    @Override
                    public ObservableSource<List<T>> apply(final BaseBeans<T> baseBean) throws Exception {

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








