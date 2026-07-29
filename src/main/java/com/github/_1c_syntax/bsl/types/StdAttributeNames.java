/*
 * This file is a part of BSL Common library.
 *
 * Copyright (c) 2021 - 2026
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

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Вспомогательный класс для хранения стандартных реквизитов
 */
@Slf4j
public class StdAttributeNames {
  public static final MultiName PREDEFINED_DATA_NAME = MultiName.create("PredefinedDataName",
    "ИмяПредопределенныхДанных");
  public static final MultiName PREDEFINED = MultiName.create("Predefined", "Предопределенный");
  public static final MultiName REF = MultiName.create("Ref", "Ссылка");
  public static final MultiName DELETION_MARK = MultiName.create("DeletionMark", "ПометкаУдаления");
  public static final MultiName IS_FOLDER = MultiName.create("IsFolder", "ЭтоГруппа");
  public static final MultiName PARENT = MultiName.create("Parent", "Родитель");
  public static final MultiName DESCRIPTION = MultiName.create("Description", "Наименование");
  public static final MultiName CODE = MultiName.create("Code", "Код");
  public static final MultiName OWNER = MultiName.create("Owner", "Владелец");
  public static final MultiName LINE_NUMBER = MultiName.create("LineNumber", "НомерСтроки");
  public static final MultiName ACTIVE = MultiName.create("Active", "Активность");
  public static final MultiName ORDER = MultiName.create("Order", "Порядок");
  public static final MultiName PERIOD = MultiName.create("Period", "Период");
  public static final MultiName THIS_NODE = MultiName.create("ThisNode", "ЭтотУзел");
  public static final MultiName RECEIVED_NO = MultiName.create("ReceivedNo", "НомерПринятого");
  public static final MultiName SENT_NO = MultiName.create("SentNo", "НомерОтправленного");
  public static final MultiName NUMBER = MultiName.create("Number", "Номер");
  public static final MultiName END_OF_BASE_PERIOD = MultiName.create("EndOfBasePeriod", "БазовыйПериодКонец");
  public static final MultiName BEG_OF_BASE_PERIOD = MultiName.create("BegOfBasePeriod", "БазовыйПериодНачало");
  public static final MultiName END_OF_ACTION_PERIOD = MultiName.create("EndOfActionPeriod", "ПериодДействияКонец");
  public static final MultiName BEG_OF_ACTION_PERIOD = MultiName.create("BegOfActionPeriod", "ПериодДействияНачало");
  public static final MultiName ACTION_PERIOD = MultiName.create("ActionPeriod", "ПериодДействия");

  public static final MultiName POSTED = MultiName.create("Posted", "Проведен");
  public static final MultiName DATE = MultiName.create("Date", "Дата");
  public static final MultiName ACTION_PERIOD_IS_BASIC = MultiName.create("ActionPeriodIsBasic", "ПериодДействияБазовый");
  public static final MultiName STARTED = MultiName.create("Started", "Стартован");
  public static final MultiName COMPLETED = MultiName.create("Completed", "Завершен");
  public static final MultiName EXECUTED = MultiName.create("Executed", "Выполнена");
  public static final MultiName OFF_BALANCE = MultiName.create("OffBalance", "Забалансовый");
  public static final MultiName REGISTRATION_PERIOD = MultiName.create("RegistrationPeriod", "ПериодРегистрации");

  public static final MultiName RECORD_TYPE = MultiName.create("RecordType", "ВидДвижения");
  public static final MultiName RECORDER = MultiName.create("Recorder", "Регистратор");
  public static final MultiName ACCOUNT = MultiName.create("Account", "Счет");
  public static final MultiName VALUE_TYPE = MultiName.create("ValueType", "ТипЗначения");
  public static final MultiName REVERSING_ENTRY = MultiName.create("ReversingEntry", "Сторно");
  public static final MultiName HEAD_TASK = MultiName.create("HeadTask", "ВедущаяЗадача");
  public static final MultiName ROUTE_POINT = MultiName.create("RoutePoint", "ТочкаМаршрута");
  public static final MultiName BUSINESS_PROCESS = MultiName.create("BusinessProcess", "БизнесПроцесс");
  public static final MultiName TYPE = MultiName.create("Type", "Тип");
  public static final MultiName CALCULATION_TYPE = MultiName.create("CalculationType", "ВидРасчета");
  public static final MultiName EXCHANGE_DATE = MultiName.create("ExchangeDate", "ДатаОбмена");
  public static final MultiName PERIOD_ADJUSTMENT = MultiName.create("PeriodAdjustment", "УточнениеПериода");

