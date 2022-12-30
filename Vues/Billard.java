package exodecorateur_angryballs.maladroit.Vues;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.Vector;

import exodecorateur_angryballs.maladroit.Modele.Bille;

public class Billard extends Canvas
{
    Vector<Bille> billes;

    public Billard(Vector<Bille> billes)
    {
        this.billes = billes;
    }

    public Billard() {}

    public void setBilles(Vector<Bille> billes){
        this.billes = billes;
    }

    @Override
    public void paint(Graphics graphics)
    {
        int i;

        for ( i = 0; i < this.billes.size(); ++i)
            this.billes.get(i).dessine(graphics);

    }
}
