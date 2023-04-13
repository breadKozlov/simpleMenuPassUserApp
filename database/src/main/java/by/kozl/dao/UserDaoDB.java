package by.kozl.dao;

import by.kozl.entity.User;
import by.kozl.exception.DaoException;
import by.kozl.utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoDB implements Dao<Integer, User> {

    private static final UserDaoDB INSTANCE = new UserDaoDB();

    private static String FIND_ALL = """
            SELECT id,name_of_user,age_user,email,login,password
            FROM users
            """;

    private static String FIND_BY_ID_SQL = FIND_ALL + """
            WHERE id = ?
            """;

    private static String SAVE_SQL = """
            INSERT INTO users (name_of_user,age_user,email,login,password) 
            VALUES (?, ?, ?, ?, ?)
            """;

    private static String DELETE_SQL = """
            DELETE FROM users
            WHERE id = ?
            """;

    private static String UPDATE_SQL = """
            UPDATE users SET
            name_of_user = ?,
            age_user = ?,
            email = ?,
            login = ?,
            password = ?
            WHERE id = ?
            """;

    @Override
    public boolean update(User user) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, user.getName());
            statement.setInt(2, user.getAge());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());
            statement.setLong(6, user.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            User user = null;
            statement.setLong(1, id);
            var result = statement.executeQuery();
            if (result.next())
                user = buildUser(result);
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> findAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_ALL)) {
            List<User> users = new ArrayList<>();
            var result = statement.executeQuery();
            while (result.next())
                users.add(buildUser(result));

            return users;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public User save(User user) {
        try (var connection = ConnectionManager.get();
             var statement = connection
                     .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setInt(2, user.getAge());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                user.setId(generatedKeys.getInt("id"));
            return user;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private User buildUser(ResultSet result) throws SQLException {
        return new User(
                result.getInt("id"),
                result.getString("name_of_user"),
                result.getInt("age_user"),
                result.getString("email"),
                result.getString("login"),
                result.getString("password"));
    }

    private UserDaoDB() {
    }

    public static UserDaoDB getInstance() {
        return INSTANCE;
    }
}
