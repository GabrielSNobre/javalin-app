package com.proj.model;

public class UserModel {
    private String nome;
    private String email;

    public UserModel(String nome, String email){
        this.nome = nome;
        this.email = email;
    }

    public UserModel(){}

    public String getEmail(){
        return this.email;
    }

    public String getNome(){
        return this.nome;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String toString(){
        return "{\"nome\": \"" + this.nome + "\", \"email\": \""+ this.email+"\"} " ;
    }

}
