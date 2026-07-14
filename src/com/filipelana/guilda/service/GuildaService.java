
package com.filipelana.guilda.service;

import com.filipelana.guilda.model.Aventureiro;

import com.filipelana.guilda.util.Entrada;


public class GuildaService {

    private Entrada entrada = new Entrada();
    private static final String[] classesValidas = {"Guerreiro", "Mago", "Ladino", "Clérigo"};
    private static final String[] ranksValidos = {"Ferro", "Cobre", "Bronze", "Prata", "Ouro", "Platina", "Diamante"};



    public Aventureiro cadastraAventureiro (Aventureiro[] aventureiros, int aventureiroAtual) {

        if (aventureiroAtual >= aventureiros.length) {

            System.out.println("Não é possível cadastrar novos aventureiros pois o limite foi atingido!");
            System.out.println();
            return null;

        }

        System.out.println("========= CADASTRO DE AVENTUREIRO =========");
        System.out.println();
        System.out.print("Digite o nome: ");
        String nome = entrada.tipoString();

        System.out.print("Digite a classe: ");
        String classe;
        while (true) {

            classe = entrada.tipoString();

            if (isValido(classe, classesValidas)) {
                break;

            } else
                System.out.print("Classe digitada inválida, digite novamente: ");

        }

        System.out.print("Digite um nível de 1 à 100: ");
        int nivel;
        while (true) {

            nivel = entrada.tipoInt();
            entrada.consomeEnter();

            if (isNivelValido(nivel)) {
                break;

            } else
                System.out.print("Nível digitado inválido, digite novamente: ");

        }

        System.out.print("Digite uma idade de no mínimo 16: ");
        int idade;
        while (true) {

            idade = entrada.tipoInt();
            entrada.consomeEnter();

            if (isIdadeValida(idade)) {
                break;

            } else
                System.out.print("Idade digitada inválida, digite novamente: ");

        }

        System.out.print("Digite o rank: ");
        String rank;
        while (true) {

            rank = entrada.tipoString();

            if (isValido(rank, ranksValidos)) {
                break;

            } else
                System.out.print("Rank digitado inválido, digite novamente: ");

        }

        System.out.println("\nNome do aventureiro: " + nome);
        System.out.println("Classe do aventureiro: " + classe);
        System.out.println("Nível do aventureiro: " + nivel);
        System.out.println("Idade do aventureiro: " + idade);
        System.out.println("Rank do aventureiro: " + rank);

        int opcaoEscolhaUsuario = confirmacao("cadastro de aventureiro");
        entrada.consomeEnter();

        opcaoEscolhaUsuario = isOpcaoValida(opcaoEscolhaUsuario, 1, 2);

        if (opcaoEscolhaUsuario == 1) {
            System.out.println("\nAventureiro cadastrado!");
            System.out.println();
            return new Aventureiro(nome, classe, nivel, idade, rank);

        } else
            return null;

    }

    public void listaAventureiros (Aventureiro[] aventureiros) {

        if (isListaVazia(aventureiros)) {

            System.out.println("A guilda está vazia, é necessário cadastrar aventureiros para prosseguir!");

            System.out.println();
            return;

        }

        System.out.println("========= LISTAGEM DE AVENTUREIROS =========");
        System.out.println();

        System.out.println("Escolha uma das opções à abaixo:");
        System.out.println("1 - Listar sem especificação");
        System.out.println("2 - Listar por classe");
        System.out.println("3 - Listar por nível");
        System.out.println("4 - Listar por idade");
        System.out.println("5 - Listar por rank");
        System.out.println("6 - Voltar ao menu");

        System.out.print("Digite sua opção de escolha aqui: ");
        int opcaoEscolhaUsuario = entrada.tipoInt();
        entrada.consomeEnter();

        opcaoEscolhaUsuario = isOpcaoValida(opcaoEscolhaUsuario, 1, 5);

        System.out.println();
        int ordenaLista;
        switch (opcaoEscolhaUsuario) {

            case 1:

                for (Aventureiro aventureiro : aventureiros) {
                    if (aventureiro != null)
                        System.out.println(aventureiro);

                }

                System.out.println();
                break;

            case 2:

                listaAventureirosClasse(aventureiros);
                break;

            case 3:

                ordenaLista = crescenteDecrescente();
                listaAventureirosNivel(aventureiros, ordenaLista);
                break;

            case 4:

                ordenaLista = crescenteDecrescente();
                listaAventureirosIdade(aventureiros, ordenaLista);
                break;

            case 5:

                listaAventureirosRank(aventureiros);
                break;

            case 6:

                break;

        }

    }

