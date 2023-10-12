package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<Product> result = repository.findAll(pageable);//pegando no banco todos os produtos e armazenamos numa lista
        return result.map(x -> new ProductDTO(x));//pegamos todos os produtos da lista e instanciamos
        //uma lista de products DTO para cada produto
    }

}
