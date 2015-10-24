/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.client.model.state;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraftforge.common.property.IUnlistedProperty;

public class ModelRotationProperty implements IUnlistedProperty<ModelRotation>{
    private final String name;
    private final Predicate<ModelRotation> validator;

    public ModelRotationProperty(String name, Predicate<ModelRotation> validator){
        this.name = name;
        this.validator = validator;
    }

    public ModelRotationProperty(String name){
        this(name, Predicates.<ModelRotation>alwaysTrue());
    }


    @Override
    public String getName(){
        return name;
    }

    @Override
    public boolean isValid(ModelRotation value){
        return validator.apply(value);
    }

    @Override
    public Class<ModelRotation> getType(){
        return ModelRotation.class;
    }

    @Override
    public String valueToString(ModelRotation value){
        return value.toString();
    }
}
