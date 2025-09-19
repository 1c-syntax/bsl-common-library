package com.github._1c_syntax.bsl.types;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ValueTypeDescriptionTest {

  @Test
  void testEmpty() {
    var empty = ValueTypeDescription.EMPTY;
    assertThat(empty.isEmpty()).isTrue();
    empty = ValueTypeDescription.create(Collections.emptyList());
    assertThat(empty.isEmpty()).isTrue();
    empty = ValueTypeDescription.create(new ArrayList<>());
    assertThat(empty.isEmpty()).isTrue();
  }

  @Test
  void testTypes() {
    var types = List.of(PrimitiveValueType.STRING, PrimitiveValueType.NULL, V8ValueType.VALUE_STORAGE);
    var vtd = ValueTypeDescription.create(types);
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes())
      .hasSize(3)
      .contains(PrimitiveValueType.STRING)
      .contains(PrimitiveValueType.NULL)
      .contains(V8ValueType.VALUE_STORAGE);
    assertThat(vtd.isComposite()).isTrue();

    vtd = ValueTypeDescription.create(types, false);
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes())
      .hasSize(3)
      .contains(PrimitiveValueType.STRING)
      .contains(PrimitiveValueType.NULL)
      .contains(V8ValueType.VALUE_STORAGE);
    assertThat(vtd.isComposite()).isFalse();
  }

  @Test
  void testType() {
    var vtd = ValueTypeDescription.create(V8ValueType.ANY_REF);
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes())
      .hasSize(1)
      .contains(V8ValueType.ANY_REF);
    assertThat(vtd.isComposite()).isTrue();

    vtd = ValueTypeDescription.create(V8ValueType.VALUE_STORAGE);
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes())
      .hasSize(1)
      .contains(V8ValueType.VALUE_STORAGE);
    assertThat(vtd.isComposite()).isFalse();

    vtd = ValueTypeDescription.create(V8ValueType.VALUE_STORAGE, true);
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes())
      .hasSize(1)
      .contains(V8ValueType.VALUE_STORAGE);
    assertThat(vtd.isComposite()).isTrue();
  }
}