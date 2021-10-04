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
        return null;
    }

    public Long logInUser(User user) {
        //유저 로그인
        return null;
    }

    public Optional<User> findUserById(Long userId) {
        //id로 조회
        return null;
    }

    public Optional<User> findUserByName(String userName) {
        //name으로 조회
        return null;
    }

    public List<User> findUserAll() {
        //user 전체 조회
        return null;
    }

    private Boolean isExistUser(User user) {
        //중복 유저 생성 방지
        return null;
    }





}
