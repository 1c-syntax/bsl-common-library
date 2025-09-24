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

import com.github._1c_syntax.bsl.types.MDOType;
import com.github._1c_syntax.bsl.types.MultiName;
import com.github._1c_syntax.bsl.types.ValueType;
import com.github._1c_syntax.bsl.types.ValueTypeVariant;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.apache.commons.collections4.list.UnmodifiableList;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Типы данных, построенные на метаданных конфигурации 1С Предприятие
 */
public final class MetadataValueType implements ValueType {
  public static final MetadataValueType ANY_REF = new MetadataValueType(MDOType.CONFIGURATION, "AnyRef", "ЛюбаяСсылка", true);

  private static final List<ValueType> BUILTIN_TYPES = computeBuiltinTypes();
  private static final Map<String, Variant> ALL_VARIANTS = computeAllVariants();
  private static final Map<String, MetadataValueType> PROVIDED_TYPES = computeProvidedTypes();

  @Getter
  @Accessors(fluent = true)
  private final MultiName fullName;

  /**
   * Признак составного типа (например DocumentRef)
   */
  @Getter
  private final boolean composite;

  /**
   * Вид метаданных, к которому относится тип значения
   */
  @Getter
  private final MDOType kind;

  private MetadataValueType(MDOType kind, String nameEn, String nameRu, boolean composite) {
    this.kind = kind;
    this.fullName = MultiName.create(nameEn, nameRu);
    this.composite = composite;
  }

  /**
   * Производит определение типа по переданной строке
   *
   * @param name Строковое представление типа
   * @return Определенный тип
   */
  @Nullable
  public static MetadataValueType fromString(String name) {
    var type = PROVIDED_TYPES.get(name.toLowerCase(Locale.ROOT));
    if (type != null) {
      return type;
    }

    var posDot = name.indexOf(".");
    if (posDot > 0) {
      var key = name.substring(0, posDot);
      var variant = ALL_VARIANTS.get(key.toLowerCase(Locale.ROOT));
      if (variant != null) {
        type = new MetadataValueType(variant.kind(), name, variant.nameRu() + name.substring(posDot), false);
        PROVIDED_TYPES.put(name.toLowerCase(Locale.ROOT), type);
      }
    }
    return type;
  }

  @Override
  public ValueTypeVariant variant() {
    return ValueTypeVariant.METADATA;
  }

  /**
   * Коллекция встроенных типов
   *
   * @return Список встроенных типов
   */
  public static List<? extends ValueType> builtinTypes() {
    return BUILTIN_TYPES;
  }

  private record Variant(String nameRu, MDOType kind) {
  }

  private static List<ValueType> computeBuiltinTypes() {
    List<ValueType> types = new ArrayList<>();
    types.add(ANY_REF);
    registers().forEach(mdoType -> {
      types.add(new MetadataValueType(mdoType,
        mdoType.getName() + "RecordManager",
        mdoType.getNameRu() + "МенеджерЗаписи",
        true));
      types.add(new MetadataValueType(mdoType,
        mdoType.getName() + "RecordSet",
        mdoType.getNameRu() + "НаборЗаписей",
        true));
    });

    references().forEach(mdoType -> {
      types.add(new MetadataValueType(mdoType,
        mdoType.getName() + "Manager",
        mdoType.getNameRu() + "Менеджер",
        true));
      types.add(new MetadataValueType(mdoType,
        mdoType.getName() + "Object",
        mdoType.getNameRu() + "Объект",
        true));
      types.add(new MetadataValueType(mdoType,
        mdoType.getName() + "Ref",
        mdoType.getNameRu() + "Ссылка",
        true));
    });

    reportsDataProcs().forEach(mdoType -> {
      types.add(new MetadataValueType(mdoType,
        mdoType.getName() + "Manager",
        mdoType.getNameRu() + "Менеджер",
        true));
      types.add(new MetadataValueType(mdoType,
        mdoType.getName() + "Object",
        mdoType.getNameRu() + "Объект",
        true));
    });

    types.add(new MetadataValueType(MDOType.DOCUMENT_JOURNAL,
      MDOType.DOCUMENT_JOURNAL.getName() + "Manager",
      MDOType.DOCUMENT_JOURNAL.getNameRu() + "Менеджер",
      true));

    types.add(new MetadataValueType(MDOType.ENUM,
      MDOType.ENUM.getName() + "Ref",
      MDOType.ENUM.getNameRu() + "Ссылка",
      true));

    types.add(new MetadataValueType(MDOType.EXTERNAL_DATA_PROCESSOR,
      MDOType.EXTERNAL_DATA_PROCESSOR.getName() + "Object",
      MDOType.EXTERNAL_DATA_PROCESSOR.getNameRu() + "Объект",
      true));

    types.add(new MetadataValueType(MDOType.EXTERNAL_REPORT,
      MDOType.EXTERNAL_REPORT.getName() + "Object",
      MDOType.EXTERNAL_REPORT.getNameRu() + "Объект",
      true));

    types.add(new MetadataValueType(MDOType.RECALCULATION,
      MDOType.RECALCULATION.getName() + "RecordManager",
      MDOType.RECALCULATION.getNameRu() + "МенеджерЗаписи",
      true));

    types.add(new MetadataValueType(MDOType.SEQUENCE,
      MDOType.SEQUENCE.getName() + "RecordManager",
      MDOType.SEQUENCE.getNameRu() + "МенеджерЗаписи",
      true));

    types.add(new MetadataValueType(MDOType.CONSTANT,
      MDOType.CONSTANT.getName() + "ValueManager",
      MDOType.CONSTANT.getNameRu() + "МенеджерЗначения",
      true));

    types.add(new MetadataValueType(MDOType.CONSTANT,
      MDOType.CONSTANT.getGroupName() + "Set",
      MDOType.CONSTANT.getGroupNameRu() + "Набор",
      true));

    types.add(new MetadataValueType(MDOType.BUSINESS_PROCESS,
      MDOType.BUSINESS_PROCESS.getName() + "RoutePointRef",
      "ТочкаМаршрутаБизнесПроцессаСсылка",
      true));

    return new UnmodifiableList<>(types);
  }

