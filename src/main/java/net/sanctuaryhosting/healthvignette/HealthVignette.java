package net.sanctuaryhosting.healthvignette;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class HealthVignette implements ModInitializer {
    public static final String modID = "healthvignette";
    public static HealthVignetteConfiguration configuration;

    @Override
    public void onInitialize() {
        AutoConfig.register(HealthVignetteConfiguration.class, GsonConfigSerializer::new);
        configuration = AutoConfig.getConfigHolder(HealthVignetteConfiguration.class).getConfig();
    }
}