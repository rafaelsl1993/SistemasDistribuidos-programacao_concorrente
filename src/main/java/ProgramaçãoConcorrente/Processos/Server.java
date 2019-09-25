
package ProgramaçãoConcorrente.Processos;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject{
    protected static String name;
    protected static int port;

    public Server() throws RemoteException{
        
    }

    public Server(String name, int port) throws RemoteException{
        this.name = name;
        this.port = port;
        runServer();
    }

    private void runServer(){
        try{
            Server server = new Server();
            Registry registry = LocateRegistry.createRegistry(port);
            registry.bind(name, server);
            System.out.println("Running " + name + " server......");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}