package com.pabloepedro.repository.orm;

import com.pabloepedro.entity.Endereco;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("cliente")
public record ClienteOrmMongo(
        String id,
        String nome,
        EnderecoMongo endereco,
        LocalDate dtNascimento
) {}