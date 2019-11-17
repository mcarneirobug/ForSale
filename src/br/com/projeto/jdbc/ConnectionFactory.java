/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    public Connection getConnection() {

        try {
//            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3309/bdvendas", "usuariocurso", "123");
        	  return DriverManager.getConnection("jdbc:mysql://localhost:3306/bdvendas?useTimezone=true&serverTimezone=UTC", "usuariocurso", "usuariocurso");
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }
}