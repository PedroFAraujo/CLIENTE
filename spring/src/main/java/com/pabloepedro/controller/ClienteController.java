package com.pabloepedro.controller;

import com.pabloepedro.controller.adapter.ClienteControllerAdapter;
import com.pabloepedro.controller.dto.request.ClientRequest;
import com.pabloepedro.controller.dto.response.ClienteResponse;
import com.pabloepedro.entity.Cliente;
import com.pabloepedro.entity.Endereco;
import com.pabloepedro.integration.EnderecoIntegration;
import com.pabloepedro.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final EnderecoIntegration enderecoIntegration;

    public ClienteController(ClienteRepository clienteRepository,
                             EnderecoIntegration enderecoIntegration) {
        this.clienteRepository = clienteRepository;
        this.enderecoIntegration = enderecoIntegration;
    }

    @PostMapping("/cadastrar")
    public ClienteResponse salvar(@RequestBody ClientRequest request) {

        Endereco enderecoViaCep = enderecoIntegration.buscaCep(request.cep());

        if (enderecoViaCep == null) {
            throw new RuntimeException("CEP inválido");
        }

        Endereco enderecoCompleto = new Endereco(
                enderecoViaCep.cep(),
                enderecoViaCep.logradouro(),
                request.numero(), // aqui entra o número
                enderecoViaCep.bairro(),
                enderecoViaCep.cidade(),
                enderecoViaCep.uf()
        );

        Cliente cliente = new Cliente(
                UUID.randomUUID().toString(),
                request.nome(),
                enderecoCompleto,
                request.dtNascimento()
        );

        Cliente clienteSalvo = clienteRepository.salve(cliente);

        return ClienteControllerAdapter.castResponse(clienteSalvo);
    }
}