package tn.esprit.rh.achat;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {OperateurServiceImpl.class})
@SpringBootTest
public class optest {
    @InjectMocks
    OperateurServiceImpl operateurService;
    @Mock
    OperateurRepository operateurRepository;


    @Test
    public void testAddOperateur() {
        Operateur operateur1 = new Operateur();
        operateur1.setNom("test");
        operateur1.setPrenom("test2");
        operateur1.setPassword("444");


        Mockito.when(operateurRepository.save(operateur1)).thenReturn(operateur1);

        Operateur result = operateurService.addOperateur(operateur1);

        assertNotNull(result);
        assertEquals("test", result.getNom());
    }


    @Test
    public void testRetrieveAllOperateurs() {

        List<Operateur> operateurList = new ArrayList<>();
        Operateur operateur1 = new Operateur();
        operateur1.setIdOperateur(1L);
        operateur1.setNom("morad");
        operateurList.add(operateur1);

        Operateur operateur2 = new Operateur();
        operateur2.setIdOperateur(2L);
        operateur2.setNom("samir");
        operateurList.add(operateur2);


        Mockito.when(operateurRepository.findAll()).thenReturn(operateurList);

        List<Operateur> result = operateurService.retrieveAllOperateurs();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("morad", result.get(0).getNom());
        assertEquals("samir", result.get(1).getNom());
    }
    @Test
    public void testUpdateOperateur() {
        Operateur operateur = new Operateur();
        operateur.setIdOperateur(1L);
        operateur.setNom("test update");

        Mockito.when(operateurRepository.save(operateur)).thenReturn(operateur);

        Operateur result = operateurService.updateOperateur(operateur);

        assertNotNull(result);
        Assertions.assertEquals(1L, result.getIdOperateur());
        assertEquals("test update", result.getNom());
    }



}
