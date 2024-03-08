package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;



public class calculadora {

    // Operaciones
    
    public static void suma(){

        System.out.print("Introduce el primer numero: ");
        double numero1 = io.leerDouble();

        System.out.print("Introduce el segundo numero: ");
        double numero2 = io.leerDouble();

        double resultado = numero1 + numero2;
        System.out.println("El resultado de la suma es: " + resultado);

    }

    public static void resta(){

        System.out.print("Introduce el primer numero: ");
        double numero1 = io.leerDouble();

        System.out.print("Introduce el segundo numero: ");
        double numero2 = io.leerDouble();
        double resultado = numero1 - numero2;
        System.out.println("El resultado de la resta es: " + resultado);

    }

    public static void multiplicacion(){

        System.out.print("Introduce el primer numero: ");
        double numero1 = io.leerDouble();

        System.out.print("Introduce el segundo numero: ");
        double numero2 = io.leerDouble();

        double resultado = numero1 * numero2;
        System.out.println("El resultado de la multiplicacion es: " + resultado);

    }

    public static void division(){

        System.out.print("Introduce el primer numero: ");
        double numero1 = io.leerDouble();

        System.out.print("Introduce el segundo numero: ");
        double numero2 = io.leerDouble();

        double resultado = numero1 / numero2;
        System.out.println("El resultado de la division es: " + resultado);

    }

    public static void potencia(){

        System.out.print("Introduce la base: ");
        double numero1 = io.leerDouble();

        System.out.print("Introduce el exponente: ");
        double numero2 = io.leerDouble();

        double resultado = Math.pow(numero1, numero2);
        System.out.println("El resultado de la potencia es: " + resultado);

    }

    public static void raizCuadrada(){

        double numero1 = io.leerInt();

        double resultado = Math.sqrt(numero1);
        System.out.println("El resultado de la raiz cuadrada es: " + resultado);

    }


    private static final String archivo = "usuarios.json";

    public static void registro() {

        System.out.print("Introduce el nombre de usuario: ");
        String username = io.leerString();
        System.out.print("Introduce la contraseña: ");
        String password = io.leerString();

        JSONObject usuarios;
        try {

            String content = new String(Files.readAllBytes(Paths.get(archivo)));
            usuarios = new JSONObject(content);

        } catch (IOException e) {
            usuarios = new JSONObject();
        }

        if (usuarios.has(username)) {
            System.out.println("El usuario ya existe.");
            return;
        }

        usuarios.put(username, password);

        try (FileWriter file = new FileWriter(archivo)) {

            file.write(usuarios.toString(4));
            System.out.println("Te has registrado correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void login(boolean login) {

        System.out.print("Introduce el nombre de usuario: ");
        String username = io.leerString();
        System.out.print("Introduce la contraseña: ");
        String password = io.leerString();

        try {

            String content = new String(Files.readAllBytes(Paths.get(archivo)));
            JSONObject users = new JSONObject(content);

            if (password.equals(users.getString(username))) {
                System.out.println("Has iniciado sesion correctamente.");
            } else {
                System.out.println("Nombre de usuario o contraseña incorrectos.");
                login = false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