  public static final MultiName TURNOVERS_ONLY = MultiName.create("TurnoversOnly", "ТолькоОбороты");
  public static final MultiName EXT_DIMENSION_TYPE = MultiName.create("ExtDimensionType", "ВидСубконто");
  public static final MultiName EXT_DIMENSION_1 = MultiName.create("ExtDimension1", "Субконто1");
  public static final MultiName EXT_DIMENSION_TYPE_1 = MultiName.create("ExtDimensionType1", "ВидСубконто1");
  public static final MultiName EXT_DIMENSION_2 = MultiName.create("ExtDimension2", "Субконто2");
  public static final MultiName EXT_DIMENSION_TYPE_2 = MultiName.create("ExtDimensionType2", "ВидСубконто2");
  public static final MultiName EXT_DIMENSION_3 = MultiName.create("ExtDimension3", "Субконто3");
  public static final MultiName EXT_DIMENSION_TYPE_3 = MultiName.create("ExtDimensionType3", "ВидСубконто3");
  public static final MultiName EXT_DIMENSION_4 = MultiName.create("ExtDimension4", "Субконто4");
  public static final MultiName EXT_DIMENSION_TYPE_4 = MultiName.create("ExtDimensionType4", "ВидСубконто4");
  public static final MultiName EXT_DIMENSION_5 = MultiName.create("ExtDimension5", "Субконто5");
  public static final MultiName EXT_DIMENSION_TYPE_5 = MultiName.create("ExtDimensionType5", "ВидСубконто5");

  private static final Map<String, MultiName> KEYS = computeKeys();

  /**
   * Возвращает мультиимя стандартного реквизита по имени (русскому или английскому)
   *
   * @param name Имя (русское или английское)
   * @return Мультиимя
   */
  public static MultiName get(String name) {
    var result = KEYS.get(name.toLowerCase(Locale.ROOT));
    if (result == null) {
      LOGGER.info("Unknown std. attribute {}", name);
      return MultiName.EMPTY;
    }
    return result;
  }

  private static Map<String, MultiName> computeKeys() {
    return Collections.unmodifiableMap(Stream.of(
      PREDEFINED_DATA_NAME, PREDEFINED, REF, DELETION_MARK, IS_FOLDER, PARENT,
      DESCRIPTION, CODE, OWNER, LINE_NUMBER, ACTIVE, ORDER, PERIOD, THIS_NODE,
      RECEIVED_NO, SENT_NO, NUMBER, END_OF_BASE_PERIOD, BEG_OF_BASE_PERIOD,
      END_OF_ACTION_PERIOD, BEG_OF_ACTION_PERIOD, ACTION_PERIOD, POSTED, DATE,
      ACTION_PERIOD_IS_BASIC, STARTED, COMPLETED, EXECUTED, OFF_BALANCE,
      REGISTRATION_PERIOD, RECORD_TYPE, RECORDER, ACCOUNT, VALUE_TYPE,
      REVERSING_ENTRY, HEAD_TASK, ROUTE_POINT, BUSINESS_PROCESS, TYPE,
      CALCULATION_TYPE, EXCHANGE_DATE, PERIOD_ADJUSTMENT,
      TURNOVERS_ONLY, EXT_DIMENSION_TYPE,
      EXT_DIMENSION_1, EXT_DIMENSION_TYPE_1,
      EXT_DIMENSION_2, EXT_DIMENSION_TYPE_2,
      EXT_DIMENSION_3, EXT_DIMENSION_TYPE_3,
      EXT_DIMENSION_4, EXT_DIMENSION_TYPE_4,
      EXT_DIMENSION_5, EXT_DIMENSION_TYPE_5
    ).collect(
      HashMap::new,
      (map, mn) -> {
        map.put(mn.getEn().toLowerCase(Locale.ROOT), mn);
        map.put(mn.getRu().toLowerCase(Locale.ROOT), mn);
      },
      HashMap::putAll
    ));
  }
}
