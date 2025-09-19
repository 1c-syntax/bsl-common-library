package com.github._1c_syntax.bsl.types;

import lombok.Getter;
import lombok.NonNull;

/**
 * Для записи неизвестного типа данных
 */
public class UnknownValueType implements ValueType {
  @Getter
  private final String name;

  public UnknownValueType(@NonNull String name) {
    this.name = name;
  }

  @Override
  public String getNameRu() {
    return getName();
  }

  @Override
  public ValueTypeVariant getVariant() {
    return ValueTypeVariant.UNKNOWN;
  }
}
