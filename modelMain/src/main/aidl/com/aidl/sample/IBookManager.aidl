// IBookManager.aidl
package com.aidl.sample;
import com.aidl.sample.Book;
import com.aidl.sample.IOnNewBookArrivedListener;

// Declare any non-default types here with import statements

interface IBookManager {

     List<Book> getBookList();

     void addBook(in Book book);

     void registerListener(IOnNewBookArrivedListener listener);

     void unregisterListener(IOnNewBookArrivedListener listener);
}
