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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import ddf.catalog.operation.impl.QueryResponseImpl;
import org.junit.Test;

/**
 * Test {@link QueryResponseImpl}
 *
 * @author Shaun Morris
 */
public class QueryResponseImplTest {

  @Test
  public void testConstructorCannotMakeProcessingDetailsNull() {
    assertThat((new QueryResponseImpl(null)).getProcessingDetails(), is(notNullValue()));
  }

  @Test
  public void testSetterCannotMakeProcessingDetailsNull() {
    QueryResponseImpl response = new QueryResponseImpl(null);
    response.setProcessingDetails(null);
    assertThat(response.getProcessingDetails(), is(notNullValue()));
  }
}
