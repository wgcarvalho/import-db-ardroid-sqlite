package com.example.newimpotdb.modelo;

/**
 * Created by williangcarv on 22/08/17.
 */

public class Pessoa {

    private int idPessoa;
    private String nomePessoa;

    public Pessoa() {
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    @Override
    public String toString() {
        return  nomePessoa;
    }
}
