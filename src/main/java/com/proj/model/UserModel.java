package com.proj.model;

public class UserModel {
    private int id;
    private String nome;
    private String email;

    public UserModel(int id, String nome, String email){
        this.id = id;
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

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String toString(){
        return "{\"id\": \""+ this.id +"\",\"nome\": \"" + this.nome +
"\",\"email\": \""+ this.email+"\"} " ;
    }

}
