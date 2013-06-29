/*
 * ReplaceContext.java
 *
 * Created on 24. Februar 2007, 07:53
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.ptvag.logeval;

import java.util.regex.Matcher;

/**
 *
 * @author schoerk
 */
public class ReplacementContext {
    
    /** Creates a new instance of ReplaceContext */
    public ReplacementContext() {
    }
    
    private String node;
    private String line;
    private Matcher matcher;

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Matcher getMatcher() {
        return matcher;
    }

    public void setMatcher(Matcher matcher) {
        this.matcher = matcher;
    }
    
    
    
}
