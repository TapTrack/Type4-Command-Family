package com.taptrack.tcmptappy.tcmp.commandfamilies.type4.commands;

import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;
import com.taptrack.tcmptappy.tcmp.commandfamilies.type4.AbstractType4Message;

public class DetectType4Command extends AbstractType4Message {
    public static final byte COMMAND_CODE = (byte) 0x01;
    private byte timeout;

    public DetectType4Command() {
        this.timeout = 0;
    }

    public DetectType4Command(byte timeout) {
        this.timeout = timeout;
    }


    public static DetectType4Command fromPayload(byte[] payload) throws MalformedPayloadException {
        if(payload.length == 1) {
            return new DetectType4Command(payload[0]);
        }
        else {
            throw new MalformedPayloadException("Payload should be a single byte");
        }
    }

    public byte getTimeout() {
        return timeout;
    }

    public void setTimeout(byte timeout) {
        this.timeout = timeout;
    }

    @Override
    public byte[] getPayload() {
        return new byte[]{timeout};
    }

    @Override
    public byte getCommandCode() {
        return COMMAND_CODE;
    }
}
