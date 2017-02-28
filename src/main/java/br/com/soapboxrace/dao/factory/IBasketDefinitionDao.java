package br.com.soapboxrace.dao.factory;

import br.com.soapboxrace.db.ISoapboxDao;
import br.com.soapboxrace.jpa.BasketDefinitionEntity;

import java.util.List;

public interface IBasketDefinitionDao extends ISoapboxDao {
	public BasketDefinitionEntity findById(Long id);

	public BasketDefinitionEntity findByProductId(String productId);
	
	List<BasketDefinitionEntity> getAll();
}
