package com.taptrack.tcmptappy.tcmp.commandfamilies.type4;

import com.taptrack.tcmptappy.tcmp.TCMPMessage;

public abstract class AbstractType4Message  extends TCMPMessage{
    @Override
    public byte[] getCommandFamily() {
        return Type4CommandLibrary.FAMILY_ID;
    }
}
