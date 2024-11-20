package champollion;

import java.util.HashSet;
import java.util.Set;

public class Enseignant extends Personne {
    private ServicePrevu servicePrevu = new ServicePrevu();
    private Set<Intervention> interventions = new HashSet<>();

    public Enseignant(String nom, String email) {
        super(nom, email);
    }

    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        servicePrevu.ajouterEnseignement(ue, TypeIntervention.CM, volumeCM);
        servicePrevu.ajouterEnseignement(ue, TypeIntervention.TD, volumeTD);
        servicePrevu.ajouterEnseignement(ue, TypeIntervention.TP, volumeTP);
    }

    public int heuresPrevues() {
        return servicePrevu.heuresEquivalentes();
    }

    public int heuresPrevuesPourUE(UE ue) {
        int total = 0;
        total += servicePrevu.getHeuresPourUE(ue, TypeIntervention.CM) * 1.5;
        total += servicePrevu.getHeuresPourUE(ue, TypeIntervention.TD);
        total += servicePrevu.getHeuresPourUE(ue, TypeIntervention.TP) * 0.75;
        return (int) Math.round(total);
    }

    public void ajouteIntervention(Intervention intervention) throws Exception {
        UE ue = intervention.getUe();
        TypeIntervention type = intervention.getType();
        int heuresPlanifiees = intervention.getHeures();

        // Vérifie que l'on ne dépasse pas le service prévu
        int heuresRestantes = servicePrevu.getHeuresPourUE(ue, type) - heuresPlanifiees;
        if (heuresRestantes < 0) {
            throw new Exception("Excédent d'heures pour l'intervention");
        }

        interventions.add(intervention);
    }

    public int resteAPlanifier(TypeIntervention type, UE ue) {
        int heuresPlanifiees = 0;
        for (Intervention intervention : interventions) {
            if (intervention.getUe().equals(ue) && intervention.getType().equals(type)) {
                heuresPlanifiees += intervention.getHeures();
            }
        }
        return servicePrevu.getHeuresPourUE(ue, type) - heuresPlanifiees;
    }
}
