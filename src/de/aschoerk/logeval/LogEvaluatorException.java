package de.aschoerk.logeval;


public class LogEvaluatorException extends RuntimeException {
    public LogEvaluatorException(Throwable e) {
        super(e);
    }
    public LogEvaluatorException(String message, Throwable e) {
        super(message, e);
    }
    public LogEvaluatorException(String message) {
        super(message);
    }
}