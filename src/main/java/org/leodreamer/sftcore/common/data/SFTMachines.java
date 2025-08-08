package org.leodreamer.sftcore.common.data;

import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import org.leodreamer.sftcore.common.data.machine.GTMultimachineTweaks;
import org.leodreamer.sftcore.common.data.machine.SFTMultiMachines;
import org.leodreamer.sftcore.common.machine.OreReplicatorMachine;

import static org.leodreamer.sftcore.SFTCore.REGISTRATE;

public class SFTMachines {

    public static final MachineDefinition ORE_REPLICATOR = REGISTRATE.machine("ore_replicator", OreReplicatorMachine::new)
            .langValue("Ore Replicator")
            .rotationState(RotationState.NONE)
            .register();

    public static void init() {
        GTMultimachineTweaks.init();
        SFTMultiMachines.init();
    }
}
