package core.maidscc.service;

import core.maidscc.dto.salesDTO;
import core.maidscc.entity.Sales;

import java.time.LocalDate;
import java.util.Map;

public interface SalesService {

    void generateSalesReport(LocalDate startDate, LocalDate endDate);

     void generateClientSalesReport(LocalDate startDate, LocalDate endDate);

     Sales createSale(salesDTO request);

     Sales updateSale(Long id, Map<String, Object> fields);

     Object getSalesByDate(LocalDate startDate, LocalDate endDate);

     void deleteSale(Long id);



    }