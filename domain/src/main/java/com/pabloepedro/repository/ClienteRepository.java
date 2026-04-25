package com.pabloepedro.repository;

import com.pabloepedro.entity.Cliente;
import java.util.List;

public interface ClienteRepository {
    Cliente salve(Cliente cliente);
    Cliente buscarPorId(String id);
    List<Cliente> listar();
    void deletar(String id);
}