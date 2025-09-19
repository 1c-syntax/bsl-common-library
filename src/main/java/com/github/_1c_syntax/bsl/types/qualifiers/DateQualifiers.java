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

import com.github._1c_syntax.bsl.types.DateFractions;
import com.github._1c_syntax.bsl.types.Qualifier;
import lombok.Getter;

public class DateQualifiers implements Qualifier {
  /**
   * Части даты
   */
  @Getter
  private final DateFractions dateFractions;

  private DateQualifiers(DateFractions dateFractions) {
    this.dateFractions = dateFractions;
  }

  /**
   * Создает квалификатор даты по умолчанию, т.е. включающий полную дату со временем
   *
   * @return Квалификатор даты
   */
  public static DateQualifiers create() {
    return create(DateFractions.DATETIME);
  }

  /**
   * Создает квалификатор даты по указанной части даты
   *
   * @param dateFractions Части даты
   * @return Квалификатор даты
   */
  public static DateQualifiers create(DateFractions dateFractions) {
    return new DateQualifiers(dateFractions);
  }
}
