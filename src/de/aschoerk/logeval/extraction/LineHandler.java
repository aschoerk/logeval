package de.aschoerk.logeval.extraction;

 

public interface LineHandler {
    void handleLine(String line);
    void startWriting();
    void ready();
    void handleField(String line, int fno);
}