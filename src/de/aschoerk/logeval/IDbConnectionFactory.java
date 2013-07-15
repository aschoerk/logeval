/*
 * IDbConnectionFactory.java
 *
 * Created on 26. Februar 2007, 12:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.aschoerk.logeval;

import java.sql.Connection;

/**
 *
 * @author xzas
 */
public interface IDbConnectionFactory {
    Connection newConnection();
    boolean testConnection();
}
