package com.sswtlzz.fluidtransporter.register;

import com.sswtlzz.fluidtransporter.FluidTransporter;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class FTCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FluidTransporter.MODID);


    public static final RegistryObject<CreativeModeTab> FLUID_TRANSPORTER = CREATIVE_MODE_TABS.register("fluid_transporter", () ->
            CreativeModeTab.builder()
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .title(Component.translatable("itemGroup." + FluidTransporter.MODID + ".fluid_transporter"))
                    .icon(() -> FTItems.BASIC.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {})
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
