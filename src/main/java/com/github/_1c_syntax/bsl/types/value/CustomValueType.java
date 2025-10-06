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

import com.github._1c_syntax.bsl.types.MDOType;
import com.github._1c_syntax.bsl.types.MultiName;
import com.github._1c_syntax.bsl.types.ValueType;
import com.github._1c_syntax.bsl.types.ValueTypeVariant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Для записи собственных типов данных
 */
@ToString(of = "fullName")
@EqualsAndHashCode(of = {"fullName", "variant", "kind"})
public final class CustomValueType implements ValueType {

  private static final Map<String, CustomValueType> KEYS = new ConcurrentHashMap<>();

  @Getter
  @Accessors(fluent = true)
  private final MultiName fullName;

  @Getter
  @Accessors(fluent = true)
  private final ValueTypeVariant variant;

  /**
   * Вид метаданных, к которому относится тип значения
   */
  @Getter
  @Accessors(fluent = true)
  private final MDOType kind;

  private CustomValueType(String name) {
    this.fullName = MultiName.create(name);
    this.variant = ValueTypeVariant.UNKNOWN;
    this.kind = MDOType.UNKNOWN;
  }

  private CustomValueType(MDOType kind, String nameEn, String nameRu) {
    this.kind = kind;
    this.fullName = MultiName.create(nameEn, nameRu);
    this.variant = ValueTypeVariant.METADATA;
  }

  /**
   * Создает новый объект для неизвестного типа. Перед созданием происходит поиск в списке ранее созданных
   *
   * @param name Имя типа
   * @return созданный тип
   */
  public static CustomValueType create(String name) {
    var key = name.toLowerCase(Locale.ROOT);
    return KEYS.computeIfAbsent(key, k -> new CustomValueType(name));
  }

  /**
   * Создает новый объект для вида метаданных. Перед созданием происходит поиск в списке ранее созданных
   * по английскому, затем по русскому имени
   *
   * @param kind   Вид метаданных, которому принадлежит тип
   * @param nameEn Полное английское имя
   * @param nameRu Полное русское имя
   * @return Созданный тип
   */
  public static CustomValueType create(MDOType kind, String nameEn, String nameRu) {
    if (!nameEn.isBlank()) {
      var value = valueByName(nameEn);
      if (value != null) {
        return value;
      }
    }

    if (!nameRu.isBlank()) {
      var value = valueByName(nameRu);
      if (value != null) {
        return value;
      }
    }

    var created = new CustomValueType(kind, nameEn, nameRu);

    if (!nameEn.isBlank()) {
      var existing = KEYS.putIfAbsent(nameEn.toLowerCase(Locale.ROOT), created);
      if (existing != null) {
        created = existing;
      }
    }

    if (!nameRu.isBlank()) {
      var existing = KEYS.putIfAbsent(nameRu.toLowerCase(Locale.ROOT), created);
      if (existing != null) {
        created = existing;
      }
    }

    return created;
  }

  /**
   * Производит определение типа по переданной строке
   *
   * @param name Строковое представление типа
   * @return Найденное значение, если не найден - то null
   */
  @Nullable
  public static CustomValueType valueByName(String name) {
    return KEYS.get(name.toLowerCase(Locale.ROOT));
  }
}
