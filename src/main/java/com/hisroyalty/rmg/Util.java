package com.hisroyalty.rmg;

import com.hisroyalty.rmg.client.RMGClient;
import com.hisroyalty.rmg.screen.ConfirmScreen;
import com.hisroyalty.rmg.screen.CultureSelectionScreen;
import net.minecraft.client.Minecraft;

import java.util.concurrent.atomic.AtomicBoolean;

public class Util {


    public static void displayCultureSelectionScreen(Minecraft minecraft) {
        minecraft.setScreen(new CultureSelectionScreen());

    }

    public static void displayConfirmationScreen(Minecraft minecraft) {
        minecraft.setScreen(new ConfirmScreen());
    }

    public static AtomicBoolean getNext() {
        return RMGClient.NEXT_PAGE;
    }





}
