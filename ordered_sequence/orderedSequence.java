package practica2;
/**
 * Clase para representar  una collecion de enteros que no admite repetidos
 */
public class orderedSequence {
	private int[] vector;
	private int posicionesOcupadas = 0;// Me indica las posiciones ocupadas
	private int maximaCapacidad;
	/**
	 * Constructor de la colleccion
	 * @param longitud longitud del vector
	 */
	public orderedSequence(int longitud) {
		this.maximaCapacidad = longitud;
		this.vector = new int[longitud];
	}
	
	/**
	 * Metodo para devolver el numero de elementos del contenedor
	 * @return el numero de elementos del contenedor
	 */
	public int cardinal() {
		return this.posicionesOcupadas;
	}
	
	/**
	 * Metodo para buscar el elemento pasado por parametro en el contenedor
	 * realizando una busqueda dicotomica
	 * @param valor Valor a buscar en el contenedor
	 * @return Verdadero si lo busco y falso en caso contrario 
	 */
	public boolean buscar(int valor) {
		int inicio = 0;
		int fin = this.posicionesOcupadas - 1;
		int medio;
	    while(inicio <= fin) {
	    	// Hallamos el punto medio
	    	medio = (inicio + fin) / 2;
		    if(this.vector[medio] == valor) {
				return true;
			}else if(valor > this.vector[medio]) {
				inicio = medio + 1;
			}else {
				fin = medio - 1;
			}
	    }
		return false;
	}

	/**
	 * Metodo para insertar nuevos elementos pasado por parametro
	 * @param valor Valor a insertar en el contenedor
	 * @return Verdadero si lo añadio y falso en caso contrario
	 */
	public boolean insertar(int valor) {
		if(this.maximaCapacidad == this.posicionesOcupadas) {
			return false;
		}
		
		int inicio = 0;
		int fin =  this.posicionesOcupadas - 1;
		int medio;
		
		while(inicio <= fin) {
		    	medio = (inicio + fin) / 2;
			    if(this.vector[medio] == valor) {
					return false;
				}else if(valor > this.vector[medio]) {
					inicio = medio + 1;
				}else {
					fin = medio - 1;
				}
		 }
		 int aux = this.vector[inicio];// Tomamos  copia para moverlo
		 int aux2;
		 this.vector[inicio] = valor;
		 // Desplazamiento de elementos mayores que el insertado
		 for(int i = (inicio + 1); i < this.vector.length; i++){
			 aux2 = this.vector[i];
			 this.vector[i] = aux;
			 aux = aux2;
		 }
		 this.posicionesOcupadas++;
		 return true;
	}
	
	/**
	 * Metodo para extraer  elementos del contenedor
	 * @param valor Valor a extraer en el contenedor
	 * @return Verdadero si lo extrajo y falso en caso contrario
	 */
	public boolean extraer(int valor) {
		int inicio = 0;
		int fin = this.posicionesOcupadas - 1;
		int medio;
	    while(inicio <= fin) {
	    	// Hallamos el punto medio
	    	medio = (inicio + fin) / 2;
		    if(this.vector[medio] == valor) {
		    	// Movemos el hueco que hemos dejado
		    	 for(int i = medio + 1; i < this.vector.length; i++){
					this.vector[i-1] = this. vector[i];
				}
		    	this.posicionesOcupadas--;
		    	return true;
			}else if(valor > this.vector[medio]) {
				inicio = medio + 1;
			}else {
				fin = medio - 1;
			}
	    }
	    return false;
	}
	
	/**
	 * Metodo para vaciar el contenedor
	 */
	public void vaciar() { 
		this.vector = new int[this.maximaCapacidad];
		this.posicionesOcupadas = 0;
	}
	
	/**
	 * Metodo que devuelve un vector ordenados de menor a mayor
	 * @return Vector con todos los elementos ordenados
	 */
	public int[] elementos() {
		int [] vectorOrdenado = new int[this.posicionesOcupadas];
		for(int i = 0; i < this.posicionesOcupadas; i++) {
			vectorOrdenado[i] = this.vector[i];
		}
		return vectorOrdenado;
	}
}