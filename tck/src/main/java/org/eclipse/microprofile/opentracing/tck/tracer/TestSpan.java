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
package org.eclipse.microprofile.opentracing.tck.tracer;

import java.util.HashMap;
import java.util.Map;

import io.opentracing.Span;
import io.opentracing.SpanContext;

/**
 * Test Tracer.
 */
public class TestSpan implements Span {

    /**
     * Tag key for the span kind.
     */
    public static final String TAG_SPAN_KIND = "span.kind";

    /**
     * Server span kind.
     */
    public static final String SPAN_KIND_SERVER = "server";

    /**
     * Client span kind.
     */
    public static final String SPAN_KIND_CLIENT = "client";

    /**
     * Start time of the span.
     */
    private long startMicros;

    /**
     * End time of the span.
     */
    private long finishMicros;

    /**
     * Trace ID of the span.
     */
    private long traceId;

    /**
     * Parent ID of the span.
     */
    private long parentId;

    /**
     * Span ID.
     */
    private long spanId;

    /**
     * Operation name of the span.
     */
    private String cachedOperationName;

    /**
     * Tags.
     */
    private Map<String, Object> tags = new HashMap<>();

    /**
     * Simulated by the test harness for comparison to the real span.
     */
    private boolean simulated;

    /**
     * Create blank span.
     */
    public TestSpan() {
    }

    /**
     * Create span with a particular kind and operation name.
     * @param spanKind Span kind
     * @param operationName Operation name
     */
    public TestSpan(final SpanKind spanKind, final String operationName) {
        simulated = true;
        tags.put(TAG_SPAN_KIND, spanKind.getTagValue());
        cachedOperationName = operationName;
    }

    /**
     * Start time of the span.
     * @return the startMicros
     */
    public long getStartMicros() {
        return startMicros;
    }

    /**
     * Set the start time of the span.
     * @param newSartMicros the startMicros to set
     */
    public void setStartMicros(final long newSartMicros) {
        this.startMicros = newSartMicros;
    }

    /**
     * End time of the span.
     * @return the finishMicros
     */
    public long getFinishMicros() {
        return finishMicros;
    }

    /**
     * Set the finish time of the span.
     * @param newFinishMicros the finishMicros to set
     */
    public void setFinishMicros(final long newFinishMicros) {
        this.finishMicros = newFinishMicros;
    }

    /**
     * Get the trace ID of the span.
     * @return the traceId
     */
    public long getTraceId() {
        return traceId;
    }

    /**
     * Set the trace ID of the span.
     * @param newTraceId the traceId to set
     */
    public void setTraceId(final long newTraceId) {
        this.traceId = newTraceId;
    }

    /**
     * Get the Parent ID of the span.
     * @return the parentId
     */
    public long getParentId() {
        return parentId;
    }

    /**
     * Set the parent ID of the span.
     * @param newParentId the parentId to set
     */
    public void setParentId(final long newParentId) {
        this.parentId = newParentId;
    }

    /**
     * Get the Span ID.
     * @return the spanId
     */
    public long getSpanId() {
        return spanId;
    }

    /**
     * Set the span ID.
     * @param newSpanId the spanId to set
     */
    public void setSpanId(final long newSpanId) {
        this.spanId = newSpanId;
    }

    /**
     * Get the operation name of the span.
     * @return the operationName
     */
    public String getCachedOperationName() {
        return cachedOperationName;
    }

    /**
     * Set the operation name of the span.
     * @param newCachedOperationName the operationName to set
     */
    public void setCachedOperationName(final String newCachedOperationName) {
        this.cachedOperationName = newCachedOperationName;
    }

    /**
     * Return a map of tags.
     * @return the tags
     */
    public Map<String, Object> getTags() {
        return tags;
    }

    /**
     * Get the span kind tag.
     * @return Span kind.
     */
    public SpanKind spanKind() {
        SpanKind result = SpanKind.MANUAL;
        String tagKind = (String) tags.get(TAG_SPAN_KIND);
        if (SPAN_KIND_SERVER.equals(tagKind)) {
            result = SpanKind.SERVER;
        }
        else if (SPAN_KIND_SERVER.equals(tagKind)) {
            result = SpanKind.CLIENT;
        }
        else {
            throw new IllegalStateException("Unexpected span kind " + tagKind);
        }
        return result;
    }

    /**
     * Set the map of tags.
     * @param newTags the tags to set
     */
    public void setTags(final Map<String, Object> newTags) {
        this.tags = newTags;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpanContext context() {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Span setTag(final String key, final String value) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Span setTag(final String key, final boolean value) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Span setTag(final String key, final Number value) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Span log(final Map<String, ?> fields) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Span log(final long timestampMicroseconds,
            final Map<String, ?> fields) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Span log(final String event) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Span log(final long timestampMicroseconds, final String event) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Span setBaggageItem(final String key, final String value) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBaggageItem(final String key) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Span setOperationName(final String operationName) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Span log(final String eventName, final Object payload) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Span log(final long timestampMicroseconds, final String eventName,
            final Object payload) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void finish() {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void finish(final long newFinishMicros) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (!simulated) {
            return "{ " + "startMicros: " + startMicros
                    + ", finishMicros: " + finishMicros + ", traceId: "
                    + traceId + ", parentId: " + parentId + ", spanId: "
                    + spanId + ", operationName: " + cachedOperationName
                    + ", tags: " + tags + " }";
        }
        else {
            // Only print the parts that are checked in equals
            return "{ " + "operationName: "
                    + cachedOperationName
                    + ", kind: " + tags.get(TAG_SPAN_KIND) + " }";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        TestSpan otherSpan = (TestSpan) obj;
        if (otherSpan != null) {
            if (!cachedOperationName.equals(otherSpan.cachedOperationName)) {
                System.out.println("Operation names don't match: "
                        + cachedOperationName + " ; "
                        + otherSpan.cachedOperationName);
                return false;
            }
            else if (spanKind() != otherSpan.spanKind()) {
                System.out.println("Span kinds don't match: "
                        + spanKind() + " ; " + otherSpan.spanKind());
                return false;
            }
            return true;
        }
        return super.equals(obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
