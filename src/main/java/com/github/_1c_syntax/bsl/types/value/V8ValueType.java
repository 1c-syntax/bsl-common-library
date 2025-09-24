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

import com.github._1c_syntax.bsl.types.MultiName;
import com.github._1c_syntax.bsl.types.ValueType;
import com.github._1c_syntax.bsl.types.ValueTypeVariant;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.apache.commons.collections4.list.UnmodifiableList;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Типы данных платформы 8
 */
public final class V8ValueType implements ValueType {
  public static final V8ValueType VALUE_STORAGE = new V8ValueType("ValueStorage", "ХранилищеЗначений");
  public static final V8ValueType UUID = new V8ValueType("UUID", "УникальныйИдентификатор");
  public static final V8ValueType FIXED_STRUCTURE = new V8ValueType("FixedStructure", "ФиксированнаяСтруктура");
  public static final V8ValueType FIXED_ARRAY = new V8ValueType("FixedArray", "ФиксированныйМассив");
  public static final V8ValueType FIXED_MAP = new V8ValueType("FixedMap", "ФиксированноеСоответствие");

  private static final List<ValueType> BUILTIN_TYPES = computeBuiltinTypes();
  private static final Map<String, V8ValueType> PROVIDED_TYPES = computeProvidedTypes();

  @Getter
  @Accessors(fluent = true)
  private final MultiName fullName;

  @Getter
  @Accessors(fluent = true)
  private final ValueTypeVariant variant;

  private V8ValueType(String nameEn, String nameRu) {
    this(nameEn, nameRu, ValueTypeVariant.V8);
  }

  private V8ValueType(String nameEn, String nameRu, ValueTypeVariant variant) {
    this.fullName = MultiName.create(nameEn, nameRu);
    this.variant = variant;
  }

  /**
   * Производит определение типа по переданной строке
   *
   * @param name Строковое представление типа
   * @return Определенный тип
   */
  @Nullable
  public static V8ValueType fromString(String name) {
    return PROVIDED_TYPES.get(name.toLowerCase(Locale.ROOT));
  }

  /**
   * Коллекция встроенных типов
   *
   * @return Список встроенных типов
   */
  public static List<ValueType> builtinTypes() {
    return BUILTIN_TYPES;
  }

  private static List<ValueType> computeBuiltinTypes() {
    List<ValueType> types = new ArrayList<>();
    types.add(VALUE_STORAGE);
    types.add(UUID);
    types.add(FIXED_STRUCTURE);
    types.add(FIXED_ARRAY);
    types.add(FIXED_MAP);

    types.add(new V8ValueType("ValueTable", "ТаблицаЗначений"));
    types.add(new V8ValueType("ValueTree", "ДеревоЗначений"));
    types.add(new V8ValueType("ValueList", "СписокЗначений"));
    types.add(new V8ValueType("AccountingRecordType", "ВидДвиженияБухгалтерии"));
    types.add(new V8ValueType("AccumulationRecordType", "ВидДвиженияНакопления"));

    types.add(new V8ValueType("FormattedDocument", "ФорматированныйДокумент"));
    types.add(new V8ValueType("SpreadsheetDocument", "ТабличныйДокумент"));
    types.add(new V8ValueType("TextDocument", "ТекстовыйДокумент"));
    types.add(new V8ValueType("GraphicalSchema", "ГрафическаяСхема"));
    types.add(new V8ValueType("Chart", "Диаграмма"));
    types.add(new V8ValueType("GanttChart", "ДиаграммаГанта"));
    types.add(new V8ValueType("GeographicalSchema", "ГеографическаяСхема"));
    types.add(new V8ValueType("PDFDocument", "PDFДокумент"));

    types.add(new V8ValueType("StandardPeriod", "СтандартныйПериод"));
    types.add(new V8ValueType("FormattedString", "ФорматированнаяСтрока"));
    types.add(new V8ValueType("TypeDescription", "ОписаниеТипа"));
    types.add(new V8ValueType("StandardBeginningDate", "СтандартнаяДатаНачала"));

    types.add(new V8ValueType("DynamicList", "ДинамическийСписок"));
    types.add(new V8ValueType("DataCompositionSettingsComposer", "КомпоновщикНастроекКомпоновкиДанных"));
    types.add(new V8ValueType("SettingsComposer", "НастройкиКомпоновщика"));
    types.add(new V8ValueType("ReportBuilder", "ПостроительОтчета"));
    types.add(new V8ValueType("DataAnalysisTimeIntervalUnitType", "ТипЕдиницыИнтервалаВремениАнализаДанных"));
    types.add(new V8ValueType("Filter", "Отбор"));
    types.add(new V8ValueType("Order", "Порядок"));
    types.add(new V8ValueType("Planner", "Планировщик"));
    types.add(new V8ValueType("ComparisonType", "ВидСравнения"));

    types.add(new V8ValueType("Color", "Цвет"));
    types.add(new V8ValueType("Font", "Шрифт"));
    types.add(new V8ValueType("VerticalAlign", "ВертикальноеПоложение"));
    types.add(new V8ValueType("Picture", "Картинка"));
    types.add(new V8ValueType("SizeChangeMode", "РежимИзмененияРазмера"));

    return UnmodifiableList.unmodifiableList(types);
  }

  private static Map<String, V8ValueType> computeProvidedTypes() {
    Map<String, V8ValueType> types = new ConcurrentHashMap<>();
    builtinTypes().forEach((ValueType valueType) ->
      types.put(valueType.nameEn().toLowerCase(Locale.ROOT),
        (V8ValueType) valueType
      ));
    return types;
  }
}
