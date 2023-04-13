package by.kozl.service;

import by.kozl.dto.UserDto;
import by.kozl.dao.UserDaoDB;
import by.kozl.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceDB {

    private static final UserServiceDB INSTANCE = new UserServiceDB();
    private final UserDaoDB userDaoDB = UserDaoDB.getInstance();

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
        return userDaoDB.findById(login)
                .map(it -> new UserDto(it.getName(),it.getAge(),it.getEmail(),it.getLogin(),it.getPassword()));

    }

    public boolean updateUser(UserDto userDto) {
        return userDaoDB.update(new User(userDto.getName(),userDto.getAge(),
                userDto.getEmail(),userDto.getLogin(),userDto.getPassword()));
    }

    public boolean registerUser(UserDto userDto) {
        var result = userDaoDB.save(new User(userDto.getName(),userDto.getAge(),
                userDto.getEmail(),userDto.getLogin(),userDto.getPassword()));
        return result == null;
    }

    private List<Optional<UserDto>> getUsersFromDAO() {
        List<Optional<User>> listUsers = userDaoDB.findAll().stream().map(Optional::ofNullable).toList();
        List<Optional<UserDto>> listUsersDto = new ArrayList<>();
        for (Optional<User> user: listUsers) {
            listUsersDto.add(user
                    .map(it -> new UserDto(it.getName(),it.getAge(),it.getEmail(),it.getLogin(),it.getPassword())));
        }
        return listUsersDto;
    }

    private UserServiceDB() {}

    public static UserServiceDB getInstance() {
        return INSTANCE;
    }
}
