/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety;

import jp.crafterkina.SurviveInSociety.block.EnumBlock;
import jp.crafterkina.SurviveInSociety.client.model.SISModelLoaderRegistrar;
import jp.crafterkina.SurviveInSociety.entity.EnumEntity;
import jp.crafterkina.SurviveInSociety.event.EnumEventHandler;
import jp.crafterkina.SurviveInSociety.guicontainer.EnumGuiContainer;
import jp.crafterkina.SurviveInSociety.internal.SISInformation;
import jp.crafterkina.SurviveInSociety.item.EnumItem;
import jp.crafterkina.SurviveInSociety.world.structure.EnumStructure;
import jp.crafterkina.SurviveInSociety.world.village.EnumVillageStructure;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;

public enum Proxy{
    Common{
        @Override
        public void preInit(FMLPreInitializationEvent event){
            SISInformation.setLogger(event.getModLog());
            EnumEventHandler.register();
            EnumItem.registerItems();
            EnumBlock.registerBlocks();
            EnumEntity.registerEntity();
            EnumGuiContainer.register();
            EnumStructure.register();
            EnumVillageStructure.register();
        }
    },
    //@SideOnly(Side.SERVER)
    Server{
        @Override
        public boolean accept(Side side){
            return side.isServer();
        }
    },
    //@SideOnly(Side.CLIENT)
    Client{
        @Override
        public void init(FMLInitializationEvent event){
            EnumBlock.registerModels();
            EnumItem.registerModels();
            EnumEntity.registerRender();
            SISModelLoaderRegistrar.register();
        }

        @Override
        public boolean accept(Side side){
            return side.isClient();
        }
    };
    public static final Proxy[] values = values();

    public void construction(FMLConstructionEvent event){

    }

    public void preInit(FMLPreInitializationEvent event){

    }

    public void init(FMLInitializationEvent event){

    }

    public void postInit(FMLPostInitializationEvent event){

    }

    public void complete(FMLLoadCompleteEvent event){

    }

    public boolean accept(Side side){
        return true;
    }
}
