package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.utils.UUIDGenerator;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private final List<Product> productData = new ArrayList<>();
    private final UUIDGenerator uuidGenerator = new UUIDGenerator();

    public Product create(Product product){
        if(product.getProductId() == null){
            product.setProductId(uuidGenerator.generateId());
        }
        productData.add(product);
        return product;
    }

    public boolean delete(Product product){
        return productData.remove(product);
    }

    public Product edit(String id, String productName, int productQuantity){
        Product p = getProduct(id);
        if (p != null) {
            p.setProductName(productName);
            p.setProductQuantity(productQuantity);
        }
        return p;
    }

    public Product getProduct(String id){
        Iterator<Product> data = this.findAll();
        while (data.hasNext()) {
            Product p = data.next();
            if (p.getProductId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }
}
