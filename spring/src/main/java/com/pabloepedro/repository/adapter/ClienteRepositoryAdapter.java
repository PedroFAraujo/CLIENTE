package com.pabloepedro.repository.adapter;

import com.pabloepedro.entity.Cliente;
import com.pabloepedro.entity.Endereco;
import com.pabloepedro.repository.orm.ClienteOrmMongo;
import com.pabloepedro.repository.orm.EnderecoMongo;

public class ClienteRepositoryAdapter {

    private ClienteRepositoryAdapter() {}

    // ENTITY → MONGO
    public static ClienteOrmMongo castEntity(Cliente entity) {

        EnderecoMongo enderecoMongo = null;

        if (entity.endereco() != null) {
            enderecoMongo = new EnderecoMongo(
                    entity.endereco().cep(),
                    entity.endereco().logradouro(),
                    entity.endereco().numero(),
                    entity.endereco().bairro(),
                    entity.endereco().cidade(),
                    entity.endereco().uf()
            );
        }

        return new ClienteOrmMongo(
                entity.id(),
                entity.nome(),
                enderecoMongo,
                entity.dtNascimento()
        );
    }

    // MONGO → ENTITY
    public static Cliente castOrm(ClienteOrmMongo orm) {

        Endereco endereco = null;

        if (orm.endereco() != null) {
            endereco = new Endereco(
                    orm.endereco().cep(),
                    orm.endereco().logradouro(),
                    orm.endereco().numero(),
                    orm.endereco().bairro(),
                    orm.endereco().cidade(),
                    orm.endereco().uf()
            );
        }

        return new Cliente(
                orm.id(),
                orm.nome(),
                endereco,
                orm.dtNascimento()
        );
    }
}