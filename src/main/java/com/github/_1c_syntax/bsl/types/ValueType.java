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
 * Описание типа значения
 */
public interface ValueType {
  /**
   * Полное (мультиязычное) имя типа
   *
   * @return Мультиязычное имя типа, на английском
   */
  MultiName fullName();

  /**
   * Вариант типа данных
   *
   * @return Вариант (область) типа данных
   */
  ValueTypeVariant variant();

  /**
   * Возвращает имя типа по умолчанию
   *
   * @return Имя типа по умолчанию
   */
  default String name() {
    return fullName().get();
  }

  /**
   * Возвращает имя типа на русском языке
   *
   * @return Имя типа на русском языке
   */
  default String nameRu() {
    return fullName().getRu();
  }

  /**
   * Возвращает имя типа на английском языке
   *
   * @return Имя типа на английском языке
   */
  default String nameEn() {
    return fullName().getEn();
  }
}
