package tn.esprit.rh.achat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class OpTest {

    @InjectMocks
    private OperateurServiceImpl operateurService;

    @Mock
    private OperateurRepository operateurRepository;



    @Test
    public void testRetrieveAllOperateurs() {
        List<Operateur> operateurList = new ArrayList<>();
        operateurList.add(new Operateur());
        operateurList.add(new Operateur());

        when(operateurRepository.findAll()).thenReturn(operateurList);

        List<Operateur> result = operateurService.retrieveAllOperateurs();

        assertEquals(2, result.size());
    }

    @Test
    public void testAddOperateur() {
        Operateur operateur = new Operateur();
        operateur.setNom("karim");
        when(operateurRepository.save(operateur)).thenReturn(operateur);

        Operateur result = operateurService.addOperateur(operateur);

        assertEquals(operateur, result);
        assertEquals("karim", result.getNom());
    }

    @Test
    public void testDeleteOperateur() {
        Long operateurIdToDelete = 1L;


        operateurService.deleteOperateur(operateurIdToDelete);


        verify(operateurRepository).deleteById(operateurIdToDelete);
    }

    @Test
    public void testUpdateOperateur() {
        Operateur operateur = new Operateur();
        operateur.setIdOperateur(1L);
        operateur.setNom("test update");


        when(operateurRepository.save(operateur)).thenReturn(operateur);

        Operateur result = operateurService.updateOperateur(operateur);

        assertNotNull(result);
        assertEquals("test update", result.getNom());
    }
    @Test
    public void testRetrieveOperateur() {
        Long id = 1L;

        Operateur operateur = new Operateur();
        operateur.setIdOperateur(1L);
        operateur.setPrenom("karim");

        when(operateurRepository.findById(id)).thenReturn(java.util.Optional.of(operateur));

        Operateur result = operateurService.retrieveOperateur(id);

        assertEquals(operateur, result);

        assertEquals("karim", result.getPrenom());
    }
}
