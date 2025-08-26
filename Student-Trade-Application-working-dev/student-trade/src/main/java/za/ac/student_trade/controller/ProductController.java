package za.ac.student_trade.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.student_trade.domain.Product;
import za.ac.student_trade.service.Impl.ProductServiceImpl;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductServiceImpl productService;

    @Autowired
    public void setProductService(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<Product> createProduct(@RequestPart Product product, @RequestPart MultipartFile productImage) {
        try{
            System.out.println("ProductController.createProduct");
            Product newProduct = productService.addProduct(product, productImage);
            System.out.println("ProductController");
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/buy")
    public ResponseEntity<byte[]> getProductImage(@RequestParam("productId") Long productId){
        Product product = productService.getProductById(productId);
        byte[] imageFile = product.getImageData();

        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(imageFile);
    }

    @GetMapping("/read/{id}")
    public Product readProduct(@PathVariable Long id) {
        return this.productService.read(id);
    }

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        return this.productService.getAll();
    }
}
