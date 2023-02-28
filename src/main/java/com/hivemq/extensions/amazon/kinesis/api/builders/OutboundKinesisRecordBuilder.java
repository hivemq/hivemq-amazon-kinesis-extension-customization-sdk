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

package com.hivemq.extensions.amazon.kinesis.api.builders;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extensions.amazon.kinesis.api.model.OutboundKinesisRecord;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * The {@link OutboundKinesisRecordBuilder} enables the creation of {@link OutboundKinesisRecord}s via its fluent API.
 * <p>
 * Make sure that at least {@code streamName}, {@code data} and {@code partitionKey} is set before calling
 * {@link OutboundKinesisRecordBuilder#build()}.
 * <p>
 * The internal state of this interface can only be changed via its methods. All arguments, that have mutable data
 * types, are deep copied before the setting method returns.
 *
 * @author Mario Schwede
 * @since 4.14.0
 */
@DoNotImplement
public interface OutboundKinesisRecordBuilder {

    /**
     * Set the {@code streamName} of the Amazon Kinesis record. This is required to successfully build a
     * {@link OutboundKinesisRecord}.
     * <p>
     * Must conform to the following guidelines:
     * <ul>
     *     <li>Contain between 1 and 128 characters</li>
     *     <li>Contain only the following characters<ul>
     *          <li>Letters [A-Za-z]</li>
     *          <li>numbers [0-9]</li>
     *          <li>dashes -</li>
     *          <li>underscores _</li>
     *          <li>periods .</li>
     *      </ul></li>
     * </ul>
     * <p>
     *
     * @param streamName The name of the Amazon Kinesis stream.
     * @return This builder.
     * @throws NullPointerException     If {@code streamName} is null.
     * @throws IllegalArgumentException If {@code streamName} is not conform with the guidelines listed above.
     * @since 4.14.0
     */
    @NotNull OutboundKinesisRecordBuilder streamName(@NotNull String streamName);

    /**
     * Set the {@code data} of the Amazon Kinesis record.
     *
     * @param data The value of the data.
     * @return This builder.
     * @throws NullPointerException     If {@code data} is null.
     * @throws IllegalArgumentException If {@code data} exceeds the max size of 1,048,576 bytes (1MB).
     * @since 4.14.0
     */
    @NotNull OutboundKinesisRecordBuilder data(@NotNull ByteBuffer data);

    /**
     * Set the {@code data} of the Amazon Kinesis record.
     *
     * @param data The value of the data.
     * @return This builder.
     * @throws NullPointerException     If {@code data} is null.
     * @throws IllegalArgumentException If {@code data} exceeds the max size of 1,048,576 bytes (1MB).
     * @since 4.14.0
     */
    @NotNull OutboundKinesisRecordBuilder data(byte @NotNull [] data);

    /**
     * Set the {@code data} of the Amazon Kinesis record.
     *
     * @param data The value of the data {@link java.nio.charset.StandardCharsets#UTF_8} is used for encoding.
     * @return This builder.
     * @throws NullPointerException     If {@code data} is null.
     * @throws IllegalArgumentException If {@code data} exceeds the max size of 1,048,576 bytes (1MB).
     * @since 4.14.0
     */
    @NotNull OutboundKinesisRecordBuilder data(@NotNull String data);

    /**
     * Set the {@code data} of the Amazon Kinesis record.
     *
     * @param data    The value of the data.
     * @param charset The {@link Charset} used for encoding.
     * @return This builder.
     * @throws NullPointerException     If {@code data} or {@code charset} is null.
     * @throws IllegalArgumentException If {@code data} exceeds the max size of 1,048,576 bytes (1MB).
     * @since 4.14.0
     */
    @NotNull OutboundKinesisRecordBuilder data(@NotNull String data, @NotNull Charset charset);


    /**
     * Set the {@code partitionKey} of the Amazon Kinesis record.
     * <p>
     * The MD5 hash of the {@code partitionKey} determines the shard in the destination Amazon Kinesis data stream.
     * If you also set {@code explicitHashKey}, the {@code explicitHashKey} determines the destination shard instead.
     *
     * @param partitionKey The partitionKey.
     * @return This builder.
     * @throws NullPointerException     If {@code partitionKey} is null.
     * @throws IllegalArgumentException If {@code partitionKey} is empty.
     * @throws IllegalArgumentException If {@code partitionKey} exceeds the max size of 256 chars.
     * @since 4.14.0
     */
    @NotNull OutboundKinesisRecordBuilder partitionKey(@NotNull String partitionKey);

    /**
     * Set the {@code partitionKey} of the Amazon Kinesis record to a random value.
     * <p>
     * The MD5 hash of the {@code partitionKey} determines the shard in the destination Amazon Kinesis data stream.
     * If you also set {@code explicitHashKey}, the {@code explicitHashKey} determines the destination shard instead.
     *
     * @return This builder.
     * @since 4.14.0
     */
    @NotNull OutboundKinesisRecordBuilder randomPartitionKey();

    /**
     * Set the {@code explicitHashKey} of the Amazon Kinesis record.
     * <p>
     * Explicitly defines the hash key for the shard assignment in the destination Amazon Kinesis Data Stream.
     * This setting overrides the {@code partitionKey} shard assignment.
     *
     * @param explicitHashKey The explicitHashKey.
     * @return This builder.
     * @throws NullPointerException     If {@code explicitHashKey} is null.
     * @throws IllegalArgumentException If {@code partitionKey} < 0 or >= 2^128.
     * @since 4.14.0
     */
    @NotNull OutboundKinesisRecordBuilder explicitHashKey(@NotNull BigInteger explicitHashKey);

    /**
     * Set the {@code explicitHashKey} of the Amazon Kinesis record to a random value.
     * <p>
     * Explicitly defines the hash key for the shard assignment in the destination Amazon Kinesis Data Stream.
     * This setting overrides the {@code partitionKey} shard assignment.
     *
     * @return This builder.
     * @since 4.14.0
     */
    @NotNull OutboundKinesisRecordBuilder randomExplicitHashKey();

    /**
     * Create a new {@link OutboundKinesisRecord} from the current state of this builder. The builder can be reused
     * afterwards.
     *
     * @return A new {@link OutboundKinesisRecord} containing a snapshot of the current state of this builder.
     * @throws IllegalStateException If {@code streamName}, {@code data} or {@code partitionKey} was not set.
     * @since 4.14.0
     */
    @NotNull OutboundKinesisRecord build();
}
