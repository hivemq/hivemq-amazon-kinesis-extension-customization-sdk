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

import java.time.Instant;

/**
 * Represents an inbound Amazon Kinesis record that was read from Kinesis.
 * <p>
 * The internal state of this interface is immutable.
 *
 * @author Mario Schwede
 * @since 4.14.0
 */
@Immutable
@DoNotImplement
public interface InboundKinesisRecord extends KinesisRecord {

    /**
     * @return The unique identifier of the record within its shard.
     * @since 4.14.0
     */
    @NotNull String getSequenceNumber();

    /**
     * @return The approximate time that the record was inserted into the stream.
     * @since 4.14.0
     */
    @NotNull Instant getApproximateArrivalTimestamp();

    /**
     * @return The encryption type used on the record. Currently, known values are {@code NONE} (no encryption) and
     *         {@code KMS} (server-side encryption by a KMS key).
     * @since 4.14.0
     */
    @NotNull String getEncryptionType();
}
