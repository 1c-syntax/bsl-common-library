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

  @Test
  void shouldLookupByRussianName() {
    var ref = StdAttributeNames.get("Ссылка");
    assertThat(ref).isNotNull();
    assertThat(ref.getEn()).isEqualTo("Ref");
    assertThat(ref.getRu()).isEqualTo("Ссылка");

    var deletionMark = StdAttributeNames.get("ПометкаУдаления");
    assertThat(deletionMark).isNotNull();
    assertThat(deletionMark.getEn()).isEqualTo("DeletionMark");
    assertThat(deletionMark.getRu()).isEqualTo("ПометкаУдаления");

    var description = StdAttributeNames.get("Наименование");
    assertThat(description).isNotNull();
    assertThat(description.getEn()).isEqualTo("Description");
    assertThat(description.getRu()).isEqualTo("Наименование");
  }

  @Test
  void shouldReturnSameInstanceForEnAndRu() {
    var byEn = StdAttributeNames.get("Ref");
    var byRu = StdAttributeNames.get("Ссылка");
    assertThat(byEn).isSameAs(byRu);
  }

  @Test
  void shouldReturnSubcontoAttributes() {
    var turnoversOnly = StdAttributeNames.get("TurnoversOnly");
    assertThat(turnoversOnly).isNotNull();
    assertThat(turnoversOnly.getEn()).isEqualTo("TurnoversOnly");
    assertThat(turnoversOnly.getRu()).isEqualTo("ТолькоОбороты");

    var extDimensionType = StdAttributeNames.get("ExtDimensionType");
    assertThat(extDimensionType).isNotNull();
    assertThat(extDimensionType.getEn()).isEqualTo("ExtDimensionType");
    assertThat(extDimensionType.getRu()).isEqualTo("ВидСубконто");

    var extDimension1 = StdAttributeNames.get("ExtDimension1");
    assertThat(extDimension1).isNotNull();
    assertThat(extDimension1.getEn()).isEqualTo("ExtDimension1");
    assertThat(extDimension1.getRu()).isEqualTo("Субконто1");

    var extDimensionType1 = StdAttributeNames.get("ExtDimensionType1");
    assertThat(extDimensionType1).isNotNull();
    assertThat(extDimensionType1.getEn()).isEqualTo("ExtDimensionType1");
    assertThat(extDimensionType1.getRu()).isEqualTo("ВидСубконто1");

    var extDimension5 = StdAttributeNames.get("ExtDimension5");
    assertThat(extDimension5).isNotNull();
    assertThat(extDimension5.getEn()).isEqualTo("ExtDimension5");
    assertThat(extDimension5.getRu()).isEqualTo("Субконто5");

    var extDimensionType5 = StdAttributeNames.get("ExtDimensionType5");
    assertThat(extDimensionType5).isNotNull();
    assertThat(extDimensionType5.getEn()).isEqualTo("ExtDimensionType5");
    assertThat(extDimensionType5.getRu()).isEqualTo("ВидСубконто5");

    var extDimension2 = StdAttributeNames.get("Субконто2");
    assertThat(extDimension2).isNotNull();
    assertThat(extDimension2.getEn()).isEqualTo("ExtDimension2");
    assertThat(extDimension2.getRu()).isEqualTo("Субконто2");

    var extDimensionType4 = StdAttributeNames.get("ВидСубконто4");
    assertThat(extDimensionType4).isNotNull();
    assertThat(extDimensionType4.getEn()).isEqualTo("ExtDimensionType4");
    assertThat(extDimensionType4.getRu()).isEqualTo("ВидСубконто4");
  }
}
