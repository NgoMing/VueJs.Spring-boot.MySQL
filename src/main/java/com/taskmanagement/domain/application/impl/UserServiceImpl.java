package com.taskmanagement.domain.application.impl;

import javax.transaction.Transactional;

import com.taskmanagement.domain.application.UserService;
import com.taskmanagement.domain.application.commands.RegistrationCommand;
import com.taskmanagement.domain.model.user.RegistrationException;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  @Override
  public void register(RegistrationCommand command) throws RegistrationException {

  }

}