package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.exceptions.DatabaseException;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)//indica ao banco que vai ser feita apenas uma leitura. BOA PRÁTICA
    public ProductDTO findById(Long id){
        Product product  = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("recurso não encontrado"));
        //faz a busca no banco e retorna um obj do tipo optional
        return new ProductDTO(product);//instanciamos o product DTO
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<Product> result = repository.findAll(pageable);//pegando no banco todos os produtos e armazenamos numa lista
        return result.map(x -> new ProductDTO(x));//pegamos todos os produtos da lista e instanciamos
        //uma lista de products DTO para cada produto
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto){
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);//retorna o objeto atualizado
        return new ProductDTO(entity);//tranformamos em productDTO novamente

    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){
        try {
            Product entity = repository.getReferenceById(id);//instancia um objeto monitorado pela JPA sem ir no banco
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if (!repository.existsById(id)){//testa se existe esse produto no banco
            throw new ResourceNotFoundException("recurso não encontrado");//se não existir, gera uma exceção
        }
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de Integridade Refencial");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImgUrl(dto.getImgUrl());
        entity.setPrice(dto.getPrice());//transfirimos todos os dados vindos do productDTO para um PRODUCT, que é o tipo de dado
        //que faz o relacionamento com o banco de dados
    }

}
