package br.com.sistema.main;

import br.com.sistema.dao.ConnectionFactory;

import java.sql.Connection;

public class TesteConexao {

    public static void main(String[] args) {

        try {
            Connection conn = ConnectionFactory.getConnection();

            System.out.println("Conectado!");
            System.out.println("Banco: " + conn.getCatalog());
            System.out.println("URL: " + conn.getMetaData().getURL());
            System.out.println("Usuário: " + conn.getMetaData().getUserName());

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}