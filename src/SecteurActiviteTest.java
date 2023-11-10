package tn.esprit.rh.achat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.SecteurActiviteServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SecteurActiviteTest {
    @Mock
    SecteurActiviteRepository secteurActiviteRepository;

    @InjectMocks
    SecteurActiviteServiceImpl SecteurActiviteService;

    @Test
    public void createSecteurActiviteTest() {
       SecteurActivite secteurActivite2 = new SecteurActivite(null, "test1.0.0", "test1.0.0", null);
        secteurActivite2.setIdSecteurActivite(2L);

        SecteurActiviteService.addSecteurActivite(secteurActivite2);

        // Verify that the 'save' method of the repository is called with the correct argument
        verify(secteurActiviteRepository, times(1)).save(secteurActivite2);
        System.out.println(secteurActivite2);
        System.out.println("createSecteurActivite -> CA MARCHE  !!!!!");
    }




    @Test
    public void testRetrieveSecteurActivite() {

        SecteurActivite secteurActivite = new SecteurActivite(null,"test","test", null);
        secteurActivite.setIdSecteurActivite(1L);

        when(secteurActiviteRepository.findById(1L)).thenReturn(Optional.of(secteurActivite));
        SecteurActiviteService.retrieveSecteurActivite(1L);
        Assertions.assertNotNull(secteurActivite);

        System.out.println(secteurActivite);
        System.out.println(" testRetrieveSecteurActivite-> CA MARCHE  !!!!!");

    }



    @Test
    public void getAllSecteurActiviteTest()
    {
        List<SecteurActivite> SecteurActivitelist = new ArrayList<SecteurActivite>() {

            {
                add(new SecteurActivite(null,"Jihed","Jihed", null));
                add(new SecteurActivite(null,"Jihed","Jihed", null));
                add(new SecteurActivite(null,"testing","testing", null));
            }};


        when(SecteurActiviteService.retrieveAllSecteurActivite()).thenReturn(SecteurActivitelist);
//test
        List<SecteurActivite> sectList = SecteurActiviteService.retrieveAllSecteurActivite();
        assertEquals(3,sectList.size());
        System.out.println(" getAllSecteurActiviteTest MARCHE !!!! ");

    }

    @Test
    public void TestDeleteSecteurActivite(){

        SecteurActivite SecteurActivite1 = new SecteurActivite(null,"aaaaa","bbbbbbb", null);
        SecteurActivite1.setIdSecteurActivite(7L);



        Mockito.lenient().when(secteurActiviteRepository.findById(SecteurActivite1.getIdSecteurActivite())).thenReturn(Optional.of(SecteurActivite1));

        SecteurActiviteService.deleteSecteurActivite(7L);
        verify(secteurActiviteRepository).deleteById(SecteurActivite1.getIdSecteurActivite());

        System.out.println(SecteurActivite1);
        System.out.println("  TestDeleteSecteurActivite MARCHE ");
    }


    // Test Update SecteurActivite
    @Test
    public void TestUpdateSecteurActivite(){

        SecteurActivite secteurActivite1 = new SecteurActivite(null,"aaaaa","bbbbbbb", null);
        secteurActivite1.setIdSecteurActivite(7L);
        secteurActivite1.setLibelleSecteurActivite("update Test");
        Mockito.lenient().when(secteurActiviteRepository.findById(secteurActivite1.getIdSecteurActivite())).thenReturn(Optional.of(secteurActivite1));
        SecteurActiviteService.updateSecteurActivite(secteurActivite1);
        verify(secteurActiviteRepository).save(secteurActivite1);
        System.out.println(secteurActivite1);
        System.out.println("  TestUpdateSecteurActivite MARCHE ");
    }

}
