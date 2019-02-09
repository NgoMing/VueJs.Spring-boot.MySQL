package com.taskmanagement.infrastructure.repository;

import com.taskmanagement.domain.model.user.User;
import com.taskmanagement.domain.model.user.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
public class HibernateUserRepositoryTests {

  @TestConfiguration
  public static class UserRepositoryTestContextConfiguration {
    @Bean
    public UserRepository userRepository(EntityManager entityManager) {
      return new HibernateUserRepository(entityManager);
    }
  }

  @Autowired
  private UserRepository repository;

  @Test(expected = PersistenceException.class)
  public void save_nullUsernameUser_shouldFail() {
    User inavlidUser = User.create(null, "test@gmail.com", "password");
    repository.save(inavlidUser);
  }

  @Test(expected = PersistenceException.class)
  public void save_nullEmailAddressUser_shouldFail() {
    User inavlidUser = User.create("test", null, "password");
    repository.save(inavlidUser);
  }

  @Test(expected = PersistenceException.class)
  public void save_nullPasswordUser_shouldFail() {
    User inavlidUser = User.create("test", "test@gmail.com", null);
    repository.save(inavlidUser);
  }

  @Test
  public void save_validUser_shouldSuccess() {
    String username = "test";
    String emailAddress = "test@gmail.com";
    User newUser = User.create(username, emailAddress, "password");
    repository.save(newUser);
    assertNotNull("New user's id should be generated", newUser.getId());
    assertNotNull("New user's created date should be generated", newUser.getCreatedDate());
    assertEquals(username, newUser.getUsername());
    assertEquals(emailAddress, newUser.getEmailAddress());
    assertEquals("", newUser.getFirstName());
    assertEquals("", newUser.getLastName());
  }

  @Test
  public void save_usernameAlreadyExist_shouldFail() {
    // Create already exist user
    String username = "exist";
    String emailAddress = "test@gmail.com";
    User alreadyExist = User.create(username, emailAddress, "password");
    repository.save(alreadyExist);

    try {
      User newUser = User.create(username, "new@gmail.com", "password");
      repository.save(newUser);
    } catch (Exception e) {
      assertEquals(ConstraintViolationException.class.toString(), e.getCause().getClass().toString());
    }
  }

  @Test
  public void save_emailAddressAlreadyExist_shouldFail() {
    // Create already exist user
    String username = "test";
    String emailAddress = "existTest@gmail.com";
    User alreadyExist = User.create(username, emailAddress, "password");
    repository.save(alreadyExist);

    try {
      User newUser = User.create("new", emailAddress, "password");
      repository.save(newUser);
    } catch (Exception e) {
      assertEquals(ConstraintViolationException.class.toString(), e.getCause().getClass().toString());
    }
  }

  @Test
  public void findByEmailAddress_notExist_shouldReturnEmptyResult() {
    String emailAddress = "test@gmail.com";
    User user = repository.findByEmailAddress(emailAddress);
    assertNull("No user should by found", user);
  }

  @Test
  public void findByEmailAddress_exist_shouldReturnResult() {
    String emailAddress = "test@gmail.com";
    String username = "test";
    User newUser = User.create(username, emailAddress, "password");
    repository.save(newUser);
    User found = repository.findByEmailAddress(emailAddress);
    assertEquals("Username should match", username, found.getUsername());
  }

  @Test
  public void findByUsername_notExist_shouldReturnEmptyResult() {
    String username = "test";
    User user = repository.findByUsername(username);
    assertNull("No user should by found", user);
  }

  @Test
  public void findByUsername_exist_shouldReturnResult() {
    String username = "test";
    String emailAddress = "test@gmail.com";
    User newUser = User.create(username, emailAddress, "password");
    repository.save(newUser);
    User found = repository.findByUsername(username);
    assertEquals("Email address should match", emailAddress, found.getEmailAddress());
  }
}
