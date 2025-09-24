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
package com.github._1c_syntax.bsl.types.value;

import com.github._1c_syntax.bsl.types.MDOType;
import com.github._1c_syntax.bsl.types.ValueTypeVariant;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MetadataValueTypeTest {

  @Test
  void fromString() {
    var type = MetadataValueType.fromString("DefinedType.ЗначениеДоступа");
    assertThat(type).isNotNull();
    assertThat(type.getKind()).isEqualTo(MDOType.DEFINED_TYPE);
    assertThat(type.fullName().getEn()).isEqualTo("DefinedType.ЗначениеДоступа");
    assertThat(type.fullName().getRu()).isEqualTo("ОпределяемыйТип.ЗначениеДоступа");
    assertThat(type.isComposite()).isFalse();

    type = MetadataValueType.fromString("EnumRef.ДополнительныеЗначенияДоступа");
    assertThat(type).isNotNull();
    assertThat(type.getKind()).isEqualTo(MDOType.ENUM);
    assertThat(type.fullName().getEn()).isEqualTo("EnumRef.ДополнительныеЗначенияДоступа");
    assertThat(type.fullName().getRu()).isEqualTo("ПеречислениеСсылка.ДополнительныеЗначенияДоступа");
    assertThat(type.isComposite()).isFalse();

    type = MetadataValueType.fromString("DataProcessorManager");
    assertThat(type).isNotNull();
    assertThat(type.getKind()).isEqualTo(MDOType.DATA_PROCESSOR);
    assertThat(type.fullName().getEn()).isEqualTo("DataProcessorManager");
    assertThat(type.fullName().getRu()).isEqualTo("ОбработкаМенеджер");
    assertThat(type.isComposite()).isTrue();

    type = MetadataValueType.fromString("AnyRef");
    assertThat(type).isNotNull();
    assertThat(type.getKind()).isEqualTo(MDOType.CONFIGURATION);
    assertThat(type.fullName().getEn()).isEqualTo("AnyRef");
    assertThat(type.fullName().getRu()).isEqualTo("ЛюбаяСсылка");
    assertThat(type.isComposite()).isTrue();

    type = MetadataValueType.fromString("Тип");
    assertThat(type).isNull();
  }

  @Test
  void test() {
    assertThat(MetadataValueType.builtinTypes())
      .hasSize(46)
      .allMatch(valueType -> valueType.variant() == ValueTypeVariant.METADATA);
  }
}