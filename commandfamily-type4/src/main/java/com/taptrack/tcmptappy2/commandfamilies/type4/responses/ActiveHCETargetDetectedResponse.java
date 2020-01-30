package com.taptrack.tcmptappy2.commandfamilies.type4.responses;

import android.support.annotation.NonNull;

import com.taptrack.tcmptappy2.MalformedPayloadException;
import com.taptrack.tcmptappy2.commandfamilies.type4.AbstractType4Message;

/**
 * Response for a Type 4 tag being detected. Includes the tag's UID
 * as well as the response to the RATS (Request for Answer to Select) command
 * if the card provided one.
 */
public class ActiveHCETargetDetectedResponse extends AbstractType4Message {
    public static final byte COMMAND_CODE = 0x08;

    private byte[] firstCommand;

    public ActiveHCETargetDetectedResponse() {
        firstCommand = new byte[0];
    }

    public ActiveHCETargetDetectedResponse(byte[] firstCommand) {
        this.firstCommand = firstCommand;
    }

    public byte[] getFirstCommand() {
        return firstCommand;
    }

    public void setFirstCommand(byte[] firstCommand) {
        this.firstCommand = firstCommand;
    }

    @Override
    public void parsePayload(@NonNull byte[] payload) throws MalformedPayloadException {
        if(payload.length == 0) {
            firstCommand = new byte[0];
        } else {
            firstCommand = payload;
        }


    }

    @Override
    public byte[] getPayload() {
        return firstCommand;
    }

    @Override
    public byte getCommandCode() {
        return COMMAND_CODE;
    }
}
