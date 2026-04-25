package com.pabloepedro.entity;

import java.time.LocalDate;

public record Cliente(
        String id,
        String nome,
        Endereco endereco,
        LocalDate dtNascimento
) {}