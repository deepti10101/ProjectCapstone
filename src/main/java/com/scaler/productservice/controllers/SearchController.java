package com.scaler.productservice.controllers;

import com.scaler.productservice.dto.ProductResponseDto;
import com.scaler.productservice.dto.SearchRequestDto;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import com.scaler.productservice.services.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SearchController {

    private SearchService searchService;

   @Autowired
    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }
    //checking
    @PostMapping("/search")
    public Page<Product> search(@RequestBody SearchRequestDto searchRequestDto){
        return searchService.search(searchRequestDto.getQuery(),
                searchRequestDto.getPageNumber(),
                searchRequestDto.getPageSize());

    }
}
