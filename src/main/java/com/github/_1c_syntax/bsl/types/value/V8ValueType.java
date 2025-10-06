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
 * Типы данных платформы 8
 */
@ToString(of = "fullName")
public enum V8ValueType implements ValueType {
  ACCOUNTING_RECORD_TYPE("AccountingRecordType", "ВидДвиженияБухгалтерии"),
  ACCUMULATION_RECORD_TYPE("AccumulationRecordType", "ВидДвиженияНакопления"),
  CHART("Chart", "Диаграмма"),
  COLOR("Color", "Цвет"),
  COMPARISON_TYPE("ComparisonType", "ВидСравнения"),
  DATA_ANALYSIS_TIME_INTERVAL_UNIT_TYPE("DataAnalysisTimeIntervalUnitType",
    "ТипЕдиницыИнтервалаВремениАнализаДанных"),
  DATA_COMPOSITION_SETTINGS_COMPOSER("DataCompositionSettingsComposer",
    "КомпоновщикНастроекКомпоновкиДанных"),
  DYNAMIC_LIST("DynamicList", "ДинамическийСписок"),
  FILTER("Filter", "Отбор"),
  FIXED_ARRAY("FixedArray", "ФиксированныйМассив"),
  FIXED_MAP("FixedMap", "ФиксированноеСоответствие"),
  FIXED_STRUCTURE("FixedStructure", "ФиксированнаяСтруктура"),
  FONT("Font", "Шрифт"),
  FORMATTED_DOCUMENT("FormattedDocument", "ФорматированныйДокумент"),
  FORMATTED_STRING("FormattedString", "ФорматированнаяСтрока"),
  GANTT_CHART("GanttChart", "ДиаграммаГанта"),
  GEOGRAPHICAL_SCHEMA("GeographicalSchema", "ГеографическаяСхема"),
  GRAPHICAL_SCHEMA("GraphicalSchema", "ГрафическаяСхема"),
  ORDER("Order", "Порядок"),
  PDF_DOCUMENT("PDFDocument", "PDFДокумент"),
  PICTURE("Picture", "Картинка"),
  PLANNER("Planner", "Планировщик"),
  REPORT_BUILDER("ReportBuilder", "ПостроительОтчета"),
  SETTINGS_COMPOSER("SettingsComposer", "НастройкиКомпоновщика"),
  SIZE_CHANGE_MODE("SizeChangeMode", "РежимИзмененияРазмера"),
  SPREADSHEET_DOCUMENT("SpreadsheetDocument", "ТабличныйДокумент"),
  STANDARD_BEGINNING_DATE("StandardBeginningDate", "СтандартнаяДатаНачала"),
  STANDARD_PERIOD("StandardPeriod", "СтандартныйПериод"),
  TEXT_DOCUMENT("TextDocument", "ТекстовыйДокумент"),
  TYPE_DESCRIPTION("TypeDescription", "ОписаниеТипа"),
  UUID("UUID", "УникальныйИдентификатор"),
  VALUE_LIST("ValueList", "СписокЗначений"),
  VALUE_STORAGE("ValueStorage", "ХранилищеЗначений"),
  VALUE_TABLE("ValueTable", "ТаблицаЗначений"),
  VALUE_TREE("ValueTree", "ДеревоЗначений"),
  VERTICAL_ALIGN("VerticalAlign", "ВертикальноеПоложение");

  private static final Map<String, V8ValueType> KEYS = EnumWithName.computeKeys(values());

  @Getter
  @Accessors(fluent = true)
  private final MultiName fullName;

  V8ValueType(String nameEn, String nameRu) {
    this.fullName = MultiName.create(nameEn, nameRu);
  }

  /**
   * Ищет элемент перечисления по именам (рус, анг)
   *
   * @param name Имя искомого элемента
   * @return Найденное значение, если не найден - то null
   */
  @Nullable
  public static V8ValueType valueByName(String name) {
    return KEYS.get(name.toLowerCase(Locale.ROOT));
  }

  @Override
  public ValueTypeVariant variant() {
    return ValueTypeVariant.V8;
  }
}
