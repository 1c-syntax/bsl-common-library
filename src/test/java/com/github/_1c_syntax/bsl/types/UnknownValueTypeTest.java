package com.github._1c_syntax.bsl.types;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UnknownValueTypeTest {

  @Test
  void test() {
    var unknowType = new UnknownValueType("any words");
    assertThat(unknowType.getName()).isEqualTo("any words");
    assertThat(unknowType.getNameRu()).isEqualTo("any words");
    assertThat(unknowType.getVariant()).isEqualTo(ValueTypeVariant.UNKNOWN);
  }
}