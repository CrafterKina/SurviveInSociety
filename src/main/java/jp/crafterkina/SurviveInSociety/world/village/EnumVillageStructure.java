/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.world.village;

import net.minecraftforge.fml.common.registry.VillagerRegistry;

public enum EnumVillageStructure{
    ;

    private final VillagerRegistry.IVillageCreationHandler handler;

    EnumVillageStructure(VillagerRegistry.IVillageCreationHandler handler){
        this.handler = handler;
    }

    public static void register(){
        for(EnumVillageStructure value : values()){
            VillagerRegistry.instance().registerVillageCreationHandler(value.handler);
        }
    }
}
