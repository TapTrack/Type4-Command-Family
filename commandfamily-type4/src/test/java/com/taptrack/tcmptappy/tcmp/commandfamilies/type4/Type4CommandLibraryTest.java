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
import com.taptrack.tcmptappy.tcmp.common.ResponseCodeNotSupportedException;

import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class Type4CommandLibraryTest {
    Type4CommandLibrary library = new Type4CommandLibrary();
    Random random = new Random();

    private static class FakeCommand extends AbstractType4Message {

        @Override
        public byte[] getPayload() {
            return new byte[0];
        }

        @Override
        public byte getCommandCode() {
            return 0x76;
        }
    }

    private static class FakeResponse extends AbstractType4Message {

        @Override
        public byte[] getPayload() {
            return new byte[0];
        }

        @Override
        public byte getCommandCode() {
            return 0x76;
        }
    }

    @Test
    public void testParseCommand() throws Exception {
        testCommand(new DetectType4Command((byte) 5), DetectType4Command.class);
        testCommand(new GetType4LibraryVersionCommand(), GetType4LibraryVersionCommand.class);

        byte[] apdu = new byte[20];
        random.nextBytes(apdu);
        testCommand(new TransceiveApduCommand(apdu), TransceiveApduCommand.class);

        boolean commandCodeNotSupportedThrown = false;
        try {
            testCommand(new FakeCommand(),FakeCommand.class);
        }
        catch (CommandCodeNotSupportedException e) {
            commandCodeNotSupportedThrown = true;
        }

        assertTrue(commandCodeNotSupportedThrown);
    }

    private void testCommand(TCMPMessage message,Class<? extends TCMPMessage> clazz) throws CommandCodeNotSupportedException, MalformedPayloadException {
        TCMPMessage parsedMessage = library.parseCommand(message);
        assertThat(parsedMessage,instanceOf(clazz));
        assertArrayEquals(message.getPayload(), parsedMessage.getPayload());
    }

    @Test
    public void testParseResponse() throws Exception {
        byte[] apdu = new byte[20];
        random.nextBytes(apdu);
        testResponse(new APDUTransceiveSuccessfulResponse(apdu), APDUTransceiveSuccessfulResponse.class);

        byte[] uid = new byte[7];
        byte[] ats = new byte[3];
        random.nextBytes(uid);
        random.nextBytes(ats);
        testResponse(new Type4DetectedResponse(uid, ats), Type4DetectedResponse.class);
        testResponse(new Type4ErrorResponse((byte) 0x02,(byte)  0x03,(byte)  0x04, "Test"), Type4ErrorResponse.class);
        testResponse(new Type4LibraryVersionResponse((byte)0x0a,(byte)0x12),Type4LibraryVersionResponse.class);
        testResponse(new Type4PollingErrorResponse(),Type4PollingErrorResponse.class);
        testResponse(new Type4TimeoutResponse(),Type4TimeoutResponse.class);

        boolean responseCodeNotSupportedThrown = false;
        try {
            testResponse(new FakeResponse(), FakeResponse.class);
        }
        catch (ResponseCodeNotSupportedException e) {
            responseCodeNotSupportedThrown = true;
        }

        assertTrue(responseCodeNotSupportedThrown);
    }

    private void testResponse(TCMPMessage message,Class<? extends TCMPMessage> clazz) throws ResponseCodeNotSupportedException, MalformedPayloadException {
        TCMPMessage parsedMessage = library.parseResponse(message);
        assertThat(parsedMessage,instanceOf(clazz));
        assertArrayEquals(message.getPayload(),parsedMessage.getPayload());
    }


    @Test
    public void testGetCommandFamilyId() throws Exception {
        assertArrayEquals(library.getCommandFamilyId(), new byte[]{0x00, 0x04});
    }
}