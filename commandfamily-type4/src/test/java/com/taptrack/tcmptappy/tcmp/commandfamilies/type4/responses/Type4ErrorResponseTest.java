package com.taptrack.tcmptappy.tcmp.commandfamilies.type4.responses;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Type4ErrorResponseTest {

    private byte[] generateErrorPayload(byte errorCode, byte errorByte, byte nfcStatus, String errorString) {
        ByteArrayOutputStream payloadStream = new ByteArrayOutputStream();
        payloadStream.write(errorCode);
        payloadStream.write(errorByte);
        payloadStream.write(nfcStatus);
        if(errorString != null) {
            try {
                payloadStream.write(errorString.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return payloadStream.toByteArray();
    }

    @Test
    public void testFromPayload() throws Exception {
        byte errorCode = (byte)0x0a;
        byte errorByte = (byte)0x0b;
        byte pn532Status = (byte)0x0c;
        String testMessage = "Test Message";

        byte[] testPayloadWithText = generateErrorPayload(errorCode,errorByte,pn532Status,testMessage);
        byte[] testPayloadWithoutText = generateErrorPayload(errorCode,errorByte,pn532Status,null);

        Type4ErrorResponse texted = Type4ErrorResponse.fromPayload(testPayloadWithText);
        Type4ErrorResponse noText = Type4ErrorResponse.fromPayload(testPayloadWithoutText);

        assertArrayEquals(texted.getPayload(), testPayloadWithText);
        assertEquals(errorCode, texted.getErrorCode());
        assertEquals(errorByte,texted.getInternalError());
        assertEquals(pn532Status, texted.getPn532Status());
        assertTrue(testMessage.equals(texted.getErrorMessage()));

        assertArrayEquals(noText.getPayload(), testPayloadWithoutText);
        assertEquals(errorCode, noText.getErrorCode());
        assertEquals(errorByte,noText.getInternalError());
        assertEquals(pn532Status, noText.getPn532Status());
        assertTrue(noText.getErrorMessage().equals(""));
    }

    @Test
    public void testGetPayload() throws Exception {
        byte errorCode = (byte)0x0a;
        byte errorByte = (byte)0x0b;
        byte pn532Status = (byte)0x0c;
        String testMessage = "Test Message";

        Type4ErrorResponse texted = new Type4ErrorResponse(errorCode,errorByte,pn532Status,testMessage);
        byte[] textedPayload = generateErrorPayload(errorCode,errorByte,pn532Status,testMessage);
        assertArrayEquals(texted.getPayload(),textedPayload);

        Type4ErrorResponse notext = new Type4ErrorResponse(errorCode,errorByte,pn532Status,"");
        byte[] emptyTextPayload = generateErrorPayload(errorCode,errorByte,pn532Status,"");
        assertArrayEquals(notext.getPayload(),emptyTextPayload);
    }

    @Test
    public void testGetCommandCode() throws Exception {
        Type4ErrorResponse response = new Type4ErrorResponse();
        assertEquals(response.getCommandCode(), (byte) 0x7F);
    }

    @Test
    public void testGetCommandFamily() throws Exception {
        Type4ErrorResponse response = new Type4ErrorResponse();
        assertArrayEquals(response.getCommandFamily(), new byte[]{0x00, 0x04});
    }
}