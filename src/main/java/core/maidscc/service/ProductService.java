package core.maidscc.service;

import core.maidscc.dto.productDTO;
import core.maidscc.entity.ProductManagement;

public interface ProductService {

    ProductManagement createProduct(productDTO productDTO);

    void updateProduct(Long Id, productDTO productDTO);


    void deleteProduct(Long productId);
}
