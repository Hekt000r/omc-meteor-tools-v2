package com.example.addon.modules;

import com.example.addon.AddonTemplate;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.gui.utils.StarscriptTextBoxRenderer;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import meteordevelopment.meteorclient.utils.player.ChatUtils;


import java.util.List;
import java.util.Random;

@Environment(EnvType.CLIENT)
public class AutoAdvertise extends Module {
    private final SettingGroup sgGeneral = this.settings.getDefaultGroup();

    private final Setting<List<String>> messages = sgGeneral.add(new StringListSetting.Builder()
        .name("messages")
        .description("The messages to send")
        .renderer(StarscriptTextBoxRenderer.class)
        .build()
    );
    private final Setting<Integer> delay = sgGeneral.add(new IntSetting.Builder()
        .name("delay-ms")
        .description("Delay in milliseconds between sending messages.")
        .defaultValue(3000)
        .min(100)
        .sliderMin(100)
        .sliderMax(600000)
        .build()
    );

    private final Random random = new Random();
    private long lastMessageTime = 0;

    public AutoAdvertise() {
        super(AddonTemplate.CATEGORY, "Auto-advertise",
            "Automatically sends messages to the chat when the keybind is pressed.");
    }

    @EventHandler
    private void onTick(TickEvent.Pre event) {
        long now = System.currentTimeMillis();
        if (now - lastMessageTime < delay.get()) return;

        List<String> list = messages.get();
        if (!list.isEmpty()) {
            String msg = list.get(random.nextInt(list.size()));
            ChatUtils.sendPlayerMsg(msg);
            lastMessageTime = now;
        }
    }
}
