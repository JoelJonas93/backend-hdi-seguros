package com.hdiseguros.ofertashdiseguros.model;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Corretor {
    @EqualsAndHashCode.Include
    private Long code;

    private String name;

    private String document;

    @CreatedDate
    private Instant createDate;
}
