/**
 * @file    Manager.java
 * @brief Servlet Manager
 * @author Doug Anson
 * @version 1.0
 * @see
 *
 * Copyright 2015. ARM Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.arm.mbed.edgex.shadow.service.servlet;

import com.arm.mbed.edgex.shadow.service.core.ErrorLogger;
import com.arm.mbed.edgex.shadow.service.core.Utils;
import com.arm.mbed.edgex.shadow.service.interfaces.mbedShadowProcessorInterface;
import com.arm.mbed.edgex.shadow.service.orchestrator.Orchestrator;
import com.arm.mbed.edgex.shadow.service.preferences.PreferenceManager;
import com.arm.mbed.edgex.shadow.service.processors.edgecore.mbedEdgeCoreServiceProcessor;
import com.arm.mbed.edgex.shadow.service.processors.edgex.EdgeXServiceProcessor;

/**
 * Main Servlet Manager
 *
 * @author Doug Anson
 */
public final class Manager {
    private Orchestrator m_orchestrator = null;
    private ErrorLogger m_error_logger = null;
    private PreferenceManager m_preference_manager = null;

    // default constructor
    @SuppressWarnings("empty-statement")
    public Manager(ErrorLogger error_logger,PreferenceManager preferences) {
        // save the error handler
        this.m_error_logger = error_logger;
        this.m_preference_manager = preferences;

        // announce our self
        this.errorLogger().warning("mbed EdgeX Shadow Service: Date: " + Utils.dateToString(Utils.now()));

        // configure the error logger logging level
        this.m_error_logger.configureLoggingLevel(this.m_preference_manager);
        
        // create the orchestrator
        this.m_orchestrator = new Orchestrator(this.m_error_logger,this.m_preference_manager);
        
        // create the mbed shadow service Processor
        mbedShadowProcessorInterface msp = new mbedEdgeCoreServiceProcessor(this.m_error_logger,this.m_preference_manager);
        
        // add our EdgeX event processor
        EdgeXServiceProcessor edgex = new EdgeXServiceProcessor(this.m_error_logger,this.m_preference_manager,msp);
        
        // bind in orchestrator
        this.m_orchestrator.setMbedEdgeCoreServiceProcessor(msp);
        this.m_orchestrator.setEdgeXServiceProcessor(edgex);
    }

    // get the error logger
    public ErrorLogger errorLogger() {
        return this.m_error_logger;
    }

    // get the preferences db instance
    public final PreferenceManager preferences() {
        return this.m_preference_manager;
    }
    
    // initialize
    public boolean initialize() {
       return this.m_orchestrator.initialize();
    }
    
    // closedown
    public void closedown() {
        this.m_orchestrator.closedown();
    }
    
    // run loop
    public void iterate() {
        this.m_orchestrator.iterate();
    }
}
