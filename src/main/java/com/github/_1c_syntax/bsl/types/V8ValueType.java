package com.github._1c_syntax.bsl.types;

import lombok.Getter;

import java.util.List;

/**
 * Типы данных платформы 8
 */
public class V8ValueType implements ValueType {
  public static final V8ValueType VALUE_STORAGE = new V8ValueType("ValueStorage", "ХранилищеЗначений");
  public static final V8ValueType UUID = new V8ValueType("UUID", "УникальныйИдентификатор");
  public static final V8ValueType ANY_REF = new V8ValueType("AnyRef", "ЛюбаяСсылка", ValueTypeVariant.METADATA);
  public static final V8ValueType FIXED_STRUCTURE = new V8ValueType("FixedStructure", "ФиксированнаяСтруктура");
  public static final V8ValueType FIXED_ARRAY = new V8ValueType("FixedArray", "ФиксированныйМассив");
  public static final V8ValueType FIXED_MAP = new V8ValueType("FixedMap", "ФиксированноеСоответствие");

  private static final List<ValueType> BUILTIN_TYPES = List.of(
    VALUE_STORAGE, UUID, ANY_REF,
    FIXED_STRUCTURE, FIXED_ARRAY, FIXED_MAP
  );

  @Getter
  private final String name;
  @Getter
  private final String nameRu;
  @Getter
  private final ValueTypeVariant variant;

  private V8ValueType(String name, String nameRu) {
    this(name, nameRu, ValueTypeVariant.V8);
  }

  private V8ValueType(String name, String nameRu, ValueTypeVariant variant) {
    this.name = name;
    this.nameRu = nameRu;
    this.variant = variant;
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
