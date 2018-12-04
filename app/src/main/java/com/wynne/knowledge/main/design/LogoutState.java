package com.wynne.knowledge.main.design;

import android.content.Context;
import android.content.Intent;

public class LogoutState implements UserState {
    @Override
    public void forward(Context context) {
        gotoLoginActivity(context);
    }

    @Override
    public void comment(Context context) {
        gotoLoginActivity(context);
    }


    private void gotoLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

}
