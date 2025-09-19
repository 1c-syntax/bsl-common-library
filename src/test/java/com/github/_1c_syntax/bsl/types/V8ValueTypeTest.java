package com.github._1c_syntax.bsl.types;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class V8ValueTypeTest {

  @Test
  void test() {
    assertThat(V8ValueType.builtinTypes()).hasSize(6);

    assertThat(V8ValueType.builtinTypes())
      .filteredOn(valueType -> valueType.getVariant() == ValueTypeVariant.V8)
      .hasSize(5);

    assertThat(V8ValueType.ANY_REF.getVariant()).isEqualTo(ValueTypeVariant.METADATA);
  }
}