/*
 * Copyright (c) Codice Foundation
 * <p/>
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details. A copy of the GNU Lesser General Public License
 * is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 */
package ddf.catalog.transformer.csv;

import static ddf.catalog.transformer.csv.common.CsvTransformer.createResponse;
import static ddf.catalog.transformer.csv.common.CsvTransformer.writeMetacardsToCsv;

import ddf.catalog.data.AttributeDescriptor;
import ddf.catalog.data.BinaryContent;
import ddf.catalog.data.Metacard;
import ddf.catalog.transform.CatalogTransformerException;
import ddf.catalog.transform.MetacardTransformer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvMetacardTransformer implements MetacardTransformer {

  private static final Logger LOGGER = LoggerFactory.getLogger(CsvMetacardTransformer.class);

  @Override
  public BinaryContent transform(Metacard metacard, Map<String, Serializable> arguments)
      throws CatalogTransformerException {

    if (metacard == null) {
      LOGGER.debug("Attempted to transform null metacard");
      throw new CatalogTransformerException("Unable to transform null metacard");
    }

    Map<String, String> aliases =
        (Map<String, String>)
            arguments.getOrDefault(CsvQueryResponseTransformer.COLUMN_ALIAS_KEY, new HashMap<>());
    List<String> attributes = getColumnOrder(arguments);

    List<AttributeDescriptor> allAttributes =
        new ArrayList<AttributeDescriptor>(metacard.getMetacardType().getAttributeDescriptors());
    List<AttributeDescriptor> descriptors =
        CollectionUtils.isEmpty(attributes)
            ? allAttributes
            : allAttributes.stream()
                .filter(attr -> attributes.contains(attr.getName()))
                .collect(Collectors.toList());
    Appendable appendable =
        writeMetacardsToCsv(Collections.singletonList(metacard), descriptors, aliases);
    return createResponse(appendable);
  }

  private List<String> getColumnOrder(Map<String, Serializable> arguments) {
    Object columnOrder = arguments.get(CsvQueryResponseTransformer.COLUMN_ORDER_KEY);
    if (columnOrder instanceof String && StringUtils.isNotBlank((String) columnOrder)) {
      return Arrays.asList(((String) columnOrder).split(","));
    }
    return Optional.ofNullable(columnOrder)
        .filter(value -> value instanceof List)
        .map(value -> (List<String>) value)
        .orElse(new ArrayList<>());
  }
}
