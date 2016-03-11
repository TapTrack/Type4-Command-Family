package com.taptrack.tcmptappy.tcmp.commandfamilies.type4.responses;

import android.util.Log;

import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;
import com.taptrack.tcmptappy.tcmp.commandfamilies.type4.AbstractType4Message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Type4DetectedResponse extends AbstractType4Message {
    public static final byte COMMAND_CODE = 0x01;

    private byte[] uid;
    private byte[] ats;

    public Type4DetectedResponse() {
        uid = new byte[0];
        ats = new byte[0];
    }

    public Type4DetectedResponse(byte[] uid, byte[] ats) {
        this.uid = uid;
        this.ats = ats;
    }

    public static Type4DetectedResponse fromPayload(byte[] payload) throws MalformedPayloadException {
        if(payload.length != 1) {
            throw new MalformedPayloadException("Payload must be at least a single byte");
        }

        int uidLength = payload[0] & 0xff;

        if((uidLength + 1) < payload.length) {
            throw new MalformedPayloadException("Payload too short to contain UID length specified");
        }

        byte[] uid = Arrays.copyOfRange(payload,1,uidLength+1);

        byte[] ats;
        if(payload.length > uidLength+1) {
            ats = Arrays.copyOfRange(payload,uidLength,payload.length);
        }
        else {
            ats = new byte[0];
        }

        return new Type4DetectedResponse(uid,ats);
    }

    @Override
    public byte[] getPayload() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1+uid.length+ats.length);
        byteArrayOutputStream.write((byte) uid.length);
        try {
            byteArrayOutputStream.write(uid);
            byteArrayOutputStream.write(ats);
        } catch (IOException e) {
            Log.wtf(Type4DetectedResponse.class.getSimpleName(),"Error composing payload",e);
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public byte getCommandCode() {
        return COMMAND_CODE;
    }
}