    public void buscaAventureiro (Aventureiro[] aventureiros) {

        if (isListaVazia(aventureiros)) {

            System.out.println("A guilda está vazia, é necessário cadastrar aventureiros para prosseguir!");

            System.out.println();
            return;

        }

        System.out.println("========= BUSCA DE AVENTUREIRO =========");
        System.out.println();

        System.out.print("Digite o nome do aventureiro aqui para buscar: ");
        String nomeAventureiroDigitado = entrada.tipoString();

        for (Aventureiro aventureiroAtual : aventureiros) {

            if (aventureiroAtual != null && aventureiroAtual.getNome().equalsIgnoreCase(nomeAventureiroDigitado)) {
                System.out.printf("O aventureiro de nome \"%s\" foi encontrado!\n", nomeAventureiroDigitado);
                System.out.println(aventureiroAtual);

                System.out.println();
                return;

            }

        }

        System.out.printf("O aventureiro de nome \"%s\" não foi encontrado!\n", nomeAventureiroDigitado);

    }

    public int editarAventureiroIndice (Aventureiro[] aventureiros) {

        System.out.println("========= EDIÇÃO DE AVENTUREIRO CADASTRADO =========");
        System.out.println();

        System.out.print("Digite o nome do aventureiro que deseja editar: ");
        String nomeAventureiroDigitado = entrada.tipoString();

        for (int i = 0; i < aventureiros.length; i++) {

            if (aventureiros[i] != null && aventureiros[i].getNome().equalsIgnoreCase(nomeAventureiroDigitado)) {

                return i;

            } else if (i == aventureiros.length - 1) {

                System.out.printf("Aventureiro de nome \"%s\" não foi encontrado!", nomeAventureiroDigitado);
                System.out.println();
                return -1;

            }

        }

        return -1;

    }

