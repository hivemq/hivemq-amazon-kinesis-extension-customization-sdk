/*
 * Copyright 2023-present HiveMQ GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
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

package com.hivemq.extensions.amazon.kinesis.api.model;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.Immutable;
import com.hivemq.extension.sdk.api.annotations.NotNull;

import java.nio.ByteBuffer;

/**
 * Represents an Amazon Kinesis record.
 * <p>
 * The internal state of this interface is immutable.
 * <ul>
 * <li>Every returned {@link ByteBuffer} is read only.</li>
 * <li>Every returned  {@code byte[]} is a defensive copy.</li>
 * </ul>
 *
 * @author Mario Schwede
 * @since 4.14.0
 */
@Immutable
@DoNotImplement
public interface KinesisRecord {

    /**
     * @return The name of the Amazon Kinesis stream where this record belongs to.
     * @since 4.14.0
     */
    @NotNull String getStreamName();

    /**
     * @return The data of this record.
     * @since 4.14.0
     */
    @NotNull @Immutable ByteBuffer getData();

    /**
     * @return The data of this record as byte array.
     * @since 4.14.0
     */
    byte @NotNull [] getDataAsByteArray();

    /**
     * @return The partition key of this record.
     * @since 4.14.0
     */
    @NotNull String getPartitionKey();
}
