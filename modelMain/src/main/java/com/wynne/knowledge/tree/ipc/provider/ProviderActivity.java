package com.wynne.knowledge.tree.ipc.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.aidl.sample.Book;
import com.aidl.sample.User;
import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.base.BaseActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Wynne
 * @date 2018/3/27
 */

public class ProviderActivity extends BaseActivity {

    public static final String TAG = "ProviderActivity";
    private static final String AUTHORITIES = "com.wynne.knowledge.tree.custom.ipc.provider";


    @Override
    public void initView() {
        /**authorities唯一标识
         Uri uri = Uri.parse("content://" + AUTHORITIES);
         getContentResolver().query(uri, null, null, null, null);
         getContentResolver().query(uri, null, null, null, null);
         getContentResolver().query(uri, null, null, null, null);**/

        Uri bookUri = Uri.parse("content://" + AUTHORITIES + "/book");
        ContentValues values = new ContentValues();
        values.put("_id", 6);
        values.put("name", "程序设计的艺术");
        getContentResolver().insert(bookUri, values);
        Cursor bookCursor = getContentResolver().query(bookUri, new String[]{"_id", "name"}, null, null, null);

        while (bookCursor.moveToNext()) {
            Book book = new Book();
            book.bookId = bookCursor.getInt(0);
            book.bookName = bookCursor.getString(1);
            Log.d(TAG, "query book: " + book.toString());
        }
        bookCursor.close();


        Uri userUri = Uri.parse("content://" + AUTHORITIES + "/user");

        Cursor userCursor = getContentResolver().query(userUri, new String[]{"_id", "name", "sex"}, null, null, null);
        while (userCursor.moveToNext()) {
            User user = new User();
            user.userId = userCursor.getInt(0);
            user.userName = userCursor.getString(1);
            user.isMale = userCursor.getInt(2) == 1;
            Log.d(TAG, "query book: " + user.toString());
        }
        userCursor.close();


        serializeImpl();
    }

    @Override
    public int getLayoutId() {
        return R.layout.provider_activity;
    }

    private void serializeImpl() {
        User user = new User(1, "Wynne", true);
        Log.d("XXW", "user :" + user.toString());
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("cache.txt"));
            out.writeObject(user);
            out.close();


            ObjectInputStream input = new ObjectInputStream(new FileInputStream("cache.txt"));
            user = (User) input.readObject();
            Log.d("XXW", "user :" + user.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
