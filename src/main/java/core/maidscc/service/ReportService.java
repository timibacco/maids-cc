package core.maidscc.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface ReportService {

        void generateReport(LocalDate startDate, LocalDate endDate);

        Object generateClientReport(LocalDate startDate, LocalDate endDate);



}
