package com.devmind.awpag.api.controller;

import com.devmind.awpag.api.assembler.ParcelamentoAssembler;
import com.devmind.awpag.api.model.ParcelamentoDTO;
import com.devmind.awpag.api.model.input.ParcelamentoInput;
import com.devmind.awpag.domain.model.Parcelamento;
import com.devmind.awpag.domain.repository.ParcelamentoRepository;
import com.devmind.awpag.domain.services.ParcelamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parcelamentos")
@RequiredArgsConstructor
public class ParcelamentoController {

    private final ParcelamentoService parcelamentoService;
    private final ParcelamentoRepository parcelamentoRepository;
    private final ParcelamentoAssembler parcelamentoAssembler;

    @GetMapping
    public List<ParcelamentoDTO> listar() {
        return parcelamentoAssembler.toCollectionDTO(parcelamentoRepository.findAll());
    }

    @GetMapping("/{parcelamentoId}")
    public ResponseEntity<ParcelamentoDTO> buscar(@PathVariable Long parcelamentoId) {
        return parcelamentoRepository.findById(parcelamentoId)
                .map(parcelamentoAssembler::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ParcelamentoDTO cadastrar(@RequestBody @Valid ParcelamentoInput parcelamentoInput) {
        Parcelamento novoParcelamento = parcelamentoAssembler.toEntity(parcelamentoInput);
        Parcelamento parcelamentoCadastrado = parcelamentoService.cadastrar(novoParcelamento);
        return parcelamentoAssembler.toDTO(parcelamentoCadastrado);
    }
}
