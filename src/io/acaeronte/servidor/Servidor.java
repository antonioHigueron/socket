package io.acaeronte.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private int puerto;
    /**
     * obj que representa la implementacion del cliente
     */
    private Socket socketCliente;
    /**
     * obj que representa la implementacion del servidor
     */
    private ServerSocket socketServidor;
    /**
     * objeto para enviar datos al cliente
     */
    private PrintWriter salida;
    /**
     * objeto para recibir datos del cliente
     */
    private BufferedReader entrada;


    public Servidor(int portNumber){
        this.puerto = portNumber;
    }

    public void arranque(){
        try {
        //crear el serversocket, indicando el puerto en el que va a escuchar
        //sino puede escuchar en ese puerto, retorna una ioexception, a capturar
            socketServidor = new ServerSocket(puerto);
        //crear el socket aceptando la conexión de un cliente
        //y así el servidor, sigue disponible para nuevas conexiones a este host y puerto
            socketCliente = socketServidor.accept();
        //vamos a obtener el input y output del cliente
            salida = new PrintWriter(socketCliente.getOutputStream(),true);
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        //vamos a leer lo que el cliente nos envíe y lo enviamos de vuelta al cliente
            String inputData;
        //mientras haya datos que leer, los recogemos y los devolvemos al cliente
            while ((inputData = entrada.readLine()) != null ){
                salida.println(inputData);
            }

        } catch (IOException e) {
            throw new RuntimeException("Ha terminado la conexion actual, se cierra el" +
                    " socket del servidor ");
        }

    }


    /**
     * detiene todas las conexiones existentes y el servidor deja de escuchar
     * IMPORTANT: los stream deben ser cerrados antes que los socket
     */
    public void stop(){
        try {
            entrada.close();
            salida.close();
            socketServidor.close();
            socketCliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * metodo para arrancar el socket y dejarlo escuchando
     */
    public static void main(String[] args) {

        Servidor servidor = new Servidor(5000);
        System.out.println("Hola, soy socket y te estoy esperando");
        while(true) {
            servidor.arranque();
        }
    }

}
























