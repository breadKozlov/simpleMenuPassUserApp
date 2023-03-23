package by.kozl.dao;

import by.kozl.createDB.CreateUserDB;
import by.kozl.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class UserDao {

    private List<User> users;

    private ObjectMapper objectMapper = new ObjectMapper();
    private static String path = CreateUserDB.PATH_TO_DB;
    private static String file = CreateUserDB.NAME_OF_DB;

    public UserDao() {
        try {
            CreateUserDB createUserDB = CreateUserDB.getInstance();
            users = createUserDB.getDataBase();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<Optional<User>> getAllUsers() {
        users = readOnDB();
        List<Optional<User>> resultList = new ArrayList<>();
        for (User user: users) {
            resultList.add(Optional.ofNullable(user));
        }
        return resultList;
    }

    public Optional<User> findByLogin(String login) {
        users = readOnDB();
        User resultUser = new User();
        for (User user: users) {
            if (user.getLogin().equals(login)) {
                resultUser = user;
                break;
            }
        }
        return Optional.of(resultUser);
    }

    public boolean updateUser(User user) {
        boolean result = false;
        users = this.readOnDB();
        for (User man: users) {
            if (man.getLogin().equals(user.getLogin())) {
                users.set(users.indexOf(man),user);
                result = true;
                break;
            }
        }
        writeOnDB(users);
        return result;
    }

    public boolean registerUser(User user){
        boolean result = false;
        users = this.readOnDB();
        for (User man: users) {
            if (man.getLogin().equals(user.getLogin())) {
                result = true;
                break;
            }
        }
        if (!result) {
            users.add(user);
            writeOnDB(users);
        }
        return result;
    }

    private void writeOnDB(List<User> list) {

        try{
            objectMapper.writeValue(new File(path + "/" + file),list);
            System.out.println("Saved");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private List<User> readOnDB() {
        try{
            users = objectMapper.readValue(new File(path + "/" + file),new TypeReference<>(){});
            System.out.println("Read");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;
    }
}
