/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.world.village.parts.trouble_center;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import java.util.List;
import java.util.Random;

public class TroubleCenterCreationHandler implements VillagerRegistry.IVillageCreationHandler{
    @Override
    public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i){
        return null;
    }

    @Override
    public Class<?> getComponentClass(){
        return null;
    }

    @Override
    public Object buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5){
        return null;
    }
}
