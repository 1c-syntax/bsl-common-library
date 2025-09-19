package com.github._1c_syntax.bsl.types;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PrimitiveValueTypeTest {

  @Test
  void test() {
    assertThat(PrimitiveValueType.builtinTypes())
      .hasSize(5)
      .allMatch(valueType -> valueType.getVariant() == ValueTypeVariant.PRIMITIVE);
  }
}