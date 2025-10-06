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
import com.github._1c_syntax.utils.StringInterner;
import edu.umd.cs.findbugs.annotations.Nullable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Используется для хранения текстовой строки на разных языках
 */
@Value
@EqualsAndHashCode
public class MultiLanguageString implements Comparable<MultiLanguageString> {

  /**
   * Ссылка на пустой элемент
   */
  public static final MultiLanguageString EMPTY = new MultiLanguageString();
  private static final GenericInterner<MultiLanguageString> interner = new GenericInterner<>();

  /**
   * Содержимое описания для каждого языка
   */
  Set<Entry> content;

  private MultiLanguageString() {
    content = Collections.emptySet();
  }

  private MultiLanguageString(String langKey, String value) {
    this(Set.of(Entry.create(langKey, value)));
  }

  private MultiLanguageString(MultiLanguageString first, MultiLanguageString second) {
    var fullContent = new HashSet<>(first.getContent());
    fullContent.addAll(second.getContent());
    content = Collections.unmodifiableSet(fullContent);
  }

  private MultiLanguageString(Set<Entry> content) {
    this.content = Set.copyOf(content);
  }

  /**
   * Создание мультиязычной строки из списка (объединение).
   * Если передан пустой список, то вернет ссылку на пустой объект.
   * Если в параметрах передан список из одного элемента, то он и будет возвращен как результат.
   *
   * @param strings Список мультиязычных строк
   * @return Объединенное значение
   */
  public static MultiLanguageString create(List<MultiLanguageString> strings) {
    if (strings.isEmpty()) {
      return EMPTY;
    } else if (strings.size() == 1) {
      return strings.get(0);
    } else {
      Set<Entry> content = new HashSet<>();
      strings.forEach(string -> content.addAll(string.getContent()));
      return new MultiLanguageString(content).intern();
    }
  }

  public static MultiLanguageString create(Set<Entry> langContent) {
    return new MultiLanguageString(langContent).intern();
  }

  public static MultiLanguageString create(MultiLanguageString first, MultiLanguageString second) {
    return new MultiLanguageString(first, second).intern();
  }

  public static MultiLanguageString create(String langKey, String value) {
    return new MultiLanguageString(langKey, value).intern();
  }

  /**
   * Возвращает содержимое для указанного языка
   *
   * @param lang Требуемый язык
   * @return Содержимое для указанного языка
   */
  public String get(String lang) {
    return content.stream()
      .filter(entry -> entry.getLangKey().equalsIgnoreCase(lang))
      .map(Entry::getValue)
      .findFirst()
      .orElse("");
  }

  /**
   * Возвращает первое попавшееся содержимое мультиязычной строки
   *
   * @return Одно из значений мультиязычной строки
   */
  public String getAny() {
    if (content.isEmpty()) {
      return "";
    }
    return content.iterator().next().getValue();
  }

  /**
   * Возвращает признак пустоты мультиязычной строки
   *
   * @return Если пустая, тогда true
   */
  public boolean isEmpty() {
    return content.isEmpty();
  }

  @Override
  public int compareTo(@Nullable MultiLanguageString multiLanguageString) {
    if (multiLanguageString == null) {
      return 1;
    }

    var leftList = content.stream().sorted().toList();
    var rightList = multiLanguageString.content.stream().sorted().toList();

    var sizeComparison = Integer.compare(leftList.size(), rightList.size());
    if (sizeComparison != 0) {
      return sizeComparison;
    }

    for (var i = 0; i < leftList.size(); i++) {
      var entryComparison = leftList.get(i).compareTo(rightList.get(i));
      if (entryComparison != 0) {
        return entryComparison;
      }
    }
    return 0;
  }

  @Override
  public String toString() {
    if (isEmpty()) {
      return "empty";
    } else {
      return content.stream()
        .map(entry -> entry.langKey + ": " + entry.value)
        .collect(Collectors.joining(", "));
    }
  }

  private MultiLanguageString intern() {
    return interner.intern(this);
  }

  @Getter
  @EqualsAndHashCode
  public static final class Entry implements Comparable<Entry> {
    private static final StringInterner stringInterner = new StringInterner();
    private static final GenericInterner<Entry> interner = new GenericInterner<>();

    private final String langKey;
    private final String value;

    private Entry(String langKey, String value) {
      this.langKey = stringInterner.intern(langKey);
      this.value = stringInterner.intern(value);
    }

    public static Entry create(String langKey, String value) {
      return new Entry(langKey, value).intern();
    }

    @Override
    public int compareTo(@Nullable Entry entry) {
      if (entry == null) {
        return 1;
      }

      if (this.equals(entry)) {
        return 0;
      }

      int compareResult = langKey.compareTo(entry.langKey);
      if (compareResult != 0) {
        return compareResult;
      }

      return value.compareTo(entry.value);
    }

    private Entry intern() {
      return interner.intern(this);
    }
  }
}
