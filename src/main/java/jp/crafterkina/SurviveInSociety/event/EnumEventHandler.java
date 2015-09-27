/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.event;

import jp.crafterkina.SurviveInSociety.client.eventhandler.ClientEventHandler;
import jp.crafterkina.SurviveInSociety.event.handler.CommonEventHandler;
import jp.crafterkina.SurviveInSociety.event.handler.FMLEventHandler;
import jp.crafterkina.SurviveInSociety.event.handler.OreGenEventHandler;
import jp.crafterkina.SurviveInSociety.event.handler.TerrainGenEventHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.EventBus;

import static net.minecraftforge.common.MinecraftForge.*;

public enum EnumEventHandler{
    Common(CommonEventHandler.instance, EVENT_BUS),
    TerrainGen(TerrainGenEventHandler.instance, TERRAIN_GEN_BUS),
    OreGen(OreGenEventHandler.instance, ORE_GEN_BUS),
    FML(FMLEventHandler.instance, FMLCommonHandler.instance().bus()),
    Client(ClientEventHandler.instance, EVENT_BUS);

    public static final EnumEventHandler[] values = values();
    private final Object instance;
    private final EventBus bus;

    EnumEventHandler(Object instance, EventBus bus){
        this.instance = instance;
        this.bus = bus;
    }

    public static void register(){
        for(EnumEventHandler handler : values){
            handler.getBus().register(handler.getInstance());
        }
    }

    protected Object getInstance(){
        return instance;
    }

    protected EventBus getBus(){
        return bus;
    }
}
