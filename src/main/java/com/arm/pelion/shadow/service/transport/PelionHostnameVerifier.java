/**
 * @file PelionHostnameVerifier.java
 * @brief Pelion Hostname Verifier (SSL)
 * @author Doug Anson
 * @version 1.0
 * @see
 *
 * Copyright 2015-2018. ARM Ltd. All rights reserved.
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
package com.arm.pelion.shadow.service.transport;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * Pelion Hostname verifier for SSL
 * @author Doug Anson
 */
public class PelionHostnameVerifier implements HostnameVerifier {
    private String m_pelion = null;
    
    // default constructor
    public PelionHostnameVerifier(String pelion) {
        this.m_pelion = pelion;
    }
    
    // we confirm that the hostname matches our configuration expectations...
    @Override
    public boolean verify(String hostname, SSLSession ssl) {
        if (hostname != null && hostname.length() > 0) {
            if (hostname.equalsIgnoreCase(this.m_pelion)) {
                return true;
            }
        }
        return false;
    }
    
}
