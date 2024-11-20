package champollion;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date; // Ajout de l'importation pour la classe Date

public class ChampollionJUnitTest {
	Enseignant untel;
	UE uml, java;

	@BeforeEach
	public void setUp() {
		untel = new Enseignant("untel", "untel@gmail.com");
		uml = new UE("UML");
		java = new UE("Programmation en java");
	}

	@Test
	public void testNouvelEnseignantSansService() {
		assertEquals(0, untel.heuresPrevues(), "Un nouvel enseignant doit avoir 0 heures prévues");
	}

	@Test
	public void testAjouteHeures() {
		// 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);

		assertEquals(10, untel.heuresPrevuesPourUE(uml), "L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

		// 20h TD pour UML
		untel.ajouteEnseignement(uml, 0, 20, 0);

		assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml), "L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");
	}

	@Test
	public void testAjoutInterventionException() {
		// Ajout d'enseignement
		untel.ajouteEnseignement(uml, 10, 5, 5); // 10h CM, 5h TD, 5h TP

		// Création d'une intervention invalide de 6h TP (plus que les 5h prévues)
		Intervention intervention1 = new Intervention(uml, TypeIntervention.TP, new Date(), 6, new Salle("Salle A", 50));

		// Vérifier qu'une exception est lancée lorsque les heures dépassent les heures prévues
		assertThrows(Exception.class, () -> {
			untel.ajouteIntervention(intervention1);
		}, "Une exception doit être levée si les heures d'intervention dépassent les heures prévues");
	}


	@Test
	public void testAjoutInterventionExceptionDepassee() {
		// Ajout d'enseignement
		untel.ajouteEnseignement(uml, 10, 5, 5); // 10h CM, 5h TD, 5h TP

		// Création d'une intervention invalide de 6h TP (plus que les 5h prévues)
		Intervention intervention1 = new Intervention(uml, TypeIntervention.TP, new Date(), 6, new Salle("Salle A", 50));

		// Vérifier qu'une exception est lancée lorsque les heures dépassent les heures prévues
		assertThrows(Exception.class, () -> {
			untel.ajouteIntervention(intervention1);
		}, "Une exception doit être levée si les heures d'intervention dépassent les heures prévues");
	}

	@Test
	public void testResteAPlanifier() {
		// Ajout d'enseignements
		untel.ajouteEnseignement(uml, 10, 5, 5); // 10h CM, 5h TD, 5h TP

		// Création d'une intervention
		Intervention intervention1 = new Intervention(uml, TypeIntervention.TP, new Date(), 3, new Salle("Salle A", 50));

		// Ajout de l'intervention
		try {
			untel.ajouteIntervention(intervention1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Vérification des heures restantes à planifier
		int heuresRestantes = untel.resteAPlanifier(TypeIntervention.TP, uml);
		assertEquals(2, heuresRestantes, "Il doit rester 2 heures de TP à planifier pour l'UE 'uml'");
	}
}
