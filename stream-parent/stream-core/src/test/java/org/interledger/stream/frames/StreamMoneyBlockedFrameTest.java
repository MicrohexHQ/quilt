package org.interledger.stream.frames;

import com.google.common.primitives.UnsignedLong;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

public class StreamMoneyBlockedFrameTest {

  @Test
  public void zero() {
    StreamMoneyBlockedFrame frame = StreamMoneyBlockedFrame.builder()
        .sendMax(UnsignedLong.ZERO)
        .streamId(UnsignedLong.ZERO)
        .build();
    assertThat(frame.totalSent()).isEqualTo(UnsignedLong.ZERO);
    assertThat(spy(StreamMoneyBlockedFrame.class).totalSent()).isEqualTo(UnsignedLong.ZERO);
  }
}
