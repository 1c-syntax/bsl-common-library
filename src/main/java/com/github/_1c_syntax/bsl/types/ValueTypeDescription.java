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

import com.github._1c_syntax.bsl.types.qualifiers.NumberQualifiers;
import com.github._1c_syntax.bsl.types.qualifiers.StringQualifiers;
import com.github._1c_syntax.bsl.types.value.PrimitiveValueType;
import com.github._1c_syntax.bsl.types.value.V8ValueType;
import lombok.Value;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Используется для хранения описания типов значения - коллекции типов и их квалификаторов
 */
@Value
public class ValueTypeDescription {
  public static final ValueTypeDescription EMPTY = new ValueTypeDescription();

  /**
   * Список типов описания
   */
  List<ValueType> types;

  /**
   * Признак составного типа (т.е. содержит несколько типов в описании)
   */
  boolean composite;

  List<Qualifier> qualifiers;

  private ValueTypeDescription() {
    this(Collections.emptyList(), Collections.emptyList(), false);
  }

  private ValueTypeDescription(List<ValueType> types, List<Qualifier> qualifiers, boolean composite) {
    this.types = types.stream()
      .sorted(Comparator.comparing(ValueType::name))
      .distinct()
      .toList();
    this.composite = composite;
    this.qualifiers = qualifiers.stream()
      .sorted(Comparator.comparing(Qualifier::description))
      .distinct()
      .toList();
  }

  /**
   * Возвращает признак пустоты описания типа
   *
   * @return Признак Пустоты
   */
  public boolean isEmpty() {
    return this == EMPTY;
  }

  /**
   * Проверяет описание типа на вхождение в состав требуемого
   *
   * @param type Искомый тип значения
   * @return Признак вхождения
   */
  public boolean contains(ValueType type) {
    return types.contains(type);
  }

  /**
   * Конструктор описания типа по одному типу
   *
   * @param type Тип
   * @return Описание типа
   */
  public static ValueTypeDescription create(ValueType type) {
    return create(List.of(type), type == V8ValueType.ANY_REF);
  }

  /**
   * Конструктор описания типа по одному типу и его квалификатору.
   *
   * @param type      Тип
   * @param qualifier Квалификатор типа
   * @return Описание типа
   */
  public static ValueTypeDescription create(ValueType type, Qualifier... qualifier) {
    return create(List.of(type), type == V8ValueType.ANY_REF, qualifier);
  }

  /**
   * Конструктор описания типа по одному типу и явным указанием признака составного типа.
   * Рекомендуется использовать только если алгоритм автоматического определения не подходит
   *
   * @param type      Тип
   * @param composite Признак составного типа
   * @return Описание типа
   */
  public static ValueTypeDescription create(ValueType type, boolean composite) {
    return create(List.of(type), composite);
  }

  /**
   * Конструктор описания типа по одному типу, явным указанием признака составного типа и
   * набором квалификаторов.
   *
   * @param type      Тип
   * @param composite Признак составного типа
   * @return Описание типа
   */
  public static ValueTypeDescription create(ValueType type, boolean composite, Qualifier... qualifiers) {
    return create(List.of(type), composite, qualifiers);
  }

  /**
   * Конструктор описания типа по списку типов
   *
   * @param types Список типов
   * @return Описание типа
   */
  public static ValueTypeDescription create(List<ValueType> types) {
    return create(types, types.size() > 1 || types.contains(V8ValueType.ANY_REF));
  }

  /**
   * Конструктор описания типа по списку типов и явным указанием признака составного типа.
   * Рекомендуется использовать только если алгоритм автоматического определения не подходит
   *
   * @param types     Список типов
   * @param composite Признак составного типа
   * @return Описание типа
   */
  public static ValueTypeDescription create(List<ValueType> types, boolean composite) {
    return create(types, composite, new Qualifier[0]);
  }

  /**
   * Конструктор описания типа по списку типов и квалификаторов
   *
   * @param types      Список типов
   * @param qualifiers Список квалификаторов
   * @return Описание типа
   */
  public static ValueTypeDescription create(List<ValueType> types, Qualifier... qualifiers) {
    return create(types, types.size() > 1 || types.contains(V8ValueType.ANY_REF), qualifiers);
  }

  /**
   * Конструктор описания типа
   *
   * @param types      Список типов
   * @param composite  Признак составного типа
   * @param qualifiers Список квалификаторов
   * @return Описание типа
   */
  public static ValueTypeDescription create(List<ValueType> types, boolean composite, Qualifier... qualifiers) {
    return create(types, composite, List.of(qualifiers));
  }

  /**
   * Конструктор описания типа
   *
   * @param types      Список типов
   * @param composite  Признак составного типа
   * @param qualifiers Список квалификаторов
   * @return Описание типа
   */
  public static ValueTypeDescription create(List<ValueType> types, boolean composite, List<Qualifier> qualifiers) {
    if (types.isEmpty()) {
      return EMPTY;
    }
    return new ValueTypeDescription(types, qualifiers, composite);
  }

  /**
   * Создает описание типа для Строки с указанной переменной длиной
   *
   * @param length Длина строки
   * @return Описание типа строки
   */
  public static ValueTypeDescription createString(int length) {
    return create(PrimitiveValueType.STRING, StringQualifiers.create(length));
  }

  /**
   * Создает описание типа для Строки указанных длиной и ее вариантом
   *
   * @param length        Длина строки
   * @param allowedLength Вариант длины
   * @return Описание типа строки
   */
  public static ValueTypeDescription createString(int length, AllowedLength allowedLength) {
    return create(PrimitiveValueType.STRING, StringQualifiers.create(length, allowedLength));
  }

  /**
   * Создает описание типа для целого Числа с указанной длиной
   *
   * @param length Длина числа
   * @return Описание типа числа
   */
  public static ValueTypeDescription createNumber(int length) {
    return create(PrimitiveValueType.NUMBER, NumberQualifiers.create(length));
  }
}
