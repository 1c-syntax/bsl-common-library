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

import com.github._1c_syntax.utils.StringInterner;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.regex.Pattern;

/**
 * Ссылка на объект в формате ВидОбъектаМетаданных.ИмяОбъекта
 */
@Value
@EqualsAndHashCode(of = {"mdoRef"})
@ToString(of = {"mdoRef"})
public class MdoReference implements Comparable<MdoReference> {
  /**
   * Ссылка на пустую ссылку
   */
  public static final MdoReference EMPTY = new MdoReference(MDOType.UNKNOWN, "", "");

  private static final String REF_SPLIT_REGEX = "\\.";
  private static final Pattern REF_SPLIT_PATTERN = Pattern.compile(REF_SPLIT_REGEX);

  private static final StringInterner stringInterner = new StringInterner();

  /**
   * Кэш всех ссылок
   */
  private static final Map<String, MdoReference> REFERENCES =
    new ConcurrentSkipListMap<>(String.CASE_INSENSITIVE_ORDER);

  /**
   * Тип объекта метаданных
   */
  MDOType type;

  /**
   * Строковое представление ссылки
   */
  String mdoRef;

  /**
   * Строковое представление ссылки на русском языке
   */
  String mdoRefRu;

  private MdoReference(MDOType type, String mdoRef, String mdoRefRu) {
    this.type = type;
    this.mdoRef = mdoRef;
    this.mdoRefRu = mdoRefRu;
  }

  /**
   * Возвращает строковое представление ссылки в зависимости от языка разработки
   *
   * @param scriptVariant Нужный язык разработки
   * @return Строковое представление ссылки
   */
  public String getMdoRef(ScriptVariant scriptVariant) {
    if (scriptVariant == ScriptVariant.ENGLISH) {
      return getMdoRef();
    } else {
      return getMdoRefRu();
    }
  }

  /**
   * Возвращает признак пустоты текущей ссылки
   *
   * @return Признак пустоты
   */
  public boolean isEmpty() {
    return this == EMPTY;
  }

  /**
   * Создает ссылку, сохраняя ее в кэш.
   * Ответственность за корректность сформированных представлений ссылки ложится на вызывающую сторону
   *
   * @param mdoType  Тип метаданных
   * @param mdoRef   Строковая ссылка
   * @param mdoRefRu Строковая ссылка на русском языке
   * @return Ссылка на объект
   */
  public static MdoReference create(MDOType mdoType, String mdoRef, String mdoRefRu) {
    return getOrCompute(mdoType, mdoRef, mdoRefRu);
  }

  /**
   * Создает ссылку, сохраняя ее в кэш
   *
   * @param mdoType Тип метаданных
   * @param name    Имя объекта метаданных
   * @return Ссылка на объект
   */
  public static MdoReference create(MDOType mdoType, String name) {
    var mdoRef = stringInterner.intern(mdoType.nameEn() + "." + name);
    var mdoRefRu = stringInterner.intern(mdoType.nameRu() + "." + name);

    return getOrCompute(mdoType, mdoRef, mdoRefRu);
  }

  /**
   * Создание дочерней ссылки
   *
   * @param mdoReferenceOwner Ссылка родитель
   * @param mdoType           Тип дочерней ссылки
   * @param name              Имя дочернего элемента
   * @return Ссылка на элемент
   */
  public static MdoReference create(@Nullable MdoReference mdoReferenceOwner,
                                    MDOType mdoType,
                                    String name) {
    return create(mdoReferenceOwner, mdoType, name, name);
  }

  /**
   * Создание дочерней ссылки с разным представлением имени на русском и английском языках.
   * Применяется для стандартных реквизитов
   *
   * @param mdoReferenceOwner Ссылка родитель
   * @param mdoType           Тип дочерней ссылки
   * @param name              Имя дочернего элемента
   * @param nameRu            Имя дочернего элемента на русском
   * @return Ссылка на элемент
   */
  public static MdoReference create(@Nullable MdoReference mdoReferenceOwner,
                                    MDOType mdoType,
                                    String name,
                                    String nameRu) {
    if (mdoReferenceOwner == null || mdoReferenceOwner.isEmpty()) {
      return create(mdoType, name);
    } else {
      var mdoRef = stringInterner.intern(mdoReferenceOwner.getMdoRef() + "." + mdoType.nameEn() + "." + name);
      var mdoRefRu = stringInterner.intern(mdoReferenceOwner.getMdoRefRu() + "." + mdoType.nameRu() + "." + nameRu);

      return getOrCompute(mdoType, mdoRef, mdoRefRu);
    }
  }

  /**
   * Создает ссылку, сохраняя ее в кэш
   *
   * @param fullName Строковая ссылка на объект метаданных
   * @return Ссылка на объект
   */
  public static MdoReference create(String fullName) {
    var nameParts = REF_SPLIT_PATTERN.split(fullName);
    if (nameParts.length <= 1) {
      throw new IllegalArgumentException("Incorrect full name " + fullName);
    }

    MdoReference ref = null;
    var step = 2;
    for (var i = 0; i < nameParts.length; i += step) {
      var mdoType = MDOType.fromValue(nameParts[i]);
      if (mdoType.isEmpty()) {
        continue;
      }
      var mdoName = nameParts[i + 1];
      if (ref == null) {
        ref = create(mdoType.get(), mdoName);
      } else {
        ref = create(ref, mdoType.get(), mdoName);
      }
    }

    if (ref == null) {
      throw new IllegalArgumentException("Incorrect full name " + fullName);
    }

    return ref;
  }

  /**
   * Выполняет поиск ссылки по имени, используя кэш прочитанных ранее
   *
   * @param mdoRef Строковое представление ссылки
   * @return Optional-контейнер для ссылки
   */
  public static Optional<MdoReference> find(String mdoRef) {
    Optional<MdoReference> result = Optional.empty();
    if (REFERENCES.containsKey(mdoRef)) {
      result = Optional.of(REFERENCES.get(mdoRef));
    }
    return result;
  }

  @Override
  public int compareTo(@Nullable MdoReference mdoReference) {
    if (mdoReference == null) {
      return 1;
    }

    if (this.equals(mdoReference)) {
      return 0;
    }

    int typeComparison = this.type.compareTo(mdoReference.getType());
    if (typeComparison != 0) {
      return typeComparison;
    }

    int mdoRefComparison = this.mdoRef.compareTo(mdoReference.getMdoRef());
    if (mdoRefComparison != 0) {
      return mdoRefComparison;
    }

    return this.mdoRefRu.compareTo(mdoReference.getMdoRefRu());
  }

  private static MdoReference getOrCompute(MDOType mdoType, String mdoRef, String mdoRefRu) {
    if (REFERENCES.containsKey(mdoRef)) {
      return REFERENCES.get(mdoRef);
    }
    var newMdoReference = new MdoReference(mdoType, mdoRef, mdoRefRu);
    REFERENCES.put(newMdoReference.getMdoRef(), newMdoReference);
    REFERENCES.put(newMdoReference.getMdoRefRu(), newMdoReference);
    return newMdoReference;
  }
}
