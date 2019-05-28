package com.wynne.knowledge.home.design;

import android.content.Context;
import android.widget.Toast;

public class LoginState implements UserState {
    @Override
    public void forward(Context context) {
        Toast.makeText(context, "转发微博", Toast.LENGTH_LONG).show();
    }

    @Override
    public void comment(Context context) {
        Toast.makeText(context, "评论微博", Toast.LENGTH_LONG).show();
    }


}
