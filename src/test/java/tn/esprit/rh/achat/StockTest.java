package tn.esprit.rh.achat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.testng.annotations.Test;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)

public class StockTest {
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @InjectMocks
    private StockServiceImpl stockService;
    @MockBean

    private StockRepository stockRepository;

    @Test
    void testRetrieveAllStocks() {
        List<Stock> mockStocks = new ArrayList<>();
        mockStocks.add(new Stock());
        Mockito.when(stockRepository.findAll()).thenReturn(mockStocks);
        List<Stock> result = stockService.retrieveAllStocks();
        assertEquals(mockStocks, result);
    }

    @Test
    void testDeleteStock() {
        stockService.deleteStock(1L);
        verify(stockRepository).deleteById(eq(1L));
    }


}
