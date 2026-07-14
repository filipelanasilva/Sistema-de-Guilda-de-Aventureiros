
package com.filipelana.guilda.util;

import java.util.Scanner;


public class Entrada {

    private Scanner entrada = new Scanner(System.in);



    public String tipoString () {

        return entrada.nextLine();

    }

    public int tipoInt () {

        return entrada.nextInt();

    }

    public String consomeEnter () {

        return entrada.nextLine();

    }

    public void fechar () {

        entrada.close();

    }

}
