package com.pabloepedro.repository.orm;

public record EnderecoMongo(
        String cep,
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        String uf
) {}