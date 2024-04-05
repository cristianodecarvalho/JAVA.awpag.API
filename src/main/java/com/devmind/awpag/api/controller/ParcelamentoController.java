package com.devmind.awpag.api.controller;

import com.devmind.awpag.domain.exception.NegocioException;
import com.devmind.awpag.domain.model.Cliente;
import com.devmind.awpag.domain.model.Parcelamento;
import com.devmind.awpag.domain.repository.ParcelamentoRepository;
import com.devmind.awpag.domain.services.ParcelamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parcelamentos")
@RequiredArgsConstructor
public class ParcelamentoController {

    private final ParcelamentoService parcelamentoService;
    private final ParcelamentoRepository parcelamentoRepository;

    @GetMapping
    public List<Parcelamento> listar() {
        return parcelamentoRepository.findAll();
    }

    @GetMapping("/{parcelamentoId}")
    public ResponseEntity<Parcelamento> buscar(@PathVariable Long parcelamentoId) {
        return parcelamentoRepository.findById(parcelamentoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Parcelamento cadastrar(@RequestBody @Valid Parcelamento parcelamento) {
        return parcelamentoService.cadastrar(parcelamento);
    }
}
