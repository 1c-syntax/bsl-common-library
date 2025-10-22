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
package com.github._1c_syntax.bsl.types.value;

import com.github._1c_syntax.bsl.types.EnumWithName;
import com.github._1c_syntax.bsl.types.MDOType;
import com.github._1c_syntax.bsl.types.MultiName;
import com.github._1c_syntax.bsl.types.ValueType;
import com.github._1c_syntax.bsl.types.ValueTypeVariant;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Map;

/**
 * Типы данных, построенные на метаданных конфигурации 1С Предприятие
 */
@ToString(of = "fullName")
public enum MDOValueType implements ValueType {
  ACCOUNTING_REGISTER_MANAGER(MDOType.ACCOUNTING_REGISTER, "Manager", "Менеджер"),
  ACCOUNTING_REGISTER_RECORD_SET(MDOType.ACCOUNTING_REGISTER, "RecordSet", "НаборЗаписей"),
  ACCUMULATION_REGISTER_MANAGER(MDOType.ACCUMULATION_REGISTER, "Manager", "Менеджер"),
  ACCUMULATION_REGISTER_RECORD_SET(MDOType.ACCUMULATION_REGISTER, "RecordSet", "НаборЗаписей"),
  ANY_REF("AnyRef", "ЛюбаяСсылка"),
  BUSINESS_PROCESS_MANAGER(MDOType.BUSINESS_PROCESS, "Manager", "Менеджер"),
  BUSINESS_PROCESS_OBJECT(MDOType.BUSINESS_PROCESS, "Object", "Объект"),
  BUSINESS_PROCESS_REF(MDOType.BUSINESS_PROCESS, "Ref", "Ссылка"),
  BUSINESS_PROCESS_ROUTE_POINT_REF(MDOType.BUSINESS_PROCESS,
    MDOType.BUSINESS_PROCESS.nameEn() + "RoutePointRef",
    "ТочкаМаршрутаБизнесПроцессаСсылка",
    true),
  CALCULATION_REGISTER_MANAGER(MDOType.CALCULATION_REGISTER, "Manager", "Менеджер"),
  CALCULATION_REGISTER_RECORD_SET(MDOType.CALCULATION_REGISTER, "RecordSet", "НаборЗаписей"),
  CATALOG_MANAGER(MDOType.CATALOG, "Manager", "Менеджер"),
  CATALOG_OBJECT(MDOType.CATALOG, "Object", "Объект"),
  CATALOG_REF(MDOType.CATALOG, "Ref", "Ссылка"),
  CHARACTERISTIC(MDOType.CHART_OF_CHARACTERISTIC_TYPES, "Characteristic", "Характеристика", true),
  CHART_OF_ACCOUNTS_MANAGER(MDOType.CHART_OF_ACCOUNTS, "Manager", "Менеджер"),
  CHART_OF_ACCOUNTS_OBJECT(MDOType.CHART_OF_ACCOUNTS, "Object", "Объект"),
  CHART_OF_ACCOUNTS_REF(MDOType.CHART_OF_ACCOUNTS, "Ref", "Ссылка"),
  CHART_OF_CALCULATION_TYPES_MANAGER(MDOType.CHART_OF_CALCULATION_TYPES, "Manager", "Менеджер"),
  CHART_OF_CALCULATION_TYPES_OBJECT(MDOType.CHART_OF_CALCULATION_TYPES, "Object", "Объект"),
  CHART_OF_CALCULATION_TYPES_REF(MDOType.CHART_OF_CALCULATION_TYPES, "Ref", "Ссылка"),
  CHART_OF_CHARACTERISTIC_TYPES_MANAGER(MDOType.CHART_OF_CHARACTERISTIC_TYPES, "Manager", "Менеджер"),
  CHART_OF_CHARACTERISTIC_TYPES_OBJECT(MDOType.CHART_OF_CHARACTERISTIC_TYPES, "Object", "Объект"),
  CHART_OF_CHARACTERISTIC_TYPES_REF(MDOType.CHART_OF_CHARACTERISTIC_TYPES, "Ref", "Ссылка"),
  CONSTANTS_SET(MDOType.CONSTANT, MDOType.CONSTANT.groupName() + "Set", MDOType.CONSTANT.groupNameRu() + "Набор", true),
  CONSTANT_VALUE_MANAGER(MDOType.CONSTANT, "ValueManager", "МенеджерЗначения"),
  DATA_PROCESSOR_MANAGER(MDOType.DATA_PROCESSOR, "Manager", "Менеджер"),
  DATA_PROCESSOR_OBJECT(MDOType.DATA_PROCESSOR, "Object", "Объект"),
  DEFINED_TYPE(MDOType.DEFINED_TYPE, "", ""),
  DOCUMENT_JOURNAL_MANAGER(MDOType.DOCUMENT_JOURNAL, "Manager", "Менеджер"),
  DOCUMENT_MANAGER(MDOType.DOCUMENT, "Manager", "Менеджер"),
  DOCUMENT_OBJECT(MDOType.DOCUMENT, "Object", "Объект"),
  DOCUMENT_REF(MDOType.DOCUMENT, "Ref", "Ссылка"),
  ENUM_LIST(MDOType.ENUM, "List", "Список"),
  ENUM_REF(MDOType.ENUM, "Ref", "Ссылка"),
  EXCHANGE_PLAN_MANAGER(MDOType.EXCHANGE_PLAN, "Manager", "Менеджер"),
  EXCHANGE_PLAN_OBJECT(MDOType.EXCHANGE_PLAN, "Object", "Объект"),
  EXCHANGE_PLAN_REF(MDOType.EXCHANGE_PLAN, "Ref", "Ссылка"),
  EXTERNAL_DATA_PROCESSOR_OBJECT(MDOType.EXTERNAL_DATA_PROCESSOR, "Object", "Объект"),
  EXTERNAL_DATA_SOURCE_TABLE_OBJECT(MDOType.EXTERNAL_DATA_SOURCE, "TableObject", "ТаблицаОбъект"),
  EXTERNAL_DATA_SOURCE_TABLE_REF(MDOType.EXTERNAL_DATA_SOURCE, "TableRef", "ТаблицаСсылка"),
  EXTERNAL_DATA_SOURCE_CUBE_DIMENSION_TABLE_OBJECT(MDOType.EXTERNAL_DATA_SOURCE, "CubeDimensionTableObject",
    "КубТаблицаИзмеренийОбъект"),
  EXTERNAL_DATA_SOURCE_CUBE_DIMENSION_TABLE_REF(MDOType.EXTERNAL_DATA_SOURCE, "CubeDimensionTableRef",
    "КубТаблицаИзмеренийСсылка"),
  EXTERNAL_REPORT_OBJECT(MDOType.EXTERNAL_REPORT, "Object", "Объект"),
  INFORMATION_REGISTER_LIST(MDOType.INFORMATION_REGISTER, "List", "Список"),
  INFORMATION_REGISTER_MANAGER(MDOType.INFORMATION_REGISTER, "Manager", "Менеджер"),
  INFORMATION_REGISTER_RECORD_MANAGER(MDOType.INFORMATION_REGISTER, "RecordManager", "МенеджерЗаписи"),
  INFORMATION_REGISTER_RECORD_SET(MDOType.INFORMATION_REGISTER, "RecordSet", "НаборЗаписей"),
  RECALCULATION_RECORD_SET(MDOType.RECALCULATION, "RecordSet", "НаборЗаписей"),
  REPORT_MANAGER(MDOType.REPORT, "Manager", "Менеджер"),
  REPORT_OBJECT(MDOType.REPORT, "Object", "Объект"),
  SEQUENCE_RECORD_SET(MDOType.SEQUENCE, "RecordSet", "НаборЗаписей"),
  TASK_MANAGER(MDOType.TASK, "Manager", "Менеджер"),
  TASK_OBJECT(MDOType.TASK, "Object", "Объект"),
  TASK_REF(MDOType.TASK, "Ref", "Ссылка");

  private static final Map<String, MDOValueType> KEYS = EnumWithName.computeKeys(values());

  @Getter
  @Accessors(fluent = true)
  private final MultiName fullName;

  /**
   * Вид метаданных, к которому относится тип значения
   */
  @Getter
  @Accessors(fluent = true)
  private final MDOType kind;

  MDOValueType(String nameEn, String nameRu) {
    this(MDOType.CONFIGURATION, nameEn, nameRu, true);
  }

  MDOValueType(MDOType kind, String partNameEn, String partNameRu) {
    this(kind, kind.nameEn() + partNameEn, kind.nameRu() + partNameRu, true);
  }

  MDOValueType(MDOType kind, String nameEn, String nameRu, boolean unused) {
    this.kind = kind;
    this.fullName = MultiName.create(nameEn, nameRu);
  }

  /**
   * Ищет элемент перечисления по именам (рус, анг)
   *
   * @param name Имя искомого элемента
   * @return Найденное значение, если не найден - то null
   */
  @Nullable
  public static MDOValueType valueByName(String name) {
    return KEYS.get(name.toLowerCase(Locale.ROOT));
  }

  @Override
  public ValueTypeVariant variant() {
    return ValueTypeVariant.METADATA;
  }
}
