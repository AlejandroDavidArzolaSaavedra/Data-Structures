package practica1;
import java.io.*;
/**
 * Clase para realizar pruebas al contenedor lineal
 * @author: Alejandro David Arzola Saavedra
 */

public class TestLinkedList {
	public static void main(String[] args) {
		RandomAccessFile  r;
		RandomAccessFile  r2;
		FileWriter fichero = null;
		PrintWriter pw = null;
		LinkedList a = new LinkedList();
		
		// 4 bytes int  cada registro  
		try {
			
			fichero = new FileWriter("output1.txt");
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
			
			/**
			 * Comprobacion de las insercciones con exito del fichero datos.dat
			 */
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
		
			/**
			 * Comprobacion de las extracciones con exito del fichero datos.dat
			 */
			valor = 0;
			valorSiguiente = 10000;
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
			
			/**
			 * Comprobacion de las busquedas con exito del fichero datos.dat
			 */
			valor = 0;
			valorSiguiente = 10000;
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
			
			/**
			 * Comprobacion de las busquedas infructuosas del fichero datos_no.dat
			 */
			valor = 0;
			valorSiguiente = 10000;
			int valorTope = 20000;
			a.vaciar();
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
		pw.println();
		if(pruebaCardinal()) {
			pw.println("Prueba del cardinal falla");
		}
		
		if(pruebaInsertar()) {
			pw.println("Prueba del insertar falla");
		}
		
		if(pruebaExtraer()) {
			pw.println("Prueba del extraer falla");
		}
		
		if(pruebaBuscar()) {
			pw.println("Prueba del buscar falla");
		}
		
		if(pruebaVaciar()) {
			pw.println("Prueba de vaciar falla");
		}
		
		if(pruebaElementos()) {
			pw.println("Prueba de elementos falla");
		}
	}
	
	/**
	 * Metodo para comprobar el cardinal
	 */
	private static boolean pruebaCardinal() {
		boolean fallo = false;
		ContenedorDeEnteros a = new ContenedorDeEnteros();
		// Prueba con 0 elementos
		if(a.cardinal() != 0) {
			fallo = true;
		}
		a.insertar(0);
		// Prueba con 1 elemento
		if(a.cardinal() != 1) {
			fallo = true;
		}
		a.insertar(1);
		// Prueba con 2 elementos
		if(a.cardinal() != 2) {
			fallo = true;
		}
		a.insertar(2);
		a.insertar(3);
		a.insertar(4);
		a.insertar(5);
		// Prueba con varios elementos
		if(a.cardinal() != 6) {
			fallo = true;
		}
		
		return fallo;
	}
	
	/**
	 * Metodo para comprobar el funcionamiento de insertar en la lista encadenada
	 */
	private static boolean pruebaInsertar() {
		boolean fallo = false;
		ContenedorDeEnteros a = new ContenedorDeEnteros();
		// Prueba insertar varios elementos
		a.insertar(0);
		a.insertar(1);
		a.insertar(2);
		a.insertar(3);
		// Prueba insertar un elemento repetido
		a.insertar(3);
		if(a.cardinal() != 4) {
			fallo = true;
		}
		// Prueba insertar un extraido recientemente
		a.extraer(0);
		a.insertar(0);
		if(a.cardinal() != 4) {
			fallo = true;
		}
		return fallo;
	}
	
	/**
	 * Metodo para comprobar el funcionamiento de extraer en la lista encadenada
	 */
	private static boolean pruebaExtraer() {
		boolean fallo = false;
		ContenedorDeEnteros a = new ContenedorDeEnteros();
		// Prueba Extraer con 0 valores
		a.extraer(0);
		if(a.cardinal() != 0) {
			fallo = true;
		}
		// Prueba Extraer con 1 elemento
		a.insertar(0);
		a.extraer(0);
		if(a.cardinal() != 0) {
			fallo = true;
		}
		// Prueba Extraer con 2 elementos
		a.insertar(0);
		a.insertar(1);
		a.extraer(1);
		if(a.cardinal() != 1) {
			fallo = true;
		}
		for(int i = 1; i < 10; i++) {
			a.insertar(i);
		}
		// Prueba Extraer con n valores al principio
		a.extraer(0);
		if(a.buscar(0)) {
			fallo = true;
		}
		a.insertar(0);
		// Prueba Extraer con n valores en medio
		a.extraer(4);
		if(a.buscar(4)) {
			fallo = true;
		}
		a.insertar(4);
		// Prueba Extraer con n valores al final
		a.extraer(9);
		if(a.buscar(9)) {
			fallo = true;
		}
		return fallo;
	}
	
	private static boolean pruebaBuscar() {
		boolean fallo = false;
		ContenedorDeEnteros a = new ContenedorDeEnteros();
		
		// Prueba buscar con 0 valores
		if(a.buscar(0)) {
			fallo = true;
		}
		a.insertar(0);
		// Prueba buscar con 1 valor
		if(!a.buscar(0)) {
			fallo = true;
		}
		a.insertar(1);
		// Prueba buscar al principio con 2 valores
		if(!a.buscar(0)) {
			fallo = true;
		}
		// Prueba buscar al final con 2 valores
		if(!a.buscar(1)) {
			fallo = true;
		}
		for(int i = 2; i < 10; i++) {
			a.insertar(i);
		}
		// Prueba buscar con varios valores
		if(!a.buscar(5)) {
			fallo = true;
		}
		if(!a.buscar(8)) {
			fallo = true;
		}
		a.extraer(9);
		// Prueba buscar con un valor extraido recientemente
		
		if(a.buscar(9)) {
			fallo = true;
		}
		return fallo;
	}
	
	private static boolean pruebaVaciar() {
		boolean fallo = false;
		ContenedorDeEnteros a = new ContenedorDeEnteros();
		a.insertar(0);
		// Prueba vaciar con 1 elemento
		a.vaciar();
		if(a.cardinal() != 0) {
			fallo = true;
		}
		a.insertar(0);
		a.insertar(1);
		// Prueba vacios con 2 elementos
		a.vaciar();
		if(a.cardinal() != 0) {
			fallo = true;
		}
		for(int i = 0; i < 10; i++) {
			a.insertar(i);
		}
		// Prueba vaciar varios elementos
		a.vaciar();
		if(a.cardinal() != 0) {
			fallo = true;
		}
		return fallo;
	}
	
	private static boolean pruebaElementos() {
		boolean fallo = false;
		ContenedorDeEnteros a = new ContenedorDeEnteros();
		
		// Prueba con 1 elementos
		a.insertar(0);
		int[] array = new int[a.cardinal()];
		array = a.elementos();
		if(array[0] != 0) {
			fallo = false;
		}
		// Prueba con varios elementos
		
		for(int i = 1; i < 10; i++) {
			a.insertar(i);
		}
		int[] comprobacion = new int[a.cardinal()];
		int[] comprobacionSegundo = new int[a.cardinal()];
		comprobacionSegundo = a.elementos();
		for(int i = 0; i < 10; i++) {
			comprobacion[i]=i;
		}
		for(int i = 0, j = comprobacion.length-1; i < comprobacionSegundo.length ; i++, j--) {
			if(comprobacion[j] != comprobacionSegundo[i]) {
				fallo = true;
			}
		}
		
		// Prueba con varios elementos repetidos
		for(int i = comprobacionSegundo.length; i > 0 ; i--) {
			a.insertar(i);
		}
		
		for(int i = 0, j = comprobacion.length-1; i < comprobacionSegundo.length ; i++, j--) {
			if(comprobacion[j] != comprobacionSegundo[i]) {
				fallo = true;
			}
		}
		
		// Prueba extraccion  con 1 elemento
		a.extraer(9);
		comprobacionSegundo = a.elementos();
		if(comprobacionSegundo[9] == comprobacion[9]) {
			fallo = true;
		}
		
		// Prueba extraccion al principio con 2 elementos
		ContenedorDeEnteros b = new ContenedorDeEnteros();
		b.insertar(0);
		b.insertar(1);
		b.extraer(0);
		if(b.elementos()[0] != 1) {
			fallo = true;
		}
		// Prueba extraccion al final con 2 elementos
		b.insertar(0);
		b.extraer(1);
		if(b.elementos()[0] != 0) {
			fallo = true;
		}
		
		// Prueba extraer varios elementos
		b.extraer(0);
		for(int i = 0; i <10 ; i++) {
			b.insertar(i);
		}
		for(int i = 9; i > 3 ; i--) {
			b.extraer(i);
		}
		int[] resultado = new int[b.cardinal()];
		for(int i = 0; i <resultado.length ; i++) {
			resultado[i] = i;
		}
		int[] copia = b.elementos();
		for(int i = 0, j = resultado.length-1; i <resultado.length ; i++, j--) {
			if(copia[j] != resultado[i]) {
				fallo = true;
			}
		}
		return fallo;
	}
}
