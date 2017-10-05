import java.util.*;

 enum Posicion {
		Left, Right
	}

/**
 * @author chunel
 *
 */
 // Cr>Mr || CL >ML
public class Estados {
	
	public int CL, ML, CR, MR;
	public Posicion bote;
	public Estados estadoP;
	public List<Estados> nodos = new ArrayList<Estados>();
	

	public Estados(int CL, int ML, Posicion bote, int CR, int MR) {
		this.setCL(CL);
		this.setML(ML);
		this.setBote(bote);
		this.setCR(CR);
		this.setMR(MR);
	}
	
	public boolean isgoal(){
		return CR==0 && MR==0;
	}
	
	public boolean isValid(){
		if(CL>=0 && ML>=0 && CR>=0 && MR>=0 
				&&(ML==0 || ML>=CL)
				&&(MR==0 || MR>=CR)){
			return true;
		}
		return false;
	}
	
	public boolean isValidChingon(){
		boolean result = true;
		if(ML>3 || ML < 0 || MR>3 || MR < 0 || CL>3 || CL < 0 || CR>3 || CR < 0){
			result = false;
		}else if(ML != 0 && ML<CL){
			result = false;
		}else if(MR != 0 && MR<CR){
			result = false;
		}else{
			result = true;
		}
		return result;
	}
	
	private void addN(List<Estados> N, Estados EstadoNuevo){
		if(EstadoNuevo.isValidChingon() == true){
			EstadoNuevo.setEstadoP(this);
			N.add(EstadoNuevo);
			Object[] st = N.toArray();
		      for (Object s : st) {
		        if (N.indexOf(s) != N.lastIndexOf(s)) {
		            N.remove(N.lastIndexOf(s));
		         }
		      }
		}
	}
	
	private void addN(Estados EstadoNuevo){
		if(EstadoNuevo.isValidChingon() == true){
			EstadoNuevo.setEstadoP(this);
			nodos.add(EstadoNuevo);
			Object[] st = nodos.toArray();
		      for (Object s : st) {
		        if (nodos.indexOf(s) != nodos.lastIndexOf(s)) {
		        	nodos.remove(nodos.lastIndexOf(s));
		         }
		      }
		}
	}
	
	public List<Estados> GenerateStates(){
		if(bote==Posicion.Left){
			addN(nodos,new Estados(CL, ML - 2, Posicion.Right, CR, MR + 2)); //Dos Misioneros Pasan de Izq a Der.
			addN(nodos,new Estados(CL - 2, ML, Posicion.Right, CR + 2, MR)); //Dos Canibales Pasan de Izq a Der.
			addN(nodos,new Estados(CL - 1, ML - 1, Posicion.Right, CR + 1, MR + 1)); //Un Canibal y Un Misionero pasan de Izq a Derecha
			addN(nodos,new Estados(CL, ML - 1, Posicion.Right, CR, MR + 1)); //Un Misionero pasa de izq a Der.
			addN(nodos,new Estados(CL - 1, ML, Posicion.Right, CR + 1, MR)); //Un Canibal pasa de izq a Der. 
		}else {
			addN(nodos,new Estados(CL, ML + 2, Posicion.Left, CR, MR - 2)); //Dos Misioneros Pasan de Izq a Der.
			addN(nodos,new Estados(CL + 2, ML, Posicion.Left, CR - 2, MR)); //Dos Canibales Pasan de Izq a Der.
			addN(nodos,new Estados(CL + 1, ML + 1, Posicion.Left, CR - 1, MR - 1)); //Un Canibal y Un Misionero pasan de Izq a Derecha
			addN(nodos,new Estados(CL, ML + 1, Posicion.Left, CR, MR - 1)); //Un Misionero pasa de izq a Der.
			addN(nodos,new Estados(CL + 1, ML, Posicion.Left, CR - 1, MR)); //Un Canibal pasa de izq a Der. 
		}
		return nodos;
	}
	
	public void GenerateStates2(){
		Estados nuevo;
		if(isValid()){
			nuevo = new Estados(CL, ML, bote, CR, MR);
			if(isOnLeft()){
				nuevo.nodos.add(new Estados(CL, ML - 2, Posicion.Right, CR, MR + 2));
				nuevo.nodos.add(new Estados(CL - 2, ML, Posicion.Right, CR + 2, MR));
				nuevo.nodos.add(new Estados(CL - 1, ML - 1, Posicion.Right, CR + 1, MR + 1));
				nuevo.nodos.add(new Estados(CL, ML - 1, Posicion.Right, CR, MR + 1));
				nuevo.nodos.add(new Estados(CL - 1, ML, Posicion.Right, CR + 1, MR));
			}else{
				nuevo.nodos.add(new Estados(CL, ML + 2, Posicion.Left, CR, MR - 2));
				nuevo.nodos.add(new Estados(CL + 2, ML, Posicion.Left, CR - 2, MR));
				nuevo.nodos.add(new Estados(CL + 1, ML + 1, Posicion.Left, CR - 1, MR - 1));
				nuevo.nodos.add(new Estados(CL, ML + 1, Posicion.Left, CR, MR - 1));
				nuevo.nodos.add(new Estados(CL + 1, ML, Posicion.Left, CR - 1, MR));
			}
		}
	}

	public void goToLeft() {
		bote = Posicion.Left;
	}

	public void goToRight() {
		bote = Posicion.Right;
	}

	public boolean isOnLeft() {
		return bote == Posicion.Left;
	}

	public boolean isOnRigth() {
		return bote == Posicion.Right;
}


	public int getCL() {
		return CL;
	}


	public void setCL(int cL) {
		CL = cL;
	}


	public int getML() {
		return ML;
	}


	public void setML(int mL) {
		ML = mL;
	}


	public int getCR() {
		return CR;
	}


	public void setCR(int cR) {
		CR = cR;
	}


	public int getMR() {
		return MR;
	}


	public void setMR(int mR) {
		MR = mR;
	}


	public Posicion getBote() {
		return bote;
	}


	public void setBote(Posicion bote) {
		this.bote = bote;
	}


	public Estados getEstadoP() {
		return estadoP;
	}


	public void setEstadoP(Estados estadoP) {
		this.estadoP = estadoP;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Estados)) {
			return false;
		}
		Estados s = (Estados) obj;
        return (s.CL == CL && s.ML == ML
        		&& s.bote == bote && s.CR == CR
&& s.MR == MR);
	}

	@Override
	public String toString() {
		if(bote == Posicion.Left){
			return "(" + CL + "," + ML + ",Izq,"
					+ CR + "," + MR + ")";
		}else{
			return "(" + CL + "," + ML + ",Der,"
					+ CR + "," + MR + ")";
		}
	}
	
	

}


