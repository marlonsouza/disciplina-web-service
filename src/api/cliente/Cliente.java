package api.cliente;

import api.mensageiro.Mensagem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Created by root on 19/09/15.
 */
public class Cliente {
    private static Integer maxId = 0;

    private Integer id;
    private String nome;

    public Cliente(Integer id,String nome) {
        if(id==null){
            id = ++maxId;
        }

        this.id = id;
        this.nome = nome;
    }

    public Cliente(String nome){
        this.id = ++maxId;
        this.nome = nome;
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

}
