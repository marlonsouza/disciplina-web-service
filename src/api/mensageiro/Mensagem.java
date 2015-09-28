package api.mensageiro;

import api.cliente.Cliente;

import java.time.LocalDateTime;

/**
 * Created by root on 19/09/15.
 */
public class Mensagem {

    private static Integer maxId = 0;

    private Integer id;
    private String mensagem;
    private Cliente cliente;
    private LocalDateTime horario;

    public Mensagem(Integer id,String mensagem, Cliente cliente) {

        if(id==null){
            id = ++maxId;
        }

        this.id = id;
        this.mensagem = mensagem;
        this.cliente = cliente;
        this.horario = LocalDateTime.now();
    }

    public Mensagem(String mensagem, Cliente cliente){
        this.id = ++maxId;
        this.mensagem = mensagem;
        this.cliente = cliente;
        this.horario = LocalDateTime.now();
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

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }
}
