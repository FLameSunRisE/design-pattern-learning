package com.flamesunrises.lab.filewriter.file;

import com.flamesunrises.lab.filewriter.file.format.IFormatStrategy;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * FileWriterStrategy.
 *
 * @author jayyeh
 * @version 1.0.0
 * @date 2023/06/05
 * @see IFileWriterStrategy
 */
public class FileWriterStrategy<T> implements IFileWriterStrategy<T> {
  @Override
  public void writeDataToFile(String filePath, List<T> dataList, IFormatStrategy<T> formatStrategy) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
      String formattedData = formatStrategy.formatDataList(dataList);
      writer.write(formattedData);
    } catch (IOException e) {
      throw new RuntimeException("Failed to write data to file", e);
    }

  }
}
