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
package com.github._1c_syntax.bsl.types.qualifiers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumberQualifiersTest {

  @Test
  void create() {
    var quaf = NumberQualifiers.create(10);
    assertThat(quaf.getPrecision()).isEqualTo(10);
    assertThat(quaf.getScale()).isZero();
    assertThat(quaf.isNonNegative()).isFalse();
    assertThat(quaf.description().getRu()).isEqualTo("КвалификаторыЧисла (10.0)");
    assertThat(quaf.description().getEn()).isEqualTo("NumberQualifiers (10.0)");

    quaf = NumberQualifiers.create(10, 5);
    assertThat(quaf.getPrecision()).isEqualTo(10);
    assertThat(quaf.getScale()).isEqualTo(5);
    assertThat(quaf.isNonNegative()).isFalse();
    assertThat(quaf.description().getRu()).isEqualTo("КвалификаторыЧисла (10.5)");
    assertThat(quaf.description().getEn()).isEqualTo("NumberQualifiers (10.5)");

    quaf = NumberQualifiers.create(10, 5, true);
    assertThat(quaf.getPrecision()).isEqualTo(10);
    assertThat(quaf.getScale()).isEqualTo(5);
    assertThat(quaf.isNonNegative()).isTrue();
    assertThat(quaf.description().getRu()).isEqualTo("КвалификаторыЧисла (10.5 неотр)");
    assertThat(quaf.description().getEn()).isEqualTo("NumberQualifiers (10.5 nonneg)");

    assertThat(quaf.compareTo(null)).isEqualTo(1);
  }

  @Test
  void compare() {
    var quaf = NumberQualifiers.create(10);
    var quaf2 = NumberQualifiers.create(10, 5);
    var quaf3 = NumberQualifiers.create(10);
    var quaf4 = NumberQualifiers.create(9);
    var quaf5 = NumberQualifiers.create(10, 5, true);

    assertThat(quaf2.compareTo(quaf)).isEqualTo(1);
    assertThat(quaf3.compareTo(quaf2)).isEqualTo(-1);
    assertThat(quaf.compareTo(quaf3)).isZero();
    assertThat(quaf.compareTo(quaf4)).isEqualTo(1);
    assertThat(quaf2.compareTo(quaf5)).isEqualTo(-1);
  }
}