// IBookManager.aidl
package com.baijunyu.testaidl;

// Declare any non-default types here with import statements
import com.baijunyu.testaidl.Book;
import com.baijunyu.testaidl.IOnNewBookArrivedListener;
interface IBookManager {
List<Book> getBookList();
void addBook(in Book book);
void registerListener(IOnNewBookArrivedListener listener);
void unregisterListener(IOnNewBookArrivedListener listener);
}
