package api.common;

import api.cliente.Cliente;
import api.cliente.ClienteDto;
import api.mensageiro.Mensageiro;
import api.mensageiro.Mensagem;
import api.mensageiro.MensagemDto;
import com.google.common.collect.Collections2;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ApiResource {

    @Inject
    private ClienteDto.ClienteRepresentationBuilder clienteRepresentation;

    @Inject
    private MensagemDto.MensagemRepresentationBuilder mensagemRepresentation;

    @POST
    @Path("mensagem")
    public Response addMessage(MensagemDto mensagem) {
        Mensagem entity = mensagemRepresentation.fromRepresentation(mensagem);

        Mensageiro.gravaMensagem(entity);

        return Response.ok(mensagemRepresentation.toRepresentation(entity)).build();
    }

    @GET
    @Path("mensagens")
    public Response listMensagens() {
        List<MensagemDto> mensagens = (List<MensagemDto>) Collections2.transform(Mensageiro.mensagens,
                m -> mensagemRepresentation.toRepresentation(m)
        );

        return Response.ok(mensagens).build();
    }

    @DELETE
    @Path("cliente/{id}")
    public Response removeCliente(@PathParam("id") Integer id) {
        Mensagem mensagem = Mensageiro.deleteCliente(id);

        return Response.ok(mensagemRepresentation.toRepresentation(mensagem)).build();
    }

    @POST
    @Path("cliente")
    public Response novoCliente(ClienteDto cliente) {

        Cliente entity = clienteRepresentation.fromRepresentation(cliente);

        Mensagem mensagem = Mensageiro.gravaCliente(entity);

        return Response.ok(mensagemRepresentation.toRepresentation(mensagem)).build();
    }

    @GET
    @Path("clientes")
    public Response listAllClientes() {
        return Response.ok(Mensageiro.clientes).build();
    }

    @OPTIONS
    @Path("cliente")
    public Response getDefaultCliente(){
        return Response.ok(ClienteDto.Builder.create().build()).build();
    }
}
