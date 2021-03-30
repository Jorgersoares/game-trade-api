package com.gametrade.api.infra.persistence.repository;

import com.gametrade.api.model.ProductItem;
import com.gametrade.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {
    List<ProductItem> findByUsuario(Usuario usuario);
}
