package core.maidscc.serviceImpl;

import core.maidscc.dto.productDTO;
import core.maidscc.entity.ProductManagement;
import core.maidscc.repository.ProductRepository;
import core.maidscc.service.ProductService;
import core.maidscc.utils.Helper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;
import java.beans.FeatureDescriptor;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;


    private final Helper helper;


    @Override
    public void createProduct(productDTO productDTO) {

        ProductManagement product = new ProductManagement();
        BeanUtils.copyProperties(productDTO, product);
        productRepository.save(product);

    }

    @Override
    public void updateProduct(productDTO productDTO) {
        Optional<ProductManagement> productOptional = productRepository.findById(productDTO.getId());
        if (productOptional.isEmpty()) {
            log.info("Product to be updated not found...{}",productDTO);
            throw new RuntimeException("Product not found");
        }

        String[] nulls  = helper.getNullPropertyNames(productDTO);

        ProductManagement product = productOptional.get();

        log.info("Product to be updated found...{}",productDTO);
        BeanUtils.copyProperties(productDTO, product, nulls);
        productRepository.save(product);
        log.info("Product updated successfully...");

    }




    @Override
    public void deleteProduct(Long productId) {

        productRepository.deleteById(productId);
        log.info("Product deleted successfully...{}",productId);

    }



}
