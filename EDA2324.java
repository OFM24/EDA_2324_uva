

import java.util.Random;
import java.util.Scanner;

public class EDA2324 {

	static int SEMILLA = 42;

	public static void main(String[] args) {
		Random rnd = new Random(SEMILLA);
		Scanner teclado = new Scanner(System.in);
		System.out.print("�Validaci�n? [S/N] ");
		boolean validar = teclado.nextLine().startsWith("S");
		System.out.print("Tama�o (n) = ");
		int n = teclado.nextInt();
		int num_rep = 1;
		if (!validar) {
			System.out.print("Repeticiones = ");
			num_rep = teclado.nextInt();
		}
		Celda celda = new CeldaSimple();
		long tpo_total = 0;
		int num_rayos = 0;
		for (int k = 0; k < num_rep; k++) {
			// Simulaci�n
			celda.Inicializar(n);
			num_rayos = 0;
			long tpo1 = System.nanoTime();
			while (!celda.Cortocircuito()) {
				// Elegir �tomo al azar y transmutarlo
				int i = rnd.nextInt(n), j = rnd.nextInt(n);
				celda.RayoCosmico(i, j);
				num_rayos++;
			}
			long tpo2 = System.nanoTime();
			tpo_total += tpo2 - tpo1;
			System.out.print("#");
		}
		System.out.printf("\nTiempo medio = %.5f\n", 1e-9 * tpo_total / num_rep);
		if (validar) {
			System.out.println("N�mero de rayos = " + num_rayos);
			System.out.println(celda);
		}
	}
}
