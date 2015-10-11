/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.world.village;

import jp.crafterkina.SurviveInSociety.SurviveInSociety;
import jp.crafterkina.SurviveInSociety.world.village.parts.trouble_center.TroubleCenterComponent;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public enum EnumVillageStructure{
    TroubleCenter(new TroubleCenterComponent()),
    ;
    public static final EnumVillageStructure[] values = values();

    private final VillagerRegistry.IVillageCreationHandler handler;
    private final Class<? extends StructureVillagePieces.Village> component;

    EnumVillageStructure(VillagerRegistry.IVillageCreationHandler handler, Class<? extends StructureVillagePieces.Village> component){
        this.handler = handler;
        this.component = component;
    }

    @SuppressWarnings("unchecked")
    EnumVillageStructure(VillagerRegistry.IVillageCreationHandler handler){
        this(handler, (Class<? extends StructureVillagePieces.Village>) handler.getComponentClass());
    }

    public static void register(){
        for(EnumVillageStructure value : values){
            MapGenStructureIO.registerStructureComponent(value.component, SurviveInSociety.PARENT_PACKAGE + value.name());
            VillagerRegistry.instance().registerVillageCreationHandler(value.handler);
        }
    }
}
