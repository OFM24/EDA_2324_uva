/**
 * 
 * @author Oscar De la Fuente Marín
 * @author Daniel Fernandez Garcia
 * 
 * El algoritmo se funciona de tal forma que en una matriz n*n de enteros , se marca a cero los atomos no constructores , 
 *	a 1 , los conductores y a 2 los conductores mediante los cuales se puede llegar a un atomo que este en la posicion i = n-1
 *	, y en el momento que marquemos un atomo con un 2 mediante a una funcion recursiva , significaria que hay un camino posible desde 
 *	el inicio al finde la placa .
 *
 */
public class CeldaAvanzada implements Celda {
	private int camino[][];
	private boolean cortocircuito;
	private int ultimo_rayo_i;
	private int ultimo_rayo_j;
	private int m;

	/**
	 * @para n Tamaño de la celda atomica
	 * Inicializa todas las posiciones de la funcion a cero, lo cual significa que todos los 
	 * atomos de la celda son aislantes
	 */
	public void Inicializar(int n) {
		m = n-2;
		camino = new int[n][n];
		cortocircuito = false;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				camino[i][j] = 0;
			}
		}
	}
	
	/**
	 * @param i Numero de fila de celda (posicion x) del atomo
	 * @param j Numero de columna de celda (posicion y) del atomo
	 * Si nos encontramos en la ultima fila de la matriz llamamos a la funcion recursiva indicando que hay camino,
	 * en cualquier otro caso llamamos a la funcion adyacente_conductor para comprobar si hay camino a traves de 
	 * alguno de sus vecinos.
	 */
	public void RayoCosmico(int i, int j) {
		if (camino[i][j]==0) {			
			camino[i][j] = 1;
			ultimo_rayo_i = i;
			ultimo_rayo_j = j;
			if (i == m + 1)recursiva(i, j);
				adyacente_conductor(i, j);			
		}
	}
	
	/**
	 * 
	 * @param i Numero de fila de celda (posicion x) del atomo
	 * @param j Numero de columna de celda (posicion y) del atomo
	 * Comprueba si puede hay camino a partir de alguno de los vecinos de la posicion i,j y
	 * si se cumple para alguno de ellos, llama a la funcion recursiva
	 */
	public void adyacente_conductor(int i, int j) {
		if(i <= m) {
			if(camino[i+1][j] == 2) {
				recursiva(i+1,j);
			}
			if(j > 0 && camino[i+1][j-1]== 2) {
				recursiva(i+1,j-1);
			}
			if(j <= (m) && camino[i+1][j+1]== 2 ) {
				recursiva(i+1,j+1);
			}
		}
		if(j <= m) {
			if( camino[i][j+1]== 2 ) {
				recursiva(i,j+1);
			}
			if(i>0  && camino[i-1][j+1]== 2) {
				recursiva(i-1,j+1);
			}
		}
		if(i>0) {
			if(camino[i-1][j]== 2) {
				recursiva(i-1,j);
			}
			if(j>0 && camino[i-1][j-1]== 2) {
				recursiva(i-1,j-1);
			}
		}
		if(j>0) {
			if(camino[i][j-1]== 2 ) {
				recursiva(i,j-1);
			}
		}
	}
	
	/**
	 * 
	 * @param i Numero de fila de celda (posicion x) del atomo
	 * @param j Numero de columna de celda (posicion y) del atomo
	 * Indica que hay camino a partir de la posicion actual y comprueba si alguno de sus vecinos
	 * son conductores para repetir el proceso de forma recursiva
	 */
	public void recursiva(int i, int j) {
		camino[i][j] = 2;
		if (i== 0) {
			cortocircuito = true;}
		if(i <= m) {
			if(camino[i+1][j] == 1) {
				recursiva(i+1,j);
			}
			if(j > 0 && camino[i+1][j-1]== 1) {
				recursiva(i+1,j-1);
			}
			if(j <= (m) && camino[i+1][j+1]== 1 ) {
				recursiva(i+1,j+1);
			}
		}
		if(j <= m) {
			if( camino[i][j+1]== 1 ) {
				recursiva(i,j+1);
			}
			if(i>0  && camino[i-1][j+1]== 1) {
				recursiva(i-1,j+1);
			}
		}
		if(i>0) {
			if(camino[i-1][j]== 1) {
				recursiva(i-1,j);
			}
			if(j>0 && camino[i-1][j-1]== 1) {
				recursiva(i-1,j-1);
			}
		}
		if(j>0) {
			if(camino[i][j-1]== 1 ) {
				recursiva(i,j-1);
			}
		}
	}
	
	/**
	 * @return un booleano que indica si se ha cortocircuitado la celda o no  
	 */
	public boolean Cortocircuito() {
		return cortocircuito;
	}
	
	/**
	 * Escribe en una cadena el contenido de la matriz camino, represenatremos con una "X" los atomos
	 * conductores, con un "." los atomos aislantes y con un "*" el ultimo atomo cortocircuitado.
	 * @return un String que representa la celda atomica
	 */
	public String toString() {
		String celda_atom = "";
		for (int i = 0; i < m+2; i++) {
			for (int j = 0; j < m+2; j++) {
				celda_atom += i == ultimo_rayo_i && j == ultimo_rayo_j ? "*" : (camino[i][j]==0) ? "." : "X";
			}
			celda_atom += "\n";
		}
		return celda_atom;
	}
}