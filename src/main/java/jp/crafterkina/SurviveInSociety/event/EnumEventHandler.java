/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.event;

import net.minecraftforge.fml.common.eventhandler.EventBus;

public enum EnumEventHandler{
    ;

    public static final EnumEventHandler[] values = values();
    private final Object instance;
    private final EventBus bus;

    EnumEventHandler(){
        this(null, null);
    }

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
