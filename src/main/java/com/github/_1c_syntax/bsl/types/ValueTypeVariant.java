package com.github._1c_syntax.bsl.types;

/**
 * Возможные варианты типов значений
 */
public enum ValueTypeVariant {
  PRIMITIVE, // Примитивный тип данных
  V8, // Типы данных платформы
  METADATA, // Тип данных, основанный на модели метаданных
  FORM, // Типы данных реквизитов форм
  UNKNOWN;  // что-то неизвестное
}
