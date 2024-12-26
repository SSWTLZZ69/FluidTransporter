package com.sswtlzz.fluidtransporter;

import com.sswtlzz.fluidtransporter.item.FluidTransporterItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(FluidTransporter.MODID)
public class FluidTransporter {

    public static final String MODID = "fluid_transporter";
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final RegistryObject<Item> FLUID_TRANSPORTER_ITEM = ITEMS.register("fluid_transporter", () -> new FluidTransporterItem(new Item.Properties()));
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("fluid_transporter", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .title(Component.translatable("itemGroup." + MODID))
            .icon(() -> FLUID_TRANSPORTER_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(FLUID_TRANSPORTER_ITEM.get());
            }).build());

    @SuppressWarnings("removal")
    public FluidTransporter() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);


        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, FluidTransporterConfig.SPEC);
    }
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

}
