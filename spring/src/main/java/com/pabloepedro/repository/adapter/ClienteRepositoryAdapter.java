package com.pabloepedro.repository.adapter;

import com.pabloepedro.entity.Cliente;
import com.pabloepedro.repository.orm.ClienteOrmMongo;

public class ClienteRepositoryAdapter {

    public static Cliente castOrm(ClienteOrmMongo orm) {
        return new Cliente(
                orm.id(),
                orm.nome(),
                orm.endereco(),
                orm.dtNascimento()
        );
    }

    public static ClienteOrmMongo castEntity(Cliente entity) {
        return new ClienteOrmMongo(
                entity.id(),
                entity.nome(),
                entity.endereco(),
                entity.dtNascimento()
        );
    }
}