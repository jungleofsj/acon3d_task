package CanTuna.acon3dtask.service;

import CanTuna.acon3dtask.domain.User;
import CanTuna.acon3dtask.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long joinUser(User user) {
        //유저 생성
        if (this.isExistUser(user)) {//중복 userName 검증
            return -1L;
        }

        userRepository.createUser(user);

        return user.getId();
    }

    public Long logInUser(User user) {
        return userRepository.authUser(user);
    }

    public Optional<User> findUserById(Long userId) {
        return userRepository.findUserById(userId);
    }

    public Optional<User> findUserByName(String userName) {
        return userRepository.findUserByName(userName);
    }

    public List<User> findUserAll() {
        return userRepository.findAllUser();
    }

    private Boolean isExistUser(User user) {
        Optional<User> result = userRepository.findUserByName(user.getName());
        return !result.isEmpty();
    }
}
