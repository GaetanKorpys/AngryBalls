package exodecorateur_angryballs.maladroit.vues;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import exodecorateur_angryballs.maladroit.AnimationBilles;
import exodecorateur_angryballs.maladroit.modele.Bille;
import musique.SonLong;
import outilsvues.EcouteurTerminaison;

import outilsvues.Outils;

/**
 * Vue dessinant les billes et contenant les 3 boutons de contr�le (arr�t du programme, lancer les billes, arr�ter les billes) 
 * et contenant la ligne des boutons de choix des sons pour la bille hurlante
 * 
 *  ICI : IL N'Y A RIEN A CHANGER 
 *  
 * 
 * */
public class CadreAngryBalls extends Frame implements VueBillard, KeyListener
{
    TextField pr�sentation;
    Billard billard;
    public Button lancerBilles, arr�terBilles, quitter;
    Panel haut, centre, bas, ligneBoutonsLancerArr�t;
    PanneauChoixHurlement ligneBoutonsChoixHurlement;
    EcouteurTerminaison ecouteurTerminaison;
    GraphicsDevice graphicsDevice;
    AnimationBilles animationBilles;

    public CadreAngryBalls(String titre, String message, Vector<Bille> billes, SonLong [] hurlements, int choixHurlementInitial) throws HeadlessException
    {
        super(titre);

        this.setIgnoreRepaint(true);
        this.setUndecorated(true);

        Outils.place(this, 0.33, 0.33, 0.5, 0.5);
        this.ecouteurTerminaison = new EcouteurTerminaison(this);


        this.haut = new Panel(); this.haut.setBackground(Color.LIGHT_GRAY);
        this.add(this.haut,BorderLayout.NORTH);

        this.centre = new Panel();
        this.add(this.centre,BorderLayout.CENTER);

        this.bas = new Panel(); this.bas.setBackground(Color.LIGHT_GRAY);
        this.add(this.bas,BorderLayout.SOUTH);

        this.pr�sentation = new TextField(message, 100); this.pr�sentation.setEditable(false);
        this.haut.add(this.pr�sentation);

        this.billard = new Billard(billes);
        this.billard.addKeyListener(this);
        this.add(this.billard);

        //------------------- placement des composants du bas du cadre -------------------------------

        int nombreLignes = 2, nombreColonnes = 1;

        this.bas.setLayout(new GridLayout(nombreLignes, nombreColonnes));

        //---------------- placement des boutons lancer - arr�ter ------------------------------------

        this.ligneBoutonsLancerArr�t = new Panel();
        this.bas.add(this.ligneBoutonsLancerArr�t);


        this.lancerBilles = new Button("Lancer les billes");
        this.ligneBoutonsLancerArr�t.add(this.lancerBilles);

        this.arr�terBilles = new Button("Arr�ter les billes");
        this.ligneBoutonsLancerArr�t.add(this.arr�terBilles);

        this.quitter = new Button("Quitter");
        this.ligneBoutonsLancerArr�t.add(this.quitter);

        //---------------- placement de la ligne de boutons de choix des sons pour le hurlement ------

        this.ligneBoutonsChoixHurlement = new PanneauChoixHurlement(hurlements, choixHurlementInitial);
        this.bas.add(this.ligneBoutonsChoixHurlement);

    }

    public double largeurBillard()
    {
    return this.billard.getWidth();
    }

    public double hauteurBillard()
    {
    return this.billard.getHeight();
    }

    @Override
    public void miseAJour()
    {
    this.billard.repaint();
    }

    /* (non-Javadoc)
     * @see exodecorateur.vues.VueBillard#montrer()
     */
    @Override
    public void montrer()
    {
    this.setVisible(true);
    }

    public void addChoixHurlementListener(ItemListener �couteurChoixHurlant)
    {
        int i;
        for ( i = 0; i < this.ligneBoutonsChoixHurlement.boutons.length; ++i) this.ligneBoutonsChoixHurlement.boutons[i].addItemListener(�couteurChoixHurlant);
    }

    public void setGraphics(GraphicsDevice gd) {graphicsDevice = gd;}

    public GraphicsDevice getGraphicsDevice(){return graphicsDevice;}

    public void setAnimationBilles (AnimationBilles animationBilles){this.animationBilles = animationBilles;}

    @Override
    public void addMouseListener(MouseListener mouseListener){billard.addMouseListener(mouseListener);}

    @Override
    public void addMouseMotionListener(MouseMotionListener mouseMotionListener){billard.addMouseMotionListener(mouseMotionListener);}

    @Override
    public void keyPressed(KeyEvent e) {
        if( e.getKeyCode() == KeyEvent.VK_ESCAPE )
        {
            if(animationBilles != null)
                animationBilles.quitter();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Todo
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Todo
    }
}