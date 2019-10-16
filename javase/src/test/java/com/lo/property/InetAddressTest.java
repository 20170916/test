package com.lo.property;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

    @Test
    public void testLocalHost() throws UnknownHostException {
        final InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost.getAddress());
        System.out.println(localHost.getCanonicalHostName());
        System.out.println(localHost.getHostAddress());
        System.out.println(localHost.getHostName());
    }
}
