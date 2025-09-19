/*
 * This file is a part of BSL Common library.
 *
 * Copyright (c) 2021 - 2025
 * Tymko Oleg <olegtymko@yandex.ru>, Maximov Valery <maximovvalery@gmail.com> and contributors
 *
 * SPDX-License-Identifier: LGPL-3.0-or-later
 *
 * BSL Common library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * BSL Common library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with BSL Common library.
 */
package com.github._1c_syntax.bsl.types;

import com.github._1c_syntax.bsl.types.qualifiers.EmptyQualifiers;
import com.github._1c_syntax.bsl.types.value.PrimitiveValueType;
import com.github._1c_syntax.bsl.types.value.V8ValueType;
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
    assertThat(empty.isComposite()).isFalse();
    assertThat(empty.getTypes()).isEmpty();
    assertThat(empty.getQualifiers()).isEmpty();
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
    assertThat(vtd.getQualifiers()).isEmpty();

    vtd = ValueTypeDescription.create(types, false);
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes())
      .hasSize(3)
      .contains(PrimitiveValueType.STRING)
      .contains(PrimitiveValueType.NULL)
      .contains(V8ValueType.VALUE_STORAGE);
    assertThat(vtd.isComposite()).isFalse();
    assertThat(vtd.getQualifiers()).isEmpty();

    assertThat(vtd.contains(V8ValueType.VALUE_STORAGE)).isTrue();
    assertThat(vtd.contains(PrimitiveValueType.DATE)).isFalse();
  }

  @Test
  void testType() {
    var vtd = ValueTypeDescription.create(V8ValueType.ANY_REF);
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes())
      .hasSize(1)
      .contains(V8ValueType.ANY_REF);
    assertThat(vtd.isComposite()).isTrue();
    assertThat(vtd.getQualifiers()).isEmpty();

    vtd = ValueTypeDescription.create(V8ValueType.VALUE_STORAGE);
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes())
      .hasSize(1)
      .contains(V8ValueType.VALUE_STORAGE);
    assertThat(vtd.isComposite()).isFalse();
    assertThat(vtd.getQualifiers()).isEmpty();

    vtd = ValueTypeDescription.create(V8ValueType.VALUE_STORAGE, true);
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes())
      .hasSize(1)
      .contains(V8ValueType.VALUE_STORAGE);
    assertThat(vtd.isComposite()).isTrue();
    assertThat(vtd.getQualifiers()).isEmpty();
  }

  void testQualifiers() {
    var vtd = ValueTypeDescription.create(V8ValueType.ANY_REF, EmptyQualifiers.EMPTY);
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes())
      .hasSize(1)
      .contains(V8ValueType.ANY_REF);
    assertThat(vtd.isComposite()).isTrue();
    assertThat(vtd.getQualifiers())
      .hasSize(1)
      .contains(EmptyQualifiers.EMPTY);

    vtd = ValueTypeDescription.create(V8ValueType.VALUE_STORAGE, EmptyQualifiers.EMPTY);
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes())
      .hasSize(1)
      .contains(V8ValueType.VALUE_STORAGE);
    assertThat(vtd.isComposite()).isFalse();
    assertThat(vtd.getQualifiers())
      .hasSize(1)
      .contains(EmptyQualifiers.EMPTY);

    vtd = ValueTypeDescription.create(List.of(V8ValueType.VALUE_STORAGE, V8ValueType.FIXED_ARRAY), EmptyQualifiers.EMPTY);
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes())
      .hasSize(2)
      .contains(V8ValueType.VALUE_STORAGE);
    assertThat(vtd.isComposite()).isTrue();
    assertThat(vtd.getQualifiers())
      .hasSize(1)
      .contains(EmptyQualifiers.EMPTY);

    vtd = ValueTypeDescription.create(List.of(V8ValueType.VALUE_STORAGE), EmptyQualifiers.EMPTY);
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes())
      .hasSize(1)
      .contains(V8ValueType.VALUE_STORAGE);
    assertThat(vtd.isComposite()).isTrue();
    assertThat(vtd.getQualifiers())
      .hasSize(1)
      .contains(EmptyQualifiers.EMPTY);
  }
}