package champollion;

import java.util.HashMap;
import java.util.Map;

public class ServicePrevu {
    private Map<UE, Map<TypeIntervention, Integer>> enseignements = new HashMap<>();

    // Ajoute un enseignement pour une UE et un type d'intervention
    public void ajouterEnseignement(UE ue, TypeIntervention type, int heures) {
        enseignements.computeIfAbsent(ue, k -> new HashMap<>())
                .merge(type, heures, Integer::sum);
    }

    // Récupère les heures totales pour une UE et un type d'intervention
    public int getHeuresPourUE(UE ue, TypeIntervention type) {
        return enseignements.getOrDefault(ue, new HashMap<>()).getOrDefault(type, 0);
    }

    // Calcule le total des heures équivalentes
    public int heuresEquivalentes() {
        double total = 0; // Changer le type total en double pour permettre les calculs avec des valeurs décimales
        for (Map<TypeIntervention, Integer> interventions : enseignements.values()) {
            for (Map.Entry<TypeIntervention, Integer> entry : interventions.entrySet()) {
                int heures = entry.getValue();
                if (entry.getKey() == TypeIntervention.CM) {
                    total += heures * 1.5;  // CM => 1.5 heures TD
                } else if (entry.getKey() == TypeIntervention.TD) {
                    total += heures;  // TD => 1 heure TD
                } else if (entry.getKey() == TypeIntervention.TP) {
                    total += heures * 0.75;  // TP => 0.75 heures TD
                }
            }
        }
        return (int) total; // Convertir le total en int après le calcul
    }



}
