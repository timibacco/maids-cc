package core.maidscc.controller;


import core.maidscc.dto.salesDTO;
import core.maidscc.service.SalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;


@Slf4j
@RestController
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;


    /**
     * This endpoint gets sales
     * Takes in a start date and an end date
     */
    @GetMapping("/sales/{startDate}/{endDate}")
    public ResponseEntity<?> getSales(@PageableDefault() @PathVariable("startDate") LocalDate startDate, @PathVariable("endDate") LocalDate endDate){

       try{
           log.info("Getting sales...from {} to {}", startDate, endDate);
           var response = salesService.getSalesByDate(startDate, endDate);
           return ResponseEntity.ok().body(response);

        }catch (Exception e){
        log.error("Error getting sales: {}", e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    /**
     * This endpoint adds new instances of sales
     */

    @PostMapping("/sales")
    public ResponseEntity<?> addSales(@RequestBody salesDTO salesDTO){

        try{

        log.info("Adding new sales...");
        var response = salesService.createSale(salesDTO);
        return ResponseEntity.ok().body(response);
        }
        catch (Exception e){
            log.error("Error adding sales: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    /**
     * This endpoint updates sales
     */

    @PatchMapping("/sales/{productID}")
    public ResponseEntity<?> updateSales(@PathVariable("productID") Long id, @RequestBody Map<String,Object> fields){


        try
        {
            log.info("Updating sales...");
            var response = salesService.updateSale(id,fields);
            return ResponseEntity.ok().body(response);
        }
        catch (Exception e){

            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    /**
     * This endpoint deletes sales
     */

    @DeleteMapping("/sales/{salesID}")
    public void deleteSales(@PathVariable("salesID") Long salesID){

        log.info("Deleting sales...");

        salesService.deleteSale(salesID);

    }
}
