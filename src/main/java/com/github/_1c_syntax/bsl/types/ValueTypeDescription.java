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
import com.github._1c_syntax.bsl.types.value.MDOValueType;
import com.github._1c_syntax.bsl.types.value.PrimitiveValueType;
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
      .sorted(Comparator.comparing(ValueType::nameEn))
      .distinct()
      .toList();
    this.composite = composite;
    this.qualifiers = qualifiers.stream()
      .sorted(Comparator.comparing(Qualifier::description))
      .filter(qualifier -> !Qualifier.EMPTY.equals(qualifier))
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
    return create(type, Qualifier.EMPTY);
  }

  /**
   * Конструктор описания типа по одному типу и его квалификатору.
   *
   * @param type      Тип
   * @param qualifier Квалификатор типа
   * @return Описание типа
   */
  public static ValueTypeDescription create(ValueType type, Qualifier qualifier) {
    return create(List.of(type), List.of(qualifier));
  }

  /**
   * Конструктор описания типа по списку типов
   *
   * @param types Список типов
   * @return Описание типа
   */
  public static ValueTypeDescription create(List<ValueType> types) {
    return create(types, Qualifier.EMPTY);
  }

  /**
   * Конструктор описания типа по списку типов и квалификаторов
   *
   * @param types      Список типов
   * @param qualifiers Список квалификаторов
   * @return Описание типа
   */
  public static ValueTypeDescription create(List<ValueType> types, Qualifier... qualifiers) {
    return create(types, List.of(qualifiers));
  }

  /**
   * Конструктор описания типа по списку типов и квалификаторов
   *
   * @param types      Список типов
   * @param qualifiers Список квалификаторов
   * @return Описание типа
   */
  public static ValueTypeDescription create(List<ValueType> types, List<Qualifier> qualifiers) {
    if (types.isEmpty()) {
      return EMPTY;
    }

    var composite = types.size() > 1 || types.get(0) instanceof MDOValueType;
    return new ValueTypeDescription(types, qualifiers, composite);
  }

  /**
   * Конструктор описания типа
   *
   * @param types      Список типов
   * @param qualifiers Список квалификаторов
   * @param composite  Признак составного типа
   * @return Описание типа
   */
  public static ValueTypeDescription create(List<ValueType> types, List<Qualifier> qualifiers, boolean composite) {
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

  /**
   * Создает описание типа для ссылки на объект метаданных
   *
   * @param mdoType Тип метаданных
   * @param mdoName Имя объекта метаданных
   * @return Описание типа ссылки
   */
  public static ValueTypeDescription createRef(MDOType mdoType, String mdoName) {
    var result = ValueTypes.getOrCompute(mdoType.nameEn() + "Ref." + mdoName);
    return create(result);
  }

  /**
   * Создает описание типа для ссылки на объект метаданных
   *
   * @param mdoReference Ссылка на объект метаданных
   * @return Описание типа ссылки
   */
  public static ValueTypeDescription createRef(MdoReference mdoReference) {
    if (mdoReference.isEmpty()) {
      return EMPTY;
    }

    return create(createRefType(mdoReference));
  }

  /**
   * Создает описание типа для набора ссылок на объекты метаданных
   *
   * @param mdoReferences Набор ссылок на объекты метаданных
   * @return Описание типа
   */
  public static ValueTypeDescription createRef(List<MdoReference> mdoReferences) {
    if (mdoReferences.isEmpty()) {
      return EMPTY;
    }

    var types = mdoReferences.stream()
      .filter(mdoReference -> !mdoReference.isEmpty())
      .map(ValueTypeDescription::createRefType)
      .toList();

    if (types.isEmpty()) {
      return EMPTY;
    }

    return create(types);
  }

  private static ValueType createRefType(MdoReference mdoReference) {
    var parts = mdoReference.getMdoRef().split("\\.");
    parts[0] += "Ref";
    return ValueTypes.getOrCompute(String.join(".", parts));
  }
}
