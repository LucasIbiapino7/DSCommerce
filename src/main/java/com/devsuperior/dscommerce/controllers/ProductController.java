package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController//configura com que essa classe possa responder via web
@RequestMapping(value = "/products")//indica a rota que ela vai responder
public class ProductController {

    @Autowired//indica que a dependencia precisa ser injetada
    private ProductService service;

    @GetMapping(value = "/{id}")//indica que essa função vai ser usada quando o metodo GET for usado nessa rota
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable){
        Page<ProductDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto){//Requesty body - casa o corpo que vem da requisaição com
        //o objeto do parametro. o proprio spring instancia o objeto
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        //cria uma resposta personalizada com o link do produto criado. BOA PRÁTICA
        return ResponseEntity.created(uri).body(dto);//retorna o código 201 e a resposta personalizada
        //BOA PRÁTICA
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();//retorna o código 204(deu certo, mas sem corpo)
    }
}
