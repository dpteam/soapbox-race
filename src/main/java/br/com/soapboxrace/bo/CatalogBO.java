package br.com.soapboxrace.bo;

import java.util.List;

import br.com.soapboxrace.dao.factory.DaoFactory;
import br.com.soapboxrace.dao.factory.ICategoryDao;
import br.com.soapboxrace.dao.factory.IProductDao;
import br.com.soapboxrace.jaxb.ArrayOfCategoryTrans;
import br.com.soapboxrace.jaxb.ArrayOfProductTrans;
import br.com.soapboxrace.jpa.ProductEntity;

/**
 * Retrieves catalog data to be sent to the client.
 *
 * @see CatalogBO#productsInCategory(String, String)
 * @see CatalogBO#categories()
 */
public class CatalogBO
{
    private IProductDao productDao = DaoFactory.getProductDao();
    private ICategoryDao categoryDao = DaoFactory.getCategoryDao();

    /**
     * Gets all products in a certain category, and of a certain type.
     *
     * @param categoryName      The category name.
     * @param clientProductType The product type.
     * @return The array of products.
     */
    public ArrayOfProductTrans productsInCategory(String categoryName, String clientProductType)
    {
        List<ProductEntity> products = productDao.findByCategoryNameClientProductType(categoryName, clientProductType);
        ArrayOfProductTrans arrayOfProductTrans = new ArrayOfProductTrans();
        arrayOfProductTrans.setProductTransList(products);
        return arrayOfProductTrans;
    }

    /**
     * Gets all categories in the database.
     *
     * @return The array of categories.
     */
    public ArrayOfCategoryTrans categories()
    {
        return categoryDao.getAll();
    }
}