package com.heavenhr.recruitment.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>The Class ObjectMapperUtil</p>
 * This is object mapper utility class.
 * Used to convert objects
 *
 * @author madhankumar
 *
 */
public final class ObjectMapperUtil {

  /**
   * Logger
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(ObjectMapperUtil.class);

  /**
   * Object Mapper
   */
  private static ObjectMapper mapper = new ObjectMapper();

  /**
   * The Default constructor
   */
  private ObjectMapperUtil() {
    // Default constructor
  }

  /**
   * This is utility singleton class and hence to access the global instance
   * introduced the getInstance.
   *
   * @return the mapper Instance
   */
  public static ObjectMapper getInstance() {
    mapper.findAndRegisterModules();
    return mapper;
  }

  /**
   * Model conversion
   * @param json String json input which needs to be converted to object
   * @param clazz input class
   * @param <T> Request object
   * @return Response object
   * @throws IOException exception occurs when model conversion failed
   */
  public static <T> T convertToObject(String json, Class<T> clazz) throws IOException {
    mapper.findAndRegisterModules();
    return mapper.readValue(json, clazz);
  }

  /**
   * Model conversion
   * @param json String json input which needs to be converted to object
   * @param clazz input class
   * @param <T> Request object
   * @return Response object
   */
  public static <T> T convertToObjectWithOutException(String json, Class<T> clazz) {
    try {
      mapper.findAndRegisterModules();
      return mapper.readValue(json, clazz);
    } catch (IOException e) {
      LOGGER.debug("Exception occurred during model without exception conversion ", e);
    }
    return null;
  }

  /**
   * List of object conversion
   * @param json String json input which needs to be converted to object
   * @param clazz input class
   * @param <T> Request object
   * @return Response object
   */
  public static <T> List<T> convertToListOfObjectWithOutException(String json, Class<T> clazz) {
    try {
      mapper.findAndRegisterModules();
      return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    } catch (IOException e) {
      LOGGER.debug("Exception occurred during list of model conversion ", e);
    }
    return new ArrayList<>();
  }

  /**
   * Convert object to string.
   *
   * @param <T> the generic type
   * @param object the object
   * @return the string
   */
  public static <T> String convertObjectToString(T object) {
    try {
      mapper.findAndRegisterModules();
      return mapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      LOGGER.debug("Exception occurred during model conversion ", e);
    }
    return null;
  }

  /**
   * This method is used to get object model mapper for the dynamic object.
   * @param clazz Class for which the model mapper needs to retrieved
   * @param <T> the generic type- dynamic object
   * @return the generic type- dynamic object
   */
  public static <T> T getModelMapper(Class<T> clazz) {
    return Mappers.getMapper(clazz);
  }

}
