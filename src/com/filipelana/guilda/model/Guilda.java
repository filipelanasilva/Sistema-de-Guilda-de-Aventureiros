
package com.filipelana.guilda.model;

import com.filipelana.guilda.service.GuildaService;


public class Guilda {

    private Aventureiro[] aventureiros = new Aventureiro[100];
    private int aventureiroAtual = 0;
    private GuildaService guildaService = new GuildaService();



    public void cadastraNovoAventureiro() {

        this.aventureiros[aventureiroAtual] = guildaService.cadastraAventureiro(aventureiros, aventureiroAtual);
        aventureiroAtual++;

    }

    public void listaAventureiros () {

        guildaService.listaAventureiros(aventureiros);

    }

    public void buscaAventureiro () {

        guildaService.buscaAventureiro(aventureiros);

    }

    public void editarAventureiro () {

        if (guildaService.isListaVazia(aventureiros)) {

            System.out.println("A guilda está vazia, é necessário cadastrar aventureiros para prosseguir!");
            System.out.println();
            return;

        }

        int indice = guildaService.editarAventureiroIndice(aventureiros);
        if (indice == -1)
            return;

        this.aventureiros[indice] = guildaService.editarAventureiro(aventureiros[indice]);

    }

    public void removerAventureiro () {

        if (guildaService.isListaVazia(aventureiros)) {

            System.out.println("A guilda está vazia, é necessário cadastrar aventureiros para prosseguir!");
            System.out.println();
            return;

        }

        int indice = guildaService.removerAventureiroIndice(aventureiros);
        if (indice == -1)
            return;

        this.aventureiros[indice] = guildaService.removerAventureiro();

    }

}
