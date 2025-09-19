package com.github._1c_syntax.bsl.types;

import lombok.Getter;

import java.util.List;

/**
 * Примитивные типы данных
 */
public class PrimitiveValueType implements ValueType {
  public static final PrimitiveValueType STRING = new PrimitiveValueType("String", "Строка");
  public static final PrimitiveValueType DATE = new PrimitiveValueType("Date", "Дата");
  public static final PrimitiveValueType NUMBER = new PrimitiveValueType("Number", "Число");
  public static final PrimitiveValueType BOOLEAN = new PrimitiveValueType("Boolean", "Булево");
  public static final PrimitiveValueType NULL = new PrimitiveValueType("Null", "Null");

  private static final List<ValueType> BUILTIN_TYPES = List.of(STRING, DATE, NUMBER, BOOLEAN, NULL);

  @Getter
  private final String name;
  @Getter
  private final String nameRu;

  private PrimitiveValueType(String name, String nameRu) {
    this.name = name;
    this.nameRu = nameRu;
  }

  @Override
  public ValueTypeVariant getVariant() {
    return ValueTypeVariant.PRIMITIVE;
  }

  /**
   * Коллекция встроенных типов
   *
   * @return Список встроенных типов
   */
  public static List<ValueType> builtinTypes() {
    return BUILTIN_TYPES;
  }
}
