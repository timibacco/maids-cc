package core.maidscc.serviceImpl;

import core.maidscc.dto.salesDTO;
import core.maidscc.entity.ClientManagement;
import core.maidscc.entity.ProductManagement;
import core.maidscc.entity.Sales;

import core.maidscc.exceptions.ClientNotFoundException;
import core.maidscc.repository.ProductRepository;
import core.maidscc.repository.SalesRepository;
import core.maidscc.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Service
@RequiredArgsConstructor
public class SalesServiceImpl implements SalesService {


    @Autowired
    private final ProductRepository productRepo;


    @Autowired
    private final SalesRepository salesRepo;

    @Override
    public void generateSalesReport(LocalDate startDate, LocalDate endDate) {

    }

    @Override
    public void generateClientSalesReport(LocalDate startDate, LocalDate endDate) {

    }

    @Override
    public Sales createSale(salesDTO request) {

        Optional<ProductManagement> productRepository = productRepo.findById(request.getProductID());
        if (productRepository.isEmpty()) {
            throw new RuntimeException("Product not found");
        }

        Sales sales = new Sales();
        // ........................... ..........................//
        sales.setQuantity(request.getQuantity());
        sales.setCreationDate(LocalDate.now());
        salesRepo.saveAndFlush(sales);

        return sales;

    }





    @Override
    public Sales updateSale(Long id, Map<String,Object> fields) {
        Optional<Sales> sales = salesRepo.findById(id);
        if (sales.isEmpty()) {
            throw new RuntimeException("Sales not found");

        }

        Sales sale = sales.get();
//...................................................//

            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Sales.class, key);
                assert field != null;
                field.setAccessible(true);
                ReflectionUtils.setField(field, sales, value);
            });
            return salesRepo.save(sale);


    }

    public Object getSales(Long id){
        return salesRepo.findById(id);
    }

    public Object getAllSales(){
        return salesRepo.findAll();
    }

    @Override
    public Object getSalesByDate(LocalDate startDate, LocalDate endDate){
        return salesRepo.findByCreationDateBetween(startDate, endDate);
    }


    @Override
    public void deleteSale(Long id){
        salesRepo.deleteById(id);
    }




}

