package com.hektor.tools;

import com.hektor.tools.commands.CommandExample;
import com.hektor.tools.hud.HudExample;
import com.hektor.tools.modules.AutoAdvertise;
import com.hektor.tools.modules.AutoWither;
import com.hektor.tools.modules.FastXP;
import com.hektor.tools.modules.LitematicaPrinter;
import com.mojang.logging.LogUtils;
import meteordevelopment.meteorclient.addons.GithubRepo;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.commands.Commands;
import meteordevelopment.meteorclient.systems.hud.Hud;
import meteordevelopment.meteorclient.systems.hud.HudGroup;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;

public class OMCTools extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final Category CATEGORY = new Category("OMC Tools");
    public static final HudGroup HUD_GROUP = new HudGroup("OMC Tools");

    @Override
    public void onInitialize() {
        LOG.info("Initializing Meteor Addon Template");

        // Modules
        Modules.get().add(new AutoAdvertise());
        Modules.get().add(new LitematicaPrinter());
        Modules.get().add(new AutoWither());
        Modules.get().add(new FastXP());
        // Commands
        Commands.add(new CommandExample());

        // HUD
        Hud.get().register(HudExample.INFO);
    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(CATEGORY);
    }

    @Override
    public String getPackage() {
        return "com.hektor.tools";
    }

    @Override
    public GithubRepo getRepo() {
        return new GithubRepo("Hekt000r", "omc-meteor-tools-v2");
    }
}
