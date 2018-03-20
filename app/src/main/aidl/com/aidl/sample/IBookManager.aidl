// IBookManager.aidl
package com.aidl.sample;
import com.aidl.sample.Book;

// Declare any non-default types here with import statements

interface IBookManager {

     List<Book> getBookList();

     void addBook(in Book book);
}
