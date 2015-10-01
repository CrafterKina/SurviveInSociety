/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety;

import jp.crafterkina.KinaCore.misclib.base.fle.AbstractFMLStateEvent;
import jp.crafterkina.SurviveInSociety.block.EnumBlock;
import jp.crafterkina.SurviveInSociety.entity.EnumEntity;
import jp.crafterkina.SurviveInSociety.event.EnumEventHandler;
import jp.crafterkina.SurviveInSociety.guicontainer.EnumGuiContainer;
import jp.crafterkina.SurviveInSociety.item.EnumItem;
import jp.crafterkina.SurviveInSociety.world.structure.EnumStructure;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonProxy extends AbstractFMLStateEvent{
    @Override
    @SubscribeEvent
    public void preInit(FMLPreInitializationEvent event){
        EnumEventHandler.register();
        EnumItem.registerItems();
        EnumBlock.registerBlocks();
        EnumEntity.registerEntity();
        EnumGuiContainer.register();
        EnumStructure.register();
    }
}
