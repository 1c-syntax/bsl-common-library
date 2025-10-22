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
import static org.junit.jupiter.api.Assertions.assertThrows;

class MdoReferenceTest {

  @Test
  void testException() {

    var exception = assertThrows(IllegalArgumentException.class, () -> {
      var empty = MdoReference.create("test.test");
    });
    assertThat(exception.getMessage()).isEqualTo("Incorrect full name test.test");

    var exception2 = assertThrows(IllegalArgumentException.class, () -> {
      var empty = MdoReference.create("");
    });
    assertThat(exception2.getMessage()).isEqualTo("Incorrect full name ");
  }

  @Test
  void testCreateFromString() {
    var mdoRef = MdoReference.create("catalogs.test");
    assertThat(mdoRef).isNotNull();
    assertThat(mdoRef.getMdoRef()).isEqualTo("Catalog.test");
    assertThat(mdoRef.getMdoRefRu()).isEqualTo("Справочник.test");
    assertThat(mdoRef.getType()).isEqualTo(MDOType.CATALOG);

    var mdoRef2 = MdoReference.create("catalog.test");
    assertThat(mdoRef2).isEqualTo(mdoRef);

    var mdoRef3 = MdoReference.create("справочник.test");
    assertThat(mdoRef3).isEqualTo(mdoRef);
  }

  @Test
  void testCreateTypeAndName() {
    var mdoRef = MdoReference.create(MDOType.CATALOG, "test5");
    assertThat(mdoRef).isNotNull();
    assertThat(mdoRef.getMdoRef()).isEqualTo("Catalog.test5");
    assertThat(mdoRef.getMdoRefRu()).isEqualTo("Справочник.test5");
    assertThat(mdoRef.getType()).isEqualTo(MDOType.CATALOG);

    var mdoRef2 = MdoReference.create("catalog.test5");
    assertThat(mdoRef2).isEqualTo(mdoRef);

    var mdoRef3 = MdoReference.create(MDOType.CATALOG, "test5");
    assertThat(mdoRef3).isEqualTo(mdoRef);
  }

  @Test
  void testCreateFull() {
    var mdoRef = MdoReference.create(MDOType.CATALOG, "catalog.test7", "справочник.test7");
    assertThat(mdoRef).isNotNull();
    assertThat(mdoRef.getMdoRef()).isEqualToIgnoringCase("catalog.test7");
    assertThat(mdoRef.getMdoRefRu()).isEqualToIgnoringCase("справочник.test7");
    assertThat(mdoRef.getType()).isEqualTo(MDOType.CATALOG);

    var mdoRef2 = MdoReference.create("catalog.test7");
    assertThat(mdoRef2).isEqualTo(mdoRef);

    var mdoRef3 = MdoReference.create("справочник.test7");
    assertThat(mdoRef3).isEqualTo(mdoRef);
  }

  @Test
  void testCreateChild() {
    var mdoRef = MdoReference.create(MDOType.CATALOG, "test10");
    assertThat(mdoRef).isNotNull();
    assertThat(mdoRef.getMdoRefRu()).isEqualTo("Справочник.test10");
    assertThat(mdoRef.getType()).isEqualTo(MDOType.CATALOG);

    var mdoRefChild = MdoReference.create(mdoRef, MDOType.TABULAR_SECTION, "ТЧ");
    assertThat(mdoRefChild).isNotNull();
    assertThat(mdoRefChild.getMdoRef()).isEqualTo("Catalog.test10.TabularSection.ТЧ");
    assertThat(mdoRefChild.getMdoRefRu()).isEqualTo("Справочник.test10.ТабличнаяЧасть.ТЧ");
    assertThat(mdoRefChild.getType()).isEqualTo(MDOType.TABULAR_SECTION);

    var mdoRef2 = MdoReference.create("Catalog.test10.TabularSection.ТЧ");
    assertThat(mdoRef2).isEqualTo(mdoRefChild);

    var mdoRef3 = MdoReference.create("Справочник.test10.ТабличнаяЧасть.ТЧ");
    assertThat(mdoRef3).isEqualTo(mdoRefChild);
  }

  @Test
  void testCreateCascade() {
    var mdoRef = MdoReference.create("Catalog.test12.TabularSection.ТЧ.Реквизит.Поле1");
    assertThat(mdoRef).isNotNull();
    assertThat(mdoRef.getMdoRefRu()).isEqualTo("Справочник.test12.ТабличнаяЧасть.ТЧ.Реквизит.Поле1");
    assertThat(mdoRef.getType()).isEqualTo(MDOType.ATTRIBUTE);

    var mdoRefParent1 = MdoReference.find("Справочник.test12.ТабличнаяЧасть.ТЧ");
    assertThat(mdoRefParent1).isNotEmpty();
    assertThat(mdoRefParent1.get().getMdoRef()).isEqualTo("Catalog.test12.TabularSection.ТЧ");
    assertThat(mdoRefParent1.get().getType()).isEqualTo(MDOType.TABULAR_SECTION);

    var mdoRefParent0 = MdoReference.find("Справочник.test12");
    assertThat(mdoRefParent0).isNotEmpty();
    assertThat(mdoRefParent0.get().getMdoRef()).isEqualTo("Catalog.test12");
    assertThat(mdoRefParent0.get().getType()).isEqualTo(MDOType.CATALOG);
  }

  @Test
  void testEmpty() {
    assertThat(MdoReference.EMPTY.isEmpty()).isTrue();

    var mdoRef = MdoReference.create("catalogs.test");
    assertThat(mdoRef).isNotNull();
    assertThat(mdoRef.isEmpty()).isFalse();
  }

  @Test
  void testCreateWithEmptyParent() {
    var mdoRef = MdoReference.create(MDOType.CATALOG, "test10");
    assertThat(mdoRef).isNotNull();
    assertThat(mdoRef.isEmpty()).isFalse();

    var mdoRef2 = MdoReference.create(MdoReference.EMPTY, MDOType.CATALOG, "test10");
    assertThat(mdoRef2).isNotNull();
    assertThat(mdoRef2.isEmpty()).isFalse();
    assertThat(mdoRef2).isEqualTo(mdoRef);

    var mdoRef3 = MdoReference.create(null, MDOType.CATALOG, "test10");
    assertThat(mdoRef3).isNotNull();
    assertThat(mdoRef3.isEmpty()).isFalse();
    assertThat(mdoRef3).isEqualTo(mdoRef);
  }

  @Test
  void testGetMdoRefAuto() {
    var mdoRef = MdoReference.create("catalog.test");
    assertThat(mdoRef).isNotNull();
    assertThat(mdoRef.isEmpty()).isFalse();

    assertThat(mdoRef.getMdoRef()).isEqualTo("Catalog.test");
    assertThat(mdoRef.getMdoRefRu()).isEqualTo("Справочник.test");
    assertThat(mdoRef.getMdoRef(ScriptVariant.ENGLISH)).isEqualTo("Catalog.test");
    assertThat(mdoRef.getMdoRef(ScriptVariant.RUSSIAN)).isEqualTo("Справочник.test");
    assertThat(mdoRef.getMdoRef(ScriptVariant.UNKNOWN)).isEqualTo("Справочник.test");
  }
}
