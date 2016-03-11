package com.taptrack.tcmptappy.tcmp.commandfamilies.type4.responses;

import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;
import com.taptrack.tcmptappy.tcmp.commandfamilies.type4.AbstractType4Message;

public class Type4LibraryVersionResponse extends AbstractType4Message {
    public static final byte COMMAND_CODE = 0x05;
    byte mMajorVersion;
    byte mMinorVersion;

    public Type4LibraryVersionResponse() {
        mMajorVersion = 0x00;
        mMinorVersion = 0x00;
    }
    public Type4LibraryVersionResponse(byte majorVersion, byte minorVersion) {
        mMajorVersion = majorVersion;
        mMinorVersion = minorVersion;
    }

    public byte getMajorVersion () {
        return mMajorVersion;
    }

    public byte getMinorVersion () {
        return mMinorVersion;
    }

    public static Type4LibraryVersionResponse fromPayload(byte[] payload) throws MalformedPayloadException {
        if(payload.length != 2)
            throw new MalformedPayloadException();

        return new Type4LibraryVersionResponse(payload[0],payload[1]);
    }

    @Override
    public byte[] getPayload() {
        return new byte[]{mMajorVersion,mMinorVersion};
    }

    @Override
    public byte getCommandCode() {
        return COMMAND_CODE;
    }
}
