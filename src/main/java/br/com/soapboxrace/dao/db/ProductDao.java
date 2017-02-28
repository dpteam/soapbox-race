package br.com.soapboxrace.dao.db;

import java.util.List;
import java.util.Random;

import br.com.soapboxrace.dao.factory.IProductDao;
import br.com.soapboxrace.db.SoapboxDao;
import br.com.soapboxrace.jpa.ProductEntity;

public class ProductDao extends SoapboxDao implements IProductDao {

	@Override
	public ProductEntity findById(Long id) {
		ProductEntity entity = (ProductEntity) super.findById(ProductEntity.class, id);
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<ProductEntity> findByCategoryNameClientProductType(String categoryName, String clientProductType) {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setCategoryName(categoryName);
		productEntity.setProductType(clientProductType);
		List<ProductEntity> find = (List<ProductEntity>) (List<?>) super.find(productEntity);
		return find;
	}

	public ProductEntity findByProductId(String productId) {
		ProductEntity carSlotProductData = new ProductEntity();
		carSlotProductData.setProductId(productId);
		carSlotProductData = (ProductEntity) super.find(carSlotProductData).get(0);
		return carSlotProductData;
	}

	@Override
	public ProductEntity findByHash(Long hash)
	{
		ProductEntity carSlotProductData = new ProductEntity();
		carSlotProductData.setHash(hash);
		carSlotProductData = (ProductEntity) super.find(carSlotProductData).get(0);
		return carSlotProductData;
	}

	@Override
	public ProductEntity selectRandom(String productType)
	{
		ProductEntity stub = new ProductEntity();
		stub.setProductType(productType);
		
		List<ProductEntity> results = (List<ProductEntity>) (List<?>) super.find(stub);
		
		return results.get(new Random().nextInt(results.size()));
	}
}