package com.wynne.knowledge.main.retrofit;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.base.model.BaseListModel;
import com.wynne.knowledge.base.model.BaseModel;
import com.wynne.knowledge.main.R;
import com.wynne.knowledge.main.model.ArticleModel;
import com.wynne.knowledge.main.net.RetrofitService;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author Wynne
 */
public class RetrofitActivity extends BaseActivity {
    @Override
    public void initView() {

        requestNet(RetrofitService.getInstance().obtainMains(0), new Observer<BaseModel<BaseListModel<ArticleModel>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseModel<BaseListModel<ArticleModel>> o) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.retrofit_activity;
    }
}
