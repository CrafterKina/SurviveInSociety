/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.*;

/**
 * the entry point of this mod. pass all of the processing to the proxy.
 */
@Mod(modid = SurviveInSociety.PARENT_PACKAGE)
public class SurviveInSociety{

    public static final String PARENT_PACKAGE = "jp.crafterkina.SurviveInSociety";

    @Instance
    private static SurviveInSociety instance;

    public static SurviveInSociety getInstance(){
        return instance;
    }


    //Proxy//

    @EventHandler
    public void construction(FMLConstructionEvent event){
        for(Proxy proxy : Proxy.values){
            if(proxy.accept(event.getSide())){
                proxy.construction(event);
            }
        }
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        for(Proxy proxy : Proxy.values){
            if(proxy.accept(event.getSide())){
                proxy.preInit(event);
            }
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
        for(Proxy proxy : Proxy.values){
            if(proxy.accept(event.getSide())){
                proxy.init(event);
            }
        }
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        for(Proxy proxy : Proxy.values){
            if(proxy.accept(event.getSide())){
                proxy.postInit(event);
            }
        }
    }

    @EventHandler
    public void complete(FMLLoadCompleteEvent event){
        for(Proxy proxy : Proxy.values){
            if(proxy.accept(event.getSide())){
                proxy.complete(event);
            }
        }
    }
}
