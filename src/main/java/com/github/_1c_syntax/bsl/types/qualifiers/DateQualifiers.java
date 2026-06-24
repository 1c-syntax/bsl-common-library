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
package com.github._1c_syntax.bsl.types.qualifiers;

import com.github._1c_syntax.bsl.types.DateFractions;
import com.github._1c_syntax.bsl.types.MultiName;
import com.github._1c_syntax.bsl.types.Qualifier;
import com.github._1c_syntax.utils.GenericInterner;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.Accessors;
import org.jspecify.annotations.Nullable;

@Value
@ToString(of = {"description"})
@EqualsAndHashCode(of = {"dateFractions"})
public class DateQualifiers implements Qualifier, Comparable<DateQualifiers> {
  /**
   * Части даты (0 = TIME, 1 = DATE, 2 = DATE_TIME)
   */
  byte dateFractions;

  /**
   * Представление квалификатора
   */
  @Accessors(fluent = true)
  @Getter(lazy = true)
  MultiName description = MultiName.create(
    "DateQualifiers (" + getDateFractions().nameEn() + ")",
    "КвалификаторыДаты (" + getDateFractions().nameRu() + ")"
  );

  private static final GenericInterner<DateQualifiers> INTERNER = new GenericInterner<>();

  public DateFractions getDateFractions() {
    return switch (dateFractions) {
      case 0 -> DateFractions.TIME;
      case 1 -> DateFractions.DATE;
      default -> DateFractions.DATE_TIME;
    };
  }

  /**
   * Создает квалификатор даты по умолчанию, т.е. включающий полную дату со временем
   *
   * @return Квалификатор даты
   */
  public static DateQualifiers create() {
    return create(DateFractions.DATE_TIME);
  }

  /**
   * Создает квалификатор даты по указанной части даты
   *
   * @param dateFractions Части даты
   * @return Квалификатор даты
   */
  public static DateQualifiers create(DateFractions dateFractions) {
    byte code = switch (dateFractions) {
      case TIME -> 0;
      case DATE -> 1;
      default -> 2;
    };
    return INTERNER.intern(new DateQualifiers(code));
  }

  @Override
  public int compareTo(@Nullable DateQualifiers other) {
    if (other == null) {
      return 1;
    }
    if (this.equals(other)) {
      return 0;
    }
    return Byte.compare(this.dateFractions, other.dateFractions);
  }
}