    public Aventureiro editarAventureiro (Aventureiro aventureiro) {

        System.out.println("Escolha uma das opções abaixo para editar sobre o aventureiro " + aventureiro.getNome() + ":");
        System.out.println("1 - Nome");
        System.out.println("2 - Classe");
        System.out.println("3 - Nível");
        System.out.println("4 - Idade");
        System.out.println("5 - Rank");
        System.out.println("6 - Voltar para o menu");

        System.out.print("Digite sua opção de escolha aqui: ");
        int opcaoEscolhaUsuario = entrada.tipoInt();
        entrada.consomeEnter();

        opcaoEscolhaUsuario = isOpcaoValida(opcaoEscolhaUsuario, 1, 6);

        switch (opcaoEscolhaUsuario) {

            case 1:

                System.out.println("Você escolheu alterar o nome do aventureiro " + aventureiro.getNome());
                System.out.print("Digite o novo nome aqui: ");
                String nomeDigitado = entrada.tipoString();

                System.out.println("Nome do aventureiro: " + nomeDigitado);
                System.out.println("Classe: " + aventureiro.getClasse());
                System.out.println("Nível: " + aventureiro.getNivel());
                System.out.println("Idade: " + aventureiro.getIdade());
                System.out.println("Rank: " + aventureiro.getRank());

                opcaoEscolhaUsuario = confirmacao("alteração do nome");
                entrada.consomeEnter();
                opcaoEscolhaUsuario = isOpcaoValida(opcaoEscolhaUsuario, 1, 2);

                if (opcaoEscolhaUsuario == 1){

                    aventureiro.setNome(nomeDigitado);
                    System.out.println("O nome do aventureiro foi alterado!\n");

                }

                break;

            case 2:

                System.out.println("Você escolheu alterar a classe do aventureiro " + aventureiro.getNome());
                System.out.println("Escolha uma das classes disponíveis abaixo:");
                System.out.println("Classe \"Guerreiro\"");
                System.out.println("Classe \"Mago\"");
                System.out.println("Classe \"Ladino\"");
                System.out.println("Classe \"Clérigo\"");

                System.out.print("Digite a nova classe aqui: ");
                String classeDigitada;
                while (true) {

                    classeDigitada = entrada.tipoString();

                    if (isValido(classeDigitada, classesValidas)) {
                        break;

                    } else
                        System.out.print("Classe digitada inválida, digite novamente: ");

                }

                System.out.println("\nNome do aventureiro: " + aventureiro.getNome());
                System.out.println("Classe: " + classeDigitada);
                System.out.println("Nível: " + aventureiro.getNivel());
                System.out.println("Idade: " + aventureiro.getIdade());
                System.out.println("Rank: " + aventureiro.getRank());

                opcaoEscolhaUsuario = confirmacao("alteração da classe");
                entrada.consomeEnter();

                opcaoEscolhaUsuario = isOpcaoValida(opcaoEscolhaUsuario, 1, 2);

                if (opcaoEscolhaUsuario == 1){

                    aventureiro.setClasse(classeDigitada);
                    System.out.println("A classe do aventureiro foi alterada!\n");

                }

                break;

            case 3:

                System.out.println("Você escolheu alterar o nível do aventureiro " + aventureiro.getNome());
                System.out.print("Digite um novo nível de 1 à 100 aqui: ");
                int nivelDigitado;
                while (true) {

                    nivelDigitado = entrada.tipoInt();
                    entrada.consomeEnter();

                    if (isNivelValido(nivelDigitado)) {
                        break;

                    } else
                        System.out.print("Nível digitado inválido, digite novamente: ");

                }

                System.out.println("\nNome do aventureiro: " + aventureiro.getNome());
                System.out.println("Classe: " + aventureiro.getClasse());
                System.out.println("Nível: " + nivelDigitado);
                System.out.println("Idade: " + aventureiro.getIdade());
                System.out.println("Rank: " + aventureiro.getRank());

                opcaoEscolhaUsuario = confirmacao("alteração do nível");
                entrada.consomeEnter();

                opcaoEscolhaUsuario = isOpcaoValida(opcaoEscolhaUsuario, 1, 2);

                if (opcaoEscolhaUsuario == 1){

                    aventureiro.setNivel(nivelDigitado);
                    System.out.println("O nível do aventureiro foi alterado!\n");

                }

                break;

            case 4:

                System.out.println("Você escolheu alterar a idade do aventureiro " + aventureiro.getNome());
                System.out.print("Digite uma nova idade aqui: ");
                int idadeDigitada;
                while (true) {

                    idadeDigitada = entrada.tipoInt();
                    entrada.consomeEnter();

                    if (isIdadeValida(idadeDigitada)) {
                        break;

                    } else
                        System.out.print("Idade digitada inválida, digite novamente: ");

                }

                System.out.println("\nNome do aventureiro: " + aventureiro.getNome());
                System.out.println("Classe: " + aventureiro.getClasse());
                System.out.println("Nível: " + aventureiro.getNivel());
                System.out.println("Idade: " + idadeDigitada);
                System.out.println("Rank: " + aventureiro.getRank());

                opcaoEscolhaUsuario = confirmacao("alteração da idade");
                entrada.consomeEnter();

                opcaoEscolhaUsuario = isOpcaoValida(opcaoEscolhaUsuario, 1, 2);

                if (opcaoEscolhaUsuario == 1){

                    aventureiro.setIdade(idadeDigitada);
                    System.out.println("A idade do aventureiro foi alterada!\n");

                }

                break;

            case 5:

                System.out.println("Você escolheu alterar o rank do aventureiro " + aventureiro.getNome());
                System.out.println("Escolha um dos ranks disponíveis abaixo:");
                System.out.println("Rank \"Ferro\"");
                System.out.println("Rank \"Cobre\"");
                System.out.println("Rank \"Bronze\"");
                System.out.println("Rank \"Prata\"");
                System.out.println("Rank \"Ouro\"");
                System.out.println("Rank \"Platina\"");
                System.out.println("Rank \"Diamante\"");

                System.out.print("Digite o novo rank aqui: ");
                String rankDigitado;
                while (true) {

                    rankDigitado = entrada.tipoString();

                    if (isValido(rankDigitado, ranksValidos)) {
                        break;

                    } else
                        System.out.print("Rank digitado inválido, digite novamente: ");

                }

                System.out.println("\nNome do aventureiro: " + aventureiro.getNome());
                System.out.println("Classe: " + aventureiro.getClasse());
                System.out.println("Nível: " + aventureiro.getNivel());
                System.out.println("Idade: " + aventureiro.getIdade());
                System.out.println("Rank: " + rankDigitado);

                opcaoEscolhaUsuario = confirmacao("alteração do rank");
                entrada.consomeEnter();

                opcaoEscolhaUsuario = isOpcaoValida(opcaoEscolhaUsuario, 1, 2);

                if (opcaoEscolhaUsuario == 1){

                    aventureiro.setRank(rankDigitado);
                    System.out.println("O rank do aventureiro foi alterado!\n");

                }

                break;

            case 6:

                break;

        }

        return aventureiro;

    }

