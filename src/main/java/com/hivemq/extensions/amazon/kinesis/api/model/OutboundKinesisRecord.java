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

import java.math.BigInteger;
import java.util.Optional;

/**
 * Represents an outbound Amazon Kinesis record, that should be written to Kinesis.
 * <p>
 * The internal state of this interface is immutable.
 *
 * @author Mario Schwede
 * @since 4.14.0
 */
@Immutable
@DoNotImplement
public interface OutboundKinesisRecord extends KinesisRecord {

    /**
     * @return An {@link Optional} of the explicit hash key of this record.
     * @since 4.14.0
     */
    @NotNull Optional<BigInteger> getExplicitHashKey();
}