  private static List<MDOType> registers() {
    return List.of(
      MDOType.ACCOUNTING_REGISTER,
      MDOType.ACCUMULATION_REGISTER,
      MDOType.CALCULATION_REGISTER,
      MDOType.INFORMATION_REGISTER
    );
  }

  private static List<MDOType> references() {
    return List.of(
      MDOType.BUSINESS_PROCESS,
      MDOType.CATALOG,
      MDOType.CHART_OF_ACCOUNTS,
      MDOType.CHART_OF_CALCULATION_TYPES,
      MDOType.CHART_OF_CHARACTERISTIC_TYPES,
      MDOType.DOCUMENT,
      MDOType.EXCHANGE_PLAN,
      MDOType.TASK
    );
  }

  private static List<MDOType> reportsDataProcs() {
    return List.of(
      MDOType.DATA_PROCESSOR,
      MDOType.REPORT
    );
  }

  private static Map<String, Variant> computeAllVariants() {
    Map<String, Variant> variants = new ConcurrentHashMap<>();
    builtinTypes().forEach((ValueType valueType) ->
      variants.put(valueType.nameEn().toLowerCase(Locale.ROOT),
        new Variant(valueType.nameRu(), ((MetadataValueType) valueType).getKind()
        ))
    );

    variants.put(MDOType.DEFINED_TYPE.getName().toLowerCase(Locale.ROOT),
      new Variant(MDOType.DEFINED_TYPE.getNameRu(), MDOType.DEFINED_TYPE));
    variants.put("Characteristic".toLowerCase(Locale.ROOT),
      new Variant("Характеристика", MDOType.CHART_OF_CHARACTERISTIC_TYPES));

    addVariant(variants, MDOType.INFORMATION_REGISTER, "RecordManager", "МенеджерЗаписи");
    addVariant(variants, MDOType.INFORMATION_REGISTER, "List", "Список");
    addVariant(variants, MDOType.ENUM, "List", "Список");

    var mdoType = MDOType.EXTERNAL_DATA_SOURCE;
    addVariant(variants, mdoType, "TableRef", "ТаблицаСсылка");
    addVariant(variants, mdoType, "TableObject", "ТаблицаОбъект");
    addVariant(variants, mdoType, "TableRecordManager", "ТаблицаМенеджерЗаписи");

    return Collections.unmodifiableMap(variants);
  }

  private static Map<String, MetadataValueType> computeProvidedTypes() {
    Map<String, MetadataValueType> types = new ConcurrentHashMap<>();
    builtinTypes().forEach((ValueType valueType) ->
      types.put(valueType.nameEn().toLowerCase(Locale.ROOT),
        (MetadataValueType) valueType
      ));
    return types;
  }

  private static void addVariant(Map<String, Variant> variants, MDOType mdoType, String name, String nameRu) {
    variants.put((mdoType.getName() + name).toLowerCase(Locale.ROOT),
      new Variant(mdoType.getNameRu() + nameRu, mdoType));
  }
}
