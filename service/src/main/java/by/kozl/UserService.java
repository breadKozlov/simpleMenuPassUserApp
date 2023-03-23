package by.kozl;

import by.kozl.dao.UserDao;
import by.kozl.entity.User;

import java.util.*;

public class UserService {

    private final UserDao userDao = new UserDao();

    public boolean checkPassword(String login, String password) {

        boolean isPresent = false;
        List<Optional<UserDto>> listUsersDto = this.getUsersFromDAO();
        for (Optional<UserDto> userDto: listUsersDto) {
            if(userDto.orElseThrow().getLogin().equals(login) &&
            userDto.orElseThrow().getPassword().equals(password)) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }

    public Optional<UserDto> getUser(String login) {
        return userDao.findByLogin(login)
                .map(it -> new UserDto(it.getName(),it.getAge(),it.getEmail(),it.getLogin(),it.getPassword()));

    }

    public boolean updateUser(UserDto userDto) {
        return userDao.updateUser(new User(userDto.getName(),userDto.getAge(),
                userDto.getEmail(),userDto.getLogin(),userDto.getPassword()));
    }

    public boolean registerUser(UserDto userDto) {
        return userDao.registerUser(new User(userDto.getName(),userDto.getAge(),
                userDto.getEmail(),userDto.getLogin(),userDto.getPassword()));
    }

    private List<Optional<UserDto>> getUsersFromDAO() {
        List<Optional<User>> listUsers = userDao.getAllUsers();
        List<Optional<UserDto>> listUsersDto = new ArrayList<>();
        for (Optional<User> user: listUsers) {
            listUsersDto.add(user
                    .map(it -> new UserDto(it.getName(),it.getAge(),it.getEmail(),it.getLogin(),it.getPassword())));
        }
        return listUsersDto;
    }



}
