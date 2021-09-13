package com.hdiseguros.ofertashdiseguros.controller;

import java.io.IOException;

import com.hdiseguros.ofertashdiseguros.dto.CorretorDTO;
import com.hdiseguros.ofertashdiseguros.service.CorretorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/corretor")
public class CorretorController {

    @Autowired
    private CorretorService corretorService;

    @GetMapping("/{doc}")
    public ResponseEntity<?> recoverWorkStation(@PathVariable("doc") String document) throws Exception {
        CorretorDTO corretorDTO = corretorService.validateCorretor(document);
        return ResponseEntity.ok(corretorDTO);
    }

}
