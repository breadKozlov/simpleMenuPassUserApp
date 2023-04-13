package by.kozl.exception;

import java.sql.SQLException;

public class DaoException extends RuntimeException{
    public DaoException(SQLException ex) {
        super(ex);
    }
}
