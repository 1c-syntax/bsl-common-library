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

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MultiLanguageStringTest {

  @Test
  void testCreate() {
    var keyRu = "ru";
    var keyEn = "en";

    var value = MultiLanguageString.create(keyRu, keyRu);
    assertThat(value.isEmpty()).isFalse();
    assertThat(value.get("RU")).isEqualTo(keyRu);
    assertThat(value.getAny()).isEqualTo(keyRu);

    var valueCopy = MultiLanguageString.create(keyRu, keyRu);
    assertThat(value).isEqualTo(valueCopy);
    assertThat(value == valueCopy).isTrue();

    var valueEn = MultiLanguageString.create(keyEn, keyEn);
    var valueRuEn = MultiLanguageString.create(value, valueEn);
    assertThat(valueRuEn.isEmpty()).isFalse();
    assertThat(valueRuEn.get("RU")).isEqualTo(keyRu);
    assertThat(valueRuEn.get("en")).isEqualTo(keyEn);

    var valueRuEnList = MultiLanguageString.create(List.of(valueEn, value));
    assertThat(valueRuEn).isEqualTo(valueRuEnList);
    assertThat(valueRuEn == valueRuEnList).isTrue();
  }
}