package com.hisroyalty.rmg.util;

import com.hisroyalty.rmg.client.RMGClient;

import java.util.concurrent.atomic.AtomicBoolean;

public class ClientUtil {

    public static AtomicBoolean getNext() {
        return RMGClient.NEXT_PAGE;
    }
}
