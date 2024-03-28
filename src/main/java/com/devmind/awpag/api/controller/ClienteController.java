package com.devmind.awpag.api.controller;

import com.devmind.awpag.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClienteController {

    @GetMapping
    public List<Cliente> listar() {
        return null;
    }
}
