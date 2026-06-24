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
package com.github._1c_syntax.bsl.types;

import com.github._1c_syntax.bsl.types.value.CustomValueType;
import com.github._1c_syntax.bsl.types.value.MDOValueType;
import com.github._1c_syntax.bsl.types.value.PrimitiveValueType;
import com.github._1c_syntax.bsl.types.value.V8ValueType;
import lombok.experimental.UtilityClass;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Помощник получения типа по ключу. Кеширует рассчитанные значения
 */
@UtilityClass
public class ValueTypes {
  private static final Map<String, ValueType> KEYS = computeKeys();

  /**
   * Производит определение типа по переданной строке
   *
   * @param name Строковое представление типа
   * @return Найденное значение, если не найден - то null
   */
  @Nullable
  public static ValueType get(String name) {
    return KEYS.get(name.toLowerCase(Locale.ROOT));
  }

  /**
   * Производит определение типа по переданной строке. Если значение не найдено, то будет создано новое
   *
   * @param name Строковое представление типа
   * @return Найденное или созданное значение
   */
  public static ValueType getOrCompute(String name) {
    var enKey = name.toLowerCase(Locale.ROOT);
    var value = KEYS.get(enKey);
    if (value == null) {
      var computed = computeValue(name);
      var existing = KEYS.putIfAbsent(enKey, computed);
      if (existing != null) {
        value = existing;
      } else {
        value = computed;
        KEYS.putIfAbsent(computed.nameEn().toLowerCase(Locale.ROOT), computed);
        KEYS.putIfAbsent(computed.nameRu().toLowerCase(Locale.ROOT), computed);
      }
    }
    return value;
  }

  private static ValueType computeValue(String name) {
    var posDot = name.indexOf(".");
    if (posDot > 0) {
      var key = name.substring(0, posDot);
      var baseType = KEYS.get(key.toLowerCase(Locale.ROOT));
      if (baseType instanceof MDOValueType mdoValueType) {
        return CustomValueType.create(mdoValueType.kind(),
          mdoValueType.nameEn() + name.substring(posDot),
          mdoValueType.nameRu() + name.substring(posDot));
      }
    }
    return CustomValueType.create(name);
  }

  private static Map<String, ValueType> computeKeys() {
    Map<String, ValueType> keysMap = new ConcurrentHashMap<>();
    List.of(MDOValueType.values(), PrimitiveValueType.values(), V8ValueType.values())
      .forEach(enums ->
        keysMap.putAll(EnumWithName.computeKeys(enums))
      );
    return keysMap;
  }
}
