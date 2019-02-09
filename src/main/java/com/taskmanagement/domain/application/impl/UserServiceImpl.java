package com.taskmanagement.domain.application.impl;

import javax.transaction.Transactional;

import com.taskmanagement.domain.application.UserService;
import com.taskmanagement.domain.application.commands.RegistrationCommand;
import com.taskmanagement.domain.common.event.DomainEventPublisher;
import com.taskmanagement.domain.common.mail.MailManager;
import com.taskmanagement.domain.common.mail.MessageVariable;
import com.taskmanagement.domain.model.user.RegistrationException;
import com.taskmanagement.domain.model.user.RegistrationManagement;
import com.taskmanagement.domain.model.user.User;
import com.taskmanagement.domain.model.user.events.UserRegisteredEvent;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  private RegistrationManagement registrationManagement;
  private DomainEventPublisher domainEventPublisher;
  private MailManager mailManager;

  public UserServiceImpl(RegistrationManagement registrationManagement,
                         DomainEventPublisher domainEventPublisher,
                         MailManager mailManager) {
    this.registrationManagement = registrationManagement;
    this.domainEventPublisher = domainEventPublisher;
    this.mailManager = mailManager;
  }

  @Override
  public void register(RegistrationCommand command) throws RegistrationException {
    Assert.notNull(command, "Parameter `command` must not be null");
    User newUser = registrationManagement.register(
      command.getUsername(),
      command.getEmailAddress(),
      command.getPassword());

    sendWelcomeMessage(newUser);
    domainEventPublisher.publish(new UserRegisteredEvent(newUser));
  }

  private void sendWelcomeMessage(User user) {
    mailManager.send(
      user.getEmailAddress(),
      "Welcome to TaskAgile",
      "welcome.ftl",
      MessageVariable.from("user", user)
    );
  }

}