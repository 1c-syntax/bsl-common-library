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

import com.github._1c_syntax.bsl.types.ValueType;
import com.github._1c_syntax.bsl.types.ValueTypeVariant;
import lombok.Getter;

import java.util.List;

/**
 * Примитивные типы данных
 */
public class PrimitiveValueType implements ValueType {
  public static final PrimitiveValueType STRING = new PrimitiveValueType("String", "Строка");
  public static final PrimitiveValueType DATE = new PrimitiveValueType("Date", "Дата");
  public static final PrimitiveValueType NUMBER = new PrimitiveValueType("Number", "Число");
  public static final PrimitiveValueType BOOLEAN = new PrimitiveValueType("Boolean", "Булево");
  public static final PrimitiveValueType NULL = new PrimitiveValueType("Null", "Null");

  private static final List<ValueType> BUILTIN_TYPES = List.of(STRING, DATE, NUMBER, BOOLEAN, NULL);

  @Getter
  private final String name;
  @Getter
  private final String nameRu;

  private PrimitiveValueType(String name, String nameRu) {
    this.name = name;
    this.nameRu = nameRu;
  }

  @Override
  public ValueTypeVariant getVariant() {
    return ValueTypeVariant.PRIMITIVE;
  }

  /**
   * Коллекция встроенных типов
   *
   * @return Список встроенных типов
   */
  public static List<ValueType> builtinTypes() {
    return BUILTIN_TYPES;
  }
}
