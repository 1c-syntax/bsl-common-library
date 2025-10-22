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

import com.github._1c_syntax.bsl.types.AllowedLength;
import com.github._1c_syntax.bsl.types.MultiName;
import com.github._1c_syntax.bsl.types.Qualifier;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.Accessors;

import javax.annotation.Nullable;

@Value
@ToString(of = "description")
public class BinaryDataQualifiers implements Qualifier, Comparable<BinaryDataQualifiers> {
  /**
   * Длина строки base64
   */
  int length;

  /**
   * Вариант длины
   */
  AllowedLength allowedLength;

  /**
   * Представление квалификатора
   */
  @Accessors(fluent = true)
  MultiName description;

  private BinaryDataQualifiers(int length, AllowedLength allowedLength) {
    this.length = length;
    this.allowedLength = allowedLength;
    this.description = MultiName.create(
      "BinaryDataQualifiers (" + length + ", " + allowedLength.nameEn() + ")",
      "КвалификаторыДвоичныхДанных (" + length + ", " + allowedLength.nameRu() + ")"
    );
  }

  /**
   * Создает квалификатор двоичных данных на основании длины строки base64.
   * Вариант длины - переменный
   *
   * @param length Длина строки base64
   * @return Квалификатор двоичных данных
   */
  public static BinaryDataQualifiers create(int length) {
    return create(length, AllowedLength.VARIABLE);
  }

  /**
   * Создает квалификатор двоичных данных
   *
   * @param length        Длина строки base64
   * @param allowedLength Вариант длины строки
   * @return Квалификатор двоичных данных
   */
  public static BinaryDataQualifiers create(int length, AllowedLength allowedLength) {
    return new BinaryDataQualifiers(length, allowedLength);
  }

  @Override
  public int compareTo(@Nullable BinaryDataQualifiers qualifiers) {
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
    return Integer.compare(this.length, qualifiers.getLength());
  }
}