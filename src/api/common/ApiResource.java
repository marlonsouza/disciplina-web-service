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
        Cliente cliente = Mensageiro.getCliente(mensagem.getCliente().getId());
        Mensagem entity = mensagemRepresentation.fromRepresentation(mensagem);

        cliente.gravaMensagem(entity);

        return Response.ok(mensagemRepresentation.toRepresentation(entity)).build();
    }

    @PUT
    @Path("mensagem/{id}")
    public Response updateMessage(@PathParam("id") Integer id, MensagemDto mensagem) {
        Cliente cliente = Mensageiro.getCliente(id);
        cliente.gravaMensagem(mensagemRepresentation.fromRepresentation(mensagem));

        return Response.accepted().build();
    }

    @GET
    @Path("cliente/{id}/mensagens")
    public Response listMensagens(@PathParam("id") Integer id) {
        Cliente cliente = Mensageiro.getCliente(id);

        List<MensagemDto> mensagens = (List<MensagemDto>) Collections2.transform(cliente.getMensagens(),
                m -> mensagemRepresentation.toRepresentation(m)
        );

        return Response.ok(mensagens).build();
    }

    @GET
    @Path("cliente/{id}")
    public Response getCliente(@PathParam("id") Integer id) {
        Cliente cliente = Mensageiro.getCliente(id);

        return Response.ok().entity(clienteRepresentation.toRepresentation(cliente)).build();
    }

    @DELETE
    @Path("cliente/{id}")
    public Response removeCliente(@PathParam("id") Integer id) {
        Mensageiro.deleteCliente(id);

        return Response.accepted().build();
    }

    @POST
    @Path("cliente")
    public Response novoCliente(ClienteDto cliente) {

        Cliente entity = clienteRepresentation.fromRepresentation(cliente);

        Mensageiro.gravaCliente(entity);

        return Response.ok(clienteRepresentation.toRepresentation(entity)).build();
    }

    @GET
    @Path("clientes")
    public Response listAllClientes() {
        return Response.ok(Mensageiro.clientes).build();
    }

}
