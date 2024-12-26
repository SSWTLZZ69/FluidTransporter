package com.sswtlzz.fluidtransporter;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = FluidTransporter.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FluidTransporterConfig
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.IntValue MAX_CAPACITY = BUILDER
            .comment("The Max capacity of the fluid transporter")
            .defineInRange("maxCapacity", 1000, 1, Integer.MAX_VALUE);
    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int maxCapacity;


    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        maxCapacity = MAX_CAPACITY.get();
    }
}
