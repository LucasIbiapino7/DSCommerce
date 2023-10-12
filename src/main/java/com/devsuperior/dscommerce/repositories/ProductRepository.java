package com.devsuperior.dscommerce.repositories;

import com.devsuperior.dscommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //passamos como parametros o tipo de dado que ele vai buscar no banco
    //e o tipo de dado da chave primária, nesse caso, o ID
}
