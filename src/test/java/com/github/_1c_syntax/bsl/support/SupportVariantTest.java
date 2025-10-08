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
package com.github._1c_syntax.bsl.support;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SupportVariantTest {

  @Test
  void testMax() {
    List<SupportVariant> variants = List.of(SupportVariant.EDITABLE_SUPPORT_ENABLED, SupportVariant.NOT_SUPPORTED);
    assertThat(SupportVariant.max(variants)).isEqualTo(SupportVariant.EDITABLE_SUPPORT_ENABLED);

    variants = List.of(SupportVariant.EDITABLE_SUPPORT_ENABLED, SupportVariant.NOT_EDITABLE);
    assertThat(SupportVariant.max(variants)).isEqualTo(SupportVariant.NOT_EDITABLE);

    variants = List.of(SupportVariant.NONE, SupportVariant.NOT_SUPPORTED);
    assertThat(SupportVariant.max(variants)).isEqualTo(SupportVariant.NOT_SUPPORTED);

    variants = Collections.emptyList();
    assertThat(SupportVariant.max(variants)).isEqualTo(SupportVariant.NONE);
  }

  @Test
  void testMax2() {
    var var0 = SupportVariant.NOT_EDITABLE;
    var var1 = SupportVariant.NOT_SUPPORTED;
    var var2 = SupportVariant.NONE;
    var var3 = SupportVariant.EDITABLE_SUPPORT_ENABLED;

    // Проверки с NOT_EDITABLE (наивысший приоритет)
    assertThat(SupportVariant.max(var0, var1)).isEqualTo(SupportVariant.NOT_EDITABLE);
    assertThat(SupportVariant.max(var0, var2)).isEqualTo(SupportVariant.NOT_EDITABLE);
    assertThat(SupportVariant.max(var0, var3)).isEqualTo(SupportVariant.NOT_EDITABLE);
    assertThat(SupportVariant.max(var0, var0)).isEqualTo(SupportVariant.NOT_EDITABLE);

    assertThat(SupportVariant.max(var1, var2)).isEqualTo(SupportVariant.NOT_SUPPORTED);
    assertThat(SupportVariant.max(var3, var1)).isEqualTo(SupportVariant.EDITABLE_SUPPORT_ENABLED);
    assertThat(SupportVariant.max(var3, var2)).isEqualTo(SupportVariant.EDITABLE_SUPPORT_ENABLED);
    assertThat(SupportVariant.max(var2, var1)).isEqualTo(SupportVariant.NOT_SUPPORTED);
    assertThat(SupportVariant.max(var2, var2)).isEqualTo(SupportVariant.NONE);

    assertThat(SupportVariant.max(var1, var3)).isEqualTo(SupportVariant.EDITABLE_SUPPORT_ENABLED);
    assertThat(SupportVariant.max(var1, var1)).isEqualTo(SupportVariant.NOT_SUPPORTED);
    assertThat(SupportVariant.max(var3, var3)).isEqualTo(SupportVariant.EDITABLE_SUPPORT_ENABLED);
  }

  @Test
  void testValueOf() {
    assertThat(SupportVariant.valueOf(0)).isEqualTo(SupportVariant.NOT_EDITABLE);
    assertThat(SupportVariant.valueOf(1)).isEqualTo(SupportVariant.EDITABLE_SUPPORT_ENABLED);
    assertThat(SupportVariant.valueOf(-1)).isEqualTo(SupportVariant.NONE);
    assertThat(SupportVariant.valueOf(99)).isEqualTo(SupportVariant.NONE);
    assertThat(SupportVariant.valueOf(2)).isEqualTo(SupportVariant.NOT_SUPPORTED);
    assertThat(SupportVariant.valueOf(3)).isEqualTo(SupportVariant.NONE);
  }
}