package api.mensageiro;

import api.cliente.ClienteDto;
import api.common.RepresentationBuilder;

import java.time.LocalDateTime;

/**
 * Created by root on 20/09/15.
 */
public class MensagemDto {

    private Integer id;
    private String mensagem;
    private ClienteDto cliente;
    private LocalDateTime horario;

    private void setId(Integer id) {
        this.id = id;
    }

    private void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    private void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public ClienteDto getCliente() {
        return cliente;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public static final class MensagemRepresentationBuilder implements RepresentationBuilder<Mensagem, MensagemDto>{

        private ClienteDto.ClienteRepresentationBuilder clienteRepresentationBuilder = new ClienteDto.ClienteRepresentationBuilder();

        @Override
        public Mensagem fromRepresentation(MensagemDto dtoRepresentation) {
            return new Mensagem(dtoRepresentation.getId(),dtoRepresentation.getMensagem(),

                    clienteRepresentationBuilder.fromRepresentation(dtoRepresentation.getCliente()));
        }

        @Override
        public MensagemDto toRepresentation(Mensagem modelRepresentation) {
            return MensagemDto.Builder.create()
                    .id(modelRepresentation.getId())
                    .mensagem(modelRepresentation.getMensagem())
                    .cliente(clienteRepresentationBuilder.toRepresentation(modelRepresentation.getCliente()))
                    .horario(modelRepresentation.getHorario())
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

        public Builder cliente(ClienteDto cliente){
            entity.setCliente(cliente);
            return this;
        }

        public Builder horario(LocalDateTime horario){
            entity.setHorario(horario);
            return this;
        }

        public MensagemDto build(){
            return entity;
        }
    }

}
