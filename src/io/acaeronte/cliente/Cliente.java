package io.acaeronte.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    /**
     * datos para conectar con el servidor
     */
    private String host;
    private int puerto;

    /**
     * representa la parte cliente de un socket
     */
    private Socket cliente;

    /**
     * datos que envía el cliente al servidor
     */
    private PrintWriter salida;

    /**
     * flujo de datos que recibe el cliente desde el servidor
     */
    private BufferedReader entrada;

    public Cliente(String host, int puerto){
        this.host = host;
        this.puerto = puerto;
    }

    /**
     * el cliente siempre es el que comienza la conexión con el servidor
     */
    public void connectInit(){
        //conectarme al servidor
        try {
            cliente = new Socket(host,puerto);
            //crear obj para enviar los datos al servidor
            salida = new PrintWriter(cliente.getOutputStream(),true);
            //obj para leer lo que el servidor nos envia a nosotros
            entrada = new BufferedReader(new InputStreamReader( cliente.getInputStream() ));

        } catch (IOException e) {
            throw new RuntimeException("Debes levantar el servidor primero, ejecuta el main de Servidor.java "+e);
        }

    }

    public String sendMessage(String msn){
        //msn que vamos a enviar al servidor
        salida.println(msn);
        //recoger la respuesta del servidor
        String response = null;
        try {
        //leemos la respuesta
        response = entrada.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return  response;
    }

    /**
     * detener intercambio de datos y cerrar conexion
     * IMPORTANT: los stream deben ser cerrados antes que los socket
     */
    public void stopConnect()  {
        try {
            //primero se cierran los stream
            entrada.close();
            salida.close();
            //despues se cierran los socket
            cliente.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
