/*
 * This file is a part of BSL Common library.
 *
 * Copyright (c) 2021 - 2024
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

class MDOTypeTest {

  @Test
  void valuesWithoutChildren() {
    var values = MDOType.valuesWithoutChildren();
    assertThat(values).hasSize(50)
      .doesNotContain(MDOType.FORM)
      .doesNotContain(MDOType.COMMAND)
      .doesNotContain(MDOType.TEMPLATE)
      .doesNotContain(MDOType.ATTRIBUTE)
      .doesNotContain(MDOType.TABULAR_SECTION)
      .doesNotContain(MDOType.RECALCULATION)
      .doesNotContain(MDOType.WS_OPERATION)
      .doesNotContain(MDOType.WS_OPERATION_PARAMETER)
      .doesNotContain(MDOType.HTTP_SERVICE_URL_TEMPLATE)
      .doesNotContain(MDOType.HTTP_SERVICE_METHOD)
      .doesNotContain(MDOType.INTEGRATION_SERVICE_CHANNEL)
      .doesNotContain(MDOType.TASK_ADDRESSING_ATTRIBUTE)
      .doesNotContain(MDOType.DIMENSION)
      .doesNotContain(MDOType.RESOURCE)
      .doesNotContain(MDOType.ENUM_VALUE)
      .doesNotContain(MDOType.COLUMN)
      .doesNotContain(MDOType.ACCOUNTING_FLAG)
      .doesNotContain(MDOType.EXT_DIMENSION_ACCOUNTING_FLAG)
      .doesNotContain(MDOType.UNKNOWN);
  }

  @Test
  void fromValue() {
    assertThat(MDOType.fromValue("FORM")).isPresent().contains(MDOType.FORM);
    assertThat(MDOType.fromValue("attribute")).isPresent().contains(MDOType.ATTRIBUTE);
    assertThat(MDOType.fromValue("string")).isNotPresent();
  }
}