package com.flamesunrises.lab.filewriter.file.csv.format;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DefaultCsvFormatConvert.
 *
 * @author jayyeh
 * @version 1.0.0
 * @date 2023/06/05
 * @see AbstractCsvFormatStrategy
 */
public class DefaultCsvFormatConvert<T> extends AbstractCsvFormatStrategy<T> {

  private static final Logger logger = LoggerFactory.getLogger("fridayBatch");

  public DefaultCsvFormatConvert(Class<T> dataClass) {
    super(dataClass);
    super.setIncludeHeader(true);
    this.setHeaderFields(getObjectFields(dataClass));
    this.setColumnFields(getObjectFields(dataClass));
  }

  public DefaultCsvFormatConvert(Class<T> dataClass, boolean includeHeader,
      List<String> headerFields, List<String> columnFields) {
    super(dataClass);
    this.setIncludeHeader(includeHeader);
    this.setHeaderFields(headerFields);
    this.setColumnFields(columnFields);
  }

  @Override
  public String formatHeader(List<String> headerFields) {
    return String.join(",", headerFields);
  }

  @Override
  public String formatColumnFields(List<String> columnFields) {
    return String.join(",", columnFields);
  }
}