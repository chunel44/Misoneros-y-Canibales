import java.util.*;

public class Main {
	static int m1,m2,c1,c2;
	static List<Estados> resList = new ArrayList<>();
	
	public static void main(String[] args) {
		
	
		Estados nodo = randomEstado();
		Estados nodos1 = new Estados(3, 1, Posicion.Right, 0, 2);
		
		if(randomP()==Posicion.Left){
			System.out.println("Estado inicial"+": "+"("+c1+","+m1+","+"Izq"+","+c2+","+m2+")");
		}else{
			System.out.println("Estado inicial"+": "+"("+c1+","+m1+","+"Der"+","+c2+","+m2+")");
		}
		
		if(isValid(nodo) == true){
			printSolution(recursiveDLS(nodo, 10));
		}else{
			System.out.println("Estado error");
		}

	}
	
	public static boolean isValid(Estados e){
		if(e.CL>=0 && e.ML>=0 && e.CR>=0 && e.MR>=0 
				&&(e.ML==0 || e.ML>=e.CL)
				&&(e.MR==0 || e.MR>=e.CR)){
			return true;
		}
		return false;
	}
	
	
	
	private static Posicion randomP(){
		Random r = new Random();
		if(r.nextDouble()<0.5){
			return Posicion.Right;
		}else{return Posicion.Left;}
	}
	
	private static Estados randomEstado(){
		Boolean flag = true;
		while(flag){
		m1 = (int)(Math.random()*(3-0+1)+0); 
		m2 = (int)(Math.random()*(3-0+1)+0);
		c1 = (int)(Math.random()*(3-0+1)+0);
		c2 = (int)(Math.random()*(3-0+1)+0);
		if(m1+m2==3 && c1+c2==3){
			flag =false;
		}
		}
		Estados nodo = new Estados(c1, m1, randomP(), c2, m2);
		return nodo;
	}
	
	private static Estados recursiveDLS(Estados state, int limit) {
		if (state.isgoal()) {
			return state;
		} else if (limit == 0) {
			return null;
		} else {
			List<Estados> successors = state.GenerateStates();
			for (Estados hijos : successors) {
				Estados result = recursiveDLS(hijos, limit - 1);
				if (null != result) {
					return result;
				}
			}
			return null;
		}
	}
	
	private static List<Estados> dFS(Estados state) {
		Estados aux;
		if(state.isgoal()){
			resList.add(state);
		}else{
		state.GenerateStates2();
		for (int i = 0; i < state.nodos.size(); i++)  {
			resList = dFS(state.nodos.get(i));
			/*if(aux != null){
				return aux;
			}*/
		}
		}
		return null;
	}
	

	
	private static void printSolution(Estados solution) {
		if (null == solution) {
			System.out.print("\nNo se Encontro Solución");
		} else {
			System.out.println("\nSolucion (CanibalIzq,MisioneroIzq,Bote,CanibalDer,MisioneroDer): ");
			List<Estados> path = new ArrayList<Estados>();
			Estados state = solution;
			while(null!=state) {
				path.add(state);
				state = state.getEstadoP();
			}
			int depth = path.size() - 1;
			for (int i = 0; i<path.size();i++) {
				state = path.get(i);
				if (state.isgoal()) {
					System.out.println(state.toString() + "<------- Estado de Solución");
				} else {
					System.out.println(state.toString() );
				}
			}
			System.out.println("\nTamaño: " + depth);
			
		}
	}

}
