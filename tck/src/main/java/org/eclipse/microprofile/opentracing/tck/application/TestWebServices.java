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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.opentracing.tck.tracer.TestSpan;
import org.eclipse.microprofile.opentracing.tck.tracer.TestTracer;

import io.opentracing.Tracer;

/**
 * Test JAXRS web services.
 */
@Path(TestWebServices.TEST_WS_PATH)
public class TestWebServices {

    /**
     * The path to this set of web services.
     */
    public static final String TEST_WS_PATH = "testServices";

    /**
     * Content returned for the simpleTest web service.
     */
    public static final String SIMPLE_TEST_CONTENT = "Hello World";

    /**
     * Mock tracer.
     */
    @Inject
    private Tracer tracer;

    /**
     * Test class-level Trace annotations.
     */
    @Inject
    private TestClassAnnotation testClassAnnotationApp;

    /**
     * Test method-level Trace annotations.
     */
    @Inject
    private TestMethodAnnotations testMethodAnnotationApp;

    /**
     * Hello world service.
     * @return Hello World string
     */
    @GET
    @Path(TestWebServicesApplication.REST_SIMPLE_TEST)
    @Produces(MediaType.TEXT_PLAIN)
    public String simpleTest() {
        return SIMPLE_TEST_CONTENT;
    }

    /**
     * Get details about completed spans.
     * Returns a
     * {@link org.eclipse.microprofile.opentracing.tck.tracer.TestTracer}
     * which has information on the spans.
     * @return Injected tracer
     * @throws SecurityException Reflection issues
     * @throws NoSuchMethodException Reflection issues
     * @throws InvocationTargetException Reflection issues
     * @throws IllegalArgumentException Reflection issues
     * @throws IllegalAccessException Reflection issues
     */
    @SuppressWarnings("unchecked")
    @GET
    @Path(TestWebServicesApplication.REST_GET_TRACER)
    @Produces(MediaType.APPLICATION_JSON)
    public Tracer getTracer() throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        TestTracer testTracer = new TestTracer();
        List<TestSpan> spans = new ArrayList<TestSpan>();

        Iterable<?> finishedSpans = (Iterable<?>) tracer.getClass()
                .getMethod("finishedSpans").invoke(tracer);
        for (Object finishedSpan : finishedSpans) {
            TestSpan testSpan = new TestSpan();

            testSpan.setStartMicros((Long) finishedSpan.getClass()
                    .getMethod("startMicros").invoke(finishedSpan));

            testSpan.setFinishMicros((Long) finishedSpan.getClass()
                    .getMethod("finishMicros").invoke(finishedSpan));

            testSpan.setCachedOperationName((String) finishedSpan.getClass()
                    .getMethod("operationName").invoke(finishedSpan));

            testSpan.setParentId((Long) finishedSpan.getClass()
                    .getMethod("parentId").invoke(finishedSpan));

            Object context = finishedSpan.getClass()
                    .getMethod("context").invoke(finishedSpan);

            testSpan.setSpanId((Long) context.getClass()
                    .getMethod("spanId").invoke(context));

            testSpan.setTraceId((Long) context.getClass()
                    .getMethod("traceId").invoke(context));

            testSpan.setTags((Map<String, Object>) finishedSpan
                    .getClass().getMethod("tags").invoke(finishedSpan));

            if (testSpan.getCachedOperationName() == null
                    || !testSpan.getCachedOperationName().endsWith(
                            TestWebServicesApplication.REST_CLEAR_TRACER)) {
                spans.add(testSpan);
            }
        }

        testTracer.setSpans(spans);
        return testTracer;
    }

    /**
     * Clear accumulated spans in the Tracer.
     * @throws IllegalAccessException Reflection issues
     * @throws IllegalArgumentException Reflection issues
     * @throws InvocationTargetException Reflection issues
     * @throws NoSuchMethodException Reflection issues
     * @throws SecurityException Reflection issues
     */
    @GET
    @Path(TestWebServicesApplication.REST_CLEAR_TRACER)
    public void clearTracer() throws IllegalAccessException,
            IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException {
        tracer.getClass().getMethod("reset").invoke(tracer);
    }
}
