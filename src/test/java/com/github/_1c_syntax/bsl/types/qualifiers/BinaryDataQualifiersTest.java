/*
 * This file is a part of BSL Common library.
 *
 * Copyright (c) 2021 - 2026
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
package com.github._1c_syntax.bsl.types.qualifiers;

import com.github._1c_syntax.bsl.types.AllowedLength;
import com.github._1c_syntax.bsl.types.MultiName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BinaryDataQualifiersTest {
  @Test
  void create() {
    var quaf = BinaryDataQualifiers.create(1);
    assertThat(quaf.getLength()).isEqualTo(1);
    assertThat(quaf.getAllowedLength()).isEqualTo(AllowedLength.VARIABLE);
    assertThat(quaf.description())
      .isEqualTo(MultiName.create("BinaryDataQualifiers (1, Variable)", "КвалификаторыДвоичныхДанных (1, Переменная)"));

    var quaf2 = BinaryDataQualifiers.create(100, AllowedLength.FIXED);
    assertThat(quaf2.getLength()).isEqualTo(100);
    assertThat(quaf2.getAllowedLength()).isEqualTo(AllowedLength.FIXED);
    assertThat(quaf2.description())
      .isEqualTo(MultiName.create("BinaryDataQualifiers (100, Fixed)", "КвалификаторыДвоичныхДанных (100, Фиксированная)"));

    var quaf3 = BinaryDataQualifiers.create(101, AllowedLength.FIXED);
    assertThat(quaf3.getLength()).isEqualTo(101);
    assertThat(quaf3.getAllowedLength()).isEqualTo(AllowedLength.FIXED);
    assertThat(quaf3.description())
      .isEqualTo(MultiName.create("BinaryDataQualifiers (101, Fixed)", "КвалификаторыДвоичныхДанных (101, Фиксированная)"));

    assertThat(quaf.compareTo(null)).isEqualTo(1);
    assertThat(quaf.compareTo(quaf2)).isLessThan(1);
    assertThat(quaf2.compareTo(quaf3)).isLessThan(1);
    assertThat(quaf2.compareTo(quaf2)).isZero();
  }
}