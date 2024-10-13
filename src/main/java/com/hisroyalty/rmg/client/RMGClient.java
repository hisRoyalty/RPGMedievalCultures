package com.hisroyalty.rmg.client;


import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.concurrent.atomic.AtomicBoolean;

@OnlyIn(Dist.CLIENT)
public class RMGClient {
    public static final AtomicBoolean AWAITING_DISPLAY = new AtomicBoolean();
    public static final AtomicBoolean NEXT_PAGE = new AtomicBoolean();



}
