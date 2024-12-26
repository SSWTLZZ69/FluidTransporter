package com.sswtlzz.fluidtransporter.data.lang;

import com.sswtlzz.fluidtransporter.FluidTransporter;
import com.sswtlzz.fluidtransporter.register.FTCreativeTabs;
import com.sswtlzz.fluidtransporter.register.FTItems;
import net.minecraftforge.common.data.LanguageProvider;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Map;

public class ChineseLangHandler {

    public static void init(RegistrateCNLangProvider provider) {
        //creativetabs
        provider.add(FTCreativeTabs.FLUID_TRANSPORTER.get(), "流体转移器");
        //item
        provider.add(FTItems.BASIC.get(), "基础流体转移器");
        provider.add(FTItems.ADVANCED.get(), "高级流体转移器");
        provider.add(FTItems.ULTIMATE.get(), "终极流体转移器");
        //tooltip
        provider.add(FluidTransporter.MODID + ".tooltip", "流体：%s，含量：%d");
    }

    public static void replace(@NotNull RegistrateCNLangProvider provider, @NotNull String key,
                               @NotNull String value) {
        try {
            // the regular lang mappings
            Field field = LanguageProvider.class.getDeclaredField("data");
            field.setAccessible(true);
            // noinspection unchecked
            Map<String, String> map = (Map<String, String>) field.get(provider);
            map.put(key, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error replacing entry in datagen.", e);
        }
    }
}
