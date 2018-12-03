package com.taptrack.tcmptappy2.commandfamilies.type4;

import com.taptrack.tcmptappy2.MalformedPayloadException;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class AbstractType4MessageTest {

    @Test
    public void testGetCommandFamily() throws Exception {
        AbstractType4Message message = new AbstractType4Message() {
            @Override
            public void parsePayload(byte[] payload) throws MalformedPayloadException {

            }

            @Override
            public byte[] getPayload() {
                return new byte[0];
            }

            @Override
            public byte getCommandCode() {
                return 0;
            }
        };

        assertArrayEquals(message.getCommandFamily(),new byte[]{0x00,0x04});
    }
}