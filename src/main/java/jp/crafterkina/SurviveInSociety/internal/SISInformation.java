/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.internal;

import org.apache.logging.log4j.Logger;

public class SISInformation{
    private static Logger logger = null;

    private SISInformation(){}

    /**
     * @return the logger of this mod.
     */
    public static Logger getLogger(){
        return logger;
    }

    /**
     * setting the logger of this mod only once.
     *
     * @param logger
     *         to set
     */
    public static void setLogger(Logger logger){
        if(SISInformation.logger != null) return;
        SISInformation.logger = logger;
    }
}
