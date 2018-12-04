package com.wynne.knowledge.main.design;

import android.view.View;
import android.widget.EditText;

import com.wynne.knowledge.main.R;
import com.wynne.knowledge.base.base.BaseActivity;

/**
 * @author Wynne
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText etAccout, etPassword;

    @Override
    public void initView() {
        etAccout = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_edit).setOnClickListener(this);


    }

    @Override
    public int getLayoutId() {
        return R.layout.login_activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (etAccout.getText().toString().equals("123") && etPassword.getText().toString().equals("123")) {

//                    cloneModel();
                    login();
                }
                break;
            case R.id.btn_edit:
                User user = LoginSession.newInstance().getLoginUser();
                user.address = new Address("长沙市", "芙蓉区", "f");
                user.toString();

                LoginSession.newInstance().getLoginUser().toString();
                break;
            default:
                break;
        }
    }

    public void cloneModel() {
        User user = new User();
        user.age = 23;
        user.name = "Wynne";
        user.phoneNum = "123456";
        user.address = new Address("杭州市", "余杭区", "翡翠城");

        user.toString();
        LoginSession.newInstance().setLoginUser(user);
    }

    private void login() {
        LoginContext.getLoginContext().setState(new LoginState());
        finish();
    }
}
