// Copyright (c) .NET Foundation. All rights reserved.
// Licensed under the Apache License, Version 2.0. See License.txt in the project root for license information.

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class WebSocketTransportTest {
    private String url;
    private String expectedUrl;

    public WebSocketTransportTest(String url, String expectedProtocol){
        this.url = url;
        this.expectedUrl = expectedProtocol;
    }

    @Parameterized.Parameters
    public static Collection protocols(){
        return Arrays.asList(new String[][] {
                {"http://example.com", "ws://example.com"},
                {"https://example.com", "wss://example.com"},
                {"ws://example.com", "ws://example.com"},
                {"wss://example.com", "wss://example.com"}});
    }

    @Test
    public void checkWebsocketUrlProtocol() throws URISyntaxException {
        WebSocketTransport webSocketTransport = new WebSocketTransport(this.url, new NullLogger());
        assertEquals(this.expectedUrl, webSocketTransport.getUrl().toString());
    }
}