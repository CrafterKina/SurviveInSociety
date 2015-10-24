/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.client.model.state;

import com.google.common.base.Predicate;
import net.minecraft.client.resources.model.ModelRotation;

public class PropertyModelRotation extends UnlistedPropertyBase<ModelRotation>{
    public PropertyModelRotation(String name){
        super(name);
    }

    public PropertyModelRotation(String name, Predicate<ModelRotation> validator){
        super(name, validator);
    }

    @Override
    public Class<ModelRotation> getType(){
        return ModelRotation.class;
    }
}
