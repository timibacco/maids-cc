package core.maidscc.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@Slf4j
@RestController
@RequestMapping("/inventory")
public class InventoryController {


    @GetMapping("/get/{startDate}/{endDate}")
    public void getInventory(@PathVariable("startDate") LocalDate startDate, @PathVariable("endDate") LocalDate endDate){

        log.info("Getting inventory...from {} to {}", startDate, endDate);



    }



}
