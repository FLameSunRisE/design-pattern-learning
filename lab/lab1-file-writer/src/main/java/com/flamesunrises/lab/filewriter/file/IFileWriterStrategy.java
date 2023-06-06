package com.flamesunrises.lab.filewriter.file;

import com.flamesunrises.lab.filewriter.file.format.IFormatStrategy;
import java.util.List;

/**
 * IFileWriterStrategy.
 *
 * @author jayyeh
 * @version 1.0.0
 * @date 2023/06/06
 */
public interface IFileWriterStrategy<T> {
  void writeDataToFile(String filePath, List<T> dataList, IFormatStrategy<T> formatStrategy);
}