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
 * Типы данных платформы 8
 */
public class V8ValueType implements ValueType {
  public static final V8ValueType VALUE_STORAGE = new V8ValueType("ValueStorage", "ХранилищеЗначений");
  public static final V8ValueType UUID = new V8ValueType("UUID", "УникальныйИдентификатор");
  public static final V8ValueType ANY_REF = new V8ValueType("AnyRef", "ЛюбаяСсылка", ValueTypeVariant.METADATA);
  public static final V8ValueType FIXED_STRUCTURE = new V8ValueType("FixedStructure", "ФиксированнаяСтруктура");
  public static final V8ValueType FIXED_ARRAY = new V8ValueType("FixedArray", "ФиксированныйМассив");
  public static final V8ValueType FIXED_MAP = new V8ValueType("FixedMap", "ФиксированноеСоответствие");

  private static final List<ValueType> BUILTIN_TYPES = List.of(
    VALUE_STORAGE, UUID, ANY_REF,
    FIXED_STRUCTURE, FIXED_ARRAY, FIXED_MAP
  );

  @Getter
  private final String name;
  @Getter
  private final String nameRu;
  @Getter
  private final ValueTypeVariant variant;

  private V8ValueType(String name, String nameRu) {
    this(name, nameRu, ValueTypeVariant.V8);
  }

  private V8ValueType(String name, String nameRu, ValueTypeVariant variant) {
    this.name = name;
    this.nameRu = nameRu;
    this.variant = variant;
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
