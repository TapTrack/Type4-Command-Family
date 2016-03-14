package com.taptrack.tcmptappy.tcmp.commandfamilies.type4;

import com.taptrack.tcmptappy.tcmp.TCMPMessage;

/**
 * Base class for messages within the Type 4 command library
 */
public abstract class AbstractType4Message  extends TCMPMessage{
    @Override
    public byte[] getCommandFamily() {
        return Type4CommandLibrary.FAMILY_ID;
    }
}
