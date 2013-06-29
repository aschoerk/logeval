/*
 * LogEvalDbConnectionFactory.java
 *
 * Created on 26. Februar 2007, 12:35
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.ptvag.logeval;

import com.ptvag.logeval.extraction.LineHandlerException;
import java.sql.Connection;

/**
 *
 * @author xzas
 */
public class LogEvalDbConnectionFactory implements IDbConnectionFactory {
    
    LogEvalConfig config;
    IOutputter logger;
    /** Creates a new instance of LogEvalDbConnectionFactory */
    public LogEvalDbConnectionFactory(LogEvalConfig config, IOutputter logger) {
        this.config = config;
        this.logger = logger;
    }
    
    public Connection newConnection() {
        try {
            Class.forName(config.getJdbcDriverClass());
            Connection conn = java.sql.DriverManager.getConnection(config.getDbUrl(),config.getDbUser(),config.getDbPassword());
            return conn;
        } catch (Throwable e) {
            throw new LogEvaluatorException("creating Connection in ConnectionFactory",e);
        }
    }
    
    public boolean testConnection() {
        try {
            Class.forName(config.getJdbcDriverClass());
            Connection conn = java.sql.DriverManager.getConnection(config.getDbUrl(),config.getDbUser(),config.getDbPassword());
            conn.close();
            return true;
        } catch (Throwable e) {
            if (logger != null) {
                logger.println("connection not created");
                logger.println(e.toString());
            }
            return false;
        }
    }
    
    
    
}
