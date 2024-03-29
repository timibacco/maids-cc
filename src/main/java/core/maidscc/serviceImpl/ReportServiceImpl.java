package core.maidscc.serviceImpl;

import core.maidscc.entity.ProductManagement;
import core.maidscc.repository.ClientRepository;
import core.maidscc.repository.ProductRepository;
import core.maidscc.repository.SalesRepository;
import core.maidscc.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    @Autowired
    private final ClientRepository clientRepository;

    @Autowired
    private final SalesRepository salesRepository;


    @Autowired
    private final ProductRepository productRepository;

    @Override
    public void generateReport(LocalDate startDate, LocalDate endDate) {

    }

    @Override
    public void generateClientReport(LocalDate startDate, LocalDate endDate) {

        Map<String,Object[]> reportData = new HashMap<>();

        



    }
}
