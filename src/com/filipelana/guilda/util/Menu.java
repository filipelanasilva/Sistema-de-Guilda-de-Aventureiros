
package com.filipelana.guilda.util;

import com.filipelana.guilda.model.Guilda;


public class Menu {

    private static final Guilda guilda = new Guilda();
    private static final Entrada entrada = new Entrada();



    public Menu () {

        do {

            switch (exibeMenu()) {

                case 1:

                    guilda.cadastraNovoAventureiro();
                    break;

                case 2:

                    guilda.listaAventureiros();
                    break;

                case 3:

                    guilda.buscaAventureiro();
                    break;

                case 4:

                    guilda.editarAventureiro();
                    break;

                case 5:

                    guilda.removerAventureiro();
                    break;

                case 6:

                    entrada.fechar();
                    return;

            }

        } while (true);

    }

    private int exibeMenu () {

        System.out.println("========= GUILDA =========");
        System.out.println();
        System.out.println("1 - Cadastrar aventureiro");
        System.out.println("2 - Listar aventureiros");
        System.out.println("3 - Buscar aventureiro");
        System.out.println("4 - Editar aventureiro");
        System.out.println("5 - Remover aventureiro");
        System.out.println("6 - Sair");

        System.out.print("Digite sua opção de escolha aqui: ");
        int opcaoEscolhaUsuario;
        while (true) {

            opcaoEscolhaUsuario = entrada.tipoInt();
            entrada.consomeEnter();

            if (opcaoEscolhaUsuario >= 1 && opcaoEscolhaUsuario <= 6) {

                System.out.println();

                return opcaoEscolhaUsuario;

            } else
                System.out.printf("A opção \"%d\" digitada é inválida, digite novamente: ", opcaoEscolhaUsuario);

        }

    }

}
