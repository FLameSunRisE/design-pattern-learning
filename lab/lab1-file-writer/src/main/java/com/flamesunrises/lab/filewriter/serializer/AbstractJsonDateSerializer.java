package com.flamesunrises.lab.filewriter.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractJsonDateSerializer extends JsonSerializer<Date> implements
    IDateSerializer {

  private SimpleDateFormat formatter;
  private final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd";

  public AbstractJsonDateSerializer() {
    this.formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
  }

  public AbstractJsonDateSerializer(String pattern) {
    if (pattern == null || pattern.isEmpty()) {
      this.formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    }
    try {
      this.formatter = new SimpleDateFormat(this.validateDatePattern(pattern));
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid date format", e);
    }
  }

  @Override
  public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider provider)
      throws IOException {
    if (date != null) {
      String formattedDate = this.formatter.format(date);
      jsonGenerator.writeString(formattedDate);
    } else {
      jsonGenerator.writeNull();
    }
  }

  protected String validateDatePattern(String datePattern) {
    if (datePattern == null || datePattern.isEmpty()) {
      return DEFAULT_DATE_FORMAT;
    }
    // 檢查日期格式是否有效，這裡僅示範一種檢查方式
    try {
      new SimpleDateFormat(datePattern);
      return datePattern;
    } catch (IllegalArgumentException e) {
      // 處理無效日期格式的情況，例如使用預設日期格式或拋出異常
      return DEFAULT_DATE_FORMAT;
    }
  }
}