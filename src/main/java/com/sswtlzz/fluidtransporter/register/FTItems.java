package com.sswtlzz.fluidtransporter.register;

import com.sswtlzz.fluidtransporter.content.item.FluidTransporterItem;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Rarity;

import static com.sswtlzz.fluidtransporter.register.FTRegistration.REGISTRATE;

public class FTItems {
    public static final ResourceKey<CreativeModeTab> FLUID_TRANSPORTER =FTCreativeTabs.FLUID_TRANSPORTER.getKey();
    // basic
    public static final ItemEntry<FluidTransporterItem> BASIC;
    public static final ItemEntry<FluidTransporterItem> ADVANCED;
    public static final ItemEntry<FluidTransporterItem> ULTIMATE;

    static {
        BASIC = simpleFluidTransporterItem("basic_fluid_transporter", Rarity.COMMON, 1000);
        ADVANCED = simpleFluidTransporterItem("advanced_fluid_transporter", Rarity.UNCOMMON, 5000);
        ULTIMATE = simpleFluidTransporterItem("ultimate_fluid_transporter", Rarity.EPIC, 100000);
    }

    public static ItemEntry<FluidTransporterItem> simpleFluidTransporterItem(String name, Rarity rarity, int maxCapacity) {
        return REGISTRATE.item(name, FluidTransporterItem::new)
                .properties(properties -> properties
                        .rarity(rarity)
                )
                .tab(FLUID_TRANSPORTER)
                .register();

    }

    public static void init() {
    }
}
