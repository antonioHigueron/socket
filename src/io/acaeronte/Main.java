package io.acaeronte;

import io.acaeronte.cliente.Cliente;

public class Main {
    /**
     * otra fuente de ejemplo:
     * https://es.stackoverflow.com/questions/409779/como-no-cerrar-aplicaci%c3%b3n-de-servidor-con-sockets
     * @param args
     */

    public static void main(String[] args) {
        //primero ejecutamos el main, de la clase del Servidor
        //para que esté esperando a recibir una conexion.

        //creamos el cliente para conectar con el servidor
        Cliente cliente = new Cliente("127.0.0.1",5000);
        //hacemos la conexion
        cliente.connectInit();
        //enviamos mensaje
        String response = cliente.sendMessage("hola");
        System.out.println("response = " + response);

        String response2 = cliente.sendMessage("¿Que tal?");
        System.out.println("response2 = " + response2);

        String response3 = cliente.sendMessage("Me llamo Acaeronte");
        System.out.println("response = " + response3);



    }
}