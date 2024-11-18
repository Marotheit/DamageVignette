package net.sanctuaryhosting.healthvignette;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = HealthVignette.modID)
public class HealthVignetteConfiguration implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    public boolean enabled = true;

    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    @ConfigEntry.Gui.Tooltip
    public int introductionThreshold = 40;

    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    @ConfigEntry.Gui.Tooltip
    public int severeThreshold = 10;

    @ConfigEntry.ColorPicker
    @ConfigEntry.Gui.Tooltip
    public int vignetteColor = 0xFF0000;

    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    @ConfigEntry.Gui.Tooltip
    public int vignetteIntensity = 20;
}