    public int removerAventureiroIndice (Aventureiro[] aventureiros) {

        System.out.println("========= REMOCÇÃO DE AVENTUREIRO CADASTRADO =========");
        System.out.println();

        System.out.print("Digite o nome do aventureiro que deseja excluir: ");
        String nomeAventureiroDigitado = entrada.tipoString();

        for (int i = 0; i < aventureiros.length; i++) {

            if (aventureiros[i] != null && aventureiros[i].getNome().equalsIgnoreCase(nomeAventureiroDigitado)) {

                return i;

            } else if (i == aventureiros.length - 1) {

                System.out.printf("Aventureiro de nome \"%s\" não foi encontrado!", nomeAventureiroDigitado);
                System.out.println();
                return -1;

            }

        }

        return -1;

    }

    public Aventureiro removerAventureiro () {

        System.out.println("O aventureiro foi excluído!\n");
        return null;

    }






    private void listaAventureirosClasse (Aventureiro[] aventureiros) {

        System.out.println("========= LISTAGEM DE AVENTUREIROS POR CLASSE =========");
        System.out.println();

        System.out.println("Escolha uma das opções à abaixo:");
        System.out.println("1 - Listar aventureiros de classe \"Guerreiro\"");
        System.out.println("2 - Listar aventureiros de classe \"Mago\"");
        System.out.println("3 - Listar aventureiros de classe \"Ladino\"");
        System.out.println("4 - Listar aventureiros de classe \"Clérigo\"");
        System.out.println("5 - Voltar ao menu");

        System.out.print("Digite sua opção de escolha aqui: ");
        int opcaoEscolha = entrada.tipoInt();
        entrada.consomeEnter();

        opcaoEscolha = isOpcaoValida(opcaoEscolha, 1, 5);

        if (opcaoEscolha <= 7) {

            System.out.println("Listagem de aventureiros de classe " + classesValidas[opcaoEscolha - 1] + ":");
            for (Aventureiro aventureiroAtual : aventureiros) {

                if (aventureiroAtual != null && aventureiroAtual.getClasse().equalsIgnoreCase(classesValidas[opcaoEscolha - 1]))
                    System.out.println(aventureiroAtual);

            }
            System.out.println();

        } else
            System.out.println();

    }

    private void listaAventureirosNivel (Aventureiro[] aventureiros, int ordenaLista) {

        Aventureiro[] aventureirosNivel = new Aventureiro[aventureiros.length];

        for (int i = 0; i < aventureiros.length; i++) {

            if (aventureiros[i] != null)
                aventureirosNivel[i] = aventureiros[i];

        }

        if (ordenaLista == 1) {

            for (int i = 0; i < aventureirosNivel.length; i++) {

                if (aventureiros[i] != null)
                    for (int j = 0; j < aventureirosNivel.length; j++) {

                        if (aventureiros[j] != null && (aventureirosNivel[i].getNivel() > aventureirosNivel[j].getNivel())) {

                            int aux = aventureirosNivel[i].getNivel();
                            aventureirosNivel[i].setNivel(aventureirosNivel[j].getNivel());
                            aventureirosNivel[j].setNivel(aux);

                        }

                    }

            }
            System.out.println();

        } else if (ordenaLista == 2) {

            for (int i = 0; i < aventureirosNivel.length; i++) {

                if (aventureiros[i] != null)
                    for (int j = 0; j < aventureirosNivel.length; j++) {

                        if (aventureiros[j] != null && (aventureirosNivel[i].getNivel() < aventureirosNivel[j].getNivel())) {

                            int aux = aventureirosNivel[i].getNivel();
                            aventureirosNivel[i].setNivel(aventureirosNivel[j].getNivel());
                            aventureirosNivel[j].setNivel(aux);

                        }

                    }

            }
            System.out.println();

        }

    }

