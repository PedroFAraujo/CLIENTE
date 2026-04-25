package com.pabloepedro.controller.dto.response;

import java.time.LocalDate;

public record ClienteResponse(
        String id,
        String nome,
        EnderecoResponse endereco,
        LocalDate dtNascimento
) {}