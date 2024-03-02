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
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author frida
 */
public class ClientTCP {
    String serverName="localhost";
    int serverPort=6789;
    Socket localSocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaFromServer;
    BufferedReader in;
    DataOutputStream out;
    
    public void ClientConnetti() throws IOException{
        try{
        localSocket= new Socket(serverName, serverPort);
        in= new BufferedReader(new InputStreamReader(localSocket.getInputStream()));
        out= new DataOutputStream(localSocket.getOutputStream());
        System.out.println("2) CLIENT partito e in esecuzione...");
        System.out.println(localSocket.getLocalPort());//porta del socket locale
        System.out.println(localSocket.getPort());//porta dell'host remoto a cui il socket è collegato
        //System.out.println(localSocket.get);
        tastiera= new BufferedReader(new InputStreamReader(System.in));
        }catch(UnknownHostException e){
            System.err.println("Host sconosciuto");
        }catch(IOException ex){
            System.out.println(ex.getMessage());
            System.out.println("Errore durante la comunicazione");
            System.exit(1);
        }        
    }
    public void ClientComunica(){
    try{
        System.out.println("4) inserisci la stringa da trasmettere al server"+'\n');   
        stringaUtente= tastiera.readLine();
        System.out.println("5) invio la stringa al server e attendo"); 
        out.writeBytes(stringaUtente+'\n');
        //leggo la risposta dal server
        stringaFromServer=in.readLine(); //data e ora
        System.out.println("8) la risposta dal server: "+'\n'+stringaFromServer);
        System.out.println("9) termina elaborazione e chiude la connessione");
        localSocket.close();   
        
    }catch(IOException e){
        System.out.println(e.getMessage());
        System.out.println("Errore durante la comunicazione");
        System.exit(1);
    }
    }
    public static void main(String args[]) throws IOException{
        ClientTCP c= new ClientTCP();
        c.ClientConnetti();
        c.ClientComunica();
    }
}
