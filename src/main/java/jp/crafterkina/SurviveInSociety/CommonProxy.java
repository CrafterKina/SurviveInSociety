/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety;

import jp.crafterkina.KinaCore.misclib.base.fle.AbstractFMLStateEvent;
import jp.crafterkina.SurviveInSociety.event.EnumEventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonProxy extends AbstractFMLStateEvent{
    @Override
    @SubscribeEvent
    public void preInit(FMLPreInitializationEvent event){
        EnumEventHandler.register();
    }
}