    private void listaAventureirosIdade (Aventureiro[] aventureiros, int ordenaLista) {

        Aventureiro[] aventureirosIdade = new Aventureiro[aventureiros.length];

        for (int i = 0; i < aventureiros.length; i++) {

            if (aventureiros[i] != null)
                aventureirosIdade[i] = aventureiros[i];

        }

        if (ordenaLista == 1) {

            for (int i = 0; i < aventureirosIdade.length; i++) {

                if (aventureiros[i] != null)
                    for (int j = 0; j < aventureirosIdade.length; j++) {

                        if (aventureiros[j] != null && (aventureirosIdade[i].getIdade() > aventureirosIdade[j].getIdade())) {

                            int aux = aventureirosIdade[i].getIdade();
                            aventureirosIdade[i].setIdade(aventureirosIdade[j].getIdade());
                            aventureirosIdade[j].setIdade(aux);

                        }

                    }

            }
            System.out.println();

        } else if (ordenaLista == 2) {

            for (int i = 0; i < aventureirosIdade.length; i++) {

                if (aventureiros[i] != null)
                    for (int j = 0; j < aventureirosIdade.length; j++) {

                        if (aventureiros[j] != null && (aventureirosIdade[i].getIdade() < aventureirosIdade[j].getIdade())) {

                            int aux = aventureirosIdade[i].getIdade();
                            aventureirosIdade[i].setIdade(aventureirosIdade[j].getIdade());
                            aventureirosIdade[j].setIdade(aux);

                        }

                    }

            }
            System.out.println();

        }

        for (int i = 0; i < aventureirosIdade.length; i++) {

            if (aventureirosIdade[i] != null)
                System.out.println(aventureirosIdade[i]);

        }
        System.out.println();

    }

    private void listaAventureirosRank (Aventureiro[] aventureiros) {

        System.out.println("========= LISTAGEM DE AVENTUREIROS POR RANK =========");
        System.out.println();

        System.out.println("Escolha uma das opções à abaixo:");
        System.out.println("1 - Listar aventureiros de rank \"Ferro\"");
        System.out.println("2 - Listar aventureiros de rank \"Cobre\"");
        System.out.println("3 - Listar aventureiros de rank \"Bronze\"");
        System.out.println("4 - Listar aventureiros de rank \"Prata\"");
        System.out.println("5 - Listar aventureiros de rank \"Ouro\"");
        System.out.println("6 - Listar aventureiros de rank \"Platina\"");
        System.out.println("7 - Listar aventureiros de rank \"Diamante\"");
        System.out.println("8 - Voltar ao menu");

        System.out.print("Digite sua opção de escolha aqui: ");
        int opcaoEscolha = entrada.tipoInt();
        entrada.consomeEnter();

        opcaoEscolha = isOpcaoValida(opcaoEscolha, 1, 8);

        if (opcaoEscolha <= 7) {

            for (Aventureiro aventureiroAtual : aventureiros) {

                if (aventureiroAtual != null && aventureiroAtual.getRank().equalsIgnoreCase(ranksValidos[opcaoEscolha - 1]))
                    System.out.println(aventureiroAtual);

            }
            System.out.println();

        } else
            System.out.println();

    }



    private int crescenteDecrescente () {

        System.out.println("Escolha uma das opções de ordenação abaixo:");
        System.out.println("1 - Visualizar lista de forma crescente");
        System.out.println("2 - Visualizar lista de forma decrescente");

        System.out.print("Digite sua opção de escolha aqui: ");
        int opcaoEscolha = entrada.tipoInt();
        entrada.consomeEnter();

        opcaoEscolha = isOpcaoValida(opcaoEscolha, 1, 2);

        return opcaoEscolha;

    }

    private int confirmacao (String acao) {

        System.out.println("Deseja confirmar " + acao + "?");
        System.out.println("1 - Sim");
        System.out.println("2 - Não, voltar ao menu");
        System.out.print("Digite sua opção de escolha aqui: ");
        return entrada.tipoInt();

    }

    public boolean isListaVazia (Aventureiro[] aventureiros) {

        int aventureirosQuantidade = 0;

        for (Aventureiro aventureiroAtual : aventureiros) {

            if (aventureiroAtual != null)
                aventureirosQuantidade++;

        }

        return aventureirosQuantidade == 0;

    }

    private boolean isValido (String valorDigitado, String[] valoresValidos) {

        for (String valorValido : valoresValidos) {

            if (valorDigitado.equalsIgnoreCase(valorValido))
                return true;

        }

        return false;

    }

    private boolean isNivelValido (int nivel) {

        return nivel >= 1 && nivel <= 100;

    }

    private boolean isIdadeValida (int idade) {

        return idade >= 16;

    }

    private int isOpcaoValida (int opcaoEscolhaUsuario, int primeiraOpcao, int ultimaOpcao) {

        if (opcaoEscolhaUsuario >= primeiraOpcao && opcaoEscolhaUsuario <= ultimaOpcao)
            return opcaoEscolhaUsuario;

        System.out.printf("A opção \"%d\" digitada é inválida, digite novamente: ", opcaoEscolhaUsuario);
        opcaoEscolhaUsuario = entrada.tipoInt();
        entrada.consomeEnter();

        return isOpcaoValida(opcaoEscolhaUsuario, primeiraOpcao, ultimaOpcao);

    }

}
