/*
 * This file is a part of BSL Common library.
 *
 * Copyright (c) 2021 - 2023
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

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Возможные типы модулей объектов
 */
@AllArgsConstructor
public enum ModuleType {

  /*
   * for bsl
   */

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

  /*
   * for oscript
   */

  OScriptClass("Class.os"),
  OScriptModule("Module.os"),

  /*
   * common
   */

  UNKNOWN(""); // для неизвестных типов модулей

  private static final Map<MDOType, Set<ModuleType>> MODULE_TYPES_FOR_MDO_TYPES = moduleTypesForMDOTypes();

  private static final List<ModuleType> OSCRIPT_MODULE_TYPES = List.of(OScriptClass, OScriptModule);

  /**
   * Имя файла
   */
  @Getter
  private final String fileName;

  /**
   * Возвращает набор типов модулей, соответствующих типу объекта метаданных
   *
   * @param mdoType Тип объекта метаданных
   * @return Набор доступных типов модулей
   */
  public static Set<ModuleType> byMDOType(MDOType mdoType) {
    return MODULE_TYPES_FOR_MDO_TYPES.getOrDefault(mdoType, Collections.emptySet());
  }

  /**
   * @return Список типов модулей для OScript
   */
  public static List<ModuleType> oScriptModuleTypes() {
    return OSCRIPT_MODULE_TYPES;
  }

  private static Map<MDOType, Set<ModuleType>> moduleTypesForMDOTypes() {
    Map<MDOType, Set<ModuleType>> result = new EnumMap<>(MDOType.class);

    for (MDOType mdoType : MDOType.values()) {
      Set<ModuleType> types = new HashSet<>();
      switch (mdoType) {
        case INTEGRATION_SERVICE:
          types.add(IntegrationServiceModule);
          break;
        case BOT:
          types.add(BotModule);
          break;
        case ACCOUNTING_REGISTER:
        case ACCUMULATION_REGISTER:
        case CALCULATION_REGISTER:
        case INFORMATION_REGISTER:
        case EXTERNAL_DATA_SOURCE_TABLE:
          types.add(ManagerModule);
          types.add(RecordSetModule);
          break;
        case BUSINESS_PROCESS:
        case CATALOG:
        case CHART_OF_ACCOUNTS:
        case CHART_OF_CALCULATION_TYPES:
        case CHART_OF_CHARACTERISTIC_TYPES:
        case DATA_PROCESSOR:
        case DOCUMENT:
        case EXCHANGE_PLAN:
        case REPORT:
        case TASK:
          types.add(ManagerModule);
          types.add(ObjectModule);
          break;
        case COMMAND_GROUP:
        case COMMON_ATTRIBUTE:
        case COMMON_PICTURE:
        case COMMON_TEMPLATE:
        case DEFINED_TYPE:
        case DOCUMENT_NUMERATOR:
        case EVENT_SUBSCRIPTION:
        case FUNCTIONAL_OPTION:
        case ROLE:
        case SCHEDULED_JOB:
        case SESSION_PARAMETER:
        case STYLE_ITEM:
        case STYLE:
        case SUBSYSTEM:
        case WS_REFERENCE:
        case XDTO_PACKAGE:
          break;
        case COMMON_COMMAND:
        case COMMAND:
          types.add(CommandModule);
          break;
        case COMMON_FORM:
        case FORM:
          types.add(FormModule);
          break;
        case COMMON_MODULE:
          types.add(CommonModule);
          break;
        case CONFIGURATION:
          types.add(ApplicationModule);
          types.add(SessionModule);
          types.add(ExternalConnectionModule);
          types.add(ManagedApplicationModule);
          types.add(OrdinaryApplicationModule);
          break;
        case CONSTANT:
          types.add(ValueManagerModule);
          types.add(ManagerModule);
          break;
        case DOCUMENT_JOURNAL:
        case ENUM:
        case FILTER_CRITERION:
        case SETTINGS_STORAGE:
          types.add(ManagerModule);
          break;
        case HTTP_SERVICE:
          types.add(HTTPServiceModule);
          break;
        case SEQUENCE:
          types.add(RecordSetModule);
          break;
        case WEB_SERVICE:
          types.add(WEBServiceModule);
          break;
        case RECALCULATION:
          types.add(RecalculationModule);
          break;
        case EXTERNAL_DATA_PROCESSOR:
        case EXTERNAL_REPORT:
          types.add(ObjectModule);
          break;
        default:
          // non
      }

      result.put(mdoType, types);
    }

    return result;
  }

}
