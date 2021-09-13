package com.hdiseguros.ofertashdiseguros.service;

import java.io.InputStream;

import javax.management.MalformedObjectNameException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hdiseguros.ofertashdiseguros.dto.CorretorDTO;
import com.hdiseguros.ofertashdiseguros.model.Corretor;
import com.hdiseguros.ofertashdiseguros.model.CorretorDetalhe;
import com.hdiseguros.ofertashdiseguros.utils.OpenConnectionAPI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CorretorService {
    @Value("${hdi.seguros.apicorretor}")
    private String urlCorretor;

    @Value("${hdi.seguros.api.corretor.detalhe}")
    private String urlCorretorDetalhe;

    public CorretorDTO validateCorretor(String document) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Corretor corretor = new Corretor();

        try {
            InputStream responseStreamCorretor = OpenConnectionAPI
                    .openConnection(String.format("%s/%s", urlCorretor, document));
            corretor = mapper.readValue(responseStreamCorretor, Corretor.class);
        } catch (Exception e) {
            throw new Exception("Corretor não cadastrado");
        }

        CorretorDetalhe corretorDetalhe = new CorretorDetalhe();

        try {
            InputStream responseStreamCorretorDetalhe = OpenConnectionAPI
                    .openConnection(String.format("%s/%s", urlCorretorDetalhe, corretor.getCode()));
            corretorDetalhe = mapper.readValue(responseStreamCorretorDetalhe, CorretorDetalhe.class);
        } catch (UnrecognizedPropertyException e) {
            throw new MalformedObjectNameException("Detalhes do corretor mal formado");
        }

        if (!corretorDetalhe.getActive())
            throw new ResponseStatusException(HttpStatus.OK,
                    String.format("Corretor com cpf %s não está ativo", document));

        CorretorDTO corretorDTO = new CorretorDTO();

        return corretorDTO.convertModelsToDto(corretor, corretorDetalhe);
    }

}
