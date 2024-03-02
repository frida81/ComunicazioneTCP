/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.comunicazionesocket.tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author frida
 */
public class ServerTCP {
    ServerSocket server; //serversocket
    Socket client; //datasocket
    String stringaRicevuta;
    String stringaModificata;
    BufferedReader in;
    DataOutputStream out;
    
    public void ServerAttendi(){
        try{
            server= new ServerSocket(6789);
            System.out.println("1) SERVER partito in esecuzione");
            client=server.accept();
            System.out.println(client.getLocalPort());//porta del socket locale
            System.out.println(client.getPort());//porta dell'host remoto a cui il socket è collegato  
            in= new BufferedReader(new InputStreamReader(client.getInputStream()));
            out=new DataOutputStream(client.getOutputStream());
            server.close(); //CONNESSIONE UNICAST, chiudo il server per inibire altri client
        }catch(IOException ex){
            System.out.println(ex.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }    
    }
    
    public void ServerComunica(){
        try{
             System.out.println("3) Benvenuto client, scrivi una frase e la trasformo in maiuscolo");
             stringaRicevuta=in.readLine();
             System.out.println("6) stringa ricevuta dal client: " + stringaRicevuta);
             stringaModificata=stringaRicevuta.toUpperCase();
             System.out.println("7) invio la stringa modificata al client");
             out.writeBytes(stringaModificata+'\n');
           
//termina il lavoro del server, quindi chiudo la connessione col client
             System.out.println("9) SERVER: fine elaborazione!");
             client.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione");
            System.exit(1);
        }
    }
    
    public static void main(String args[]){
        ServerTCP s= new ServerTCP();
        s.ServerAttendi();
        s.ServerComunica();
    }
}
