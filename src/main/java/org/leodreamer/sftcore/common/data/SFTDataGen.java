package org.leodreamer.sftcore.common.data;

import com.tterrag.registrate.providers.ProviderType;
import org.leodreamer.sftcore.common.data.lang.SFTLangHandler;

import static org.leodreamer.sftcore.SFTCore.REGISTRATE;

public class SFTDataGen {
    public static void init() {
        REGISTRATE.addDataGenerator(ProviderType.LANG, SFTLangHandler::init);
    }
}
