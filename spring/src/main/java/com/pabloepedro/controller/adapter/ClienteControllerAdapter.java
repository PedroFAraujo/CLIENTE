package com.pabloepedro.controller.adapter;

import com.pabloepedro.controller.dto.response.ClienteResponse;
import com.pabloepedro.controller.dto.response.EnderecoResponse;
import com.pabloepedro.entity.Cliente;

public class ClienteControllerAdapter {

    private ClienteControllerAdapter() {}

    public static ClienteResponse castResponse(Cliente cliente) {

        if (cliente == null) {
            return null;
        }

        EnderecoResponse enderecoResponse = null;

        if (cliente.endereco() != null) {
            enderecoResponse = new EnderecoResponse(
                    cliente.endereco().logradouro(),
                    cliente.endereco().numero(),
                    cliente.endereco().cidade(),
                    cliente.endereco().uf()
            );
        }

        return new ClienteResponse(
                cliente.id(),
                cliente.nome(),
                enderecoResponse,
                cliente.dtNascimento()
        );
    }
}