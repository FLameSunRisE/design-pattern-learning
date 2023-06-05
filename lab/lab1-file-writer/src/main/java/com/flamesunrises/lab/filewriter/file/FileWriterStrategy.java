package com.flamesunrises.lab.filewriter.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * FileWriterStrategy.
 *
 * @author jayyeh
 * @version 1.0.0
 * @date 2023/06/05
 * @see IFileWriterStrategy
 */
public class FileWriterStrategy implements IFileWriterStrategy {
  @Override
  public void writeData(String filePath, String content) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
      writer.write(content);
    } catch (IOException e) {
      throw new RuntimeException("Failed to write data to file", e);
    }
  }
}
