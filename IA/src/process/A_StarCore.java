package process;

import java.util.ArrayList;

import data.Character;
import data.Element;
import data.Hole;
import data.Score;
import data.Target;

public class A_StarCore {
	private Character character;
	private MoovCharacter mv;
	private Grille map;
	private static final int dimMap =100;
	
	private Hole[] holes; //contiendra les r�f�rences des trous(objectif) placer sur la carte
	private int g; //cout du d�placement
	private int h; // h est la fonction heuristique. h (n) estime le co�t pour atteindre l'objectif � partir du n�ud n.
	private int f; //f=g+h
	private ArrayList<Integer> openSet; // L'ensemble des n�uds d�couverts qui peuvent avoir besoin d'�tre (re) d�velopp�s. 
	
	public A_StarCore(int g, int h, int f, Grille map) {
		this.g = g;
		this.h = h;
		this.f = f;
		this.map = map;
		
		//initialisation du personnage
		character = new Character(0,0,new Score());
		mv =new MoovCharacter(character,dimMap);
		
		holes = new Hole [3]; //il y aura min. 3 trou avec diff�rentes formes
		openSet.add(0);	// Initialement, seul le n�ud de d�part est connu. (on utilisera la meme notation que les etat du qlearning)
	}

	/**
	 * Place les trous connu dans le tableau
	 * 
	 * @param hole1
	 * @param hole2
	 * @param hole3
	 */
	public void initHoles(Hole hole1, Hole hole2, Hole hole3) {
		holes[0]=hole1;
		holes[1]=hole2;
		holes[2]=hole3;
	}
	
	/**
	 *fonction qui calculera la distance selon 1 vol d'oiseau ou ligne droite
	 *
	 */
	public void calcH() {
		//calcul
		
		calcF();// une fois le calcul de H mis � jour il faudrai mettre � jour celui de F aussi (normalement)
	}

	/**
	 * calcul f selon g et h
	 */
	public void calcF() {
		f=g+h;
	}
	
	
}
