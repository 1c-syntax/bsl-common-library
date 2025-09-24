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

import com.github._1c_syntax.utils.GenericInterner;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.EqualsAndHashCode;

/**
 * Используется для хранения имени объекта/свойства имеющего
 * 2 равноценных варианта на русском и английском языках.
 * Пример "Document.МойДокумент.Code" === "Документ.МойДокумент.Код"
 */
@EqualsAndHashCode
public final class MultiName implements Comparable<MultiName> {
  /**
   * Ссылка на пустой элемент
   */
  public static final MultiName EMPTY = new MultiName();
  private static final GenericInterner<MultiName> interner = new GenericInterner<>();

  /**
   * Имя на русском языке
   */
  private final String nameRu;

  /**
   * Имя на английском языке
   */
  private final String nameEn;

  private MultiName() {
    nameRu = "";
    nameEn = "";
  }

  private MultiName(String name) {
    if (containsNonEnglish(name)) {
      this.nameRu = name;
      this.nameEn = "";
    } else {
      this.nameRu = "";
      this.nameEn = name;
    }
  }

  private MultiName(String nameEn, String nameRu) {
    this.nameRu = nameRu;
    this.nameEn = nameEn;
  }

  /**
   * Создает мультиязычное имя по переданным именам английского и русского вариантов
   *
   * @param nameEn Английский вариант имени
   * @param nameRu Русский вариант имени
   * @return Созданное мультиязычное имя
   */
  public static MultiName create(String nameEn, String nameRu) {
    if (nameEn.isEmpty() && nameRu.isEmpty()) {
      return EMPTY;
    } else if (nameEn.isEmpty()) {
      return new MultiName(nameRu).intern();
    } else if (nameRu.isEmpty()) {
      return new MultiName(nameEn).intern();
    } else {
      return new MultiName(nameEn, nameRu).intern();
    }
  }

  /**
   * Создает мультиязычное имя по переданному имени. Вариант определяется автоматически
   *
   * @param name Имя
   * @return Созданное мультиязычное имя
   */
  public static MultiName create(String name) {
    return create(name, "");
  }

  /**
   * Возвращает значение имени по умолчанию.
   *
   * @return Имя нужного кода
   */
  public String get() {
    if (nameRu.isEmpty()) {
      return nameEn;
    }
    return nameRu;
  }

  /**
   * Возвращает имя по указанному коду языка (ru/en).
   *
   * @param code Код языка
   * @return Имя нужного имени
   */
  public String get(String code) {
    return get(ScriptVariant.valueByString(code));
  }

  /**
   * Возвращает имя по указанному варианту языка синтаксиса.
   *
   * @param scriptVariant Код языка
   * @return Имя нужного кода
   */
  public String get(ScriptVariant scriptVariant) {
    if (scriptVariant == ScriptVariant.ENGLISH) {
      return getEn();
    }
    return getRu();
  }

  /**
   * Возвращает имя на английском языке
   *
   * @return Имя на английском языке
   */
  public String getEn() {
    return nameEn;
  }

  /**
   * Возвращает имя на русском языке
   *
   * @return Имя на русском языке
   */
  public String getRu() {
    return nameRu;
  }

  /**
   * Возвращает признак пустоты мультиязычного имени
   *
   * @return Если пустая, тогда true
   */
  public boolean isEmpty() {
    return this == EMPTY;
  }

  @Override
  public int compareTo(@Nullable MultiName multiName) {
    if (multiName == null) {
      return 1;
    }

    if (this.equals(multiName)) {
      return 0;
    }

    int compareResult = nameRu.compareTo(multiName.nameRu);
    if (compareResult != 0) {
      return compareResult;
    }

    return nameEn.compareTo(multiName.nameEn);
  }

  @Override
  public String toString() {
    if (isEmpty()) {
      return "";
    } else {
      return "MultiName (ru: " + nameRu + ", en: " + nameEn + ")";
    }
  }

  private MultiName intern() {
    return interner.intern(this);
  }

  /**
   * Проверка строки на содержание символов, неиспользуемых при только английском синтаксисе
   *
   * @param text Исходная строка
   * @return Признак того, что в строке есть символы, неиспользуемые в чисто английском синтаксисе
   */
  private static boolean containsNonEnglish(String text) {
    if (text.isEmpty()) {
      return false;
    }

    for (int i = 0; i < text.length(); i++) {
      var c = text.charAt(i);

      // если символ вне ASCII - точно не английский
      if (c > 127) {
        return true;
      }

      // Проверка английских символов в ASCII диапазоне
      if (!((c >= 'A' && c <= 'Z')
        || (c >= 'a' && c <= 'z')
        || (c >= '0' && c <= '9')
        || c == '_')) {
        return true;
      }
    }

    return false;
  }
}
