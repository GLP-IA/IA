package process;
import java.util.Random;

import data.Character;
import data.Element;
import data.Score;
import data.Target;
import data.QLearningPara;

public class QLearningCore {
	private Map map;
	private Target t;
	private Character character;
	private QTable qTable;
	
	private QFonction f;
	Random rand = new Random();
	
	
	public QLearningCore(Map map, Target t){
		this.map = map;
		this.t=t;
		character = new Character(0,0);//positionne le personnage
		qTable = new QTable();
		f=new QFonction(qTable,QLearningPara.GAMMA,QLearningPara.ALPHA);
	}

	public void run() {
				double exp=rand.nextDouble();
				if(exp<QLearningPara.GAMMA)
					learning();
				else
					application();
				
				if(map.getCase(character.getCoordX(),character.getCoordY()).getReward()==100) { //on considere que l'objectif a pour recompense 100
					t.setAchieved(true);
					//System.out.println("\n >> Bravo l'objectif est atteint ! <<");
				}
				map.hasMooved(character.getCoordX(),character.getCoordY());
				//debug(exp);
	}
	
	/**
	 * explore al�atoirement la carte
	 */
	public void learning() {
		int reward;	
		int oldX=getOldX();
		int oldY=getOldY();
		
		//generation d'un nombre al�atoirement entre 0 et 4 pour choisir le deplacement a effectue
		int r=rand.nextInt(4);
		
		if(r==0) {
			MoovCharacter.moovUp(character,QLearningPara.DIM_MAP);
			//System.out.println("direction : UP ");
		}
		if(r==1) {
			MoovCharacter.moovDown(character,QLearningPara.DIM_MAP);
			//System.out.println("direction : DOWN ");
		}

		if(r==2) {
			MoovCharacter.moovLeft(character,QLearningPara.DIM_MAP);
			//System.out.println("direction : LEFT ");
		}
	
		if(r==3) {
			MoovCharacter.moovRight(character,QLearningPara.DIM_MAP);
			//System.out.println("direction : RIGHT ");
		}
		
		Element pos=map.getCase(character.getCoordX(),character.getCoordY());
		reward = pos.getReward();
		f.update(character.getCoordX(),character.getCoordY(),oldX,oldY,reward);
	}
	
	/**
	 * utilisation de la QTable pour se deplacer
	 */
	public void application() {
		int oldX=getOldX();
		int oldY=getOldY();
		int reward;
		
		//recup�re l'�tat qui � la plus grande esp�rance de r�compense
		int nextDir=qTable.maxDirection(States.getState(oldX,oldY));

		if(nextDir==0) {
			MoovCharacter.moovUp(character,QLearningPara.DIM_MAP);
			//System.out.println("direction : UP ");
		}
		if(nextDir==1) {
			MoovCharacter.moovDown(character,QLearningPara.DIM_MAP);
			//System.out.println("direction : DOWN ");
		}
		if(nextDir==2) { 
			MoovCharacter.moovLeft(character,QLearningPara.DIM_MAP);
			//System.out.println("direction : LEFT ");
		}
		if(nextDir==3) {
			MoovCharacter.moovRight(character, QLearningPara.DIM_MAP);
			//System.out.println("direction : RIGHT ");
		}
		
		Element pos=map.getCase(character.getCoordX(),character.getCoordY());
		reward=pos.getReward();
		f.update(character.getCoordX(),character.getCoordY(),oldX,oldY,reward);
	}
	
	public int getOldX(){
		return character.getCoordX();
	}
	
	public int getOldY() {
		return character.getCoordY();
	}
	
	/**
	 * permet de remettre les valeurs "par d�faut" pour ainsi relancer le programme 
	 */
	public void reset() {
		character.setCoordY(rand.nextInt(5));
		character.setCoordX(rand.nextInt(5));
		map.hasMooved(character.getCoordX(),character.getCoordY());
		t.setAchieved(false);
	}
	
	/**
	 * r�duit progressivement le taux d'exploration pour ainsi favoriser l'exploitation
	 */
	public void dicreasedExploration() {
		QLearningPara.GAMMA-=0.8;
	}
	
	/**
	 * affiche la qtable une fois remplis
	 */
	public void result() {
		qTable.afficher();
	}
	
	/**
	 * permet de tester en mode console le programme
	 * 
	 * @param exp nombre g�n�r� al�atoirement pour d�finir le choix entre l'exploitation et l'exploration
	 */
	public void debug(double exp) {
		map.printMapQLearning();
		System.out.println("exp: "+exp+ " exploration Rate: "+ QLearningPara.GAMMA);
		if(exp<QLearningPara.GAMMA)
			System.out.println(">> EXPLORATION (deplacement aleatoire) <<");
		else
			System.out.println(">> EXPLOITATION (utilise la Qtable) <<");
		System.out.println("coord:"+character.getCoordX()+","+character.getCoordY());
		map.printMapQLearning();
	}
}