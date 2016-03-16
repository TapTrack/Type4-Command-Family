package com.taptrack.tcmptappy.tcmp.commandfamilies.type4.responses;

import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;
import com.taptrack.tcmptappy.tcmp.StandardLibraryVersionResponse;
import com.taptrack.tcmptappy.tcmp.StandardLibraryVersionResponseDelegate;
import com.taptrack.tcmptappy.tcmp.commandfamilies.type4.AbstractType4Message;

/**
 * Response with the major and minor version of Type 4 library that the
 * Tappy supports.
 */
public class Type4LibraryVersionResponse extends AbstractType4Message implements StandardLibraryVersionResponse {
    public static final byte COMMAND_CODE = 0x05;
    private StandardLibraryVersionResponseDelegate delegate;

    public Type4LibraryVersionResponse() {
        delegate = new StandardLibraryVersionResponseDelegate();
    }
    public Type4LibraryVersionResponse(byte majorVersion, byte minorVersion) {
        delegate = new StandardLibraryVersionResponseDelegate(majorVersion,minorVersion);
    }

    @Override
    public byte getMajorVersion() {
        return delegate.getMajorVersion();
    }

    @Override
    public void setMajorVersion(byte majorVersion) {
        delegate.setMajorVersion(majorVersion);
    }

    @Override
    public byte getMinorVersion() {
        return delegate.getMinorVersion();
    }

    @Override
    public void setMinorVersion(byte minorVersion) {
        delegate.setMinorVersion(minorVersion);
    }

    @Override
    public void parsePayload(byte[] payload) throws MalformedPayloadException {
        delegate.parsePayload(payload);
    }

    @Override
    public byte[] getPayload() {
        return delegate.getPayload();
    }

    @Override
    public byte getCommandCode() {
        return COMMAND_CODE;
    }
}
