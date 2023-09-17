package picpaydesafiobackend.authentication.service;

import picpaydesafiobackend.application.exceptions.UserException;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.authentication.payload.request.UserRequest;

import java.util.List;

public interface UserService {
    User createUser(UserRequest userRequest) throws UserException;
    void validateUser(String userId) throws UserException;
    User findUserById(String id) throws UserException;
    List<User> findAllUsers();
    User findUserByEmail(String email) throws UserException;
}
