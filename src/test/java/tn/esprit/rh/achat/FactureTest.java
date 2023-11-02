package tn.esprit.rh.achat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.rh.achat.entities.*;
import tn.esprit.rh.achat.repositories.*;
import tn.esprit.rh.achat.services.*;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class FactureTest {

    @Mock
    private FactureRepository factureRepository;

    @Mock
    private OperateurRepository operateurRepository;

    @Mock
    private DetailFactureRepository detailFactureRepository;

    @Mock
    private FournisseurRepository fournisseurRepository;

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private ReglementServiceImpl reglementService;

    @InjectMocks
    private FactureServiceImpl factureService;

    @Test
    public void retrieveAllFactures_shouldReturnListOfFactures() {
        List<Facture> expectedFactures = new ArrayList<>();

        Mockito.when(factureRepository.findAll()).thenReturn(expectedFactures);

        List<Facture> actualFactures = factureService.retrieveAllFactures();

        assertEquals(expectedFactures, actualFactures);
    }

    @Test
    public void addFacture_shouldReturnFacture() {
        Facture factureToAdd = new Facture();
        Mockito.when(factureRepository.save(Mockito.any(Facture.class))).thenReturn(factureToAdd);

        Facture addedFacture = factureService.addFacture(factureToAdd);

        assertEquals(factureToAdd, addedFacture);
    }

    // Write similar tests for other methods

    @Test
    public void cancelFacture_shouldSetArchiveeToTrue() {
        Long factureId = 1L; // Replace with an actual facture ID
        Facture facture = new Facture();
        facture.setArchivee(false);

        Mockito.when(factureRepository.findById(factureId)).thenReturn(Optional.of(facture));

        factureService.cancelFacture(factureId);

        assertEquals(true, facture.getArchivee());
    }

    @Test
    public void retrieveFacture_shouldReturnFacture() {
        Long factureId = 1L; // Replace with an actual facture ID
        Facture expectedFacture = new Facture(); // Create a mock Facture object

        Mockito.when(factureRepository.findById(factureId)).thenReturn(Optional.of(expectedFacture));

        Facture actualFacture = factureService.retrieveFacture(factureId);

        assertEquals(expectedFacture, actualFacture);
    }

    @Test
    public void getFacturesByFournisseur_shouldReturnListOfFactures() {
        Long fournisseurId = 1L;
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setIdFournisseur(fournisseurId);
        List<Facture> expectedFactures = new ArrayList<>(); // Populate expectedFactures with some mock Facture objects

        Mockito.when(fournisseurRepository.findById(fournisseurId)).thenReturn(Optional.of(fournisseur));
        Mockito.when(fournisseur.getFactures()).thenReturn((Set<Facture>) expectedFactures);

        List<Facture> actualFactures = factureService.getFacturesByFournisseur(fournisseurId);

         assertEquals(expectedFactures, actualFactures);
    }

    @Test
    public void assignOperateurToFacture_shouldAddFactureToOperateur() {
        Long idOperateur = 1L;
        Long idFacture = 2L;

        Operateur operateur = new Operateur();
        Facture facture = new Facture();

        Mockito.when(operateurRepository.findById(idOperateur)).thenReturn(Optional.of(operateur));
        Mockito.when(factureRepository.findById(idFacture)).thenReturn(Optional.of(facture));

        factureService.assignOperateurToFacture(idOperateur, idFacture);

        assertEquals(true, operateur.getFactures().contains(facture));
    }


}
