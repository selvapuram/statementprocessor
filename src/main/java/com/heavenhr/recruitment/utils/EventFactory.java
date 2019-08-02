/*

Copyright 2019, madhankumar
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are
met:

 * Redistributions of source code must retain the above copyright
notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above
copyright notice, this list of conditions and the following disclaimer
in the documentation and/or other materials provided with the
distribution.
 * Neither the name of Google Inc. nor the names of its
contributors may be used to endorse or promote products derived from
this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,           
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY           
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 */
package com.heavenhr.recruitment.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

import com.heavenhr.recruitment.events.CustomSpringEventPublisher;
import com.heavenhr.recruitment.model.dto.Application;
import com.heavenhr.recruitment.model.dto.ApplicationStatus;
import com.heavenhr.recruitment.model.events.AppliedEvent;
import com.heavenhr.recruitment.model.events.HiredEvent;
import com.heavenhr.recruitment.model.events.InvitedEvent;
import com.heavenhr.recruitment.model.events.RejectedEvent;

/**
 * @author madhankumar
 *
 */
public class EventFactory {
  /* The LOGGER */
  private static final Logger LOGGER = LoggerFactory.getLogger(CustomSpringEventPublisher.class);

  public static ApplicationEvent createEvent(Application application, ApplicationStatus as, String message) {
    LOGGER.debug("event creation {}", message);
    switch (as) {
      case APPLIED:
        return new AppliedEvent(application, message);
      case REJECTED:
        return new RejectedEvent(application, message);
      case INVITED:
        return new InvitedEvent(application, message);
      case HIRED:
        return new HiredEvent(application, message);
      default:
        throw new AssertionError("Unknown operations ");
    }
  }
}
