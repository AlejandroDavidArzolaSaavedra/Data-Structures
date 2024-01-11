package practica3;
import java.util.ArrayList;
/**
 * Clase para representar el comportamiento de un contenedor lineal
 * de un árbol binario de búsqueda en memoria dinámica sin operaciones 
 * de reequilibrado y sin elementos repetidos
 */
public class ContenedorDeEnteros {
	protected int numElem;
	private NodoBinario nodoRaiz;
	
	/**
	 * Clase interna para representar los nodos del árbol binario
	 */
	public class NodoBinario {
		public int value;
		public NodoBinario[] enlaces;
		
		/**
		 * Constructor del nodo del árbol binario de búsqueda
		 * @param value valor del nodo raiz del árbol binario de búsqueda
		 */
		public NodoBinario(int value) {
			this.value = value;
			this.enlaces = new NodoBinario[2];
			// enlace izquierdo del nodo
			this.enlaces[0] = null;
			// enlace derecho del nodo
			this.enlaces[1] = null;
		}
	}
	
	/**
	 * Constructor vacio
	 */
	public ContenedorDeEnteros() {
		this.nodoRaiz = null;
		this.numElem = 0;
	}
	
	/**
	 * Metodo para devolver el numero de elementos del contenedor
	 * @return longitud del árbol binario de búsqueda
	 */
	public int cardinal() {
		return this.numElem;
	}
	
	/**
	 * Metodo decorador que llama al insertar del arbol binario
	 * @param value valor a insertar en el árbol binario de búsqueda
	 * @return verdadero si lo añadio y falso sino lo añadio
	 */
	public boolean insertar(int value) {
		int aux = this.numElem; // Si tiene misma longitud no se insertaron elementos 
		this.nodoRaiz = insertar(this.nodoRaiz,value);
		return aux < this.numElem;
	}
	
	/**
	 * Metodo para insertar un nodo en el arbol binario
	 * @param nodo elemento del arbol por el que se aplica el recorrido
	 * @param value valor a insertar en el árbol binario de búsqueda
	 * @return verdadero si lo añadio y falso sino lo añadio
	 */
	private NodoBinario insertar(NodoBinario nodo, int value) {
		// Caso base arbol con 0 elementos o nos encontramos un nodo hoja
		if(nodo == null) {
			nodo = new NodoBinario(value);
			this.numElem++;
		}else {
			if(nodo.value != value) {
				if(nodo.value > value) {
					// Avanzamos por en enlace izquierdo
					nodo.enlaces[0] = insertar(nodo.enlaces[0],value);
				}else {
					// Avanzamos por en enlace derecho
					nodo.enlaces[1] = insertar(nodo.enlaces[1],value);
				}
			}
		}
		return nodo;
	}
	
	/**
	 * Metodo decorador que llama al extraer del arbol binario
	 * Si no se encuentra no se encuentra no se altera el contenedor
	 * @param value valor a eliminar del contenedor
	 * @return verdadero si lo elimino y falso en caso contrario
	 */
	public boolean extraer(int value) {
		int aux = this.numElem; // Si tiene misma longitud no se extrayeron elementos 
		this.nodoRaiz = extraer(this.nodoRaiz,value);
		return aux > this.numElem;
	}
	
	/**
	 * Metodo extrae un nodo del arbol binario
	 * Si no se encuentra no se encuentra no se altera el contenedor
	 * @param nodo nodo del arbol binario para extraer
	 * @param value valor a eliminar del contenedor
	 * @return verdadero si lo elimino y falso en caso contrario
	 */
	private NodoBinario extraer(NodoBinario nodo, int value) {
		if(nodo != null) {
			// Si encontramos el nodo que buscamos
			if(nodo.value == value) {
					if(nodo.enlaces[0]==null || nodo.enlaces[1]==null) {
						this.numElem--;
						// No tiene hijo izquierdo
						if(nodo.enlaces[0] == null) {
							return nodo.enlaces[1];
						// No tiene hijo derecho
						}else {
							return nodo.enlaces[0];
						}
					}else {
						// Tenemos 2 hijos por lo que buscamos su sucesor simetrico
						nodo.enlaces[1] = extraerSucesor(nodo,nodo.enlaces[1]);
				}
			}else {
				// Avanzamos por el nodo izquierdo
				if(nodo.value > value) {
					nodo.enlaces[0] = extraer(nodo.enlaces[0],value);
					// Avanzamos por el nodo derecho
				}else {
					nodo.enlaces[1] = extraer(nodo.enlaces[1],value);
				}
			}
		}
		return nodo;
	}
	
