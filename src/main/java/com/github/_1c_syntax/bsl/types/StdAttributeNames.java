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

import java.util.Locale;
import java.util.Map;

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

  private static final Map<String, MultiName> KEYS = computeKeys();

  /**
   * Возвращает мультиимя стандартного реквизита по английскому имени
   *
   * @param name Английское имя
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
    return Map.ofEntries(Map.entry(PREDEFINED_DATA_NAME.getEn().toLowerCase(Locale.ROOT), PREDEFINED_DATA_NAME),
      Map.entry(PREDEFINED.getEn().toLowerCase(Locale.ROOT), PREDEFINED),
      Map.entry(REF.getEn().toLowerCase(Locale.ROOT), REF),
      Map.entry(DELETION_MARK.getEn().toLowerCase(Locale.ROOT), DELETION_MARK),
      Map.entry(IS_FOLDER.getEn().toLowerCase(Locale.ROOT), IS_FOLDER),
      Map.entry(PARENT.getEn().toLowerCase(Locale.ROOT), PARENT),
      Map.entry(DESCRIPTION.getEn().toLowerCase(Locale.ROOT), DESCRIPTION),
      Map.entry(CODE.getEn().toLowerCase(Locale.ROOT), CODE),
      Map.entry(OWNER.getEn().toLowerCase(Locale.ROOT), OWNER),
      Map.entry(LINE_NUMBER.getEn().toLowerCase(Locale.ROOT), LINE_NUMBER),
      Map.entry(ACTIVE.getEn().toLowerCase(Locale.ROOT), ACTIVE),
      Map.entry(ORDER.getEn().toLowerCase(Locale.ROOT), ORDER),
      Map.entry(PERIOD.getEn().toLowerCase(Locale.ROOT), PERIOD),
      Map.entry(THIS_NODE.getEn().toLowerCase(Locale.ROOT), THIS_NODE),
      Map.entry(RECEIVED_NO.getEn().toLowerCase(Locale.ROOT), RECEIVED_NO),
      Map.entry(SENT_NO.getEn().toLowerCase(Locale.ROOT), SENT_NO),
      Map.entry(NUMBER.getEn().toLowerCase(Locale.ROOT), NUMBER),
      Map.entry(END_OF_BASE_PERIOD.getEn().toLowerCase(Locale.ROOT), END_OF_BASE_PERIOD),
      Map.entry(BEG_OF_BASE_PERIOD.getEn().toLowerCase(Locale.ROOT), BEG_OF_BASE_PERIOD),
      Map.entry(END_OF_ACTION_PERIOD.getEn().toLowerCase(Locale.ROOT), END_OF_ACTION_PERIOD),
      Map.entry(BEG_OF_ACTION_PERIOD.getEn().toLowerCase(Locale.ROOT), BEG_OF_ACTION_PERIOD),
      Map.entry(ACTION_PERIOD.getEn().toLowerCase(Locale.ROOT), ACTION_PERIOD),
      Map.entry(POSTED.getEn().toLowerCase(Locale.ROOT), POSTED),
      Map.entry(DATE.getEn().toLowerCase(Locale.ROOT), DATE),
      Map.entry(ACTION_PERIOD_IS_BASIC.getEn().toLowerCase(Locale.ROOT), ACTION_PERIOD_IS_BASIC),
      Map.entry(STARTED.getEn().toLowerCase(Locale.ROOT), STARTED),
      Map.entry(COMPLETED.getEn().toLowerCase(Locale.ROOT), COMPLETED),
      Map.entry(EXECUTED.getEn().toLowerCase(Locale.ROOT), EXECUTED),
      Map.entry(OFF_BALANCE.getEn().toLowerCase(Locale.ROOT), OFF_BALANCE),
      Map.entry(REGISTRATION_PERIOD.getEn().toLowerCase(Locale.ROOT), REGISTRATION_PERIOD),
      Map.entry(RECORD_TYPE.getEn().toLowerCase(Locale.ROOT), RECORD_TYPE),
      Map.entry(RECORDER.getEn().toLowerCase(Locale.ROOT), RECORDER),
      Map.entry(ACCOUNT.getEn().toLowerCase(Locale.ROOT), ACCOUNT),
      Map.entry(VALUE_TYPE.getEn().toLowerCase(Locale.ROOT), VALUE_TYPE),
      Map.entry(REVERSING_ENTRY.getEn().toLowerCase(Locale.ROOT), REVERSING_ENTRY),
      Map.entry(HEAD_TASK.getEn().toLowerCase(Locale.ROOT), HEAD_TASK),
      Map.entry(ROUTE_POINT.getEn().toLowerCase(Locale.ROOT), ROUTE_POINT),
      Map.entry(BUSINESS_PROCESS.getEn().toLowerCase(Locale.ROOT), BUSINESS_PROCESS),
      Map.entry(TYPE.getEn().toLowerCase(Locale.ROOT), TYPE),
      Map.entry(CALCULATION_TYPE.getEn().toLowerCase(Locale.ROOT), CALCULATION_TYPE),
      Map.entry(EXCHANGE_DATE.getEn().toLowerCase(Locale.ROOT), EXCHANGE_DATE),
      Map.entry(PERIOD_ADJUSTMENT.getEn().toLowerCase(Locale.ROOT), PERIOD_ADJUSTMENT));
  }
}
