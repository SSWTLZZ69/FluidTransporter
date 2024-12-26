package com.sswtlzz.fluidtransporter;

import com.mojang.logging.LogUtils;
import com.sswtlzz.fluidtransporter.data.FTDatagen;
import com.sswtlzz.fluidtransporter.register.FTCreativeTabs;
import com.sswtlzz.fluidtransporter.register.FTItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(FluidTransporter.MODID)
public class FluidTransporter {

    public static final String MODID = "fluid_transporter";
    private static final Logger LOGGER = LogUtils.getLogger();

    public FluidTransporter() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        FTItems.init();
        FTCreativeTabs.register(modEventBus);
        FTDatagen.init();

    }

    public static ResourceLocation RL(String path) {
        return new ResourceLocation(MODID, path);
    }
}
