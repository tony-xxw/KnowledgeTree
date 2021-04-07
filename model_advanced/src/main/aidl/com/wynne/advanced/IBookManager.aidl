// IBookManager.aidl
package com.wynne.advanced;
import com.wynne.advanced.Book;
// Declare any non-default types here with import statements

interface IBookManager {
  List<Book> getBookList();
  void addBook(in Book book);
}