/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.world.village.parts.trouble_center;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

import java.util.List;
import java.util.Random;

public class TroubleCenterCreationHandler implements IVillageCreationHandler{
    @Override
    public PieceWeight getVillagePieceWeight(Random random, int i){
        return new PieceWeight(TroubleCenterComponent.class, 20, MathHelper.getRandomIntegerInRange(random, i, i + 1));
    }

    @Override
    public Class<?> getComponentClass(){
        return TroubleCenterComponent.class;
    }

    @Override
    public Object buildComponent(PieceWeight villagePiece, Start startPiece, List pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5){
        return TroubleCenterComponent.build(startPiece, pieces, random, p1, p2, p3, facing, p5);
    }
}
