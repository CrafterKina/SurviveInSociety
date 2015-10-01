/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.world;

import jp.crafterkina.SurviveInSociety.world.structure.EnumStructure;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public enum TerrainGenEventHandler{
    instance;

    @SubscribeEvent
    public void pre(PopulateChunkEvent.Pre pre){
        EnumStructure.tryGen(pre);
    }
}
