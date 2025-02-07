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

import lombok.Getter;
import org.apache.commons.collections4.map.CaseInsensitiveMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Типы объектов метаданных
 */
@Getter
public enum MDOType {
  ACCOUNTING_FLAG("AccountingFlag", "AccountingFlags", "ПризнакУчета", "ПризнакиУчета"),
  ACCOUNTING_REGISTER("AccountingRegister", "AccountingRegisters",
    "РегистрБухгалтерии", "РегистрыБухгалтерии"),
  ACCUMULATION_REGISTER("AccumulationRegister", "AccumulationRegisters",
    "РегистрНакопления", "РегистрыНакопления"),
  ATTRIBUTE("Attribute", "Attributes", "Реквизит", "Реквизиты"),

  BOT("Bot", "Bots", "Бот", "Боты"),
  BUSINESS_PROCESS("BusinessProcess", "BusinessProcesses",
    "БизнесПроцесс", "БизнесПроцессы"),

  CALCULATION_REGISTER("CalculationRegister", "CalculationRegisters",
    "РегистрРасчета", "РегистрыРасчета"),
  CATALOG("Catalog", "Catalogs", "Справочник", "Справочники"),
  CHART_OF_ACCOUNTS("ChartOfAccounts", "ChartsOfAccounts",
    "ПланСчетов", "ПланыСчетов"),
  CHART_OF_CALCULATION_TYPES("ChartOfCalculationTypes", "ChartsOfCalculationTypes",
    "ПланВидовРасчета", "ПланыВидовРасчета"),
  CHART_OF_CHARACTERISTIC_TYPES("ChartOfCharacteristicTypes", "ChartsOfCharacteristicTypes",
    "ПланВидовХарактеристик", "ПланыВидовХарактеристик"),
  COLUMN("Column", "Columns", "Колонка", "Колонки"),
  COMMAND("Command", "Commands", "Команда", "Команды"),
  COMMAND_GROUP("CommandGroup", "CommandGroups", "ГруппаКоманд", "ГруппыКоманд"),
  COMMON_ATTRIBUTE("CommonAttribute", "CommonAttributes",
    "ОбщийРеквизит", "ОбщиеРеквизиты"),
  COMMON_COMMAND("CommonCommand", "CommonCommands",
    "ОбщаяКоманда", "ОбщиеКоманды"),
  COMMON_FORM("CommonForm", "CommonForms", "ОбщаяФорма", "ОбщиеФормы"),
  COMMON_MODULE("CommonModule", "CommonModules", "ОбщийМодуль", "ОбщиеМодули"),
  COMMON_PICTURE("CommonPicture", "CommonPictures", "ОбщаяКартинка", "ОбщиеКартинки"),
  COMMON_TEMPLATE("CommonTemplate", "CommonTemplates", "ОбщийМакет", "ОбщиеМакеты"),
  CONFIGURATION("Configuration", "", "Конфигурация", ""),
  CONSTANT("Constant", "Constants", "Константа", "Константы"),

  DATA_PROCESSOR("DataProcessor", "DataProcessors", "Обработка", "Обработки"),
  DEFINED_TYPE("DefinedType", "DefinedTypes", "ОпределяемыйТип", "ОпределяемыеТипы"),
  DIMENSION("Dimension", "Dimensions", "Измерение", "Измерения"),
  DOCUMENT("Document", "Documents", "Документ", "Документы"),
  DOCUMENT_JOURNAL("DocumentJournal", "DocumentJournals",
    "ЖурналДокументов", "ЖурналыДокументов"),
  DOCUMENT_NUMERATOR("DocumentNumerator", "DocumentNumerators",
    "НумераторДокументов", "НумераторыДокументов"),

