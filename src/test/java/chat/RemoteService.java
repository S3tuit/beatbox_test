package chat;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteService extends UnicastRemoteObject implements MyRemote {

    public void main(String[] args){
        try{
            RemoteService rs = new RemoteService();
            Naming.rebind("RemoteService", rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RemoteService() throws RemoteException {
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Server says hello";
    }
}
