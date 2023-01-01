package exodecorateur_angryballs.maladroit.Modele.Decorateur;

import exodecorateur_angryballs.maladroit.Modele.Bille;
import exodecorateur_angryballs.maladroit.Modele.DecorateurBille;
import exodecorateur_angryballs.maladroit.Modele.State.BilleAttrapableState;
import exodecorateur_angryballs.maladroit.Modele.State.BilleAttrapeState;
import exodecorateur_angryballs.maladroit.Modele.State.State;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Vector;


public class BillePilote extends DecorateurBille {

    State currentState;
    BilleAttrapableState attrapableState;
    BilleAttrapeState attrapeState;
    MouseEvent e;

    public BillePilote(Bille bille) {
        super(bille);
        construireGrapheState();
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    private void construireGrapheState() {
        this.attrapableState = new BilleAttrapableState(this, null, null);
        this.attrapeState = new BilleAttrapeState(this, this.attrapableState, this.attrapableState);
        this.attrapableState.setPrevious(this.attrapeState);
        this.attrapableState.setNext(this.attrapeState);
        this.currentState = this.attrapableState;
    }

    @Override
    public void sendEventToCor(MouseEvent e) {
        this.e = e;
        this.currentState.sendEventToCor(e);
    }

    //On dessine une bille supplémentaire à l'intérieur de la bille pilotée pour confirmer qu'elle est influencée par la souris
    public void dessine(Graphics g) {
        _decoredBille.dessine(g);
        currentState.dessine(this,g);
    }

    //Selon l'état de la bille on gère l'accélération différement (attrapable on ne fait, attrapee on modifie l'accélération)
    public void gestionAccélération(Vector<Bille> billes) {
        _decoredBille.gestionAccélération(billes);
        currentState.gestionAccélération(this,billes,e);
    }
}
