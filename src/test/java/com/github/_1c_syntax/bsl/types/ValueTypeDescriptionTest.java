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

import com.github._1c_syntax.bsl.types.qualifiers.DateQualifiers;
import com.github._1c_syntax.bsl.types.value.CustomValueType;
import com.github._1c_syntax.bsl.types.value.MDOValueType;
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

    var empty2 = ValueTypeDescription.create(Collections.emptyList(), Collections.emptyList());
    assertThat(empty2).isEqualTo(empty);

    empty2 = ValueTypeDescription.create(Collections.emptyList(), Collections.emptyList(), false);
    assertThat(empty2).isEqualTo(empty);
  }

  @Test
  void testTypes() {
    List<ValueType> types = List.of(PrimitiveValueType.STRING, PrimitiveValueType.NULL, V8ValueType.VALUE_STORAGE);
    var vtd = ValueTypeDescription.create(types);
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes())
      .hasSize(3)
      .contains(PrimitiveValueType.STRING)
      .contains(PrimitiveValueType.NULL)
      .contains(V8ValueType.VALUE_STORAGE);
    assertThat(vtd.isComposite()).isTrue();
    assertThat(vtd.getQualifiers()).isEmpty();

    assertThat(vtd.contains(V8ValueType.VALUE_STORAGE)).isTrue();
    assertThat(vtd.contains(PrimitiveValueType.DATE)).isFalse();
  }

  @Test
  void testType() {
    var vtd = ValueTypeDescription.create(MDOValueType.ANY_REF);
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes())
      .hasSize(1)
      .contains(MDOValueType.ANY_REF);
    assertThat(vtd.isComposite()).isTrue();
    assertThat(vtd.getQualifiers()).isEmpty();

    vtd = ValueTypeDescription.create(V8ValueType.VALUE_STORAGE);
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes())
      .hasSize(1)
      .contains(V8ValueType.VALUE_STORAGE);
    assertThat(vtd.isComposite()).isFalse();
    assertThat(vtd.getQualifiers()).isEmpty();

    vtd = ValueTypeDescription.create(V8ValueType.VALUE_STORAGE, Qualifier.EMPTY);
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes())
      .hasSize(1)
      .contains(V8ValueType.VALUE_STORAGE);
    assertThat(vtd.isComposite()).isFalse();
    assertThat(vtd.getQualifiers()).isEmpty();
  }

  @Test
  void testQualifiers() {
    var qf = DateQualifiers.create();
    var vtd = ValueTypeDescription.create(MDOValueType.ANY_REF, Qualifier.EMPTY);
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes())
      .hasSize(1)
      .contains(MDOValueType.ANY_REF);
    assertThat(vtd.isComposite()).isTrue();
    assertThat(vtd.getQualifiers()).isEmpty();

    vtd = ValueTypeDescription.create(V8ValueType.VALUE_STORAGE, qf);
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes())
      .hasSize(1)
      .contains(V8ValueType.VALUE_STORAGE);
    assertThat(vtd.isComposite()).isFalse();
    assertThat(vtd.getQualifiers())
      .hasSize(1)
      .contains(qf);

    vtd = ValueTypeDescription.create(List.of(V8ValueType.VALUE_STORAGE, V8ValueType.FIXED_ARRAY), qf);
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes())
      .hasSize(2)
      .contains(V8ValueType.VALUE_STORAGE);
    assertThat(vtd.isComposite()).isTrue();
    assertThat(vtd.getQualifiers())
      .hasSize(1)
      .contains(qf);

    vtd = ValueTypeDescription.create(List.of(V8ValueType.VALUE_STORAGE), qf);
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes())
      .hasSize(1)
      .contains(V8ValueType.VALUE_STORAGE);
    assertThat(vtd.isComposite()).isFalse();
    assertThat(vtd.getQualifiers())
      .hasSize(1)
      .contains(qf);
  }

  @Test
  void testString() {
    var vtd = ValueTypeDescription.createString(1);
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes())
      .hasSize(1)
      .contains(PrimitiveValueType.STRING);
    assertThat(vtd.isComposite()).isFalse();
    assertThat(vtd.getQualifiers()).hasSize(1);
    assertThat(vtd.getQualifiers().get(0).description().getRu())
      .isEqualTo("КвалификаторыСтроки (1, Переменная)");
    assertThat(vtd.getQualifiers().get(0).description().getEn())
      .isEqualTo("StringQualifiers (1, Variable)");

    vtd = ValueTypeDescription.createString(1, AllowedLength.FIXED);
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes())
      .hasSize(1)
      .contains(PrimitiveValueType.STRING);
    assertThat(vtd.isComposite()).isFalse();
    assertThat(vtd.getQualifiers()).hasSize(1);
    assertThat(vtd.getQualifiers().get(0).description().getRu())
      .isEqualTo("КвалификаторыСтроки (1, Фиксированная)");
    assertThat(vtd.getQualifiers().get(0).description().getEn())
      .isEqualTo("StringQualifiers (1, Fixed)");
  }

  @Test
  void testNumber() {
    var vtd = ValueTypeDescription.createNumber(1);
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes())
      .hasSize(1)
      .contains(PrimitiveValueType.NUMBER);
    assertThat(vtd.isComposite()).isFalse();
    assertThat(vtd.getQualifiers()).hasSize(1);
    assertThat(vtd.getQualifiers().get(0).description().getRu())
      .isEqualTo("КвалификаторыЧисла (1.0)");
    assertThat(vtd.getQualifiers().get(0).description().getEn())
      .isEqualTo("NumberQualifiers (1.0)");
  }

  @Test
  void testCreateRef() {
    var vtd = ValueTypeDescription.createRef(MDOType.ENUM, "Enum1");
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes()).hasSize(1);
    assertThat(vtd.isComposite()).isFalse();
    assertThat(vtd.getQualifiers()).isEmpty();

    var type = vtd.getTypes().get(0);
    assertThat(type.variant()).isEqualTo(ValueTypeVariant.METADATA);
    assertThat(type.fullName()).isEqualTo(MultiName.create("EnumRef.Enum1", "ПеречислениеСсылка.Enum1"));
    assertThat(type).isInstanceOf(CustomValueType.class);
    assertThat(((CustomValueType) type).kind()).isEqualTo(MDOType.ENUM);

    var vtd2 = ValueTypeDescription.createRef(MdoReference.create(MDOType.ENUM, "Enum1"));
    assertThat(vtd2).isEqualTo(vtd);

    var vtd3 = ValueTypeDescription.createRef(MdoReference.EMPTY);
    assertThat(vtd3).isEqualTo(ValueTypeDescription.EMPTY);
  }

  @Test
  void testCreateRefs() {
    var ref1 = MdoReference.create(MDOType.ENUM, "Enum1");
    var ref2 = MdoReference.create(MDOType.ENUM, "Enum2");
    var ref3 = MdoReference.create("Catalog.Catalog1");
    var vtd = ValueTypeDescription.createRef(List.of(ref1, ref2, ref3));
    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes()).hasSize(3);
    assertThat(vtd.isComposite()).isTrue();
    assertThat(vtd.getQualifiers()).isEmpty();
    assertThat(vtd.getTypes())
      .allMatch(type -> type.variant() == ValueTypeVariant.METADATA)
      .allMatch(CustomValueType.class::isInstance);

    var vtd2 = ValueTypeDescription.createRef(List.of(ref1, ref2, ref3, ref3));
    assertThat(vtd2).isEqualTo(vtd);

    var vtd3 = ValueTypeDescription.createRef(List.of(ref1, ref2, MdoReference.EMPTY, ref3));
    assertThat(vtd3).isEqualTo(vtd);

    assertThat(ValueTypeDescription.createRef(Collections.emptyList())).isEqualTo(ValueTypeDescription.EMPTY);
  }

  @Test
  void testCreateMultiLevelRef() {
    var ref = MdoReference.create("Catalog.Product.Attribute.Price");
    var vtd = ValueTypeDescription.createRef(ref);

    assertThat(vtd.isEmpty()).isFalse();
    assertThat(vtd.getTypes()).hasSize(1);

    var type = vtd.getTypes().get(0);
    // Проверить, что ключ сформирован корректно как "CatalogRef.Product.Attribute.Price"
    assertThat(type.fullName().getEn()).contains("Ref.Product").contains("Attribute.Price");
  }
}