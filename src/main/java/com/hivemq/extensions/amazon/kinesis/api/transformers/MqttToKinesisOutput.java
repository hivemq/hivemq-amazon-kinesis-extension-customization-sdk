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

package com.hivemq.extensions.amazon.kinesis.api.transformers;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extensions.amazon.kinesis.api.builders.OutboundKinesisRecordBuilder;
import com.hivemq.extensions.amazon.kinesis.api.model.OutboundKinesisRecord;

import java.util.List;

/**
 * The output parameter of the {@link MqttToKinesisTransformer}. It allows access to the
 * {@link OutboundKinesisRecordBuilder}.
 * <p>
 * After the {@link MqttToKinesisTransformer#transformMqttToKinesis(MqttToKinesisInput, MqttToKinesisOutput)} method
 * returns
 * the {@link OutboundKinesisRecord}s given to this output will be published to Amazon Kinesis by the HiveMQ
 * Enterprise Extension for Amazon Kinesis.
 *
 * @author Mario Schwede
 * @since 4.14.0
 */
@DoNotImplement
public interface MqttToKinesisOutput {

    /**
     * Create a new {@link OutboundKinesisRecordBuilder}. A single {@link OutboundKinesisRecordBuilder} can be used to
     * build multiple Amazon Kinesis records.
     *
     * @return An empty instance of the {@link OutboundKinesisRecordBuilder}.
     * @since 4.14.0
     */
    @NotNull OutboundKinesisRecordBuilder newOutboundKinesisRecordBuilder();

    /**
     * Sets the {@link OutboundKinesisRecord}s, that will be pushed to Amazon Kinesis after the
     * {@link MqttToKinesisTransformer#transformMqttToKinesis(MqttToKinesisInput, MqttToKinesisOutput)} call returns.
     * The
     * "HiveMQ Enterprise Extension for Amazon Kinesis" will publish the records in the order provided by the
     * {@code outboundKinesisRecords} argument.
     * <p>
     * If desired, the same records can occupy multiple places in the {@code outboundKinesisRecords} list. When no
     * record shall be pushed to Amazon Kinesis for a given
     * {@link com.hivemq.extension.sdk.api.packets.publish.PublishPacket}, provide an empty list or just don't call
     * this
     * method.
     * <p>
     * Use the {@link OutboundKinesisRecordBuilder} to create new records as desired.
     * <p>
     * Each additional call of this method will overwrite the previous one.
     *
     * @param outboundKinesisRecords A list of to be published {@link OutboundKinesisRecord}s.
     * @throws NullPointerException     If {@code outboundKinesisRecords} or any element of it is null.
     * @throws IllegalArgumentException If any element in {@code outboundKinesisRecords} was not created via a
     *                                  {@link OutboundKinesisRecordBuilder}.
     * @since 4.14.0
     */
    void setOutboundKinesisRecords(@NotNull List<@NotNull OutboundKinesisRecord> outboundKinesisRecords);
}
