package api.mensageiro;

import api.cliente.Cliente;

import java.util.*;

/**
 * Created by root on 19/09/15.
 */
public class Mensageiro {

    public static final List<Cliente> clientes = new ArrayList<>();

    public void gravaMensagem(Cliente cliente, Mensagem mensagem){
        if(mensagem == null || cliente == null){
            throw new IllegalArgumentException("Parametros nulos em gravaMensagem");
        }

        cliente.gravaMensagem(mensagem);

    }

    public static void deleteCliente(Integer id){
        if(id!=null){
            clientes.removeIf(cli -> cli.getId()==id);
        }
    }

    public static void gravaCliente(Cliente cliente){
        if(cliente!=null){
            clientes.add(cliente);
        }
    }

    public static Cliente getCliente(Integer id){
        return clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .get();
    }
}
