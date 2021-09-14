/*
 * This file is a part of BSL Common library.
 *
 * Copyright © 2021 - 2021
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
package com.github._1c_syntax.bsl.support;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 * Варианты поддержки конфигурации поставщика
 */
@AllArgsConstructor
public enum SupportVariant {
  NOT_EDITABLE(0), EDITABLE_SUPPORT_ENABLED(1), NOT_SUPPORTED(2), NONE(99);

  @Getter
  private final int priority;

  /**
   * Возвращает вариант поддержки с максимальным уровнем
   *
   * @param variants Список вариантов поддержки
   * @return Максимальный вариант поддержки
   */
  public static SupportVariant max(Collection<SupportVariant> variants) {
    return variants.stream().min(Comparator.comparing(SupportVariant::getPriority))
      .orElse(SupportVariant.NONE);
  }

  /**
   * Находит элемент по приоритету
   *
   * @param priority номер приоритета
   * @return Найденное значение
   */
  public static SupportVariant valueOf(int priority) {
    var result = Arrays.stream(values())
      .filter(supportVariant -> supportVariant.priority == priority).findFirst();
    if (result.isEmpty()) {
      return NONE;
    }
    return result.get();
  }
}
