package champollion;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {
            // Création des enseignants, salles et UE
            Enseignant enseignant = new Enseignant("John Doe", "johndoe@example.com");
            Salle salle = new Salle("Salle A", 50);
            UE uml = new UE("UML");

            // Ajout d'enseignements
            enseignant.ajouteEnseignement(uml, 10, 5, 5);  // 10h CM, 5h TD, 5h TP

            // Ajout d'interventions
            Intervention intervention1 = new Intervention(uml, TypeIntervention.TP, new Date(), 3, salle);
            enseignant.ajouteIntervention(intervention1); // On ajoute une intervention TP de 3 heures

            // Affichage du service prévu et des heures restantes à planifier
            System.out.println("Heures prévues pour 'UML': " + enseignant.heuresPrevuesPourUE(uml));
            System.out.println("Heures restantes pour 'UML' TP: " + enseignant.resteAPlanifier(TypeIntervention.TP, uml));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
