package com.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;

public class calculadora {

    private static String usuarioactual;

    // Operaciones

    public static void suma(String username) {

        System.out.print("Introduce el primer numero: ");
        double numero1 = io.leerDouble();

        System.out.print("Introduce el segundo numero: ");
        double numero2 = io.leerDouble();

        double resultado = numero1 + numero2;
        System.out.println("");
        System.out.println("El resultado de la suma es: " + resultado);

        logOperation(username, "suma", numero1, numero2, resultado);
    }

    public static void resta(String username) {

        System.out.print("Introduce el primer numero: ");
        double numero1 = io.leerDouble();

        System.out.print("Introduce el segundo numero: ");
        double numero2 = io.leerDouble();

        double resultado = numero1 - numero2;
        System.out.println("");
        System.out.println("El resultado de la resta es: " + resultado);

        logOperation(username, "resta", numero1, numero2, resultado);
    }

    public static void multiplicacion(String username) {

        System.out.print("Introduce el primer numero: ");
        double numero1 = io.leerDouble();

        System.out.print("Introduce el segundo numero: ");
        double numero2 = io.leerDouble();

        double resultado = numero1 * numero2;
        System.out.println("");
        System.out.println("El resultado de la multiplicacion es: " + resultado);

        logOperation(username, "multiplicacion", numero1, numero2, resultado);
    }

    public static void division(String username) {

        System.out.print("Introduce el primer numero: ");
        double numero1 = io.leerDouble();

        System.out.print("Introduce el segundo numero: ");
        double numero2 = io.leerDouble();

        if (numero2 == 0) {
            System.out.println("No se puede dividir por cero.");
            return;
        }

        double resultado = numero1 / numero2;
        System.out.println("");
        System.out.println("El resultado de la division es: " + resultado);

        logOperation(username, "division", numero1, numero2, resultado);
    }

    public static void potencia(String username) {

        System.out.print("Introduce la base: ");
        double base = io.leerDouble();

        System.out.print("Introduce el exponente: ");
        double exponente = io.leerDouble();

        double resultado = Math.pow(base, exponente);
        System.out.println("");
        System.out.println("El resultado de la potencia es: " + resultado);

        logOperation(username, "potencia", base, exponente, resultado);
    }

    public static void raizCuadrada(String username) {

        System.out.print("Introduce el numero: ");
        double numero = io.leerDouble();

        if (numero < 0) {
            System.out.println("No se puede calcular la raiz cuadrada de un numero negativo.");
            return;
        }

        double resultado = Math.sqrt(numero);
        System.out.println("");
        System.out.println("El resultado de la raiz cuadrada es: " + resultado);

        logOperation(username, "raizCuadrada", numero, 0, resultado);
    }

    // Registro y login

    private static final String archivo = "src/main/java/com/example/Save/usuarios.json";

    public static void registro() {

        System.out.print("Introduce el nombre de usuario: ");
        String username = io.leerString();

        if (username.trim().isEmpty()) {

            System.out.println("El nombre de usuario no puede estar en blanco.");
            return;

        }

        System.out.print("Introduce la contraseña: ");
        String password = io.leerString();

        if (password.trim().isEmpty()) {

            System.out.println("La contraseña no puede estar en blanco.");
            return;

        }

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

            createLogFile(username);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean login() {

        System.out.print("Introduce el nombre de usuario: ");
        String username = io.leerString();
        System.out.print("Introduce la contraseña: ");
        String password = io.leerString();
        System.out.println("");

        try {

            String content = new String(Files.readAllBytes(Paths.get(archivo)));
            JSONObject users = new JSONObject(content);

            if (users.has(username) && password.equals(users.getString(username))) {
                System.out.println("Has iniciado sesion correctamente.");
                usuarioactual = username;  
                return true;
            } else {
                System.out.println("Nombre de usuario o contraseña incorrectos.");
                return false;
            }

        } catch (IOException e) {
            System.out.println("Error en el archivo de usuarios, es posible que no haya usuarios registrados, pruebe a registrarse primero.");
            return false;
        }
    }

    public static String usuarioactual() {
        return usuarioactual;
    }

    private static void createLogFile(String username) {
        try {

            File logFile = new File("src/main/java/com/example/Save/Logs/" + username + "_log.txt");

            if (logFile.createNewFile()) {
                System.out.println("Archivo de log creado para el usuario: " + username);
            } else {
                System.out.println("El archivo de log ya existe para el usuario: " + username);
            }

        } catch (IOException e) {
            System.out.println("Error al crear el archivo de log.");
            e.printStackTrace();
        }
    }


    private static void logOperation(String username, String operation, double num1, double num2, double resultado) {
        try {
            
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/com/example/Save/Logs/" + username + "_log.txt", true));

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedNow = now.format(formatter);

            if (operation.equals("raizCuadrada")) {
                writer.append("Operación: " + operation + ", Número: " + num1 + ", Resultado: " + resultado + ", Fecha y hora: " + formattedNow + "\n");
            } else {
                writer.append("Operación: " + operation + ", Números: " + num1 + ", " + num2 + ", Resultado: " + resultado + ", Fecha y hora: " + formattedNow + "\n");
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error al registrar la operación en el archivo de log.");
            e.printStackTrace();
        }
    }
}