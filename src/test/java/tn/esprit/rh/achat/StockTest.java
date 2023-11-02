package tn.esprit.rh.achat;


import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {StockServiceImpl.class})
public class StockTest {

    @Mock

    private StockRepository stockRepository;
    @InjectMocks
    private StockServiceImpl stockService;
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
