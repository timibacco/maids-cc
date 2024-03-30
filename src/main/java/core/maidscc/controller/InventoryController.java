package core.maidscc.controller;


import core.maidscc.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@Slf4j
@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {


    private final ReportService reportService;


    /**
     * This endpoint generates a report.
     * Takes in a start date and an end date
     */

    @GetMapping("/generate-report/{startDate}/{endDate}")
    public ResponseEntity<?> getInventory(@PathVariable("startDate") LocalDate startDate, @PathVariable("endDate") LocalDate endDate){

       try
       {
           log.info("Getting inventory...from {} to {}", startDate, endDate);
        var response = reportService.generateClientReport(startDate, endDate);
        return ResponseEntity.ok().body(response);
        }
        catch (Exception e){
            log.error("Error getting inventory: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }





    }

    /**
     * This endpoint generates a report.
     * Takes in a start date and an end date
     */

    @GetMapping("/get/{startDate}/{endDate}")
    public ResponseEntity<?> getClientReport(@PathVariable("startDate") LocalDate startDate, @PathVariable("endDate") LocalDate endDate) {

        try{


        log.info("Getting inventory...from {} to {}", startDate, endDate);

        reportService.generateReport(startDate, endDate);
        return ResponseEntity.ok().build();
        }
        catch (Exception e){
            log.error("Error getting inventory: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
