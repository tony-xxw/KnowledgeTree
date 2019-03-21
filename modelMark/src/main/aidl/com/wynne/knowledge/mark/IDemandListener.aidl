// IDemandListener.aidl
package com.wynne.knowledge.mark;

// Declare any non-default types here with import statements
import com.wynne.knowledge.mark.MessageBean;

interface IDemandListener {


    void onDemandReceiver(in MessageBean msg);

}
