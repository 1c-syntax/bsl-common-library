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

import com.github._1c_syntax.bsl.types.Qualifier;
import lombok.Getter;

@Getter
public class NumberQualifiers implements Qualifier {
  /**
   * Длина числа
   */
  private final int precision;

  /**
   * Точность (количество знаков после запятой)
   */
  private final int scale;

  /**
   * Признак неотрицательности (если ложь - возможны любые варианты)
   */
  private final boolean nonNegative;

  private NumberQualifiers(int precision, int scale, boolean nonNegative) {
    this.precision = precision;
    this.scale = scale;
    this.nonNegative = nonNegative;
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
}
