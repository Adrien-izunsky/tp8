package champollion;

import java.util.Date;

public class Intervention {
    private UE ue;
    private TypeIntervention type;
    private Date date;
    private int heures;
    private Salle salle;

    public Intervention(UE ue, TypeIntervention type, Date date, int heures, Salle salle) {
        this.ue = ue;
        this.type = type;
        this.date = date;
        this.heures = heures;
        this.salle = salle;
    }

    public UE getUe() {
        return ue;
    }

    public TypeIntervention getType() {
        return type;
    }

    public int getHeures() {
        return heures;
    }

    public Salle getSalle() {
        return salle;
    }
}
