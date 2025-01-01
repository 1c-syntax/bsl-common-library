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

import static org.assertj.core.api.Assertions.assertThat;

class CompatibilityModeTest {

  @Test
  void testParse() {

    var version8_3_10 = "Version_8_3_10";
    var versionDontUse = "DontUse";

    assertThat(CompatibilityMode.getMAJOR()).isEqualTo(8);

    CompatibilityMode version;

    version = new CompatibilityMode(3, 99);

    assertThat(version.getMinor()).isEqualTo(3);
    assertThat(version.getVersion()).isEqualTo(99);

    version = new CompatibilityMode(versionDontUse);
    assertThat(version.getMinor()).isEqualTo(3);
    assertThat(version.getVersion()).isEqualTo(99);

    version = new CompatibilityMode(version8_3_10);
    assertThat(version.getMinor()).isEqualTo(3);
    assertThat(version.getVersion()).isEqualTo(10);

    var version8_1 = "Version8_1";
    version = new CompatibilityMode(version8_1);
    assertThat(version.getMinor()).isEqualTo(1);
    assertThat(version.getVersion()).isZero();

    version = new CompatibilityMode();
    assertThat(version.getMinor()).isEqualTo(3);
    assertThat(version.getVersion()).isEqualTo(99);
  }

  @Test
  void testCompareTo() {

    var versionA = new CompatibilityMode(3, 10);
    var versionB = new CompatibilityMode(3, 11);
    var versionC = new CompatibilityMode(2, 19);

    assertThat(CompatibilityMode.compareTo(versionA, versionB)).isEqualTo(1);
    assertThat(CompatibilityMode.compareTo(versionB, versionA)).isEqualTo(-1);
    assertThat(CompatibilityMode.compareTo(versionA, new CompatibilityMode(3, 10))).isZero();
    assertThat(CompatibilityMode.compareTo(versionA, "Version_8_3_10")).isZero();
    assertThat(CompatibilityMode.compareTo(versionB, versionC)).isEqualTo(-1);
    assertThat(CompatibilityMode.compareTo(versionA, versionC)).isEqualTo(-1);

  }

}