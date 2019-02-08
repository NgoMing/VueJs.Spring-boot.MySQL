package com.taskmanagement.web.apis;

import javax.validation.Valid;

import com.taskmanagement.domain.application.UserService;
import com.taskmanagement.domain.model.user.EmailAddressExistedException;
import com.taskmanagement.domain.model.user.RegistrationException;
import com.taskmanagement.domain.model.user.UsernameExistedException;
import com.taskmanagement.web.payload.RegistrationPayload;
import com.taskmanagement.web.results.ApiResult;
import com.taskmanagement.web.results.Result;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RegistrationApiController {

  private UserService service;

  public RegistrationApiController(UserService service) {
    this.service = service;
  }

  @PostMapping("/api/registrations")
  public ResponseEntity<ApiResult> register(@Valid @RequestBody RegistrationPayload payload) {
    try {
      service.register(payload.toCommand());
      return Result.created();
    } catch(RegistrationException e) {
      String errorMessage = "Registration failed";
      if (e instanceof UsernameExistedException) {
        errorMessage = "Username already existed";
      } else if (e instanceof EmailAddressExistedException) {
        errorMessage = "Email address already existed";
      }
      return Result.failure(errorMessage);
    }
  }

}