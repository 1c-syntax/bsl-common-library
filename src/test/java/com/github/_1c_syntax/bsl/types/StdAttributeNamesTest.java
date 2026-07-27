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
package com.github._1c_syntax.bsl.types;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StdAttributeNamesTest {

  @Test
  void shouldReturnKnownAttributes() {
    var ref = StdAttributeNames.get("Ref");
    assertThat(ref).isNotNull();
    assertThat(ref.getEn()).isEqualTo("Ref");
    assertThat(ref.getRu()).isEqualTo("Ссылка");

    var deletionMark = StdAttributeNames.get("DeletionMark");
    assertThat(deletionMark).isNotNull();
    assertThat(deletionMark.getEn()).isEqualTo("DeletionMark");
    assertThat(deletionMark.getRu()).isEqualTo("ПометкаУдаления");

    var description = StdAttributeNames.get("Description");
    assertThat(description).isNotNull();
    assertThat(description.getEn()).isEqualTo("Description");
    assertThat(description.getRu()).isEqualTo("Наименование");
  }

  @Test
  void shouldLookupCaseInsensitive() {
    var lower = StdAttributeNames.get("ref");
    var upper = StdAttributeNames.get("REF");
    var mixed = StdAttributeNames.get("Ref");

    assertThat(lower).isNotNull();
    assertThat(upper).isNotNull();
    assertThat(mixed).isNotNull();
    assertThat(lower).isSameAs(upper);
    assertThat(upper).isSameAs(mixed);
  }

  @Test
  void shouldReturnNullForUnknownName() {
    assertThat(StdAttributeNames.get("NonExistentAttribute")).isEqualTo(MultiName.EMPTY);
    assertThat(StdAttributeNames.get("")).isEqualTo(MultiName.EMPTY);
  }
}
