/*
 * This file is a part of BSL Common library.
 *
 * Copyright © 2021 - 2022
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
import static org.junit.jupiter.api.Assertions.*;

class ModuleTypeTest {

  @Test
  void byMDOType() {
    var modules = ModuleType.byMDOType(MDOType.FORM);
    assertThat(modules)
      .hasSize(1)
      .contains(ModuleType.FormModule);

    modules = ModuleType.byMDOType(MDOType.RECALCULATION);
    assertThat(modules)
      .hasSize(1)
      .contains(ModuleType.RecalculationModule);

    modules = ModuleType.byMDOType(MDOType.ACCOUNTING_REGISTER);
    assertThat(modules)
      .hasSize(2)
      .contains(ModuleType.RecordSetModule)
      .contains(ModuleType.ManagerModule);

    modules = ModuleType.byMDOType(MDOType.ATTRIBUTE);
    assertThat(modules).isEmpty();

    modules = ModuleType.byMDOType(MDOType.TEMPLATE);
    assertThat(modules).isEmpty();
  }
}