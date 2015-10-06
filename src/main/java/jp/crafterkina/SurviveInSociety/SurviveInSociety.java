/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety;

import jp.crafterkina.KinaCore.misclib.base.fle.IFMLStateEvents;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = SurviveInSociety.PARENT_PACKAGE)
public class SurviveInSociety implements IFMLStateEvents{

    public static final String PARENT_PACKAGE = "jp.crafterkina.SurviveInSociety";

    @Instance
    private static SurviveInSociety instance;

    public static SurviveInSociety getInstance(){
        return instance;
    }


    //Proxy//

    @Override
    @EventHandler
    public void construction(FMLConstructionEvent event){
        for(Proxy proxy : Proxy.values){
            if(proxy.accept(event.getSide())){
                proxy.construction(event);
            }
        }
    }

    @Override
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        for(Proxy proxy : Proxy.values){
            if(proxy.accept(event.getSide())){
                proxy.preInit(event);
            }
        }
    }

    @Override
    @EventHandler
    public void init(FMLInitializationEvent event){
        for(Proxy proxy : Proxy.values){
            if(proxy.accept(event.getSide())){
                proxy.init(event);
            }
        }
    }

    @Override
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        for(Proxy proxy : Proxy.values){
            if(proxy.accept(event.getSide())){
                proxy.postInit(event);
            }
        }
    }

    @Override
    @EventHandler
    public void complete(FMLLoadCompleteEvent event){
        for(Proxy proxy : Proxy.values){
            if(proxy.accept(event.getSide())){
                proxy.complete(event);
            }
        }
    }
}
