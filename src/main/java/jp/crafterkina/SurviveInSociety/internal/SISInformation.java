/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.internal;

import org.apache.logging.log4j.Logger;

public class SISInformation{
    private static Logger logger = null;

    public static Logger getLogger(){
        return logger;
    }

    public static void setLogger(Logger logger){
        if(SISInformation.logger != null) return;
        SISInformation.logger = logger;
    }
}
