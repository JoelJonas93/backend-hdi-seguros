package com.hdiseguros.ofertashdiseguros.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Corretor {
    private String code;

    private String name;

    private String document;

    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date createDate;
}
