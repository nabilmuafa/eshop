package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public void create(Product product){
        productRepository.create(product);
    }

    @Override
    public void delete(String id){
        Product product = productRepository.getProduct(id);
        productRepository.delete(product);
    }

    @Override
    public void edit(String id, Product product){
        String productName = product.getProductName();
        int productQuantity = product.getProductQuantity();
        productRepository.edit(id, productName, productQuantity);
    }

    @Override
    public Product getProduct(String id){
        return productRepository.getProduct(id);
    }

    @Override
    public List<Product> findAll(){
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }
}
