package com.taskmanagement.domain.application;

import com.taskmanagement.domain.application.commands.RegistrationCommand;
import com.taskmanagement.domain.model.user.RegistrationException;

public interface UserService {

  void register(RegistrationCommand command) throws RegistrationException;
}