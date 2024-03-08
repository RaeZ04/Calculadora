package com.example;

public class Main {
    public static void main(String[] args) {

        boolean ejecucion = true;

        do {

            System.out.println("CUENTA");
            System.out.println("");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesion");
            System.out.println("3. Salir");
            System.out.println("");
            System.out.print("Elige una opcion: ");

            int eleccion = io.leerInt();

            if (eleccion == 1) {

                calculadora.registro();

            } else if (eleccion == 2) {

                boolean login = true;

                calculadora.login(login);

                boolean salir = false;

                if (login == true) {

                    do {

                        System.out.println("");
                        System.out.println("CALCULADORA");
                        System.out.println("");
                        System.out.println("1. Suma");
                        System.out.println("2. Resta");
                        System.out.println("3. Multiplicacion");
                        System.out.println("4. Division");
                        System.out.println("5. Potencia");
                        System.out.println("6. Raiz cuadrada");
                        System.out.println("7. Volver atrás");
                        System.out.println("8. Salir");
                        System.out.println("");
                        System.out.print("Elige una opcion: ");

                        int eleccion2 = io.leerInt();

                        if (eleccion2 == 1) {

                            calculadora.suma();

                        } else if (eleccion2 == 2) {

                            calculadora.resta();

                        } else if (eleccion2 == 3) {

                            calculadora.multiplicacion();

                        } else if (eleccion2 == 4) {

                            calculadora.division();

                        } else if (eleccion2 == 5) {

                            calculadora.potencia();

                        } else if (eleccion2 == 6) {

                            calculadora.raizCuadrada();

                        } else if (eleccion2 == 7) {

                            salir = true;

                        } else if (eleccion2 == 8) {

                            System.out.println("Has salido del programa.");
                            System.exit(0);

                        } else {
                            System.out.println("Opcion no valida");
                        }

                    } while (salir == false);

                } else {
                    System.out.println("Usuario o contraseña incorrectos");
                }

            } else if (eleccion == 3) {

                System.out.println("Has salido del programa.");
                ejecucion = false;

            }

            else {
                System.out.println("Opcion no valida, ingrese del 1-3.");
            }

        } while (ejecucion == true);

    }
}