package com.wyg.mapper;

import java.util.List;
import com.wyg.pojo.Product;

public interface ProductMapper {
		 List<Product> getHotProducts();
		 List<Product> getHotSalesProducts();
		 List<Product> getProductsByCategoryId(long id);
		 List<Product> getProductsByDetailId(long id);
		 List<Product> getProductsByPublishId(long id);
		 List<Product> getProductsBySaleNum();
		 List<Product> getProductsByStartTime();
		 
		 
		 List<Product> getProductsByPriceODID(long id);
		 List<Product> getProductsByPriceODSA(long id);
		 List<Product> getProductsByPriceODPR(long id);
		 List<Product> getProductsByPriceODST(long id);
		 
		 List<Product>getProductsByPriceAndPublishODID(long priceid,long publishid);
		 List<Product>getProductsByPriceAndPublishODPR(long priceid,long publishid);
		 List<Product>getProductsByPriceAndPublishODSA(long priceid,long publishid);
		 List<Product>getProductsByPriceAndPublishODST(long priceid,long publishid);
		 
		 List<Product>getProductsByNewPublishODID(long id);
		 List<Product>getProductsByNewPublishODST(long id);
		 List<Product>getProductsByNewPublishODPR(long id);
		 List<Product>getProductsByNewPublishODSA(long id);
		 
		 Product getProductsById(long id);
		 void updateStock(Product product);
		 void updatesalenum(Product product);
}
