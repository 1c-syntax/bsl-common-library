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

import static org.assertj.core.api.Assertions.assertThat;

class MultiNameTest {

  @Test
  void testCreate() {
    var keyRu = "рус";
    var keyEn = "en";
    var keyAuto = "ComОбъект";

    var value = MultiName.create(keyRu);
    assertThat(value.isEmpty()).isFalse();
    assertThat(value.get("RU")).isEqualTo(keyRu);
    assertThat(value.get()).isEqualTo(keyRu);
    assertThat(value.get(ScriptVariant.RUSSIAN)).isEqualTo(keyRu);
    assertThat(value.getRu()).isEqualTo(keyRu);
    assertThat(value.getEn()).isEmpty();

    var valueCopy = MultiName.create(keyRu);
    assertThat(value).isEqualTo(valueCopy);
    assertThat(value == valueCopy).isTrue();

    var valueEn = MultiName.create(keyEn);
    assertThat(valueEn.isEmpty()).isFalse();
    assertThat(valueEn.get("en")).isEqualTo(keyEn);
    assertThat(valueEn.get()).isEqualTo(keyEn);
    assertThat(valueEn.get(ScriptVariant.ENGLISH)).isEqualTo(keyEn);
    assertThat(valueEn.getRu()).isEmpty();
    assertThat(valueEn.getEn()).isEqualTo(keyEn);

    var valueRuEn = MultiName.create(keyEn, keyRu);
    assertThat(valueRuEn.isEmpty()).isFalse();
    assertThat(valueRuEn.get("en")).isEqualTo(keyEn);
    assertThat(valueRuEn.get()).isEqualTo(keyRu);
    assertThat(valueRuEn.get(ScriptVariant.RUSSIAN)).isEqualTo(keyRu);
    assertThat(valueRuEn.getRu()).isEqualTo(keyRu);
    assertThat(valueRuEn.getEn()).isEqualTo(keyEn);

    var valueAuto = MultiName.create(keyAuto);
    assertThat(valueAuto.isEmpty()).isFalse();
    assertThat(valueAuto.get("RU")).isEqualTo(keyAuto);
    assertThat(valueAuto.get()).isEqualTo(keyAuto);
    assertThat(valueAuto.get(ScriptVariant.UNKNOWN)).isEqualTo(keyAuto);
    assertThat(valueAuto.getRu()).isEqualTo(keyAuto);
    assertThat(valueAuto.getEn()).isEmpty();
  }
}