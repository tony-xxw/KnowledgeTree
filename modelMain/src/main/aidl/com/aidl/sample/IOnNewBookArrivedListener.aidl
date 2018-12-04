// IOnNewBookArrivedListener.aidl
package com.aidl.sample;
import com.aidl.sample.Book;
// Declare any non-default types here with import statements

interface IOnNewBookArrivedListener {

      void onNewBookArrived(in Book newBook);
}
