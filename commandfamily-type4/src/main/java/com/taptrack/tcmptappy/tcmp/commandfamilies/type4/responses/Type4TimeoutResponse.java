package com.taptrack.tcmptappy.tcmp.commandfamilies.type4.responses;

import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;
import com.taptrack.tcmptappy.tcmp.commandfamilies.type4.AbstractType4Message;

/**
 * Response indicating that a scan for type 4 tags has timed out.
 */
public class Type4TimeoutResponse extends AbstractType4Message {
    public static final byte COMMAND_CODE = 0x03;


    public Type4TimeoutResponse() {

    }

    public static Type4TimeoutResponse fromPayload(byte[] payload) throws MalformedPayloadException {
        return new Type4TimeoutResponse();
    }

    @Override
    public byte[] getPayload() {
        return new byte[0];
    }

    @Override
    public byte getCommandCode() {
        return COMMAND_CODE;
    }
}
