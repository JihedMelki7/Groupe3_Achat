package tn.esprit.rh.achat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;
import tn.esprit.rh.achat.services.ProduitServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {OperateurServiceImpl.class})
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OperateurTest {


    @MockBean
    private OperateurRepository operateurRepository;

    @Autowired
    private OperateurServiceImpl operateurService;


    @Test
    void testRetrieveAllop() {
        ArrayList<Operateur> opList = new ArrayList<>();
        when(operateurRepository.findAll()).thenReturn(opList);
        List<Operateur> actualRetrieveAllopResult = operateurService.retrieveAllOperateurs();
        assertSame(opList, actualRetrieveAllopResult);
        assertTrue(actualRetrieveAllopResult.isEmpty());
        verify(operateurRepository).findAll();
    }

    @Test
    void testDeleteop() {
        doNothing().when(operateurRepository).deleteById((Long) any());
        operateurService.deleteOperateur(123L);
        verify(operateurRepository).deleteById((Long) any());
    }

    @Test
    void testUpdateOperateur() {

        Operateur existingOperateur = new Operateur();
        existingOperateur.setIdOperateur(123L);
        existingOperateur.setNom("testoperature2");


        when(operateurRepository.save(existingOperateur)).thenReturn(existingOperateur);


        Operateur updatedOperateur = operateurService.updateOperateur(existingOperateur);


        Assertions.assertEquals(123L, updatedOperateur.getIdOperateur());
       Assertions.assertEquals("testoperature2", updatedOperateur.getNom());


        verify(operateurRepository).save(existingOperateur);
    }
    @Test
    void testAddOperateur() {

        Operateur newOperateur = new Operateur();
        newOperateur.setNom("karim");
        newOperateur.setPrenom("mejbri");
        newOperateur.setPassword("0");


        when(operateurRepository.save(newOperateur)).thenReturn(newOperateur);


        Operateur addedOperateur = operateurService.addOperateur(newOperateur);


        Assertions.assertNotNull(addedOperateur);

        verify(operateurRepository).save(newOperateur);
    }

}
