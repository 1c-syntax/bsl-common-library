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

import com.github._1c_syntax.bsl.types.ValueTypeVariant;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class V8ValueTypeTest {

  @Test
  void test() {
    assertThat(V8ValueType.values())
      .hasSize(36)
      .allMatch(valueType -> valueType.variant() == ValueTypeVariant.V8);
  }

  @Test
  void fromString() {
    var type = V8ValueType.valueByName("UUID");
    assertThat(type).isNotNull();
    assertThat(type.fullName().getEn()).isEqualTo("UUID");
    assertThat(type.fullName().getRu()).isEqualTo("УникальныйИдентификатор");

    assertThat(V8ValueType.valueByName("uuid")).isSameAs(type);
    var typeRu = V8ValueType.valueByName("УникальныйИдентификатор");
    assertThat(typeRu).isSameAs(type);

    type = V8ValueType.valueByName("Тип");
    assertThat(type).isNull();
  }
}