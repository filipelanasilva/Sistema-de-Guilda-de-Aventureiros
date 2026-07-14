
package com.filipelana.guilda.model;


public class Aventureiro {

    private String nome;
    private String classe;
    private int nivel;
    private int idade;
    private String rank;



    public Aventureiro (String nome, String classe, int nivel, int idade, String rank) {

        this.nome = nome;
        this.classe = classe;
        this.nivel = nivel;
        this.idade = idade;
        this.rank = rank;

    }

    @Override
    public String toString() {

        return "Nome do aventureiro: " + nome + "\nClasse: " + classe + "\nNível: " +
                nivel + "\nIdade: " + idade + " anos" + "\nRank: " + rank;

    }

    public String getNome() {

        return nome;

    }

    public void setNome (String nome) {

        this.nome = nome;

    }

    public String getClasse() {

        return classe;

    }

    public void setClasse (String classe) {

        this.classe = classe;

    }

    public int getNivel() {

        return nivel;

    }

    public void setNivel (int nivel) {

        this.nivel = nivel;

    }

    public int getIdade() {

        return idade;

    }

    public void setIdade (int idade) {

        this.idade = idade;

    }

    public String getRank() {

        return rank;

    }

    public void setRank (String rank) {

        this.rank = rank;

    }

}
