package Servidor;

import java.io.IOException;
import java.net.*;

import java.io.*;
import java.util.Scanner;


public class RodaServidor {

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(12345);
        System.out.println("Servidor com conexao aberta");

        Socket cliente = servidor.accept();
        System.out.println("Nova conexão com o cliente " +
                cliente.getInetAddress().getHostAddress()
        );

        Scanner s = new Scanner(cliente.getInputStream());
        while (s.hasNextLine()) {
            System.out.println(s.nextLine());
        }

        s.close();
        servidor.close();
        cliente.close();
    }
}



