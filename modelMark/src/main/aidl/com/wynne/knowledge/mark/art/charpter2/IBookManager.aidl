// IBookManager.aidl
package com.wynne.knowledge.mark.art.charpter2;

// Declare any non-default types here with import statements
import com.wynne.knowledge.mark.art.charpter2.BookAIDL;
interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    List<BookAIDL> getLit();

    void addBook(in BookAIDL book);
}
