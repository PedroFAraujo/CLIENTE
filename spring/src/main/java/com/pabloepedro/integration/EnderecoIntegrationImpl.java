package com.pabloepedro.integration;

import com.pabloepedro.entity.Endereco;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EnderecoIntegrationImpl implements EnderecoIntegration {

    @Override
    public Endereco buscaCep(String cep) {

        String url = "https://viacep.com.br/ws/" + cep + "/json";

        RestTemplate restTemplate = new RestTemplate();
        ViaCepResponse response = restTemplate.getForObject(url, ViaCepResponse.class);

        if (response == null || response.cep() == null) {
            throw new RuntimeException("Erro ao buscar CEP");
        }

        return new Endereco(
                response.cep(),
                response.logradouro(),
                null,
                response.bairro(),
                response.localidade(),
                response.uf()
        );
    }
}