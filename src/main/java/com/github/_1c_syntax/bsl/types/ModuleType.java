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
package com.github._1c_syntax.bsl.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Возможные типы модулей объектов
 */
@AllArgsConstructor
public enum ModuleType {

  BotModule("Module.bsl"),
  IntegrationServiceModule("Module.bsl"),
  CommandModule("CommandModule.bsl"),
  CommonModule("Module.bsl"),
  ObjectModule("ObjectModule.bsl"),
  ManagerModule("ManagerModule.bsl"),
  FormModule("Module.bsl"),
  RecordSetModule("RecordSetModule.bsl"),
  ValueManagerModule("ValueManagerModule.bsl"),
  ApplicationModule("ApplicationModule.bsl"),
  ManagedApplicationModule("ManagedApplicationModule.bsl"),
  SessionModule("SessionModule.bsl"),
  ExternalConnectionModule("ExternalConnectionModule.bsl"),
  OrdinaryApplicationModule("OrdinaryApplicationModule.bsl"),
  HTTPServiceModule("Module.bsl"),
  WEBServiceModule("Module.bsl"),
  RecalculationModule("RecordSetModule.bsl"),
  UNKNOWN(""); // для неизвестных типов модулей

  /**
   * Имя файла
   */
  @Getter
  private final String fileName;
}
