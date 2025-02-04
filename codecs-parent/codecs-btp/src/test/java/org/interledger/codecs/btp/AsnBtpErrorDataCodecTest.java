package org.interledger.codecs.btp;

/*-
 * ========================LICENSE_START=================================
 * Bilateral Transfer Protocol Core Codecs
 * %%
 * Copyright (C) 2017 - 2019 Hyperledger and its contributors
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

import org.interledger.btp.BtpError;
import org.interledger.btp.BtpErrorCode;
import org.interledger.encoding.asn.codecs.AsnUint8Codec;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link AsnBtpErrorDataCodec}.
 */
public class AsnBtpErrorDataCodecTest {

  private static final String BTP_ERROR_PACKET_B64
      = "Ag4CATs9RjAwEE5vdEFjY2VwdGVkRXJyb3ITMjAxODA5MjcxNTEyMTQuNDUyWhJpbnZhbGlkIGF1dGhfdG9rZW4BAA==";
  private AsnBtpErrorCodec codec;

  private BtpError btpError;

  @Before
  public void setUp() {
    codec = new AsnBtpErrorCodec();
    btpError = BtpError.builder()
        .errorCode(BtpErrorCode.F00_NotAcceptedError)
        .triggeredAt(Instant.now())
        .requestId(123L).build();
  }

  @Test
  public void decode() {
    final AsnUint8Codec uint8Codec = new AsnUint8Codec();
    uint8Codec.encode((short) 2);

    codec.setValueAt(0, (short) 2);
    codec.setValueAt(1, 123L);
    codec.setValueAt(2, btpError);

    final BtpError decodedBtpError = codec.decode();
    assertThat(decodedBtpError).isEqualTo(btpError);
  }

  @Test
  public void encode() {
    // TODO: FIXME!
//;=F00NotAcceptedError20180927151214.452Zinvalid auth_token 

    codec.encode(btpError);
    assertThat((short) codec.getValueAt(0)).isEqualTo(btpError.getType().getCode());
    assertThat((long) codec.getValueAt(1)).isEqualTo(btpError.getRequestId());
    assertThat((BtpError) codec.getValueAt(2)).isEqualTo(btpError);

    //    assertThat(codec.getValueAt(0), is(nullValue()));

    final BtpError decodedBtpError = codec.decode();
    assertThat(decodedBtpError).isEqualTo(btpError);
  }
}
