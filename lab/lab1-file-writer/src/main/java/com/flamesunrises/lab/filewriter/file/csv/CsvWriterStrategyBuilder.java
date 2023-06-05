package com.flamesunrises.lab.filewriter.file.csv;

import com.flamesunrises.lab.filewriter.file.csv.format.DefaultCsvFormatConvert;
import com.flamesunrises.lab.filewriter.file.csv.format.ICsvFormatStrategy;
import com.flamesunrises.lab.filewriter.file.writer.CsvWriterStrategy;
import com.flamesunrises.lab.filewriter.file.FileWriterStrategy;
import com.flamesunrises.lab.filewriter.file.IFileWriterStrategy;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * CsvWriterStrategyBuilder.
 *
 * @author jayyeh
 * @version 1.0.0
 * @date 2023/06/05
 */
public class CsvWriterStrategyBuilder<T> {

  private Class<T> dataClass;
  private boolean includeHeader;
  private List<String> headerFields;
  private List<String> columnFields;
  private ICsvFormatStrategy<T> formatStrategy;


  public CsvWriterStrategyBuilder(Class<T> dataClass) {
    this.dataClass = dataClass;
    this.includeHeader = true;
    this.headerFields = null;
    this.columnFields = null;
    this.formatStrategy = null;
  }

  public CsvWriterStrategyBuilder<T> withIncludeHeader(boolean includeHeader) {
    this.includeHeader = includeHeader;
    return this;
  }

  public CsvWriterStrategyBuilder<T> withHeaderFields(List<String> headerFields) {
    if ((headerFields == null || headerFields.isEmpty()) && includeHeader) {
      List<String> objectFields = getObjectFields(dataClass);
      this.headerFields = objectFields;
    } else {
      this.headerFields = headerFields;
    }
    return this;
  }

  public CsvWriterStrategyBuilder<T> withColumnFields(List<String> columnFields) {
    if ((columnFields == null || columnFields.isEmpty())) {
      List<String> objectFields = getObjectFields(dataClass);
      this.columnFields = objectFields;
    } else {
      this.columnFields = columnFields;
    }
    return this;
  }

  public CsvWriterStrategyBuilder<T> withFormatStrategy(ICsvFormatStrategy formatStrategy) {
    if (formatStrategy == null) {
      this.formatStrategy = new DefaultCsvFormatConvert<T>(dataClass, includeHeader, headerFields,
          columnFields);
    } else {
      this.formatStrategy = formatStrategy;
    }
    return this;
  }

  public CsvWriterStrategy<T> build() {
    IFileWriterStrategy writerStrategy = createWriterStrategy();
    return new CsvWriterStrategy<>(formatStrategy, writerStrategy);
  }

  private IFileWriterStrategy createWriterStrategy() {
    return new FileWriterStrategy();
  }

  private List<String> getObjectFields(Class<?> clazz) {
    List<String> fieldList = new ArrayList<>();
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      fieldList.add(field.getName());
    }
    System.out.println("fieldList = " + fieldList.toString());
    return fieldList;
  }
}