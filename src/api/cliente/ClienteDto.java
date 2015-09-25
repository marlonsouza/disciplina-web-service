package api.cliente;

import api.common.RepresentationBuilder;
import api.mensageiro.Mensagem;
import api.mensageiro.MensagemDto;
import com.google.common.base.Optional;
import com.google.common.collect.Collections2;

import javax.inject.Inject;
import javax.ws.rs.Produces;
import java.util.Collections;
import java.util.List;

/**
 * Created by root on 20/09/15.
 */
public class ClienteDto {

    private Integer id;
    private String nome;
    private List<MensagemDto> mensagens = Collections.emptyList();

    private void setId(Integer id) {
        this.id = id;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    private void setMensagens(List<MensagemDto> mensagens) {
        this.mensagens.clear();
        this.mensagens.addAll(mensagens);
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<MensagemDto> getMensagens() {
        return mensagens;
    }

    @Produces
    public static final class ClienteRepresentationBuilder implements RepresentationBuilder<Cliente, ClienteDto> {

        @Inject
        private MensagemDto.MensagemRepresentationBuilder mensagemRepresentation;

        @Override
        public Cliente fromRepresentation(ClienteDto dtoRepresentation) {
            return new Cliente(dtoRepresentation.getId(), dtoRepresentation.getNome(),
                    (List<Mensagem>)
                            Optional.fromNullable(dtoRepresentation.getMensagens())
                                    .transform(list ->
                                            Collections2.transform(
                                                    list, m -> mensagemRepresentation.fromRepresentation(m)
                                            ))
            );
        }

        @Override
        public ClienteDto toRepresentation(Cliente modelRepresentation) {
            return Builder.create()
                    .id(modelRepresentation.getId())
                    .nome(modelRepresentation.getNome())
                    .mensagens((List<MensagemDto>) Optional.fromNullable(modelRepresentation.getMensagens())
                                    .transform(list -> Collections2.transform(
                                                    list,
                                                    m -> mensagemRepresentation.toRepresentation(m)
                                            )
                                    ).orNull()
                    )
                    .build();
        }
    }

    public static class Builder {
        private ClienteDto entity;
        private static final Builder self = new Builder();

        public static Builder create() {
            return self.entity(new ClienteDto());
        }

        public static Builder from(ClienteDto cliente) {
            return self.entity(cliente);
        }

        private Builder entity(ClienteDto entity) {
            this.entity = entity;
            return this;
        }

        public Builder id(Integer id) {
            entity.setId(id);
            return this;
        }

        public Builder mensagens(List<MensagemDto> mensagens) {
            entity.setMensagens(mensagens);
            return this;
        }

        public Builder nome(String nome) {
            entity.setNome(nome);
            return this;
        }

        public ClienteDto build() {
            return entity;
        }
    }
}
