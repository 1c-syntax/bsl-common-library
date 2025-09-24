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

import com.github._1c_syntax.bsl.types.MultiName;
import com.github._1c_syntax.bsl.types.Qualifier;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.Accessors;

import javax.annotation.Nullable;

@Value
@ToString(of = "description")
public class NumberQualifiers implements Qualifier, Comparable<NumberQualifiers> {
  /**
   * Длина числа
   */
  int precision;

  /**
   * Точность (количество знаков после запятой)
   */
  int scale;

  /**
   * Признак неотрицательности (если ложь - возможны любые варианты)
   */
  boolean nonNegative;

  /**
   * Представление квалификатора
   */
  @Accessors(fluent = true)
  MultiName description;

  private NumberQualifiers(int precision, int scale, boolean nonNegative) {
    this.precision = precision;
    this.scale = scale;
    this.nonNegative = nonNegative;

    this.description = MultiName.create(
      "NumberQualifiers (" + precision + "." + scale + (nonNegative ? " nonneg)" : ")"),
      "КвалификаторыЧисла (" + precision + "." + scale + (nonNegative ? " неотр)" : ")")
    );
  }

  /**
   * Создает квалификатор числа на основании длины.
   * Точность устанавливается в 0, признак неотрицательности - ложь
   *
   * @param precision Длина
   * @return Квалификатор числа
   */
  public static NumberQualifiers create(int precision) {
    return create(precision, 0, false);
  }

  /**
   * Создает квалификатор числа на основании длины и точности.
   * Признак неотрицательности - ложь
   *
   * @param precision Длина
   * @param scale     Точность
   * @return Квалификатор числа
   */
  public static NumberQualifiers create(int precision, int scale) {
    return create(precision, scale, false);
  }

  /**
   * Создает квалификатор числа
   *
   * @param precision   Длина
   * @param scale       Точность
   * @param nonNegative Признак неотрицательности
   * @return Квалификатор числа
   */
  public static NumberQualifiers create(int precision, int scale, boolean nonNegative) {
    return new NumberQualifiers(precision, scale, nonNegative);
  }

  @Override
  public int compareTo(@Nullable NumberQualifiers qualifiers) {
    if (qualifiers == null) {
      return 1;
    }

    if (this.equals(qualifiers)) {
      return 0;
    }

    int precisionComparison = Integer.compare(this.precision, qualifiers.getPrecision());
    if (precisionComparison != 0) {
      return precisionComparison;
    }

    int scaleComparison = Integer.compare(this.scale, qualifiers.getScale());
    if (scaleComparison != 0) {
      return scaleComparison;
    }

    return Boolean.compare(this.nonNegative, qualifiers.isNonNegative());
  }
}
