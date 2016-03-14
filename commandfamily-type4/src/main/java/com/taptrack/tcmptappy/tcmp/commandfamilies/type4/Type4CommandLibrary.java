package com.taptrack.tcmptappy.tcmp.commandfamilies.type4;

import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;
import com.taptrack.tcmptappy.tcmp.TCMPMessage;
import com.taptrack.tcmptappy.tcmp.commandfamilies.type4.commands.DetectType4Command;
import com.taptrack.tcmptappy.tcmp.commandfamilies.type4.commands.GetType4LibraryVersionCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.type4.commands.TransceiveApduCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.type4.responses.APDUTransceiveSuccessfulResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.type4.responses.Type4DetectedResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.type4.responses.Type4ErrorResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.type4.responses.Type4LibraryVersionResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.type4.responses.Type4PollingErrorResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.type4.responses.Type4TimeoutResponse;
import com.taptrack.tcmptappy.tcmp.common.CommandCodeNotSupportedException;
import com.taptrack.tcmptappy.tcmp.common.CommandFamily;
import com.taptrack.tcmptappy.tcmp.common.ResponseCodeNotSupportedException;

/**
 * Command library for Type 4 commands and responses.
 */
public class Type4CommandLibrary implements CommandFamily {
    public static final byte[] FAMILY_ID = new byte[]{0x00,0x04};

    @Override
    public TCMPMessage parseCommand(TCMPMessage message) throws CommandCodeNotSupportedException, MalformedPayloadException {
        switch(message.getCommandCode()) {
            case DetectType4Command.COMMAND_CODE:
                return DetectType4Command.fromPayload(message.getPayload());
            case GetType4LibraryVersionCommand.COMMAND_CODE:
                return GetType4LibraryVersionCommand.fromPayload(message.getPayload());
            case TransceiveApduCommand.COMMAND_CODE:
                return TransceiveApduCommand.fromPayload(message.getPayload());
            default:
                throw new CommandCodeNotSupportedException(
                        Type4CommandLibrary.class.getSimpleName()+
                                " doesn't support command code "+String.format("%02X",message.getCommandCode()));
        }
    }

    @Override
    public TCMPMessage parseResponse(TCMPMessage message) throws ResponseCodeNotSupportedException, MalformedPayloadException {
        switch(message.getCommandCode()) {
            case APDUTransceiveSuccessfulResponse.COMMAND_CODE:
                return APDUTransceiveSuccessfulResponse.fromPayload(message.getPayload());
            case Type4DetectedResponse.COMMAND_CODE:
                return Type4DetectedResponse.fromPayload(message.getPayload());
            case Type4ErrorResponse.COMMAND_CODE:
                return Type4ErrorResponse.fromPayload(message.getPayload());
            case Type4LibraryVersionResponse.COMMAND_CODE:
                return Type4LibraryVersionResponse.fromPayload(message.getPayload());
            case Type4PollingErrorResponse.COMMAND_CODE:
                return Type4PollingErrorResponse.fromPayload(message.getPayload());
            case Type4TimeoutResponse.COMMAND_CODE:
                return Type4TimeoutResponse.fromPayload(message.getPayload());
            default:
                throw new ResponseCodeNotSupportedException(
                        Type4CommandLibrary.class.getSimpleName()+
                                " doesn't support response code "+String.format("%02X",message.getCommandCode()));
        }
    }

    @Override
    public byte[] getCommandFamilyId() {
        return FAMILY_ID;
    }
}
