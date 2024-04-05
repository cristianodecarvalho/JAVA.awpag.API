package com.devmind.awpag.api.controller;

import com.devmind.awpag.api.model.ParcelamentoDTO;
import com.devmind.awpag.domain.model.Parcelamento;
import com.devmind.awpag.domain.repository.ParcelamentoRepository;
import com.devmind.awpag.domain.services.ParcelamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public List<Parcelamento> listar() {
        return parcelamentoRepository.findAll();
    }

    @GetMapping("/{parcelamentoId}")
    public ResponseEntity<ParcelamentoDTO> buscar(@PathVariable Long parcelamentoId) {
        return parcelamentoRepository.findById(parcelamentoId)
                .map(parcelamento -> {
                    var parcelamentoDTO = new ParcelamentoDTO();
                    parcelamentoDTO.setId(parcelamento.getId());
                    parcelamentoDTO.setNomeCliente(parcelamento.getCliente().getNome());
                    parcelamentoDTO.setDescricao(parcelamento.getDescricao());
                    parcelamentoDTO.setValorTotal(parcelamento.getValorTotal());
                    parcelamentoDTO.setParcelas(parcelamento.getQuantidadeParcelas());
                    parcelamentoDTO.setDataCriacao(parcelamento.getDataCriacao());
                    return ResponseEntity.ok(parcelamentoDTO);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Parcelamento cadastrar(@RequestBody @Valid Parcelamento parcelamento) {
        return parcelamentoService.cadastrar(parcelamento);
    }
}