  ENUM("Enum", "Enums", "Перечисление", "Перечисления"),
  ENUM_VALUE("EnumValue", "EnumValues", "ЗначениеПеречисления", "ЗначенияПеречисления"),
  EVENT_SUBSCRIPTION("EventSubscription", "EventSubscriptions",
    "ПодпискаНаСобытие", "ПодпискиНаСобытия"),
  EXCHANGE_PLAN("ExchangePlan", "ExchangePlans", "ПланОбмена", "ПланыОбмена"),
  EXTERNAL_DATA_PROCESSOR("ExternalDataProcessor", "ExternalDataProcessors",
    "ВнешняяОбработка", "ВнешниеОбработки"),
  EXTERNAL_DATA_SOURCE("ExternalDataSource", "ExternalDataSources",
    "ВнешнийИсточникДанных", "ВнешниеИсточникиДанных"),
  EXTERNAL_DATA_SOURCE_TABLE("Table", "Tables", "Таблица", "Таблицы"),
  EXTERNAL_DATA_SOURCE_TABLE_FILED("Field", "Fields", "Поле", "Поля"),
  EXTERNAL_REPORT("ExternalReport", "ExternalReports",
    "ВнешнийОтчет", "ВнешниеОтчеты"),
  EXT_DIMENSION_ACCOUNTING_FLAG("ExtDimensionAccountingFlag", "ExtDimensionAccountingFlags",
    "ПризнакУчетаСубконто", "ПризнакиУчетаСубконто"),

  FILTER_CRITERION("FilterCriterion", "FilterCriteria",
    "КритерийОтбора", "КритерииОтбора"),
  FORM("Form", "Forms", "Форма", "Формы"),
  FUNCTIONAL_OPTION("FunctionalOption", "FunctionalOptions",
    "ФункциональнаяОпция", "ФункциональныеОпции"),
  FUNCTIONAL_OPTIONS_PARAMETER("FunctionalOptionsParameter", "FunctionalOptionsParameters",
    "ПараметрФункциональныхОпций", "ПараметрыФункциональныхОпций"),

  HTTP_SERVICE("HTTPService", "HTTPServices", "HTTPСервис", "HTTPСервисы"),
  HTTP_SERVICE_METHOD("Method", "Methods", "Метод", "Методы"),
  HTTP_SERVICE_URL_TEMPLATE("URLTemplate", "URLTemplates", "ШаблонURL", "ШаблоныURL"),

  INFORMATION_REGISTER("InformationRegister", "InformationRegisters",
    "РегистрСведений", "РегистрыСведений"),
  INTEGRATION_SERVICE("IntegrationService", "IntegrationServices",
    "СервисИнтеграции", "СервисыИнтеграции"),
  INTEGRATION_SERVICE_CHANNEL("IntegrationServiceChannel", "IntegrationServiceChannels",
    "КаналСервисаИнтеграции", "Каналы"),

  INTERFACE("Interface", "Interfaces", "Интерфейс", "Интерфейсы"),

  LANGUAGE("Language", "Languages", "Язык", "Языки"),

  PALETTE_COLOR("PaletteColor", "PaletteColors",
    "ЦветПалитры", "ЦветаПалитры"),

  RECALCULATION("Recalculation", "Recalculations", "Перерасчет", "Перерасчеты"),
  REPORT("Report", "Reports", "Отчет", "Отчеты"),
  RESOURCE("Resource", "Resources", "Ресурс", "Ресурсы"),
  ROLE("Role", "Roles", "Роль", "Роли"),

  SCHEDULED_JOB("ScheduledJob", "ScheduledJobs",
    "РегламентноеЗадание", "РегламентныеЗадания"),
  SEQUENCE("Sequence", "Sequences", "Последовательность", "Последовательности"),
  SESSION_PARAMETER("SessionParameter", "SessionParameters",
    "ПараметрСеанса", "ПараметрыСеанса"),
  SETTINGS_STORAGE("SettingsStorage", "SettingsStorages",
    "ХранилищеНастроек", "ХранилищаНастроек"),
  STANDARD_ATTRIBUTE("StandardAttribute", "StandardAttributes",
    "СтандартныйРеквизит", "СтандартныеРеквизиты"),
  STANDARD_TABULAR_SECTION("StandardTabularSection", "StandardTabularSections",
    "СтандартнаяТабличнаяЧасть", "СтандартныеТабличныеЧасти"),
  STYLE("Style", "Styles", "Стиль", "Стили"),
  STYLE_ITEM("StyleItem", "StyleItems", "ЭлементСтиля", "ЭлементыСтиля"),
  SUBSYSTEM("Subsystem", "Subsystems", "Подсистема", "Подсистемы"),

