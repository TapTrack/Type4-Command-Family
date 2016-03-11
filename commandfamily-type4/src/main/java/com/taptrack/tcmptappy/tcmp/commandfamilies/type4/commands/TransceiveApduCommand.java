package com.taptrack.tcmptappy.tcmp.commandfamilies.type4.commands;

import com.taptrack.tcmptappy.tcmp.commandfamilies.type4.AbstractType4Message;

public class TransceiveApduCommand extends AbstractType4Message {
    public static final byte COMMAND_CODE = (byte) 0x02;
    private byte[] apdu;

    public TransceiveApduCommand() {
        this.apdu = new byte[0];
    }

    public TransceiveApduCommand(byte[] apdu) {
        this.apdu = apdu;
    }

    public static TransceiveApduCommand fromPayload(byte[] payload) {
        return new TransceiveApduCommand(payload);
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
