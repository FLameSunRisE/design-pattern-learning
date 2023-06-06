package com.flamesunrises.lab.filewriter.file.format.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DefaultJsonFormatConverter.
 *
 * @author jayyeh
 * @version 1.0.0
 * @date 2023/06/06
 * @see AbstractJsonFormatStrategy
 */
public class DefaultJsonFormatConverter<T> extends AbstractJsonFormatStrategy<T> {

  private static final Logger logger = LoggerFactory.getLogger("fridayBatch");

  public DefaultJsonFormatConverter(Class<T> dataClass) {
    super(dataClass);
  }

  public DefaultJsonFormatConverter(Class<T> dataClass, JsonSerializer dateSerializer) {
    super(dataClass, dateSerializer);
  }

  public String formatData(T data) {
    try {
      return objectMapper.writeValueAsString(data);
    } catch (JsonProcessingException e) {
      logger.error("JsonProcess fail" + e.getMessage(), e);
      return "";
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return "";
    }
  }
}
