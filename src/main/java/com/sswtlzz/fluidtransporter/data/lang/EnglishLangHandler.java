package com.sswtlzz.fluidtransporter.data.lang;

import com.sswtlzz.fluidtransporter.FluidTransporter;
import com.sswtlzz.fluidtransporter.register.FTCreativeTabs;
import com.tterrag.registrate.providers.RegistrateLangProvider;

public class EnglishLangHandler {

    public static void init(RegistrateLangProvider provider) {
        //creativetabs
        provider.add(FTCreativeTabs.FLUID_TRANSPORTER.get(), "Fluid Transporter");
        //tooltip
        provider.add(FluidTransporter.MODID + ".tooltip", "Fluid：%s，Amount：%d");
    }
}
