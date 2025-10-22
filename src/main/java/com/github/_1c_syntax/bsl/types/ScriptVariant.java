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
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Возможные варианты языков, на которых разрабатывается код
 */
@ToString(of = "fullName")
public enum ScriptVariant implements EnumWithName {
  ENGLISH("English", "Английский", "en"),
  RUSSIAN("Russian", "Русский", "ru"),
  UNKNOWN("unknown", "неизвестный", "--");

  private static final Map<String, ScriptVariant> KEYS = computeKeys();

  @Getter
  @Accessors(fluent = true)
  private final MultiName fullName;

  /**
   * Сокращенное имя
   */
  @Getter
  @Accessors(fluent = true)
  private final String shortName;

  ScriptVariant(String nameEn, String nameRu, String shortName) {
    this.fullName = MultiName.create(nameEn, nameRu);
    this.shortName = shortName;
  }

  /**
   * Ищет элемент перечисления по именам (рус, анг, короткое)
   *
   * @param string Имя искомого элемента
   * @return Найденное значение, если не найден - то RUSSIAN
   */
  public static ScriptVariant valueByName(String string) {
    return KEYS.getOrDefault(string, RUSSIAN);
  }

  private static Map<String, ScriptVariant> computeKeys() {
    Map<String, ScriptVariant> keysMap = new ConcurrentSkipListMap<>(String.CASE_INSENSITIVE_ORDER);
    for (var element : values()) {
      if (element == UNKNOWN) {
        continue;
      }
      keysMap.put(element.nameEn().toLowerCase(Locale.ROOT), element);
      keysMap.put(element.nameRu().toLowerCase(Locale.ROOT), element);
      keysMap.put(element.shortName().toLowerCase(Locale.ROOT), element);
    }
    return keysMap;
  }
}
