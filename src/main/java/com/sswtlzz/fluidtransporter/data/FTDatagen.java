package com.sswtlzz.fluidtransporter.data;

import com.sswtlzz.fluidtransporter.data.lang.ChineseLangHandler;
import com.sswtlzz.fluidtransporter.data.lang.EnglishLangHandler;
import com.sswtlzz.fluidtransporter.data.lang.RegistrateCNLangProvider;
import com.tterrag.registrate.providers.ProviderType;

import static com.sswtlzz.fluidtransporter.register.FTRegistration.REGISTRATE;

public class FTDatagen {
    public static final ProviderType<RegistrateCNLangProvider> CNLANG = ProviderType.register("cdcore_cn_lang", (p, e) -> new RegistrateCNLangProvider(p, e.getGenerator().getPackOutput()));

    public static void init() {
        REGISTRATE.addDataGenerator(ProviderType.LANG, EnglishLangHandler::init);
        REGISTRATE.addDataGenerator(CNLANG, ChineseLangHandler::init);
    }
}
