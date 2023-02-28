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

import com.codahale.metrics.MetricRegistry;
import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.Immutable;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extensions.amazon.kinesis.api.model.CustomSettings;

/**
 * The general interface for the input objects.
 *
 * @author Mario Schwede
 * @since 4.14.0
 */
@Immutable
@DoNotImplement
public interface TransformerInput {

    /**
     * Get the {@link MetricRegistry} of this HiveMQ node. It is possible to add own metrics to monitor custom business
     * logic.
     *
     * @return The {@link MetricRegistry} of the HiveMQ node this "Enterprise Extension for Amazon Kinesis" is
     *         running on.
     * @since 4.14.0
     */
    @NotNull MetricRegistry getMetricRegistry();

    /**
     * @return The {@link CustomSettings} this transformer is associated with.
     * @since 4.14.0
     */
    @NotNull CustomSettings getCustomSettings();
}
