package com.pabloepedro.controller;

import com.pabloepedro.controller.adapter.ClienteControllerAdapter;
import com.pabloepedro.controller.dto.request.ClientRequest;
import com.pabloepedro.controller.dto.response.ClienteResponse;
import com.pabloepedro.entity.Cliente;
import com.pabloepedro.entity.Endereco;
import com.pabloepedro.integration.EnderecoIntegration;
import com.pabloepedro.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final EnderecoIntegration enderecoIntegration;

    public ClienteController(ClienteRepository clienteRepository,
                             EnderecoIntegration enderecoIntegration) {
        this.clienteRepository = clienteRepository;
        this.enderecoIntegration = enderecoIntegration;
    }

    @PostMapping
    public ClienteResponse salvar(@RequestBody ClientRequest request) {

        Endereco enderecoViaCep = enderecoIntegration.buscaCep(request.cep());

        Endereco enderecoCompleto = new Endereco(
                enderecoViaCep.cep(),
                enderecoViaCep.logradouro(),
                request.numero(),
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

    @GetMapping("/{id}")
    public ClienteResponse buscarPorId(@PathVariable String id) {
        Cliente cliente = clienteRepository.buscarPorId(id);

        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado");
        }

        return ClienteControllerAdapter.castResponse(cliente);
    }

    @GetMapping
    public List<ClienteResponse> listar() {
        return clienteRepository.listar()
                .stream()
                .filter(java.util.Objects::nonNull)
                .map(ClienteControllerAdapter::castResponse)
                .toList();
    }

    @PutMapping("/{id}")
    public ClienteResponse atualizar(@PathVariable String id,
                                     @RequestBody ClientRequest request) {

        Cliente existente = clienteRepository.buscarPorId(id);

        if (existente == null) {
            throw new RuntimeException("Cliente não encontrado");
        }

        Endereco enderecoViaCep = enderecoIntegration.buscaCep(request.cep());

        Endereco enderecoAtualizado = new Endereco(
                enderecoViaCep.cep(),
                enderecoViaCep.logradouro(),
                request.numero(),
                enderecoViaCep.bairro(),
                enderecoViaCep.cidade(),
                enderecoViaCep.uf()
        );

        Cliente clienteAtualizado = new Cliente(
                id,
                request.nome(),
                enderecoAtualizado,
                request.dtNascimento()
        );

        Cliente salvo = clienteRepository.salve(clienteAtualizado);

        return ClienteControllerAdapter.castResponse(salvo);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        clienteRepository.deletar(id);
    }
}