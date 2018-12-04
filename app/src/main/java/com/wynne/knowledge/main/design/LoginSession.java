package com.wynne.knowledge.main.design;

public class LoginSession {
    static LoginSession sLoginSession = null;

    private User loginUser;

    private LoginSession() {

    }

    public static LoginSession newInstance() {
        if (sLoginSession == null) {
            sLoginSession = new LoginSession();
        }
        return sLoginSession;
    }

    public void setLoginUser(User user) {
        loginUser = user;
    }

    public User getLoginUser() {
        return loginUser.clone();
    }
}
