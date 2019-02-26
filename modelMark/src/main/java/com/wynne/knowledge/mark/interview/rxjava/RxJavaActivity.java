package com.wynne.knowledge.mark.interview.rxjava;

import android.util.Log;
import android.view.View;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.base.utils.LogUtil;
import com.wynne.knowledge.mark.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * 线程切换源码分析
 * @author Wynne
 */
public class RxJavaActivity extends BaseActivity {
    @Override
    public void initView() {

    }


    @OnClick({R.id.btn_one, R.id.btn_two, R.id.btn_three,
            R.id.btn_four, R.id.btn_five, R.id.btn_six, R.id.btn_sevent})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.btn_one) {
            stepOne();
        } else if (view.getId() == R.id.btn_two) {
            stepTwo();
        } else if (view.getId() == R.id.btn_three) {
            stepThree();
        } else if (view.getId() == R.id.btn_four) {
            stepFour();
        } else if (view.getId() == R.id.btn_five) {
            stepFive();
        } else if (view.getId() == R.id.btn_six) {
            stepSix();
        } else if (view.getId() == R.id.btn_sevent) {
            requestUp();
        }
    }

    private void requestUp() {
        if (mSubscription != null) {
            mSubscription.request(1);
        }
    }

    private Subscription mSubscription;

    private void stepSix() {
        Flowable<Integer> flowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io());
        flowable.observeOn(Schedulers.newThread()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                mSubscription = s;
            }

            @Override
            public void onNext(Integer o) {
                LogUtil.d("onNext " + o.toString());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    private void stepFive() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 10; i++) {
                    LogUtil.d("subscribe: " + i);
                    emitter.onNext(i);
                }
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Thread.sleep(2000);
                LogUtil.d("accept : " + integer.toString());
            }
        });
    }


    private void stepThree() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);

            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                LogUtil.d("flatMap: " + integer.toString());
                if (integer == 1) {
                    return Observable.fromArray("第一个");
                } else if (integer == 2) {
                    return Observable.fromArray("第二个");
                } else {
                    return Observable.fromArray("第三个");
                }
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                LogUtil.d("consumer : " + s);
            }
        });
    }

    private void stepFour() {
        Observable observableMain = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(2);
                emitter.onNext(4);
                emitter.onNext(6);
                emitter.onNext(8);
            }
        });

        Observable observableOther = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(2);
                emitter.onNext(4);
                emitter.onNext(6);
            }
        });

        Observable.zip(observableMain, observableOther, new BiFunction<Integer, Integer, String>() {
            @Override
            public String apply(Integer integer, Integer integer2) throws Exception {
                return String.valueOf(integer + integer2);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String string) throws Exception {
                LogUtil.d(string);
            }
        });
    }

    private void stepTwo() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d("XXW", "subscribe");
                emitter.onNext(1);
                emitter.onComplete();
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return integer.toString();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                LogUtil.d("onNext " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void stepOne() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                LogUtil.d("observable: " + Thread.currentThread().getName());
                emitter.onNext("Android开发");
                emitter.onNext("Android开发");
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtil.d("onSubscribe");
                        LogUtil.d("observer: " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String s) {
                        LogUtil.d("onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.d("onError");
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.d("onComplete");
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.rxjava_activity;
    }


}
