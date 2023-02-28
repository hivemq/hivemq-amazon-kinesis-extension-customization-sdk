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

import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extensions.amazon.kinesis.api.model.OutboundKinesisRecord;

import java.util.List;

/**
 * Implement this transformer for the programmatic creation of {@link OutboundKinesisRecord}s from
 * {@link com.hivemq.extension.sdk.api.packets.publish.PublishPacket}s.
 * <p>
 * Your implementation of the MqttToKinesisTransformer must be placed in a java archive (.jar) together with all its
 * dependencies in the {@code customizations} folder of the HiveMQ Enterprise Extension for Amazon Kinesis. In addition,
 * a {@code <mqtt-to-kinesis-transformer>} referencing the implementing class via its canonical name must be configured
 * in the {@code amazon-kinesis-configuration.xml} file.
 *
 * @author Mario Schwede
 * @since 4.14.0
 */
@FunctionalInterface
public interface MqttToKinesisTransformer {

    /**
     * Override the init method to initialize the transformer.
     *
     * @param mqttToKinesisInitInput The {@code mqttToKinesisInitInput}
     * @since 4.14.0
     */
    default void init(final @NotNull MqttToKinesisInitInput mqttToKinesisInitInput) {
    }

    /**
     * This callback is executed for every MQTT PUBLISH that arrives at your HiveMQ cluster matching the
     * {@code <mqtt-topic-filters>} tag configured in the {@code <mqtt-to-kinesis-route>}. It allows the
     * publication of any number of {@link OutboundKinesisRecord}s via the {@link MqttToKinesisOutput} object.
     *
     * @param mqttToKinesisInput  The {@link MqttToKinesisInput} contains the triggering
     *                            {@link com.hivemq.extension.sdk.api.packets.publish.PublishPacket} information.
     * @param mqttToKinesisOutput Pass the list of new {@link OutboundKinesisRecord}s to the
     *                            {@link MqttToKinesisOutput#setOutboundKinesisRecords(List)} method.
     * @since 4.14.0
     */
    void transformMqttToKinesis(
            @NotNull MqttToKinesisInput mqttToKinesisInput, @NotNull MqttToKinesisOutput mqttToKinesisOutput);
}
