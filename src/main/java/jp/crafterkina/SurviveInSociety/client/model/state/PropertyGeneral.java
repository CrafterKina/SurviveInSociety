/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.client.model.state;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

/**
 * use easier than {@link UnlistedPropertyBase}
 *
 * @param <T>
 *         contains type.
 * @see jp.crafterkina.SurviveInSociety.client.model.state.UnlistedPropertyBase
 * @see net.minecraftforge.common.property.IUnlistedProperty
 */
public class PropertyGeneral<T> extends UnlistedPropertyBase<T>{
    private final Class<T> type;

    public PropertyGeneral(String name, Class<T> type){
        this(name, Predicates.<T>alwaysTrue(), type);
    }

    public PropertyGeneral(String name, Predicate<T> validator, Class<T> type){
        super(name, validator);
        this.type = type;
    }

    @Override
    public Class<T> getType(){
        return type;
    }
}
