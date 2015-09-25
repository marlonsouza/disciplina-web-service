package api.mensageiro;

import api.cliente.Cliente;
import api.common.RepresentationBuilder;

import javax.ws.rs.Produces;

/**
 * Created by root on 20/09/15.
 */
public class MensagemDto {

    private Integer id;
    private String mensagem;
    private Cliente cliente;

    private void setId(Integer id) {
        this.id = id;
    }

    private void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    private void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Produces
    public static final class MensagemRepresentationBuilder implements RepresentationBuilder<Mensagem, MensagemDto>{

        @Override
        public Mensagem fromRepresentation(MensagemDto dtoRepresentation) {
            return new Mensagem(dtoRepresentation.getId(),dtoRepresentation.getMensagem(), dtoRepresentation.getCliente());
        }

        @Override
        public MensagemDto toRepresentation(Mensagem modelRepresentation) {
            return MensagemDto.Builder.create()
                    .id(modelRepresentation.getId())
                    .mensagem(modelRepresentation.getMensagem())
                    .cliente(modelRepresentation.getCliente())
                    .build();
        }
    }

    public static class Builder{
        private MensagemDto entity;

        private Builder(MensagemDto entity){
            this.entity = entity;
        }

        public static Builder create(){
            return new Builder(new MensagemDto());
        }

        public static Builder from(MensagemDto entity){
            return new Builder(entity);
        }

        public Builder id(Integer id){
            entity.setId(id);
            return this;
        }

        public Builder mensagem(String mensagem){
            entity.setMensagem(mensagem);
            return this;
        }

        public Builder cliente(Cliente cliente){
            entity.setCliente(cliente);
            return this;
        }

        public MensagemDto build(){
            return entity;
        }
    }

}
