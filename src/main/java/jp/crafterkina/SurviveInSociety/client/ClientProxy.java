/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.client;

import jp.crafterkina.KinaCore.misclib.base.fle.AbstractFMLStateEvent;
import jp.crafterkina.SurviveInSociety.block.EnumBlock;
import jp.crafterkina.SurviveInSociety.item.EnumItem;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends AbstractFMLStateEvent{
    @Override
    public void preInit(FMLPreInitializationEvent event){
        EnumBlock.registerModels();
        EnumItem.registerModels();
    }
}
