package by.kozl.createDB;

import by.kozl.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserDB implements Serializable {


    public static final String PATH_TO_DB = "./webAppDB/json";
    public static final String NAME_OF_DB = "Users.json";

    private static CreateUserDB instance;
    private List<User> users = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();

    private CreateUserDB() {}

    public static CreateUserDB getInstance() {
        if (instance == null) {
            instance = new CreateUserDB();
        }
        return instance;
    }

    public List<User> getDataBase() throws IOException {

        if (!instance.checkDataBase()) {
            instance.createDB();
        }

        users = mapper.readValue(new File(PATH_TO_DB + "/" + NAME_OF_DB),new TypeReference<>(){});

        return users;
    }

    private void createDB() throws IOException {

        users.add(new User("Pasha",33,"killersarequiet@gmail.com","breadKozlov","password"));
        users.add(new User("Sasha",33,"gopnik12@mail.ru","Sasha112","123456789"));
        this.saveDB(users);
    }

    private void saveDB(List<User> users) throws IOException {
        File file = new File(PATH_TO_DB);
        if (file.mkdirs()) {
            System.out.println("Directory is created");
        }
        mapper.writeValue(new File(file.getPath() + "/" + NAME_OF_DB),users);
    }

    private boolean checkDataBase() {

        boolean result = false;
        File file = new File(PATH_TO_DB);
        if(file.isDirectory() && Objects.requireNonNull(file.listFiles()).length > 0) {

            for (File f : Objects.requireNonNull(file.listFiles())) {

                if (f.isFile() && f.getName().equalsIgnoreCase(NAME_OF_DB)) {
                    result = true;
                    break;
                }
            }
        }
        System.out.println(result);
        return result;
    }
}
