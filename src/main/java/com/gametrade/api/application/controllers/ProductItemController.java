package com.gametrade.api.application.controllers;

import com.gametrade.api.model.ProductItem;
import com.gametrade.api.model.Usuario;
import com.gametrade.api.presentation.dtos.ProductItemResponse;
import com.gametrade.api.presentation.dtos.ProductItemUpdate;
import com.gametrade.api.application.service.ProductItemService;
import com.gametrade.api.application.service.UserService;
import com.gametrade.api.exception.AppException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/items")
public class ProductItemController {

    @Autowired
    ProductItemService productItemService;

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ProductItemResponse>> listar(){
        List<ProductItem> items = productItemService.listarTodos();
        return new ResponseEntity<>(items
                .stream()
                .map(productItem -> modelMapper.map(productItem, ProductItemResponse.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ProductItemResponse>> listarPorUsuario(@PathVariable Long id){
        Usuario usuario = userService.getUsuario(id);
        List<ProductItem> items = productItemService.listarPorUsuario(usuario);
        return new ResponseEntity<>(items
                .stream()
                .map(productItem -> modelMapper.map(productItem, ProductItemResponse.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductItemResponse> addProductItem(
            @Valid @RequestBody ProductItem productItem,
            @RequestHeader("user-id") String userId){
        Usuario usuario = userService.getUsuario(Long.parseLong(userId));
        ProductItem productItemCreate = productItemService.adicionar(usuario, productItem);;
        return new ResponseEntity<>(modelMapper.map(productItemCreate, ProductItemResponse.class), HttpStatus.CREATED);
    }
    
    @PatchMapping("/")
    public ResponseEntity<HttpStatus> editProductItem(@RequestBody ProductItemUpdate productItem) {
    	productItemService.editProductItem(productItem);
    	return new ResponseEntity<>(HttpStatus.OK);
    	
	}
    
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProductItem(@PathVariable Long id) throws AppException{
    	productItemService.delete(id);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
}
