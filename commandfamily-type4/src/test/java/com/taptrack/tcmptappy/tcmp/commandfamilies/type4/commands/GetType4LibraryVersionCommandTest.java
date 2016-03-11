package com.taptrack.tcmptappy.tcmp.commandfamilies.type4.commands;

import org.junit.Test;

import static org.junit.Assert.*;

public class GetType4LibraryVersionCommandTest {

    @Test
    public void testGetCommandCode() throws Exception {
        GetType4LibraryVersionCommand command = new GetType4LibraryVersionCommand();
        assertEquals(command.getCommandCode(),(byte)0xFF);
    }

    @Test
    public void testGetFamilyId() throws Exception {
        GetType4LibraryVersionCommand command = new GetType4LibraryVersionCommand();
        assertArrayEquals(command.getCommandFamily(),new byte[]{(byte)0x00,(byte)0x04});
    }
}