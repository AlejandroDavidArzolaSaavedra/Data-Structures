package practica4;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

/**
 * Clase para realizar pruebas al contenedor (Arbol b)
 * @author Alejandro David Arzola Saavedra
 */
public class TestBTree {
	public static void main(String[] args) {
		
		BTree a;		
		// Comienzo de las pruebas de funcionamiento y las de rendimiento
		RandomAccessFile  r;
		RandomAccessFile  r2;
		FileWriter fichero = null;
		PrintWriter pw = null;
		a = new BTree();
		int[] contenedor = new int[100000];
		int[] contenedor_no = new int[20000];
		
		// Cargamos el contenido de datos.dat y datos_no.dat
		try {
			// 4 bytes int  cada registro  	
			fichero = new FileWriter("output4.txt");
			pw = new PrintWriter(fichero);
			r = new RandomAccessFile("datos.dat","rw");
			r2 = new RandomAccessFile("datos_no.dat","rw");
			
			int i = 0;
			int p = 0;
			
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
			
			// Pruebas de funcionamiento
			if(!realizarPruebasFuncionamiento(contenedor, contenedor_no)) {
				pw.println("");
				pw.println("Las pruebas de Funcionamineto han fallado");
				pw.println("");
			}
				
			// Pruebas de Rendimiento para los siguientes ordenes
			// 5, 7, 9, 11, 20, 25, 55, 75, 105, 201 y 301
							
			int[] ordenesDePruebasRendimiento = {5, 7, 9, 11, 20, 25, 55, 75, 105, 201 ,301};	
			realizarPruebasRendimiento(ordenesDePruebasRendimiento, pw, contenedor, contenedor_no);
			
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
	 * Metodo para realizar las pruebas de funcionamiento
	 * @param contenedor
	 * @param contenedor_no
	 * @return verdadero si las pruebas funcionan y falso en caso contrario
	 */
	private static boolean realizarPruebasFuncionamiento(int[] contenedor, int[] contenedor_no) {
		boolean banderaDeFallos = true;
		ContenedorDeEnteros a = new ContenedorDeEnteros();
		// Empezamos realizando pruebas con el arbol b minimo de orden 5
		a.crear("pruebasFuncionamiento",5);
		// Empezamos insertando 1 elemento
		a.insertar(0);
		// Probamos que funcione cardinal y elementos
		if(a.cardinal() == 1 && a.elementos()[0] != 0)
			banderaDeFallos  =  true;
		// Probamos que funcione el metodo insertar() insertando 20 alementos
		for(int i = 1; i < 20; i++) {
			if(!a.insertar(i)) {
				banderaDeFallos = true;
			}
		}
		// Probamos que funcione el metodo extraer() Extrayendo la mitad de elemtnos
		for(int i = 20; i >= 10; i--) {
			if(!a.extraer(i)) {
				banderaDeFallos = true;
			}
		}
		// Volvemos a comprobar que el primer y el ultimo elemento coinciden que estan ordenados
		if(a.elementos()[0] != 0 ^ a.elementos()[a.cardinal() - 1] == 9)
			banderaDeFallos = true;
		// Vaciamos el arbol b
		a.vaciar();
		// Probamos que hemos vaciado buscando si siguen elementos que insertamos previamente
		for(int i = 0; i < 10; i++) {
			if(!a.buscar(i)) {
				banderaDeFallos = true;
			}
		}
		// Seguimos con pruebas de orden 10
		a.crear("pruebasFuncionamiento",10);
		// Insertamos 100 elementos
		for(int i = 0; i < 100; i++) {
			if(!a.insertar(i)) {
				banderaDeFallos = true;
			}
		}
		// Extraemos 50 elementos
		for(int i = 99; i >= 50; i--) {
			if(!a.extraer(i)) {
				banderaDeFallos = true;
			}
		}
		// Comprobamos que los otros 50 siguen en el contenedor
		for(int i = 0; i < 51; i++) {
			if(!a.buscar(i)) {
				banderaDeFallos = true;
			}
		}
		// Cerramos el archivo
		a.cerrar();
		return banderaDeFallos;
	}

	/**
	 * Metodo para realizar las pruebas de rendimiento del arbol b
	 * @param pruebasRendimiento Los distintos ordenes del arbol b donde realizaremos las pruebas de rendimiento
	 * @param pw fichero donde se escriben los resultados
	 * @param contenedor contenedor con los elementos del archivos datos.dat
	 * @param contenedor_no contenedor con los elementos del archivos datos_no.dat
	 */
	private static void realizarPruebasRendimiento(int[] pruebasRendimiento, PrintWriter pw, int[] contenedor, int[] contenedor_no) {
			ContenedorDeEnteros a = new ContenedorDeEnteros();
			int valor = 0;
			int valorSiguiente = 10000;
			int lm = 0;
			pw.println("");
			pw.println("Pruebas de rendimiento");
			pw.println("");
			do {	
				
				//  Comprobacion de las insercciones del fichero datos.dat
				a.crear("pruebasRendimiento",pruebasRendimiento[lm]);
				valor = 0;
				valorSiguiente = 10000;
				pw.println("");
				pw.println("Orden del arbol b: " + pruebasRendimiento[lm]);
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
				
				//  Comprobacion de las extracciones del fichero datos.dat
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
				a.vaciar();
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
					pw.println(String.format("%.2f",(double)(nuevoTiempoPromedio - tiempoPromedio) / (10. * k)));
				}
				
				//  Comprobacion de las busquedas infructuosas del fichero datos_no.dat		
				valor = 0;
				valorSiguiente = 10000;
				int valorTope = 20000;
				a.vaciar();
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
					pw.println(String.format("%.2f",(double)(nuevoTiempoPromedio - tiempoPromedio)/20.));
				}
				lm++;
				a.cerrar();
			}while(lm < pruebasRendimiento.length);
	}
}
