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
import com.hivemq.extension.sdk.api.annotations.ThreadSafe;
import com.hivemq.extension.sdk.api.services.publish.Publish;
import com.hivemq.extensions.amazon.kinesis.api.model.InboundKinesisRecord;

/**
 * Implement this transformer for the programmatic creation of
 * {@link Publish}es from {@link InboundKinesisRecord}s. One
 * instance of the implementing class is created per reference in the amazon-kinesis-configuration.xml. The methods
 * of this interface may be called concurrently and must be thread-safe.
 * <p>
 * Your implementation of the KinesisToMqttTransformer must be placed in a Java archive (.jar) together with all its
 * dependencies in the {@code customizations} folder of the "HiveMQ Enterprise Extension for Amazon Kinesis". In
 * addition, a {@code <kinesis-to-mqtt-transformer>} referencing the implementing class via its canonical name must be
 * configured in the {@code amazon-kinesis-configuration.xml} file.
 *
 * @author Mario Schwede
 * @since 4.14.0
 */
@FunctionalInterface
public interface KinesisToMqttTransformer {

    /**
     * Override the init method to initialize the transformer.
     *
     * @param kinesisToMqttInitInput The {@code kinesisToMqttInitInput}
     * @since 4.14.0
     */
    default void init(final @NotNull KinesisToMqttInitInput kinesisToMqttInitInput) {
    }

    /**
     * This callback is executed for every {@link InboundKinesisRecord} that the "HiveMQ Enterprise Extension for
     * Amazon
     * Kinesis" polls from Amazon Kinesis according to the configured {@code <kinesis-consumers>}
     * in the {@code <kinesis-to-mqtt-transformer>} tag. It allows the publication of any number of
     * {@link Publish}es via the {@link KinesisToMqttOutput}
     * object. This method is called by multiple threads concurrently. Extensions are responsible for their own
     * exception handling and this method must not throw any {@link Exception}.
     *
     * @param kinesisToMqttInput  The {@link KinesisToMqttInput} contains the triggering {@link InboundKinesisRecord}.
     * @param kinesisToMqttOutput The {@link KinesisToMqttOutput} allows to
     *                            {@link KinesisToMqttOutput#setPublishes(java.util.List)}.
     *                            If no output is set, an empty List is used as default and the Kinesis records will
     *                            not be processed again, but ignored.
     * @since 4.14.0
     */
    @ThreadSafe
    void transformKinesisToMqtt(
            @NotNull KinesisToMqttInput kinesisToMqttInput, @NotNull KinesisToMqttOutput kinesisToMqttOutput);
}
