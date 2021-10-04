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


/*        User testCreator = new User();
        testCreator.setName("creator1");
        testCreator.setType(User.USER_TYPE_CREATOR);

        User testEditor = new User();
        testEditor.setName("editor1");
        testEditor.setType(User.USER_TYPE_EDITOR);

        User testCustomer = new User();
        testCustomer.setName("customer1");
        testCustomer.setType(User.USER_TYPE_CUSTOMER);
*/


    @Test
    void joinUser() {
        //given
        User testCreator = new User();
        testCreator.setName("creator1");
        testCreator.setType(User.USER_TYPE_CREATOR);

        //when
        Long savedId = userService.joinUser(testCreator);
        System.out.println(savedId);
        User findUser = userService.findUserById(savedId).get();

        //then
        Assertions.assertThat(testCreator.getName()).isEqualTo(findUser.getName());
        System.out.println("USERID" + findUser.getId());
    }

    @Test
    void logInUser() {
//        public Long logInUser(User user) {
//            return userRepository.authUser(user);
//        }

        //given
        User testEditor = new User();
        testEditor.setName("editor1");
        testEditor.setType(User.USER_TYPE_EDITOR);
        //when
        userService.joinUser(testEditor);

        User loginUser = new User();
        loginUser.setName("editor1");
        loginUser.setType(User.USER_TYPE_EDITOR);
        //then
        Long logIn = userService.logInUser(loginUser);
        Assertions.assertThat(logIn).isEqualTo(testEditor.getType());
    }

    @Test
    void findUserById() {
    }

    @Test
    void findUserByName() {
    }
}