/*******************************************************************************
 * Copyright 2019 madhankumar
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.rabobank.statementprocessor.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.rabobank.statementprocessor.model.events.BatchCompletedEvent;
import com.rabobank.statementprocessor.model.events.BatchStartedEvent;

/**
 * @author madhankumar
 *
 */
@Component
public class CustomSpringEventListener {

  /* The LOGGER */
  private static final Logger LOGGER = LoggerFactory.getLogger(CustomSpringEventPublisher.class);

  @EventListener
  public void handleBatchStartedEvent(BatchStartedEvent e) {
    LOGGER.info("Handling BatchStartedEvent {}", e.getMessage());
    // business logic goes further
  }

  @EventListener
  public void handleBatchCompletedEvent(BatchCompletedEvent e) throws Exception {
    LOGGER.info("Handling BatchCompletedEvent {}", e.getMessage());
    // business logic goes further
  }
}
