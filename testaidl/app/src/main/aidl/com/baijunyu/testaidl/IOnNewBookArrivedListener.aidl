// IOnNewBookArrivedListener.aidl
package com.baijunyu.testaidl;

// Declare any non-default types here with import statements
import com.baijunyu.testaidl.Book;

interface IOnNewBookArrivedListener {
   void onNewBookArrived(in Book newBook);
}