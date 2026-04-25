package com.pabloepedro.controller.dto.request;

import java.time.LocalDate;

public record ClientRequest(
        String nome,
        String cep,
        String numero,
        LocalDate dtNascimento
) {}