package com.flamesunrises.lab.filewriter.serializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DateSerializer.
 *
 * @author jayyeh
 * @version 1.0.0
 * @date 2023/05/29
 * @see IDateSerializer
 */
public class CustomJsonDateSerializer extends AbstractJsonDateSerializer {

  private static final Logger logger = LoggerFactory.getLogger("fridayBatch");

  public CustomJsonDateSerializer() {
    super();
  }

  public CustomJsonDateSerializer(String dateFormat) {
    super(dateFormat);
  }
}
