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

/**
 * Расширение для перечислений, подсказывающее о наличии строкового значения, по которому его можно найти
 */
public interface EnumWithName {
  /**
   * Возвращает имена значения перечисления
   *
   * @return Мультиязычное имя значения перечисления
   */
  MultiName value();

  /**
   * Возвращает имя значения по умолчанию
   *
   * @return Имя значения по умолчанию
   */
  default String name() {
    return value().get();
  }

  /**
   * Возвращает имя значения на русском языке
   *
   * @return Имя значения на русском языке
   */
  default String nameRu() {
    return value().getRu();
  }

  /**
   * Возвращает имя значения на английском языке
   *
   * @return Имя значения на английском языке
   */
  default String nameEn() {
    return value().getEn();
  }

  /**
   * Признак того, что значение используется как значение для неизвестных
   */
  default boolean isUnknown() {
    return false;
  }
}
