package com.taptrack.tcmptappy.tcmp.commandfamilies.type4.responses;

import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;
import com.taptrack.tcmptappy.tcmp.commandfamilies.type4.AbstractType4Message;

public class APDUTransceiveSuccessfulResponse extends AbstractType4Message {
    public static final byte COMMAND_CODE = 0x02;

    private byte[] apdu;

    public APDUTransceiveSuccessfulResponse() {
        apdu = new byte[0];
    }

    public APDUTransceiveSuccessfulResponse(byte[] apdu) {
        this.apdu = apdu;
    }

    public static APDUTransceiveSuccessfulResponse fromPayload(byte[] payload) throws MalformedPayloadException {
        return new APDUTransceiveSuccessfulResponse(payload);
    }

    public byte[] getApdu() {
        return apdu;
    }

    public void setApdu(byte[] apdu) {
        this.apdu = apdu;
    }

    @Override
    public byte[] getPayload() {
        return apdu;
    }

    @Override
    public byte getCommandCode() {
        return COMMAND_CODE;
    }
}
