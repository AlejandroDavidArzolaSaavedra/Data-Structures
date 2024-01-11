package practica2;
import java.io.*;
/**
 * Clase para realizar pruebas al contenedor lineal
 * @author Alejandro David Arzola Saavedra
 */
public class TestOrderedSequence {
	public static void main(String[] args) {
		RandomAccessFile  r;
		RandomAccessFile  r2;
		FileWriter fichero = null;
		PrintWriter pw = null;
		orderedSequence a = new orderedSequence(100000);
		// 4 bytes int  cada registro  
		try {
			
			fichero = new FileWriter("salida2.txt");
			pw = new PrintWriter(fichero);
			r = new RandomAccessFile("datos.dat","rw");
			r2 = new RandomAccessFile("datos_no.dat","rw");
			int i = 0;
			int p = 0;
			int[] contenedor = new int[100000];
			int[] contenedor_no = new int[20000];
			int valor = 0;
			int valorSiguiente = 10000;
			// Cargamos los valores del fichero datos.dat
			while(i < r.length()) {
				r.seek(i);
				contenedor[p] = r.readInt();
				i+=4;
				p++;
			}
			i = 0;
			p = 0;
			// Cargamos los valores del fichero datos_no.dat
			while(i < r2.length()) {
				r2.seek(i);
				contenedor_no[p] = r2.readInt();
				i+=4;
				p++;
			}
			
			
			// Comprobacion de las insercciones con exito del fichero datos.dat
			
			pw.println("Insercciones:");
			for(int k = 0; k < 10; k++) {
				long tiempoPromedio = System.currentTimeMillis();
				for (int j = valor; j < valorSiguiente; j++) {
					a.insertar(contenedor[j]);
				}
				long nuevoTiempoPromedio = System.currentTimeMillis();
				valor = valorSiguiente;
				valorSiguiente += 10000;
				pw.println(String.format("%.2f",(double)(nuevoTiempoPromedio - tiempoPromedio) / 10.0));
			}
		
			
			//Comprobacion de las extracciones con exito del fichero datos.dat
			
			valor = 0;
			valorSiguiente = 10000;
			pw.println();
			pw.println("Extracciones:");
			for(int k = 0; k < 10; k++) {
				long tiempoPromedio = System.currentTimeMillis();
				for (int j = valor; j < valorSiguiente; j++) {
					a.extraer(contenedor[j]);
				}
				long nuevoTiempoPromedio = System.currentTimeMillis();
				valor = valorSiguiente;
				valorSiguiente += 10000;
				pw.println(String.format("%.2f",(double)(nuevoTiempoPromedio - tiempoPromedio) / 10.0));
			}
			
			
			//  Comprobacion de las busquedas con exito del fichero datos.dat
			
			valor = 0;
			valorSiguiente = 10000;
			pw.println();
			pw.println("Busquedas con exito:");
			for(int k = 1; k < 11; k++) {
				for (int j = valor; j < valorSiguiente; j++) {
					a.insertar(contenedor[j]);
				}
				long tiempoPromedio = System.currentTimeMillis();
				for (int j = 0; j < valorSiguiente; j++) {
					a.buscar(contenedor[j]);
				}
				long nuevoTiempoPromedio = System.currentTimeMillis();
				valor = valorSiguiente;
				valorSiguiente += 10000;
				pw.println(String.format("%.2f",(double)(nuevoTiempoPromedio - tiempoPromedio) / (10 * k)));
			}
			
			
			//  Comprobacion de las busquedas infructuosas del fichero datos_no.dat
			
			valor = 0;
			valorSiguiente = 10000;
			int valorTope = 20000;
			a.vaciar();
			a = new ContenedorDeEnteros(100000);
			pw.println();
			pw.println("Busquedas con infructuosas:");
			for(int k = 0; k < 10; k++) {
				for (int j = valor; j < valorSiguiente; j++) {
					a.insertar(contenedor[j]);
				}
				long tiempoPromedio = System.currentTimeMillis();
				for (int j = 0; j < valorTope; j++) {
					a.buscar(contenedor_no[j]);
				}
				long nuevoTiempoPromedio = System.currentTimeMillis();
				valor = valorSiguiente;
				valorSiguiente += 10000;
				pw.println(String.format("%.2f",(double)(nuevoTiempoPromedio - tiempoPromedio)/20));
			}
			
			conjuntoDePruebas(pw);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(fichero != null) {
					fichero.close();
				}
			}catch(Exception error) {
				error.printStackTrace();
			}
		}
	}
	
	/**
	 * Metodo que comprueba el correto funcionamiento de todas las operaciones
	 */
	public static void  conjuntoDePruebas(PrintWriter pw) {
		if(pruebaCardinalyElementos()) {
			pw.println("Prueba del cardinal() y elementos() falla");
		}
		
		if(pruebaInsertaryExtraer()) {
			pw.println("Prueba de insercciones() y extracciones() falla");
		}
		
		if(pruebaBuscaryVaciar()) {
			pw.println("Prueba del buscar() y vaciar() falla");
		}
	}
	
	/**
	 * Metodo para comprobar el cardinal
	 */
	private static boolean pruebaCardinalyElementos() {
		boolean fallo = false;
		ContenedorDeEnteros a = new ContenedorDeEnteros(20);
		// Probamos insertar 10 elementos
		for(int i = 0; i < 20;i++) {
			a.insertar(i);
		}
		if(a.cardinal() != 20 ^ a.elementos()[0] != 0) {
			fallo = true;
		}
		// Comprobacion de que si nos pasamos del limite no añade mas
		for(int i = 20; i < 100;i++) {
			a.insertar(i);
		}
		if(a.cardinal() != 20 ^ a.elementos()[19] != 19) {
			fallo = true;
		}
		return fallo;
	}
	
	/**
	 * Metodo para comprobar el funcionamiento de insertar en la lista encadenada
	 */
	private static boolean pruebaInsertaryExtraer() {
		boolean fallo = false;
		ContenedorDeEnteros a = new ContenedorDeEnteros(101);
		// Prueba inserccion 100 elementos al reves
		for(int i = 100; i >= 0;i--) {
			a.insertar(i);
		}
		//  Comprobamos el 1 elemento y ultimo 0 y 100 
		if(a.elementos()[0] != 0 ^ a.elementos()[100] != 100) {
			fallo = true;
		}
		// Nos cargamos los elementos pares
		for(int i = 0; i <= 100;i++) {
			if(i%2 == 0 ) {
				a.extraer(i);
			}
		}
		//  Comprobamos el 1 elemento y ultimo impar entre 0 y 100 = (1,99)
		if(a.elementos()[0] != 1 ^ a.elementos()[49] != 99) {
			fallo = true;
		}
		return fallo;
	}
	
	private static boolean pruebaBuscaryVaciar() {
		ContenedorDeEnteros a = new ContenedorDeEnteros(100);
		boolean fallo = false;
		for(int i = 100; i >= 0;i--) {
			a.insertar(i);
		}
		// Comprobamos si se encuentran los elementos introducidos
		if(!a.buscar(5) ^ !a.buscar(100) ^ !a.buscar(50)) {
			fallo = true;
		}
		a.vaciar();
		// Comprobamos si no tiene mas elementos
		if(a.cardinal() != 0) {
			fallo = true;
		}
		return false;
	}
}
