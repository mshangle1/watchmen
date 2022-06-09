/*
 * Copyright 2022 Ally Financial, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.ally.d3.watchmen.steps;

import com.ally.d3.watchmen.utilities.RequestHelper;
import com.ally.d3.watchmen.utilities.RestAssuredHelper;
import com.ally.d3.watchmen.utilities.dataDriven.PlaceholderResolve;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Component

//This object need to share state between steps on one scenario
//all variables will be reset for every new scenario on Before hook

public class TestScope {

    public static ValidatableResponse responseToValidate;

    public static ResponseOptions<Response> response;

    public static RequestSpecification requestSpec;

    public static StringBuilder requestBasePath =  new StringBuilder();

    public static Map<String, String> container = new HashMap<>();

    public static Map<String, JsonNode> jsonContainer = new HashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(TestScope.class);



    @Autowired
    RestAssuredHelper restAssuredHelper;

    @Value("${useProxy:false}")
    private Boolean useProxy;

    //To avoid leaking between scenarios need to reset all variables
    public void reset (){

        response = null;
        responseToValidate =null;
        requestBasePath =  new StringBuilder();
        //bodyJsonTree=null;
        //bodyXmlDoc=null;
        container = new HashMap<>();
        jsonContainer = new HashMap<>();
        requestSpec = restAssuredHelper.startNewRequestSpecification(useProxy);
    }

    //If you need to chain few API calls in one scenario
    public void resetPreviousAPICall(){

        response = null;
        requestSpec = restAssuredHelper.startNewRequestSpecification(useProxy);
        responseToValidate =null;
        requestBasePath =  new StringBuilder();
        //bodyJsonTree=null;
        //bodyXmlDoc=null;
    }

    public void resetPreviousAPICallAndSwitchProxy(){

        response = null;
        requestSpec = restAssuredHelper.startNewRequestSpecification(!useProxy);
        responseToValidate =null;
        requestBasePath =  new StringBuilder();
        //bodyJsonTree=null;
        //bodyXmlDoc=null;
    }

    public void saveInContainer(String key, String  val){

        container.put(key,val);
        logger.debug("Save in container "+key+" = "+val);
        logger.trace(" \nSave in container "+key+" = "+val);
        logger.info(" \nSave in container "+key+" = "+val);
    }

    public String getFromContainer(String key){

        if (container.containsKey(key)) return container.get(key);
        else {
            logger.error("Was not able to get "+key+" from Scenario Scope");
            throw new RuntimeException("was not able to get "+key+" from Scenario Scope");
        }

    }

    public void saveInJsonContainer(String key, JsonNode  json){

        jsonContainer.put(key,json);
        logger.debug("Save to JSON container "+key);
        logger.debug("Save to the JSON container "+key);
    }

    public JsonNode getFromJsonContainer(String key){

        if (jsonContainer.containsKey(key)) return jsonContainer.get(key);
        else {
            System.out.println("Was not able to get "+key+" from Json Container");
            logger.error("Was not able to get "+key+" from Json Container");
            throw new RuntimeException("was not able to get "+key+" from Json Container");
        }

    }

}


