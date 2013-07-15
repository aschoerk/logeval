package de.aschoerk.logeval.extraction;


public class LineHandlerException extends RuntimeException {
    public LineHandlerException(Throwable e) {
        super(e);
    }
    public LineHandlerException(String message, Throwable e) {
        super(message, e);
    }
    public LineHandlerException(String message) {
        super(message);
    }
}