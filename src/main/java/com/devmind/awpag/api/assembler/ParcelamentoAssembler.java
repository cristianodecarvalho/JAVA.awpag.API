package com.devmind.awpag.api.assembler;

import com.devmind.awpag.api.model.ParcelamentoDTO;
import com.devmind.awpag.api.model.input.ParcelamentoInput;
import com.devmind.awpag.domain.model.Parcelamento;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ParcelamentoAssembler {
    private final ModelMapper modelMapper;

    public ParcelamentoDTO toDTO(Parcelamento parcelamento) {
        return modelMapper.map(parcelamento, ParcelamentoDTO.class);
    }

    public List<ParcelamentoDTO> toCollectionDTO(List<Parcelamento> parcelamentos) {
        return parcelamentos.stream()
                .map(this::toDTO)
                .toList();
    }

    public Parcelamento toEntity(ParcelamentoInput parcelamentoInput) {
        return modelMapper.map(parcelamentoInput, Parcelamento.class);
    }
}
