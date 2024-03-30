package core.maidscc.controller;

import core.maidscc.dto.productDTO;
import core.maidscc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;



    /**
     * This endpoint creates new products
     */
    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody productDTO request){
        try{
          var response = productService.createProduct(request);
        return ResponseEntity.ok().body(response);

    }catch (Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    }


    /**
     * This endpoint updates products
     */

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long Id, @RequestBody productDTO request){
        try{
            productService.updateProduct(Id , request);
            return ResponseEntity.ok().body("Product updated successfully");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    /**
     * This endpoint deletes products
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        try{
            productService.deleteProduct(id);
            return ResponseEntity.ok().body("Product deleted successfully");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
