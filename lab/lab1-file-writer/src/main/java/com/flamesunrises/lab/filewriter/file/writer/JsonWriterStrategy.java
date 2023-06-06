package com.flamesunrises.lab.filewriter.file.writer;

import com.flamesunrises.lab.filewriter.file.IFileWriterStrategy;
import com.flamesunrises.lab.filewriter.file.format.json.IJsonFormatStrategy;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JsonWriterStrategy.
 *
 * @author jayyeh
 * @version 1.0.0
 * @date 2023/06/06
 * @see IWriterStrategy
 */
public class JsonWriterStrategy<T> implements IWriterStrategy<T> {

  private static final Logger logger = LoggerFactory.getLogger(JsonWriterStrategy.class);

  private IJsonFormatStrategy<T> formatStrategy;
  private IFileWriterStrategy writerStrategy;

  public JsonWriterStrategy(IJsonFormatStrategy<T> formatStrategy,
      IFileWriterStrategy writerStrategy) {
    this.formatStrategy = formatStrategy;
    this.writerStrategy = writerStrategy;
  }

  @Override
  public void writeData(String filePath, List<T> dataList) {
    writerStrategy.writeDataToFile(filePath, dataList, formatStrategy);
  }

  public String getContentData(List<T> dataList) {
    String csvContent = formatStrategy.formatDataList(dataList);
    logger.info("getContentData : {}", csvContent);
    return csvContent;
  }
}
