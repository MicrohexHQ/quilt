package org.interledger.spsp;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentPointerTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void parseHostAndPath() {
    assertThat(PaymentPointer.of("$example.com/foo"))
      .isEqualTo(ImmutablePaymentPointer.builder().host("example.com").path("/foo").build());
  }

  @Test
  public void parseHostOnly() {
    assertThat(PaymentPointer.of("$example.com"))
      .isEqualTo(ImmutablePaymentPointer.builder().host("example.com").path("/").build());
    assertThat(PaymentPointer.of("$bob.example.com"))
      .isEqualTo(ImmutablePaymentPointer.builder().host("bob.example.com").path("/").build());
    assertThat(PaymentPointer.of("$localhost"))
      .isEqualTo(ImmutablePaymentPointer.builder().host("localhost").path("/").build());
  }

  @Test
  public void exceptionIfDoesntStartWithDollarSign() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("PaymentPointers must begin with $");
    PaymentPointer.of("foo");
  }

  @Test
  public void exceptionIfHostNameEmpty() {
    thrown.expect(IllegalStateException.class);
    thrown.expectMessage("PaymentPointers must specify a host");
    PaymentPointer.of("$");
  }

  @Test
  public void zalgoIsTonyThePonyCannotBePaid() {
    thrown.expect(IllegalStateException.class);
    thrown.expectMessage("PaymentPointers may only contain ASCII characters");
    PaymentPointer.of("$ZA̡͊͠͝LGΌ IS̯͈͕̹̘̱ͮ TO͇̹̺ͅƝ̴ȳ̳ TH̘Ë͖́̉ ͠P̯͍̭O̚N̐Y̡");
  }

//  @Test
//  public void toUrlWithPath() {
//    assertThat(PaymentPointer.of("$example.com/foo").resolve()).isEqualTo("https://example.com/foo");
//  }
//
//  @Test
//  public void toUrlWithout() {
//    assertThat(PaymentPointer.of("$example.com").toUrl()).isEqualTo("https://example.com/.well-known/pay");
//  }

}