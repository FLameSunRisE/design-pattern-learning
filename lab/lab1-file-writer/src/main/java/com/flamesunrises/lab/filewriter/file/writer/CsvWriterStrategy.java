package com.flamesunrises.lab.filewriter.file.writer;

import com.flamesunrises.lab.filewriter.file.IFileWriterStrategy;
import com.flamesunrises.lab.filewriter.file.csv.format.ICsvFormatStrategy;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CsvWriterStrategy.
 *
 * @author jayyeh
 * @version 1.0.0
 * @date 2023/06/05
 * @see IWriterStrategy
 */
public class CsvWriterStrategy<T> implements IWriterStrategy<T> {

  private static final Logger logger = LoggerFactory.getLogger("fridayBatch");

  private ICsvFormatStrategy<T> formatStrategy;
  private IFileWriterStrategy writerStrategy;

  public CsvWriterStrategy(ICsvFormatStrategy<T> formatStrategy,
      IFileWriterStrategy writerStrategy) {
    this.formatStrategy = formatStrategy;
    this.writerStrategy = writerStrategy;
  }

  @Override
  public void writeData(String filePath, List<T> dataList) {
    String csvContent = formatStrategy.formatDataList(dataList);
    logger.info("csvContent : {}", csvContent);
    writerStrategy.writeData(filePath, csvContent);
  }

  public String getCsvContentData(List<T> dataList) {
    String csvContent = formatStrategy.formatDataList(dataList);
    logger.info("csvContent : {}", csvContent);
    return csvContent;
  }
}