package com.gametrade.api.application.service;

import com.gametrade.api.model.ProductItem;
import com.gametrade.api.model.Usuario;
import com.gametrade.api.presentation.dtos.ProductItemUpdate;
import com.gametrade.api.exception.AppException;
import com.gametrade.api.infra.persistence.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

	public HttpStatus delete(Long id) throws AppException {
		Optional<ProductItem> product = productItemRepository.findById(id);
		
		if (product.isEmpty()) {
			throw new AppException(HttpStatus.NOT_FOUND, "Nenhum produto encontrado com esse id.", HttpStatus.NOT_FOUND.value());
		} 
		
		productItemRepository.deleteById(id);
		return HttpStatus.OK;
	}

	public void editProductItem(ProductItemUpdate productItem) {
		
		Optional<ProductItem> product = productItemRepository.findById(productItem.getId());
		
		if (!product.isEmpty()) {
			product.get().setTitle(productItem.getTitle());
			product.get().setDescription(productItem.getDescription());
			product.get().setImagePath(productItem.getImagePath());
			product.get().setProductItemLabel(productItem.getProductItemLabel());
			product.get().setPrice(productItem.getPrice());
			productItemRepository.save(product.get());
		}
		
	}
}
