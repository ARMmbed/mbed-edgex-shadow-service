/**
 * @file JSONGenerator.java
 * @brief JSON Generator wrapper class
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
package com.arm.pelion.shadow.service.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;

/**
 * JSON Generator wrapper class
 * @author Doug Anson
 */
public class JSONGenerator {

    // default constructor
    public JSONGenerator() {
    }

    // create JSON (Map)
    public String generateJson(Map json) {
        try {
            return new ObjectMapper().writeValueAsString(json);
        }
        catch (JsonProcessingException ex) {
            // silent
        }
        return null;
    }
    
    // create JSON (List)
    public String generateJson(List json) {
        try {
            return new ObjectMapper().writeValueAsString(json);
        }
        catch (JsonProcessingException ex) {
            // silent
        }
        return null;
    }
}