  TABULAR_SECTION("TabularSection", "TabularSections", "ТабличнаяЧасть", "ТабличныеЧасти"),
  TASK("Task", "Tasks", "Задача", "Задачи"),
  TASK_ADDRESSING_ATTRIBUTE("AddressingAttribute", "AddressingAttributes",
    "РеквизитАдресации", "Реквизиты адресации"),
  TEMPLATE("Template", "Templates", "Макет", "Макеты"),

  WEB_SERVICE("WebService", "WebServices", "WebСервис", "WebСервисы"),
  WS_OPERATION("Operation", "Operations", "Операция", "Операции"),
  WS_OPERATION_PARAMETER("Parameter", "Parameters", "Параметр", "Параметры"),
  WS_REFERENCE("WSReference", "WSReferences", "WSСсылка", "WSСсылки"),

  XDTO_PACKAGE("XDTOPackage", "XDTOPackages", "ПакетXDTO", "ПакетыXDTO"),

  UNKNOWN("", "", "", "");

  private static final Map<String, MDOType> MAP_TYPES = computeMapTypes();
  private static final Set<MDOType> CHILD_TYPES = computeChildTypes();

  /**
   * Англоязычное имя типа
   */
  private final String name;

  /**
   * Англоязычное имя группы объектов типа
   */
  private final String groupName;

  /**
   * Русскоязычное имя типа
   */
  private final String nameRu;

  /**
   * Русскоязычное имя группы объектов типа
   */
  private final String groupNameRu;

  MDOType(String nameEn, String groupNameEn, String nameRu, String groupNameRu) {
    this.name = nameEn;
    this.groupName = groupNameEn;
    this.nameRu = nameRu;
    this.groupNameRu = groupNameRu;
  }

  /**
   * Возвращает список элементов перечисления без дочерних
   *
   * @return - список с примененным фильтром (без дочерних)
   */
  public static List<MDOType> valuesWithoutChildren() {
    return Arrays.stream(values()).filter(mdoType ->
        !CHILD_TYPES.contains(mdoType) && mdoType != UNKNOWN)
      .collect(Collectors.toList());
  }

  /**
   * Возвращает MDOType по строковому идентификатору
   *
   * @param value - Строковый идентификатор типа. Может быть на русском или английском языках,
   *              а так же во множественном или единственном числе
   * @return - Найденный тип
   */
  public static Optional<MDOType> fromValue(String value) {
    return Optional.ofNullable(MAP_TYPES.get(value));
  }

  private static Map<String, MDOType> computeMapTypes() {
    Map<String, MDOType> map = new CaseInsensitiveMap<>();
    for (MDOType mdoType : MDOType.values()) {
      map.put(mdoType.getName(), mdoType);
      map.put(mdoType.getGroupName(), mdoType);
      map.put(mdoType.getNameRu(), mdoType);
      map.put(mdoType.getGroupNameRu(), mdoType);
    }
    return map;
  }

  private static Set<MDOType> computeChildTypes() {
    return Set.of(FORM, COMMAND, TEMPLATE, ATTRIBUTE, TABULAR_SECTION, RECALCULATION, WS_OPERATION,
      WS_OPERATION_PARAMETER, HTTP_SERVICE_URL_TEMPLATE, HTTP_SERVICE_METHOD, INTEGRATION_SERVICE_CHANNEL,
      TASK_ADDRESSING_ATTRIBUTE, DIMENSION, RESOURCE, ENUM_VALUE, COLUMN,
      ACCOUNTING_FLAG, EXT_DIMENSION_ACCOUNTING_FLAG, STANDARD_ATTRIBUTE, STANDARD_TABULAR_SECTION,
      EXTERNAL_DATA_SOURCE_TABLE, EXTERNAL_DATA_SOURCE_TABLE_FILED);
  }
}