	/**
	 * Metodo auxiliar para extraer el sucesor simetrico
	 * @param nodoExtraer nodo que se desea extrer
	 * @param nodo nodo por el que se empieza el recorrido
	 * @return devuelve el arbol cambiando el valor por su sucesor  simetrico
	 */
	private NodoBinario extraerSucesor(NodoBinario nodoEx, NodoBinario nodo) {
		// LLegamos a su sucesor simetrico
		if(nodo.enlaces[0] == null) {
			nodoEx.value = nodo.value;
			this.numElem--;
			nodo = nodo.enlaces[1];
		}else {
			// Avanzamos por el nodo izquierdo
			nodo.enlaces[0] = extraerSucesor(nodoEx, nodo.enlaces[0]);
		}
		return nodo;
	}
	
	/**
	 * Metodo decorador que llama al buscar del arbol binario
	 * @param value valor a buscar en el contenedor
	 * @return verdadero si lo encuentra y falso en caso contrario
	 */
	public boolean buscar(int value) {
		return buscar(this.nodoRaiz, value);
	}
	
	/**
	 * Metodo para buscar un elemento del arbol binario
	 * @param nodo por el que vamos buscando por el arbol
	 * @param value valor a buscar en el contenedor
	 * @return verdadero si lo encuentra y falso en caso contrario
	 */
	private boolean buscar(NodoBinario nodo, int value) {
		if(nodo != null) {
			if(nodo.value == value) {
				return true;
			// Avanzamos por en enlace izquierdo
			}else if (nodo.value > value) {
				return buscar(nodo.enlaces[0],value);
			// Avanzamos por en enlace derecho
			}else if(nodo.value < value) {
				return buscar(nodo.enlaces[1],value);
			}
		}
		return false;
	}
	
	/**
	 * Metodo para vaciar el árbol binario de búsqueda
	 */
	public void vaciar() {
		this.numElem = 0;
		this.nodoRaiz = null;
	}
	
	/**
	 * Metodo para devolver un vector con los elementos del arbol
	 * @return todos los elementos del arbol en in-orden 
	 */
    public int[] elementos() {
        int[] contenedor = new int[this.numElem];
        ArrayList<Integer> nombreArrayList = new ArrayList<Integer>();// Contenedor auxiliar
        inorder(this.nodoRaiz, contenedor,nombreArrayList);
        for(int i = 0; i < nombreArrayList.size(); i++) {
        	contenedor[i] = nombreArrayList.get(i);
        }
        return contenedor;
    }
    
    /**
 	* Método auxiliar que implementa un recorrido in-orden
 	* @param nodo nodo donde empieza a realizar el recorrido
 	* @param contenedor vector que contiene los elementos in-orden del arbol
 	* @param pos posicion a insertar en contenedor
 	*/
    private void inorder(NodoBinario nodo,int[] contenedor, ArrayList<Integer> nombre) {
        if (nodo != null) {
        	// Miramos si tiene hijo izquierdo
        	if(nodo.enlaces[0] != null) {
        		inorder(nodo.enlaces[0],contenedor, nombre);
        	}
	        nombre.add(nodo.value);
	        // Miramos si tiene hijo derecho
	        if(nodo.enlaces[1] != null) {
	        	inorder(nodo.enlaces[1],contenedor, nombre);
	        }
        }
    }
}
