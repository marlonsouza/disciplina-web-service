package api.cliente;

import api.common.RepresentationBuilder;
import api.mensageiro.MensagemDto;
import com.google.common.base.Optional;
import com.google.common.collect.Collections2;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by root on 20/09/15.
 */
public class ClienteDto {

    private Integer id;
    private String nome;

    private void setId(Integer id) {
        this.id = id;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static final class ClienteRepresentationBuilder implements RepresentationBuilder<Cliente, ClienteDto> {

        @Override
        public Cliente fromRepresentation(ClienteDto dtoRepresentation) {
            return new Cliente(dtoRepresentation.getId(), dtoRepresentation.getNome());
        }

        @Override
        public ClienteDto toRepresentation(Cliente modelRepresentation) {
            return Builder.create()
                    .id(modelRepresentation.getId())
                    .nome(modelRepresentation.getNome())
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

        public Builder nome(String nome) {
            entity.setNome(nome);
            return this;
        }

        public ClienteDto build() {
            return entity;
        }
    }
}
