/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety;

import jp.crafterkina.KinaCore.misclib.base.fle.IFMLStateEvents;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = SurviveInSociety.PARENT_PACKAGE)
public class SurviveInSociety implements IFMLStateEvents{

    public static final String PARENT_PACKAGE = "jp.crafterkina.SurviveInSociety";

    @SidedProxy(modId = PARENT_PACKAGE, clientSide = PARENT_PACKAGE + "client.ClientProxy", serverSide = PARENT_PACKAGE + "common.CommonProxy")
    private static IFMLStateEvents proxy;

    @Instance
    private static SurviveInSociety instance;

    public static SurviveInSociety getInstance(){
        return instance;
    }


    //Proxy//

    @Override
    @EventHandler
    public void construction(FMLConstructionEvent event){
        proxy.construction(event);
    }

    @Override
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        proxy.preInit(event);
    }

    @Override
    @EventHandler
    public void init(FMLInitializationEvent event){
        proxy.init(event);
    }

    @Override
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        proxy.postInit(event);
    }

    @Override
    @EventHandler
    public void complete(FMLLoadCompleteEvent event){
        proxy.complete(event);
    }
}
