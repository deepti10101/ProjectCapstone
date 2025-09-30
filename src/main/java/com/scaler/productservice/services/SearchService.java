package com.scaler.productservice.services;

import com.scaler.productservice.dto.SearchRequestDto;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    ProductRepository productRepository;

    @Autowired
    public SearchService(ProductRepository productRepository){
        this.productRepository= productRepository;
    }

    public Page<Product> search(String query, int pageNumber , int pageSize){
        Sort sort=Sort.by("title").descending().
                                    and(Sort.by("price")).descending();
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        return productRepository.findByTitleContaining(query, pageable);
    }
}
