package tn.esprit.rh.achat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
@RunWith( SpringRunner.class)

public class OperatureMOckitoTest {

    @InjectMocks
    private OperateurServiceImpl operateurService;

    @Mock
    private OperateurRepository operateurRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
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
    public void testAddOperateur() {
        Operateur operateur = new Operateur();
        operateur.setNom("test");

        Mockito.when(operateurRepository.save(operateur)).thenReturn(operateur);

        Operateur result = operateurService.addOperateur(operateur);

        assertNotNull(result);
        assertEquals("test", result.getNom());
    }

    @Test
    public void testDeleteOperateur() {
        Long operateurIdToDelete = 1L;


        operateurService.deleteOperateur(operateurIdToDelete);


        Mockito.verify(operateurRepository).deleteById(operateurIdToDelete);
    }
    @Test
    public void testUpdateOperateur() {
        Operateur operateur = new Operateur();
        operateur.setIdOperateur(1L);
        operateur.setNom("test update");

        Mockito.when(operateurRepository.save(operateur)).thenReturn(operateur);

        Operateur result = operateurService.updateOperateur(operateur);

        assertNotNull(result);
        Assertions.assertEquals(1L,result.getIdOperateur());
        assertEquals("test update", result.getNom());
    }
    @Test
    public void testRetrieveOperateur() {
        Long operateurId = 99L;
        Operateur operateur = new Operateur();
        operateur.setIdOperateur(operateurId);
        operateur.setNom("mohamed");

        Mockito.when(operateurRepository.findById(operateurId)).thenReturn(Optional.of(operateur));

        Operateur result = operateurService.retrieveOperateur(operateurId);

        assertNotNull(result);
        Assertions.assertEquals(99L, result.getIdOperateur());
        assertEquals("mohamed", result.getNom());
    }
}

