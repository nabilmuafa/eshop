package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;
public interface ProductService {
    public void create(Product product);
    public void delete(String id);
    public void edit(String id, Product product);
    public Product getProduct(String id);
    public List<Product> findAll();
}
