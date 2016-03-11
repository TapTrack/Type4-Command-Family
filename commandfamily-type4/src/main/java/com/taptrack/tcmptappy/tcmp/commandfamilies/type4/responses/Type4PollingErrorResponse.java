package com.taptrack.tcmptappy.tcmp.commandfamilies.type4.responses;

import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;
import com.taptrack.tcmptappy.tcmp.commandfamilies.type4.AbstractType4Message;

public class Type4PollingErrorResponse extends AbstractType4Message {
    public static final byte COMMAND_CODE = 0x04;


    public Type4PollingErrorResponse() {

    }

    public static Type4PollingErrorResponse fromPayload(byte[] payload) throws MalformedPayloadException {
        return new Type4PollingErrorResponse();
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