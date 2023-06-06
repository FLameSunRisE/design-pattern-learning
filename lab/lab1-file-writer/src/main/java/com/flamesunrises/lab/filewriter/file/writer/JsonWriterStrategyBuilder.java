package com.flamesunrises.lab.filewriter.file.writer;

import com.flamesunrises.lab.filewriter.file.FileWriterStrategy;
import com.flamesunrises.lab.filewriter.file.IFileWriterStrategy;
import com.flamesunrises.lab.filewriter.file.format.json.IJsonFormatStrategy;
import com.flamesunrises.lab.filewriter.serializer.CustomJsonDateSerializer;

public class JsonWriterStrategyBuilder<T> {

  private Class<T> dataClass;
  private IJsonFormatStrategy<T> formatStrategy;
  private CustomJsonDateSerializer dateSerializer;
  private IFileWriterStrategy writerStrategy;

  public JsonWriterStrategyBuilder(Class<T> dataClass) {
    if (dataClass == null) {
      throw new IllegalArgumentException("Data class cannot be null");
    }
    this.dataClass = dataClass;
    this.formatStrategy = null;
    this.dateSerializer = new CustomJsonDateSerializer();
  }

  public JsonWriterStrategyBuilder<T> withFormatStrategy(IJsonFormatStrategy<T> formatStrategy) {
    if (formatStrategy != null) {
      this.formatStrategy = formatStrategy;
    }
    return this;
  }

  public JsonWriterStrategyBuilder<T> withDateFieldsFormat(String formatStr) {
    if (formatStr != null && !formatStr.isEmpty()) {
      CustomJsonDateSerializer serializer = new CustomJsonDateSerializer(formatStr);
      this.withDateSerializer(serializer);
    }
    return this;
  }

  public JsonWriterStrategyBuilder<T> withDateSerializer(CustomJsonDateSerializer dateSerializer) {
    if (dateSerializer == null) {
      this.dateSerializer = new CustomJsonDateSerializer();
    } else {
      this.dateSerializer = dateSerializer;
    }
    return this;
  }

  public JsonWriterStrategyBuilder<T> withWriterStrategy(IFileWriterStrategy writerStrategy) {
    if (writerStrategy == null) {
      throw new IllegalArgumentException("Writer strategy cannot be null");
    }
    this.writerStrategy = writerStrategy;
    return this;
  }

  public JsonWriterStrategy<T> build() {
    if (formatStrategy == null) {
      throw new IllegalArgumentException("Format strategy cannot be null");
    }
    if (writerStrategy == null) {
      writerStrategy = createWriterStrategy();
    }
    return new JsonWriterStrategy<T>(formatStrategy, writerStrategy);
  }

  private IFileWriterStrategy createWriterStrategy() {
    return new FileWriterStrategy();
  }
}
