package lk.lahiru.springandhibernateintigration.tasks.dao.exception;

public class DataAccessException extends RuntimeException{

    public DataAccessException() {
    }

    public DataAccessException(String message) {
        super(message);
    }
}
