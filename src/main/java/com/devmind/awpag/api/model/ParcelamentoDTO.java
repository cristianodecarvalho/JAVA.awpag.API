package com.devmind.awpag.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class ParcelamentoDTO {
    private Long id;
    private String nomeCliente;
    private String descricao;
    private BigDecimal valorTotal;
    private Integer parcelas;
    private OffsetDateTime dataCriacao;
}
