package com.wynne.knowledge.tree.custom.rx;

import android.util.Log;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.base.BaseActivity;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author Wynne
 * @date 2018/6/4
 */

public class RxJavaActivity extends BaseActivity {


    @Override
    public void initView() {
        defaultFormat();
        justFormat();
        fromFormat();
    }

    @Override
    public int getLayoutId() {
        return R.layout.rx_study_activity;
    }

    /**
     * fromArray 操作符
     * 与just操作符类似,事件可以超过10个,并且可以传入一个数组
     */
    private void fromFormat() {
        Observable.fromArray(new Integer[]{1, 3, 5, 7, 9})
                .subscribe(observer);


        Observable.fromCallable(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return "callable";
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d("XXW", "Callable  :" + o);
            }
        });
    }

    //Just操作符  事件不能超过10个
    private void justFormat() {
        Observable.just(1, 2, 3, 4, 5)
                .subscribe(observer);

    }

    private void defaultFormat() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(12);
                emitter.onNext(123);
                emitter.onComplete();
            }
        }).subscribe(observer);
    }


    Observer observer = new Observer() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.d("XXW", "onSubscribe  :" + d);
        }

        @Override
        public void onNext(Object o) {
            Log.d("XXW", "onNext  :" + o);
        }

        @Override
        public void onError(Throwable e) {
            Log.d("XXW", "onError  :" + e);
        }

        @Override
        public void onComplete() {
            Log.d("XXW", "onComplete");
        }
    };


}
