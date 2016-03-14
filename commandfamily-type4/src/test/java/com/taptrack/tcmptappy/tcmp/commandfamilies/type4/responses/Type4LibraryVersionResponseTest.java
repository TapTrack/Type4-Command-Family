package com.taptrack.tcmptappy.tcmp.commandfamilies.type4.responses;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Type4LibraryVersionResponseTest {

    @Test
    public void testFromPayload() throws Exception {
        byte majorVersion = 0x01;
        byte minorVersion = 0x12;
        byte[] payload = new byte[]{majorVersion,minorVersion};
        Type4LibraryVersionResponse response = Type4LibraryVersionResponse.fromPayload(payload);
        assertEquals(majorVersion,response.getMajorVersion());
        assertEquals(minorVersion,response.getMinorVersion());
    }

    @Test
    public void testGetPayload() throws Exception {
        byte majorVersion = (byte) 0xF1;
        byte minorVersion = 0x17;
        byte[] payload = new byte[]{majorVersion,minorVersion};

        Type4LibraryVersionResponse response = new Type4LibraryVersionResponse(majorVersion,minorVersion);
        assertArrayEquals(response.getPayload(),payload);
    }

    @Test
    public void testGetCommandCode() throws Exception {
        Type4LibraryVersionResponse response = new Type4LibraryVersionResponse();
        assertEquals(response.getCommandCode(),(byte) 0x05);
    }

}