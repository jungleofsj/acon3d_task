package CanTuna.acon3dtask.repository;

import CanTuna.acon3dtask.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User createUser(User user);

    Optional<User> findUserById(Long userId);

    Optional<User> findUserByName(String userName);

    List<User> findByUserType(Long userType);

    List<User> findAllUser();

    Long authUser(User user);

}
