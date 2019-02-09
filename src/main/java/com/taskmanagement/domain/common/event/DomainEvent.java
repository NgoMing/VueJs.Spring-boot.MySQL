package com.taskmanagement.domain.common.event;

import org.springframework.context.ApplicationEvent;

public abstract class DomainEvent extends ApplicationEvent {

  private static final long serialVersionUID = -6340194133410228738L;

  public DomainEvent(Object source) {
    super(source);
  }

  public long occurredAt() {
    return getTimestamp();
  }
}
