package capstoneProject;

// Custom exception class to handle log file operations
class ChainException extends Exception {
    public ChainException(String message) {
        super(message);
    }
    public ChainException(String message, Throwable cause) {
        super(message, cause);
    }
}
