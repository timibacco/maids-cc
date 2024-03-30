package core.maidscc.service;

import core.maidscc.dto.productDTO;

public interface ProductService {

    void createProduct(productDTO productDTO);

    void updateProduct(productDTO productDTO);


    void deleteProduct(Long productId);
}
