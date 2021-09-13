package com.hdiseguros.ofertashdiseguros.dto;

import java.util.Date;

import com.hdiseguros.ofertashdiseguros.model.Corretor;
import com.hdiseguros.ofertashdiseguros.model.CorretorDetalhe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CorretorDTO {
    private String code;

    private String name;

    private String document;

    private Date createDate;

    private Boolean active;

    private Double commissionRate;

    public CorretorDTO convertModelsToDto(Corretor corretor, CorretorDetalhe corretorDetalhe) {
        CorretorDTO corretorDTO = new CorretorDTO();

        corretorDTO.setCode(corretor.getCode());
        corretorDTO.setName(corretor.getName());
        corretorDTO.setDocument(corretor.getDocument());
        corretorDTO.setCreateDate(corretor.getCreateDate());
        corretorDTO.setActive(corretorDetalhe.getActive());
        corretorDTO.setCommissionRate(corretorDetalhe.getCommissionRate());

        return corretorDTO;
    }
}
