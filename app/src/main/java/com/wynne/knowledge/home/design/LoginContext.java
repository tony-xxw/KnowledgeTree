package com.wynne.knowledge.home.design;

import android.content.Context;

public class LoginContext {
    private UserState mState = new LogoutState();

    private static LoginContext sLoginContext = new LoginContext();

    private LoginContext() {

    }

    public static LoginContext getLoginContext() {
        return sLoginContext;
    }

    public void setState(UserState state) {
        this.mState = state;
    }

    public void forWard(Context context) {
        mState.forward(context);
    }

    public void comment(Context context) {
        mState.comment(context);
    }
}
