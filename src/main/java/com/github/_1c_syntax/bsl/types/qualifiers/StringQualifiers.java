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

import com.github._1c_syntax.bsl.types.AllowedLength;
import com.github._1c_syntax.bsl.types.MultiName;
import com.github._1c_syntax.bsl.types.Qualifier;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.Accessors;
import org.jspecify.annotations.Nullable;

@Value
@ToString(of = "description")
public class StringQualifiers implements Qualifier, Comparable<StringQualifiers> {
  /**
   * Длина строки
   */
  long length;

  /**
   * Вариант длины
   */
  AllowedLength allowedLength;

  /**
   * Представление квалификатора
   */
  @Accessors(fluent = true)
  MultiName description;

  private StringQualifiers(long length, AllowedLength allowedLength) {
    this.length = length;
    this.allowedLength = allowedLength;

    this.description = MultiName.create(
      "StringQualifiers (" + length + ", " + allowedLength.nameEn() + ")",
      "КвалификаторыСтроки (" + length + ", " + allowedLength.nameRu() + ")"
    );
  }

  /**
   * Создает квалификатор строки на основании длины строки.
   * Вариант длины - переменный
   *
   * @param length Длина строки base64
   * @return Квалификатор строки
   */
  public static StringQualifiers create(int length) {
    return create((long) length);
  }

  /**
   * Создает квалификатор строки на основании длины строки.
   * Вариант длины - переменный
   *
   * @param length Длина строки base64
   * @return Квалификатор строки
   */
  public static StringQualifiers create(long length) {
    return create(length, AllowedLength.VARIABLE);
  }

  /**
   * Создает квалификатор строки
   *
   * @param length        Длина строки 
   * @param allowedLength Вариант длины строки
   * @return Квалификатор строки
   */
  public static StringQualifiers create(long length, AllowedLength allowedLength) {
    return new StringQualifiers(length, allowedLength);
  }

  /**
   * Создает квалификатор строки
   *
   * @param length        Длина строки 
   * @param allowedLength Вариант длины строки
   * @return Квалификатор строки
   */
  public static StringQualifiers create(int length, AllowedLength allowedLength) {
    return create((long) length, allowedLength);
  }

  @Override
  public int compareTo(@Nullable StringQualifiers qualifiers) {
    if (qualifiers == null) {
      return 1;
    }

    if (this.equals(qualifiers)) {
      return 0;
    }

    int allowedLengthComparison = this.allowedLength.fullName()
      .compareTo(qualifiers.getAllowedLength().fullName());
    if (allowedLengthComparison != 0) {
      return allowedLengthComparison;
    }
    return Long.compare(this.length, qualifiers.getLength());
  }
}
