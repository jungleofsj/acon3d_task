package CanTuna.acon3dtask.service;

import CanTuna.acon3dtask.domain.User;
import CanTuna.acon3dtask.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    void joinUser() {
        User testCreator = new User();
        testCreator.setName("creator1");
        testCreator.setType(User.USER_TYPE_CREATOR);

        Long savedId = userService.joinUser(testCreator);
        System.out.println(savedId);
        User findUser = userService.findUserById(savedId).get();

        Assertions.assertThat(testCreator.getName()).isEqualTo(findUser.getName());
        System.out.println("USERID" + findUser.getId());
    }

    @Test
    void logInUser() {
    }

    @Test
    void findUserById() {
    }

    @Test
    void findUserByName() {
    }
}