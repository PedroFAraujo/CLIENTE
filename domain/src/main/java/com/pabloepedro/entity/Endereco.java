package com.pabloepedro.entity;

public record Endereco(
        String cep,
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        String uf
) {}