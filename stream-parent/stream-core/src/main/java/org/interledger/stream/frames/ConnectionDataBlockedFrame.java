package org.interledger.stream.frames;

/*-
 * ========================LICENSE_START=================================
 * Interledger Core
 * %%
 * Copyright (C) 2017 - 2018 Hyperledger and its contributors
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

import com.google.common.primitives.UnsignedLong;
import org.interledger.core.Immutable;

/**
 * <p>Used to advertise to the other stream party that the frame sender has more data to send, but this would exceed
 * the other endpoint's advertised value as found in the latest {@link StreamDataMaxFrame} frame.</p>
 *
 * <p>The amounts in this frame are denominated in the units of the endpoint sending the frame, so the other endpoint
 * must use their calculated exchange rate to determine how much more they can send for this stream.</p>
 *
 * <p>Note that this frame is primarily intended for debugging purposes.</p>
 */
@Immutable
public interface ConnectionDataBlockedFrame extends StreamFrame {

  /**
   * Get the default builder.
   *
   * @return a {@link ConnectionDataBlockedFrameBuilder} instance.
   */
  static ConnectionDataBlockedFrameBuilder builder() {
    return new ConnectionDataBlockedFrameBuilder();
  }

  @Override
  default StreamFrameType streamFrameType() {
    return StreamFrameType.ConnectionDataBlocked;
  }

  /**
   * The total number of bytes the endpoint is willing to receive on this connection.
   *
   * @return An {@link UnsignedLong} containing the max offset.
   */
  UnsignedLong maxOffset();

}
