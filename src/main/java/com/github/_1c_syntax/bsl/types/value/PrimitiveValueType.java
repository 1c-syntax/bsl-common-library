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
package com.github._1c_syntax.bsl.types.value;

import com.github._1c_syntax.bsl.types.EnumWithName;
import com.github._1c_syntax.bsl.types.MultiName;
import com.github._1c_syntax.bsl.types.ValueType;
import com.github._1c_syntax.bsl.types.ValueTypeVariant;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Map;

/**
 * Примитивные типы данных
 */
@ToString(of = "fullName")
public enum PrimitiveValueType implements ValueType {
  STRING("String", "Строка"),
  DATE("Date", "Дата"),
  NUMBER("Number", "Число"),
  BOOLEAN("Boolean", "Булево"),
  NULL("Null", "Null");

  private static final Map<String, PrimitiveValueType> KEYS = EnumWithName.computeKeys(values());

  @Getter
  @Accessors(fluent = true)
  private final MultiName fullName;

  PrimitiveValueType(String nameEn, String nameRu) {
    this.fullName = MultiName.create(nameEn, nameRu);
  }

  @Override
  public ValueTypeVariant variant() {
    return ValueTypeVariant.PRIMITIVE;
  }

  /**
   * Производит определение типа по переданной строке
   *
   * @param name Строковое представление типа
   * @return Найденное значение, если не найден - то null
   */
  @Nullable
  public static PrimitiveValueType valueByName(String name) {
    return KEYS.get(name.toLowerCase(Locale.ROOT));
  }
}
