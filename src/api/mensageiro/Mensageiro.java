package api.mensageiro;

import api.cliente.Cliente;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by root on 19/09/15.
 */
public class Mensageiro {

    public static final List<Cliente> clientes = new ArrayList<>();
    public static final List<Mensagem> mensagens = new ArrayList<>();

    public static void gravaMensagem(Mensagem mensagem){
        if(mensagem == null){
            throw new IllegalArgumentException("Parametros nulos em gravaMensagem");
        }

        mensagens.add(mensagem);

    }

    public static Mensagem deleteCliente(Integer id){
        Mensagem mensagem = null;
        Predicate<Cliente> predicateCliente= cli -> cli.getId().equals(id);

        Cliente clienteExitente = clientes.stream().filter(predicateCliente).findFirst().get();

        if(id!=null){
            clientes.removeIf(predicateCliente);
            mensagem = new Mensagem("Cliente "+clienteExitente.getNome()+" desconectou", clienteExitente);
            mensagens.add(mensagem);
        }

        return mensagem;
    }

    public static Mensagem gravaCliente(Cliente cliente){
        Mensagem mensagem = null;
        if(cliente!=null){
            clientes.add(cliente);
            mensagem = new Mensagem("Cliente "+String.valueOf(cliente.getNome()+" conectado"), cliente);
            mensagens.add(mensagem);
        }

        return mensagem;
    }

    public static Cliente getCliente(Integer id){
        return clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .get();
    }
}
