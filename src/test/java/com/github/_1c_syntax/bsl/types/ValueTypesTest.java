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

import com.github._1c_syntax.bsl.types.value.CustomValueType;
import com.github._1c_syntax.bsl.types.value.PrimitiveValueType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ValueTypesTest {

  @Test
  void get() {
    var type = ValueTypes.get("number");
    assertThat(type).isNotNull();
    assertThat(type.fullName().getEn()).isEqualTo("Number");
    assertThat(type.fullName().getRu()).isEqualTo("Число");
    assertThat(type).isInstanceOf(PrimitiveValueType.class);

    assertThat(ValueTypes.get("NuMbEr")).isInstanceOf(PrimitiveValueType.class);

    type = ValueTypes.get("Дата");
    assertThat(type).isNotNull();
    assertThat(type.fullName().getEn()).isEqualTo("Date");
    assertThat(type.fullName().getRu()).isEqualTo("Дата");
    assertThat(type).isInstanceOf(PrimitiveValueType.class);

    assertThat(ValueTypes.get("дАтА")).isInstanceOf(PrimitiveValueType.class);

    type = ValueTypes.get("МойТип");
    assertThat(type).isNull();
  }

  @Test
  void getOrCompute() {
    var type = ValueTypes.getOrCompute("DefinedType.ЗначениеДоступа");
    assertThat(type).isNotNull();
    assertThat(type.fullName().getEn()).isEqualTo("DefinedType.ЗначениеДоступа");
    assertThat(type.fullName().getRu()).isEqualTo("ОпределяемыйТип.ЗначениеДоступа");

    assertThat(type).isInstanceOf(CustomValueType.class);
    var customValueType = (CustomValueType) type;
    assertThat(customValueType.kind()).isEqualTo(MDOType.DEFINED_TYPE);

    type = ValueTypes.getOrCompute("EnumRef.ДополнительныеЗначенияДоступа");
    assertThat(type).isNotNull();
    assertThat(type.fullName().getEn()).isEqualTo("EnumRef.ДополнительныеЗначенияДоступа");
    assertThat(type.fullName().getRu()).isEqualTo("ПеречислениеСсылка.ДополнительныеЗначенияДоступа");

    assertThat(type).isInstanceOf(CustomValueType.class);
    customValueType = (CustomValueType) type;
    assertThat(customValueType.kind()).isEqualTo(MDOType.ENUM);

    type = ValueTypes.getOrCompute("МойТип");
    assertThat(type).isNotNull();
    assertThat(type.fullName().getEn()).isEmpty();
    assertThat(type.fullName().getRu()).isEqualTo("МойТип");
    assertThat(type).isInstanceOf(CustomValueType.class);
    customValueType = (CustomValueType) type;
    assertThat(customValueType.kind()).isEqualTo(MDOType.UNKNOWN);
    assertThat(customValueType.variant()).isEqualTo(ValueTypeVariant.UNKNOWN);

    assertThat(ValueTypes.getOrCompute("МойТип")).isSameAs(type);
  }
}