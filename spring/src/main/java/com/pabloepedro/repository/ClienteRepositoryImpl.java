package com.pabloepedro.repository;

import com.pabloepedro.entity.Cliente;
import com.pabloepedro.repository.adapter.ClienteRepositoryAdapter;
import com.pabloepedro.repository.mongo.ClienteRepositoryWithMongoDB;
import com.pabloepedro.repository.orm.ClienteOrmMongo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    private final ClienteRepositoryWithMongoDB repository;

    public ClienteRepositoryImpl(ClienteRepositoryWithMongoDB repository) {
        this.repository = repository;
    }

    @Override
    public Cliente salve(Cliente cliente) {
        ClienteOrmMongo orm = ClienteRepositoryAdapter.castEntity(cliente);
        ClienteOrmMongo saved = repository.save(orm);
        return ClienteRepositoryAdapter.castOrm(saved);
    }

    @Override
    public Cliente buscarPorId(String id) {
        return repository.findById(id)
                .map(ClienteRepositoryAdapter::castOrm)
                .orElse(null);
    }

    @Override
    public List<Cliente> listar() {
        return repository.findAll()
                .stream()
                .map(ClienteRepositoryAdapter::castOrm)
                .toList();
    }

    @Override
    public void deletar(String id) {
        repository.deleteById(id);
    }
}