package com.pabloepedro.integration;

import com.pabloepedro.entity.Endereco;

public interface EnderecoIntegration {
    Endereco buscaCep(String cep);
}