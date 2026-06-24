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
import com.github._1c_syntax.utils.GenericInterner;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.Accessors;
import org.jspecify.annotations.Nullable;

@Value
@ToString(of = {"description"})
@EqualsAndHashCode(of = {"length", "allowedLength"})
public class BinaryDataQualifiers implements Qualifier, Comparable<BinaryDataQualifiers> {
  private static final GenericInterner<BinaryDataQualifiers> INTERNER = new GenericInterner<>();

  /**
   * Длина строки base64
   */
  long length;

  /**
   * Вариант длины (0 = VARIABLE, 1 = FIXED)
   */
  byte allowedLength;

  /**
   * Представление квалификатора
   */
  @Accessors(fluent = true)
  @Getter(lazy = true)
  MultiName description = MultiName.create(
    "BinaryDataQualifiers (" + length + ", " + getAllowedLength().nameEn() + ")",
    "КвалификаторыДвоичныхДанных (" + length + ", " + getAllowedLength().nameRu() + ")"
  );

  public AllowedLength getAllowedLength() {
    return allowedLength == 0 ? AllowedLength.VARIABLE : AllowedLength.FIXED;
  }

  /**
   * Создает квалификатор двоичных данных на основании длины строки base64.
   * Вариант длины - переменный
   *
   * @param length Длина строки base64
   * @return Квалификатор двоичных данных
   */
  public static BinaryDataQualifiers create(int length) {
    return create((long) length);
  }

  /**
   * Создает квалификатор двоичных данных на основании длины строки base64.
   * Вариант длины - переменный
   *
   * @param length Длина строки base64
   * @return Квалификатор двоичных данных
   */
  public static BinaryDataQualifiers create(long length) {
    return INTERNER.intern(new BinaryDataQualifiers(length, (byte) 0));
  }

  /**
   * Создает квалификатор двоичных данных
   *
   * @param length        Длина строки base64
   * @param allowedLength Вариант длины строки
   * @return Квалификатор двоичных данных
   */
  public static BinaryDataQualifiers create(long length, AllowedLength allowedLength) {
    byte code = allowedLength == AllowedLength.FIXED ? (byte) 1 : (byte) 0;
    return INTERNER.intern(new BinaryDataQualifiers(length, code));
  }

  /**
   * Создает квалификатор двоичных данных
   *
   * @param length        Длина строки base64
   * @param allowedLength Вариант длины строки
   * @return Квалификатор двоичных данных
   */
  public static BinaryDataQualifiers create(int length, AllowedLength allowedLength) {
    return create((long) length, allowedLength);
  }

  @Override
  public int compareTo(@Nullable BinaryDataQualifiers other) {
    if (other == null) {
      return 1;
    }
    if (this.equals(other)) {
      return 0;
    }
    int cmp = Byte.compare(this.allowedLength, other.allowedLength);
    return cmp != 0 ? cmp : Long.compare(this.length, other.length);
  }
}
