package api.mensageiro;

import api.cliente.Cliente;

/**
 * Created by root on 19/09/15.
 */
public class Mensagem {

    private static Integer maxId = 0;

    private Integer id;
    private String mensagem;
    private Cliente cliente;

    public Mensagem(Integer id,String mensagem, Cliente cliente) {

        if(id==null){
            id = ++maxId;
        }

        this.id = id;
        this.mensagem = mensagem;
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


}
