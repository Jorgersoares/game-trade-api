package com.gametrade.api.application.service;

import com.gametrade.api.model.ProductItem;
import com.gametrade.api.model.Usuario;
import com.gametrade.api.infra.persistence.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductItemService {

    @Autowired
    ProductItemRepository productItemRepository;

    public List<ProductItem> listarTodos(){
        return productItemRepository.findAll();
    }

    public List<ProductItem> listarPorUsuario(Usuario usuario){
        return productItemRepository.findByUsuario(usuario);
    }

    public ProductItem adicionar(Usuario usuario, ProductItem productItem){
        productItem.setUsuario(usuario);
        return productItemRepository.save(productItem);
    }
}
