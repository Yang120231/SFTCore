package org.leodreamer.sftcore.common.data;

import com.tterrag.registrate.providers.ProviderType;
import org.leodreamer.sftcore.common.data.lang.SGTLangHandler;

import static org.leodreamer.sftcore.SFTCore.REGISTRATE;

public class SGTDataGen {
    public static void init() {
        REGISTRATE.addDataGenerator(ProviderType.LANG, SGTLangHandler::init);
    }
}
