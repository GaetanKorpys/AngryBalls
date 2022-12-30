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
 * Vue dessinant les billes et contenant les 3 boutons de contrôle (arrêt du programme, lancer les billes, arréter les billes) 
 * et contenant la ligne des boutons de choix des sons pour la bille hurlante
 * 
 *  ICI : IL N'Y A RIEN A CHANGER 
 *  
 * 
 * */
public class CadreAngryBalls extends Frame implements VueBillard, KeyListener
{
    TextField présentation;
    Billard billard;
    public Button lancerBilles, arrêterBilles, quitter;
    Panel haut, centre, bas, ligneBoutonsLancerArrêt;
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

        this.présentation = new TextField(message, 100); this.présentation.setEditable(false);
        this.haut.add(this.présentation);

        this.billard = new Billard(billes);
        this.billard.addKeyListener(this);
        this.add(this.billard);

        //------------------- placement des composants du bas du cadre -------------------------------

        int nombreLignes = 2, nombreColonnes = 1;

        this.bas.setLayout(new GridLayout(nombreLignes, nombreColonnes));

        //---------------- placement des boutons lancer - arrêter ------------------------------------

        this.ligneBoutonsLancerArrêt = new Panel();
        this.bas.add(this.ligneBoutonsLancerArrêt);


        this.lancerBilles = new Button("Lancer les billes");
        this.ligneBoutonsLancerArrêt.add(this.lancerBilles);

        this.arrêterBilles = new Button("Arrêter les billes");
        this.ligneBoutonsLancerArrêt.add(this.arrêterBilles);

        this.quitter = new Button("Quitter");
        this.ligneBoutonsLancerArrêt.add(this.quitter);

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

    public void addChoixHurlementListener(ItemListener écouteurChoixHurlant)
    {
        int i;
        for ( i = 0; i < this.ligneBoutonsChoixHurlement.boutons.length; ++i) this.ligneBoutonsChoixHurlement.boutons[i].addItemListener(écouteurChoixHurlant);
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