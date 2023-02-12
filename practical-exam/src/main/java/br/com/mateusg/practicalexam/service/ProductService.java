package br.com.mateusg.practicalexam.service;

import br.com.mateusg.practicalexam.model.Product;
import br.com.mateusg.practicalexam.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public boolean existsById(Long idProduct) {
        return productRepository.existsById(idProduct);
    }

    public void create(Product product) {
        productRepository.save(product);
    }

    private Double convertToTwoDecimalPlaces(Double number){
        return Math.round(number * 100.0) / 100.0;
    }
}
