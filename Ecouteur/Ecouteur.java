package exodecorateur_angryballs.maladroit.Ecouteur;

import exodecorateur_angryballs.maladroit.Observeur.Diffuseur.Diffuseur;

public abstract class Ecouteur {

    private Diffuseur diffuseur;

    public Ecouteur(Diffuseur diffuseur) {
        this.diffuseur = diffuseur;
    }

    public Diffuseur getDiffuseur() {
        return this.diffuseur;
    }
}

