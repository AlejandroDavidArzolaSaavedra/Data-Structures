package practica1;
/**
 * Clase para representar el comportamiento de un contenedor lineal
 * con una lista encadenada sin ordenacion y sin elementos repetidos
 */
public class LinkedList {
	private int len;
	private Nodo first;
	
	/**
	 * Clase interna para representar el nodo de la lista encadenada
	 */
	private class Nodo {
		private final int value;
		private Nodo nextNode;
		
		/**
		 * Constructor del nodo de la lista
		 * @param value valor del elemento de la lista encadenada
		 * @param nextNode direccion hacia el siguiente elemento de la lista
		 */
		private Nodo(int value, Nodo nextNode) {
			this.value = value;
			this.nextNode = nextNode;
		}
	}
	
	/**
	 * Constructor vacio
	 */
	public ContenedorDeEnteros() {
		this.len = 0;
		this.first = null;
	}
	
	/**
	 * Metodo para devolver el numero de elementos del contenedor
	 * @return longitud de la lista encadenada
	 */
	public int cardinal() {
		return this.len;
	}
	
	/**
	 * Metodo para insertar un elemento en el contenedor lineal
	 * @param value valor a insertar en la lista encadenada
	 * @return verdadero si lo aÃ±adio y falso sino lo aÃ±adio
	 */
	public boolean insertar(int value) {
		if(!buscar(value)) {
			ContenedorDeEnteros.Nodo e = new ContenedorDeEnteros.Nodo(value, this.first);
			this.first = e;
			this.len++;
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Metodo para extraer el elemento pasado por parametro
	 * Sino se encuentra no se encuentra no se altera el contenedor
	 * @param value valor a eliminar del contenedor
	 * @return verdadero si lo elimino y falso en caso contrario
	 */
	public boolean extraer(int value) {
		boolean extraccionConExito=false;
		// Caso 2 la lista no esta vacia
		if(this.first != null) {
			// Caso 3 se elimina el 1 elemento de la lista encadenada
			if(this.first.value == value) {
				this.first = this.first.nextNode;
				this.len--;
				extraccionConExito = true;
			} else {
				Nodo p = this.first.nextNode;
				Nodo anterior = this.first;
				while(p != null && p.value != value) {
					anterior = p;
					p = p.nextNode;
				}
				if(p != null) {
					anterior.nextNode = p.nextNode;
					this.len--;
					extraccionConExito = true;;
				}
			}
		}
		return extraccionConExito;
	}
	
	/**
	 * Metodo para buscar un elemento de la lista encadenada
	 * @param value valor a buscar en el contenedor
	 * @return verdadero si lo encuentra y falso en caso contrario
	 */
	public boolean buscar(int value) {
		Nodo aux = this.first;
		boolean valorEncontrado = false;
		while(aux != null) {
			if(aux.value == value) {
				valorEncontrado = true;
			}
			aux = aux.nextNode;
		}
		return valorEncontrado;
	}
	
	/**
	 * Metodo para vaciar la lista encadenada
	 */
	public void vaciar() {
		this.first = null;
		this.len = 0;
	}
	
	/**
	 * Metodo que devuelve un vector con los elementos del contenedor
	 * @return vector con todos los elementos de la lista encadenada
	 */
	public int[] elementos() {
		int[] resultado = new int[this.len];
		Nodo aux = this.first;
		int i = 0;
		while(aux != null) {
			resultado[i] = aux.value;
			i++;
			aux = aux.nextNode;
		}
		return resultado;
	}
}