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
package com.github._1c_syntax.bsl.types;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Возможные варианты языков, на которых разрабатывается код
 */
@Getter
public enum ScriptVariant implements EnumWithName {
  ENGLISH("English", "Английский", "en"),
  RUSSIAN("Russian", "Русский", "ru"),
  UNKNOWN("unknown", "неизвестный", "--") {
    @Override
    public boolean isUnknown() {
      return true;
    }
  };

  private static final Map<String, ScriptVariant> keys = computeKeys();

  ScriptVariant(String nameEn, String nameRu, String shortName) {
    this.value = MultiName.create(nameEn, nameRu);
    this.shortName = shortName;
  }

  @Accessors(fluent = true)
  private final MultiName value;

  /**
   * Сокращенное имя
   */
  @Accessors(fluent = true)
  private final String shortName;

  /**
   * Ищет элемент перечисления по именам (рус, анг, короткое)
   *
   * @param string Имя искомого элемента
   * @return Найденное значение, если не найден - то RUSSIAN
   */
  public static ScriptVariant valueByString(String string) {
    return keys.getOrDefault(string, RUSSIAN);
  }

  private static Map<String, ScriptVariant> computeKeys() {
    Map<String, ScriptVariant> keysMap = new ConcurrentSkipListMap<>(String.CASE_INSENSITIVE_ORDER);
    for (var element : values()) {
      if (element.isUnknown()) {
        continue;
      }
      keysMap.put(element.nameEn(), element);
      keysMap.put(element.nameRu(), element);
      keysMap.put(element.shortName(), element);
    }
    return keysMap;
  }
}
