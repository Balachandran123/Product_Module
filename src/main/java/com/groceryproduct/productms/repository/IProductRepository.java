package com.groceryproduct.productms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.groceryproduct.productms.entity.Product;


@Repository
@Transactional
public interface IProductRepository extends JpaRepository<Product, Long>
{
 List<Product> findByproductName(String productName);
 List<Product> findByproductType(String productType);
 
 @Modifying
 @Query(value="DELETE FROM Product p WHERE p.productName = :name")
 void deleteByName(@Param("name") String productName);
}
