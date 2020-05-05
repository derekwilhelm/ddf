/**
 * Copyright (c) Codice Foundation
 *
 * <p>This is free software: you can redistribute it and/or modify it under the terms of the GNU
 * Lesser General Public License as published by the Free Software Foundation, either version 3 of
 * the License, or any later version.
 *
 * <p>This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details. A copy of the GNU Lesser General Public
 * License is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 */
package ddf.catalog.operation;

import java.util.List;

/**
 * ResultAttributeHighlight represents a specific attribute that matched a query and provides
 * indications of what the specific data the query matched on via a list of highlights. The
 * individual highlights are meant to be snippets of highlighted data from the attribute's value.
 */
public interface ResultAttributeHighlight {
  String getAttributeName();

  List<String> getHighlights();
}