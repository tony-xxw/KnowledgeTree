// IDemandManager.aidl
package com.wynne.knowledge.mark;

// Declare any non-default types here with import statements
import com.wynne.knowledge.mark.MessageBean;

interface IDemandManager {

    MessageBean getDemand();

    void setDemandIn(in MessageBean msg);

    void setDemandOut(out MessageBean msg);

    void setDeamandInOut(inout MessageBean msg);

}
