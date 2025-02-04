package org.interledger.encoding.asn.codecs;

/*-
 * ========================LICENSE_START=================================
 * Interledger Codec Framework
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

/**
 * An ASN.1 codec for UInt64 objects that decodes them into {@link UnsignedLong} values.
 */
public class AsnUint64CodecUL extends AsnOctetStringBasedObjectCodec<UnsignedLong> {

  public AsnUint64CodecUL() {
    super(new AsnSizeConstraint(8));
  }

  @Override
  public UnsignedLong decode() {
    byte[] bytes = getBytes();
    long value = 0;
    for (int i = 0; i <= 7; i++) {
      value <<= Byte.SIZE;
      value |= (bytes[i] & 0xFF);
    }

    return UnsignedLong.fromLongBits(value);
  }

  @Override
  public void encode(UnsignedLong value) {
    byte[] bytes = new byte[8];
    for (int i = 0; i <= 7; i++) {
      bytes[i] = ((byte) ((value.longValue() >> (Byte.SIZE * (7 - i))) & 0xFF));
    }
    setBytes(bytes);
  }

}
