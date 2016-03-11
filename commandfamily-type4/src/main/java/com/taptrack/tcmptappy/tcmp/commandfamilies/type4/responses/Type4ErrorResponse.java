package com.taptrack.tcmptappy.tcmp.commandfamilies.type4.responses;

import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;
import com.taptrack.tcmptappy.tcmp.commandfamilies.type4.AbstractType4Message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Type4ErrorResponse extends AbstractType4Message {
    public static final byte COMMAND_CODE = 0x7F;
    byte errorCode;
    byte internalError;
    byte pn532Status;
    String errorMessage;

    public Type4ErrorResponse() {
        errorCode = 0x00;
        internalError= 0x00;
        pn532Status = 0x00;
        errorMessage = "";
    }

    public Type4ErrorResponse(byte errorCode, byte internalError, byte pn532Status, String errorMessage) {
        this.errorCode = errorCode;
        this.internalError = internalError;
        this.pn532Status = pn532Status;
        this.errorMessage = errorMessage;
    }

    public static Type4ErrorResponse fromPayload(byte[] payload) throws MalformedPayloadException {
        if (payload.length >= 3) {
            byte errorCode = (byte) (payload[0] & 0xff);
            byte internalError = (byte) (payload[1] & 0xff);
            byte pn532Status = (byte) (payload[2] & 0xff);
            String errorMessage;
            if (payload.length > 3) {
                byte[] message = Arrays.copyOfRange(payload, 3, payload.length);
                errorMessage = new String(message);
            } else {
                errorMessage = "";
            }
            return new Type4ErrorResponse(errorCode,internalError,pn532Status,errorMessage);
        } else {
            throw new MalformedPayloadException("Payload too short");
        }
    }


    public byte getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(byte errorCode) {
        this.errorCode = errorCode;
    }

    public byte getInternalError() {
        return internalError;
    }

    public void setInternalError(byte internalError) {
        this.internalError = internalError;
    }

    public byte getPn532Status() {
        return pn532Status;
    }

    public void setPn532Status(byte pn532Status) {
        this.pn532Status = pn532Status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public byte[] getPayload() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(3+errorMessage.length());
        outputStream.write(errorCode);
        outputStream.write(internalError);
        outputStream.write(pn532Status);
        try {
            outputStream.write(errorMessage.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] payload = outputStream.toByteArray();
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return payload;
    }

    @Override
    public byte getCommandCode() {
        return COMMAND_CODE;
    }
}
