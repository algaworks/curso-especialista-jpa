package com.algaworks.eventos.model;

import java.util.Date;

public class Evento {

    private Integer id;

    private String nome;

    private Date data;

    public Evento() {}

    public Evento(Integer id, String nome, Date data) {
        this.id = id;
        this.nome = nome;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Evento other = (Evento) obj;

        if (id == null || other.id == null) {
            return false;
        }

        return id.equals(other.id);
    }
}
