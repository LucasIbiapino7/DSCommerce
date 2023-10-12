package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)//indica ao banco que vai ser feita apenas uma leitura. BOA PRÁTICA
    public ProductDTO findById(Long id){
        Optional<Product> result = repository.findById(id);//faz a busca no banco e retorna um obj do tipo optional
        Product product = result.get();//pegamos o product do obj optional
        ProductDTO dto = new ProductDTO(product);//instanciamos o product DTO
        return dto;
    }

}
