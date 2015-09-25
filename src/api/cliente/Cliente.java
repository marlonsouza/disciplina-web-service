package api.cliente;

import api.mensageiro.Mensagem;

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
    private List<Mensagem> mensagens = Collections.emptyList();

    public Cliente(Integer id,String nome, List<Mensagem> mensagens) {
        if(id==null){
            id = ++maxId;
        }

        this.id = id;
        this.mensagens.clear();
        this.mensagens.addAll(mensagens);
    }

    public List<Mensagem> getMensagens() {
        return mensagens;
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

    public void gravaMensagem(Mensagem mensagem){

        Consumer<Mensagem> action = new Consumer<Mensagem>() {
            @Override
            public void accept(Mensagem mensagem) {
                mensagens.removeIf(m -> m.getId() == mensagem.getId());
                mensagens.add(mensagem);
            }
        };

        Optional.ofNullable(mensagem)
                .ifPresent(action);


    }
}
