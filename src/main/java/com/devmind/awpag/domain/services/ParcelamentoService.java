package com.devmind.awpag.domain.services;

import com.devmind.awpag.domain.exception.NegocioException;
import com.devmind.awpag.domain.model.Cliente;
import com.devmind.awpag.domain.model.Parcelamento;
import com.devmind.awpag.domain.repository.ClienteRepository;
import com.devmind.awpag.domain.repository.ParcelamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class ParcelamentoService {

    private final ParcelamentoRepository parcelamentoRepository;
    private final ClienteService clienteService;

    @Transactional
    public Parcelamento cadastrar(Parcelamento parcelamento) {
        if (parcelamento.getId() != null) {
            throw new NegocioException("Parcelamento a ser criado não deve possuir código");
        }

        Cliente cliente = clienteService.buscar(parcelamento.getCliente().getId());


        parcelamento.setCliente(cliente);
        parcelamento.setDataCriacao(OffsetDateTime.now());

        return parcelamentoRepository.save(parcelamento);
    }
}
