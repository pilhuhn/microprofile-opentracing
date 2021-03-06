/*
 * Copyright (c) 2017 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eclipse.microprofile.opentracing.tck.application;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 * Test web services JAXRS application.
 */
@ApplicationPath(TestWebServicesApplication.TEST_WEB_SERVICES_CONTEXT_ROOT)
public class TestWebServicesApplication extends Application {

    /**
     * The context root of JAXRS web services.
     */
    public static final String TEST_WEB_SERVICES_CONTEXT_ROOT = "rest";

    /**
     * Web Service endpoint for the simpleTest call.
     */
    public static final String REST_SIMPLE_TEST = "simpleTest";

    /**
     * Web Service endpoint for the getTracer call.
     */
    public static final String REST_GET_TRACER = "getTracer";

    /**
     * Web Service endpoint for the clearTracer call.
     */
    public static final String REST_CLEAR_TRACER = "clearTracer";

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(Arrays.asList(TestWebServices.class,
                JacksonJsonProvider.class));
    }
}
