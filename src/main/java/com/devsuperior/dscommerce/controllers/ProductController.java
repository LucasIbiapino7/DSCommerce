package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController//configura com que essa classe possa responder via web
@RequestMapping(value = "/products")//indica a rota que ela vai responder
public class ProductController {

    @Autowired//indica que a dependencia precisa ser injetada
    private ProductService service;

    @GetMapping(value = "/{id}")//indica que essa função vai ser usada quando o metodo GET for usado nessa rota
    public ProductDTO findById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping
    public Page<ProductDTO> findAll(Pageable pageable){
        return service.findAll(pageable);
    }
}
