package br.com.fiap.loja.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    //demais métodos omitidos

    @GetMapping
    public Page<Cliente> listarTodos(
        Pageable pageable, 
        @RequestParam(required = false) String nome
    ) {
        
        if (nome == null) return repository.findAll(pageable);
        
        return repository.findByNome(nome, pageable);
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody @Valid Cliente cliente) {
        repository.save(cliente);
        return ResponseEntity.status(201).body(cliente);
    }
    
}
