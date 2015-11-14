/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.client.model.state;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.minecraftforge.common.property.IUnlistedProperty;

/**
 * basic implements of {@link IUnlistedProperty}.
 *
 * @param <T>
 *         contains type.
 * @see net.minecraftforge.common.property.IUnlistedProperty
 */
public abstract class UnlistedPropertyBase<T> implements IUnlistedProperty<T>{
    private final String name;
    private final Predicate<T> validator;

    public UnlistedPropertyBase(String name, Predicate<T> validator){
        this.name = name;
        this.validator = validator;
    }

    public UnlistedPropertyBase(String name){
        this(name, Predicates.<T>alwaysTrue());
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public boolean isValid(T value){
        return validator.apply(value);
    }

    @Override
    public abstract Class<T> getType();

    @Override
    public String valueToString(T value){
        return value.toString();
    }
}
