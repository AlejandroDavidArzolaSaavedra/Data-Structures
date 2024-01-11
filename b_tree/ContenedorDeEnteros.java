package practica4;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Clase para representar el comportamiento de un arbol b 
 * que no admite repetidos, usando de almacenamiento memoria secundaria
 * 
 */
public class ContenedorDeEnteros {
	private int raiz;
	private int numElem;
	private int Orden;
	private int minimoclaves;
	private FicheroAyuda fichero;
	private String nombreFichero;
	
	/**
	 * Constructor del contenedor
	 */
	public ContenedorDeEnteros() {
		fichero = new FicheroAyuda();
	}
	
	
	/**
	 * Clase interna para representar un nodo del arbol b
	 */
	class Nodo {
		private int[] clavei;
		private int[] enlacei;
		private int numElei;
		private int direccióni;
		
		/**
		 * Constructor del nodo
		 */
		Nodo(){
			this.direccióni = FicheroAyuda.dirNula;
			this.numElei = 0;
			this.clavei = new int[Orden];
			this.enlacei = new int[Orden + 1];
		}
		
		/**
		 * Getter de la direccion del nodo
		 * @return direccion del nodo
		 */
		private int dirección() {
			return this.direccióni;
		}
		
		/**
		 * Setter de la direccion del nodo
		 * @param nuevaDir establece la nueva direccion del nodo
		 */
		private void dirección(int nuevaDir) {
			this.direccióni = nuevaDir;
		}
		
		/**
		 * Getter del cardinal
		 * @return numero de elementos que tiene el nodo
		 */
		private int cardinal() {
			return this.numElei;
		}
		
		/**
		 * Setter de la cardinal
		 * @param nuevoNumElei  establece el nuevo numero de datos almacenados
		 */
		private void cardinal(int nuevoNumElei) {
			this.numElei = nuevoNumElei;
		}
		
		/**
		 * Getter del enlace
		 * @param i elemento del enlace que se quiere mirar
		 * @return enlace del nodo del parametro de entrada
		 */
		private int enlace(int i) {
			return this.enlacei[i];
		}
		
		/**
		 * Setter del enlace
		 * @param i elemento del enlace que se quiere actualizar
		 * @param nuevoEnlace establece el nuevo enlace del nodo
		 */
		private void enlace(int i, int nuevoEnlace) {
			this.enlacei[i] = nuevoEnlace;
		}
		
		/**
		 * Getter de la clave del nodo
		 * @param i elemento de la clave
		 * @return clave del nodo
		 */
		private int clave(int i) {
			return this.clavei[i-1];
		}

		/**
		 * Setter de la clave del nodo
		 * @param i elemento de la clave
		 * @param clave establace la nueva clave del nodo
		 */
		private void clave(int i, int clave) {
			this.clavei[i-1] = clave;
		}
		
		/**
		 * Metodo para buscar un elemento del nodo
		 * @param ele elemento que se desea buscar
		 * @return la posicion de la clave menor que es mayor o igual que ele
		 */
		private int buscar(int ele) {
			int pos, prim, ulti, med;
			prim = 1;
			ulti = this.cardinal();
			// realizamos busqueda binaria
			while(prim <= ulti) {
				med = (prim + ulti) / 2;
				if(ele == clave(med)) {
					pos = med;
					return pos;
				}
				// desplazamos a la izq
				if(ele < clave(med))
					ulti = med -1;
				// desplazamos a la der
				else
					prim = med + 1;
			}
			pos = prim - 1;
			return pos;
		}
		
		/**
		 * Metodo auxiliar que te indica si la clave esta en el nodo
		 * @param e elemento a buscar 
		 * @return verdadero si lo encuentra y falso en caso contrario
		 */
		private boolean está(int e) {
			int prim, ulti, med;
			prim = 1;
			ulti = this.cardinal();
			// realizamos busqueda binaria
			while(prim <= ulti) {
				med = (prim + ulti) /2;
				if(e == clave(med)) {
					return true;
				}
				// desplazamos a la izq
				if(e < clave(med))
					ulti = med -1;
				// desplazamos a la der
				else
					prim = med + 1;
			}
			return false;
		}
		
		/**
		 * Metodo para insertar una clave en el nodo
		 * @param clave clave que se desea insertar 
		 * @param dir direccion donde se inserta 
		 * @param pos posicion del nodo donde se desea insertar
		 */
		private void insertar(int clave, int dir, int pos) {
			this.numElei++; // aumenta el numero de elementos
			for(int i = this.numElei - 1; i>= pos; i--) {
				clave(i + 1, clave(i));
				enlace(i + 1, enlace(i));
			}
			// insertamos en pos una clave y enlace 
			clave(pos,clave);
			enlace(pos,dir);
		}

		/**
		 * Metodo para extraer un elemento del nodo
		 * @param pos pisicion del nodo que se desea extraer
		 */
		private void extraer(int pos) {
			// extraemos la clave que hay en pos
			for(int i = pos; i < this.numElei; i++) {
				clave(i, clave(i + 1));
				enlace(i, enlace(i + 1));
			}
			this.numElei--; // disminuye el numero de elementos
		}
		
		/**
		 * Metodo que devuelve el tamaño del nodo
		 * @return tamaño del nodo
		 */
		public int tamaño() {
			return (((2 * Conversor.INTBYTES) + ((Orden - 1) * Conversor.INTBYTES)) + (Orden * Conversor.INTBYTES)); 
		}
		
		/**
		 * Metodo que inicializa un nodo a partir de un byte[]
		 * @param leer datos que se convierten 
		 */
		private void deByte(byte[] leer) {
			dirección(Conversor.aInt(Conversor.toma(leer, 0, 4)));
			cardinal(Conversor.aInt(Conversor.toma(leer, 4, 4)));
			int avanze = 8;
			int tope = this.numElei;
			// cargamos las claves
			deByterAux(this.clavei, leer, avanze, tope);
			avanze = 8 + (4 * this.numElei);
			tope++;
			// cargamos los enlaces
			deByterAux(this.enlacei, leer, avanze, tope);
		}
		
		/**
		 * Metodo auxiliar para convertir un conjunto de bytes[] tanto en enlaces como claves
		 * @param n tanto enlaces como claves
		 * @param leer conjunto de bytes a convertir
		 * @param avanze posicion para realizar la conversion
		 * @param tope numero de elementos del conjunto
		 * @return el avanze de realizar cada conversion de cada conjunto de bytes
		 */
		private void deByterAux(int[] n , byte[] leer, int avanze, int tope) {
			for(int i = 0; i < tope; i++, avanze += 4) {
				n[i] = Conversor.aInt(Conversor.toma(leer, avanze, 4));
			}
		}
	
		
		/**
		 * Metodo que convierte un nodo en un cojunto de bytes
		 * @return devuelve el nodo en forma de byte[]
		 */
		private byte[] aByte() {
			int posicion = 0;
			byte[] conjuntoDeBytes = new byte[this.tamaño()];
			int tope = this.numElei;
			// tomamos la direcccion y el numero de elementos y lo pasamos al conjunto de bytes
			posicion = Conversor.añade(conjuntoDeBytes, Conversor.aByte(this.direccióni), posicion);
			posicion = Conversor.añade(conjuntoDeBytes, Conversor.aByte(this.numElei), posicion);
			// tomamos las claves y enlacess y lo pasamos al conjunto de bytes
			posicion = aByterAux(posicion, this.clavei, conjuntoDeBytes, tope);
			tope++;
			aByterAux(posicion, this.enlacei, conjuntoDeBytes, tope);
			return conjuntoDeBytes;
		}
		
		/**
		 * Metodo auxiliar para convertir tanto enlaces como claves en conjunto de bytes
		 * @param posicion posicion del conjunto de bytes convertidos
		 * @param n tanto enlaces como claves a convertir
		 * @param conjuntoDeBytes el conjunto de bytes convertido
		 * @param tope numero de elementos del conjunto
		 * @return devuelve el conjunto de bytes convertidos
		 */
		private int aByterAux(int posicion , int[] n, byte[] conjuntoDeBytes, int tope) {
			for(int i = 0; i < tope; i++) {
				posicion = Conversor.añade(conjuntoDeBytes, Conversor.aByte(n[i]), posicion);
			}
			return posicion;
		}
	}
	
	
	/**
	 * Clase interna para almacenar el recorrido realizado en una pila
	 *
	 */
	class InfoPila {
		private Nodo nodo;
		private int pos;
		
		/**
		 * Constructor de la pila para realizar el recorrido
		 * @param nodo2 nodo por el que se ha pasado
		 * @param pos2 posicion donde se encuentra el elemento
		 */
		public InfoPila(Nodo nodo2, int pos2) {
			this.nodo = nodo2;
			this.pos = pos2;
		}
	}
	
	
	/**
	 * Clase auxiliar que permite devolver una pareja formada por clave y enlace
	 *
	 */
	class ParejaInsertar {
		public int clave;
		public int dirección;
	}
	
	/**
	 * Metodo para leer una pagina o registro del fichero
	 * @param dir registro del fichero
	 * @return nodo que bha leido
	 */
	private Nodo leer(int dir) {
		Nodo n = new Nodo();
		n.deByte(this.fichero.leer(dir)); // leemos el conjunto de bytes
		return n;
	}
	
	/**
	 * Metodo para escribir el nodo en el fichero
	 * @param nodo a escribir de manera permanente
	 */
	private void escribir(Nodo nodo) {
		this.fichero.escribir(nodo.aByte(), nodo.dirección());
	}
	
	/**
	 * Procedimimento para crear un arbol b
	 * @param direccion ruta del fichero
	 * @param Orden orden del arbol
	 */
	public void crear(String direccion, int Orden){
		cerrar(); // cerramos el archivo si estaba abierto previamente
		this.Orden = Orden;
		Nodo nodo = new Nodo();
		this.nombreFichero = direccion;
		this.fichero.crear(this.nombreFichero, nodo.tamaño(), 3);
		this.raiz = FicheroAyuda.dirNula;
		this.numElem = 0;
		this.minimoclaves = (this.Orden + 1) / 2 - 1;
		// creamos el fichero con 3 adjuntos
		this.fichero.adjunto(0,this.raiz);
		this.fichero.adjunto(1,this.numElem);
		this.fichero.adjunto(2, this.Orden);
	}
	
	/**
	 * Procedimimento para abrir el arbol b almacenado en el fichero
	 * @param ruta ruta del fichero asociado al arbol b
	 */
	public void abrir(String ruta) {
		this.fichero.abrir(ruta);
		// leemos los adjuntos
		this.raiz = this.fichero.adjunto(0);
		this.numElem = this.fichero.adjunto(1);
		this.Orden = this.fichero.adjunto(2);
		this.minimoclaves = (this.Orden + 1) / 2 - 1;
	}
	/**
	 * Procedimimento para cerrar el fichero asociado
	 */
	public void cerrar() {
		this.fichero.cerrar();
	}

	/**
	 * Metodo que devuelve el numero de elementos del arbol b
	 * @return numero de elementos del contenedor
	 */
	public int cardinal() {
		return this.numElem;
	}
	
	/**
	 * Metodo para insertar un elemento al arbol b
	 * @param i elemento a añadir
	 * @return vedadero si lo añadio y falso en caso contrario
	 */
	public boolean insertar(int i) {
		// pila del recorrido en el arbol
		Stack<InfoPila> pila = new Stack<InfoPila>();
		// revisamos si ya se encuentra en el contenedor
		if(buscar(i, pila))
			return false;
		Nodo nodoActual = new Nodo();
		InfoPila info;
		ParejaInsertar pa = new ParejaInsertar();
		pa.clave = i;
		pa.dirección = FicheroAyuda.dirNula;
		this.fichero.adjunto(1, ++this.numElem);
		// Si la pila no esta vacia es que el arbol tampoco
		if(!pila.isEmpty()) {
			info = pila.pop();
			nodoActual = info.nodo;
			int pos = info.pos;
			nodoActual.insertar(pa.clave, pa.dirección, pos + 1);
			if(nodoActual.cardinal() < this.Orden) {
				escribir(nodoActual);
				return true;
			}
			while(!pila.empty()) {
				info = pila.pop();
				Nodo der;
				Nodo izq;
				der = new Nodo();
				izq = new Nodo();
				Nodo padre;
				padre = info.nodo;
				pos = info.pos;
				if(pos > 0) {
					izq = leer(padre.enlace(pos -1));
					if(izq.cardinal() < this.Orden -1) {
						// rotacion para la sobrecarga
						rotaciónderizq(padre, pos -1, izq, nodoActual);
						return true;
					}
				}
				if(pos < padre.cardinal()) {
					der = leer(padre.enlace(pos + 1));
					if(der.cardinal() < this.Orden - 1) {
						// rotacion para la sobrecarga
						rotaciónizqder(padre, pos, nodoActual, der);
						return true;
					}
				}
				if(pos == 0)
					partición_2_3(padre, pos, nodoActual, der);
				else
					partición_2_3(padre, pos - 1, izq, nodoActual);
				// termina la sobrecarga en el padre
				if(padre.cardinal() < this.Orden) {
					escribir(padre);
					return true;
				}
				nodoActual = padre;
				
			}
			pa = partición_1_2(nodoActual);// raiz sobrecargada
		}
		// le asignamos los valores al nodo
		nodoActual.cardinal(1);
		nodoActual.enlace(0, raiz);
		nodoActual.clave(1, pa.clave);
		nodoActual.enlace(1, pa.dirección);
		// tomamos una pagina dispnible
		nodoActual.dirección(this.fichero.tomarPágina());
		raiz = nodoActual.dirección();
		// escribimos en el fichero
		escribir(nodoActual);
		fichero.adjunto(0, this.raiz);
		return true;
	}
	
	/**
	 * Metodo para extaer un elemento del arbol b
	 * @param i elemento a extraer
	 * @return verdadero si lo elimino y falso en caso contrario
	 */
	public boolean extraer(int i) {
		// pila para guardar el recorrido realizado
		Stack<InfoPila> pila = new Stack<InfoPila>();
		// si no se encuentra no se extrae
		if(!buscar(i,pila)) 
			return false;
		this.fichero.adjunto(1, --this.numElem);
		InfoPila info = pila.pop();
		Nodo nodoActual = info.nodo;
		int pos = info.pos;
		if(nodoActual.enlace(0) != FicheroAyuda.dirNula) {
			pila.add(new InfoPila(info.nodo, info.pos));
			LinkedList<InfoPila> cola = new LinkedList<InfoPila>();
			int dir = nodoActual.enlace(pos);
			// buscamos el sucesor 
			do {
				nodoActual = leer(dir); // modificado
				dir = nodoActual.enlace(0);
				if(dir == FicheroAyuda.dirNula) 
					pos = 1;
				else 
					pos = 0;
				cola.addLast(new InfoPila(nodoActual, pos));
			}while(dir != FicheroAyuda.dirNula);
			info = pila.pop();
			info.nodo.clave(info.pos, nodoActual.clave(1));
			escribir(info.nodo);
			pila.add(info);
			// añadimos a la pila lo que hay en la cola
			while(!cola.isEmpty()) {
				pila.add(cola.getFirst());
				cola.removeFirst();	
			}
			info = pila.pop();
			nodoActual = info.nodo;
			pos = info.pos;
		}
		nodoActual.extraer(pos);
		while(nodoActual.cardinal() < this.minimoclaves && nodoActual.dirección() != this.raiz) {
			Nodo padre; 
			Nodo der = new Nodo();
			Nodo izq = new Nodo();
			info = pila.pop();
			padre = info.nodo;
			pos = info.pos;
			if(pos < padre.cardinal()) {
				der = leer(padre.enlace(pos + 1));
				// hermano derecho sobre minimo, por lo que hacemos rotacion hacia la izquierda
				if(der.cardinal() > this.minimoclaves) {
					rotaciónderizq(padre, pos, nodoActual, der);
					return true;
				}
			}
			// tenemos un hermano izquierdo
			if(pos > 0) {
				izq = leer(padre.enlace(pos - 1));
				if(izq.cardinal() > this.minimoclaves) {
					rotaciónizqder(padre, pos -1, izq, nodoActual);
					return true;
				}
			}
			// si no podemos rotar realizmamos recombinaciones
			if(pos > 0 && pos < padre.cardinal()) 
				recombinación_3_2(padre, pos, izq, nodoActual, der);
			else if (pos > 0)
				recombinación_2_1(padre, pos - 1, izq, nodoActual);
			else 
				recombinación_2_1(padre, pos, nodoActual, der);
			nodoActual = padre;
		}
		// situacion bajo minimo o se llego a la raiz
		if(nodoActual.cardinal() > 0) {
			escribir(nodoActual);
			return true;
		}
		else {
			this.raiz = nodoActual.enlace(0);
			// liberamos la pagina extraida
			this.fichero.liberarPágina(nodoActual.dirección());
			this.fichero.adjunto(0, this.raiz);
			return true;
		}
	}
	
	/**
	 * Metodo interno para buscar el elemento en el contenedor
	 * @param e elemento que se quiere buscar
	 * @param pila que se queda con informacion del recorrido
	 * @return verdadero si lo encontro y falso en caso contrario
	 */
	private boolean buscar(int e, Stack<InfoPila> pila) {
		int dirNodo, pos;
		Nodo nodo = new Nodo();
		dirNodo = this.raiz;
		pila.clear(); // borramos el contenido de la pila
		while(dirNodo != FicheroAyuda.dirNula) {
			nodo = leer(dirNodo);
			pos = nodo.buscar(e);
			pila.add(new InfoPila(nodo, pos));// actualizamos el recorrido en la pila
			// comprobamos si esta o no el elemento
			if(nodo.está(e)) return true;
			dirNodo = nodo.enlace(pos);
		}
		return false;
	}

	/**
	 * Metodo decorativo que busca si el elemento se encuentra en el contenedor
	 * @param elemento elemento a buscar en el contenedor
	 * @return Devuelve verdadero si pertenece al contenedor y falso en caso contrario
	 */
	public boolean buscar(int elemento) {
		return buscar(elemento, new Stack<InfoPila>());
	}
	
	/**
	 * Procedimimento para dejar el contenedor sin ningun elemento
	 */
	public void vaciar() {
		this.fichero.cerrar(); // cerramos el fichero
		this.crear(this.nombreFichero, this.Orden);
	}
	
	
	/**
	 * Clase auxiliar para añadir elementos in-orden al vector
	 */
	class myarray {
		private int[] vector;
		private int elm;
		private int posicion = 0;
		
		/**
		 * Constructor del array
		 * @param elementos longitud del vector
		 */
		myarray(int elementos){
			this.vector = new int[elementos];
			this.elm = elementos;
		}
		
		/**
		 * Metodo para añadir elementos al vector
		 * @param e elemento a añadir
		 */
		private void añadir(int e) {
			vector[this.posicion] = e;
			this.posicion++;
		}
		
		/**
		 * Getter del vector
		 * @return vector con elementos in-orden del arbol
		 */
		private int[] getVector() {
			return this.vector;
		}
		
		/**
		 * Getter del los elementos
		 * @return elementos del arbol
		 */
		private int getElm() {
			return this.elm;
		}
	}
	
	
	/**
	 * Metodo auxiliar para realizar un recorrido in-Order al arbol b
	 * @param elems contenedor de los elementos del arbol 
	 * @param raiz raiz del arbol por donde empieza el recorrido
	 */
	private void inOrder(myarray elems, int raiz) {
	    if(raiz != FicheroAyuda.dirNula){
    		int j = 1;
	    	Nodo nodo = new Nodo(); 
            nodo.deByte(fichero.leer(raiz));
	    	// avanzamos por el enlace izquierdo de manera recursiva
            inOrder(elems, nodo.enlace(0));
            // añadimos los elementos al vector
            do {
            	elems.añadir(nodo.clave(j));
            	// Comprobamos que no es un enlace a nulo para avanzar al siguiente
            	if(nodo.enlace(j) != FicheroAyuda.dirNula) {
        			inOrder(elems, nodo.enlace(j));
        			}
            	j++;
            }while(j <= nodo.numElei);
	    	}
		}
	
	/**
	 * Metodo que devuelve un vector de enteros ordenados
	 * @return el vector ordenado
	 */
	public int[] elementos() {
		int [] vector = new int[0];
		// Comprobamos si el arbol b esta vacio
		if(this.cardinal() == 0) {
			return vector;
		}else {
			myarray myvector = new myarray(this.numElem);
			// realizamos recorrido In order al arbol b
			inOrder(myvector, this.raiz);
			return myvector.getVector();
		}
	}

	/**
	 * Metodo para hacer particion 1/2, situacion cuando el nodo esta sobrecargado
	 * @param nodo nodo que contiene m valores clave
	 * @return Par clave/direccion que se inserta en el padre
	 */
	private ParejaInsertar partición_1_2(Nodo nodo) {
		ParejaInsertar pa = new ParejaInsertar();
		Nodo nuevoNodo = new Nodo();
		// establecemos el numero de claves y nodo nuevos
		int ncnuevo = this.Orden / 2;
		int ncnodo = this.Orden - ncnuevo -1;
		int dirNuevo = this.fichero.tomarPágina();
		nuevoNodo.dirección(dirNuevo);
		nuevoNodo.cardinal(ncnuevo);
		nuevoNodo.enlace(0, nodo.enlace(ncnodo +1));
		// transferimos las claves y enlaces al nuevo nodo
		for(int i = 1; i <= nuevoNodo.cardinal(); i++) {
			nuevoNodo.clave(i, nodo.clave(ncnodo + 1 + i));
			nuevoNodo.enlace(i, nodo.enlace(ncnodo + 1 + i));
		}
		// clave y enlace que asciende al padre
		pa.clave = nodo.clave(ncnodo + 1);
		pa.dirección = nuevoNodo.dirección();
		nodo.cardinal(ncnodo); // actualizamos la cardinalidad
		// escribimos en el fichero
		escribir(nodo);
		escribir(nuevoNodo);
		return pa;
	}
	
	/**
	 * Metodo para hacer una particion 2/3 para distribuir mejor los valores de claves
	 * @param padre nodo padre del nodo sobrecargado
	 * @param posizq posicion del nodo izquierdo
	 * @param izq nodo izquierdo
	 * @param der nodo derecho
	 */
	private void partición_2_3(Nodo padre, int posizq, Nodo izq, Nodo der) {
		int clavesRepartir = izq.cardinal() + der.cardinal() - 1;
		Nodo reg = new Nodo();
		// Fijamos las claves
		int ncizq = (clavesRepartir) / 3;
		int ncreg = (clavesRepartir + 1) / 3;
		int ncder = (clavesRepartir + 2) / 3;
		int antncder = der.cardinal();
		int antncizq = izq.cardinal();
		// tomamos nueva pagina disponible
		reg.dirección(fichero.tomarPágina());
		padre.insertar(izq.clave(ncizq + 1), reg.dirección(), posizq + 1);
		reg.cardinal(ncreg);
		reg.enlace(0, izq.enlace(ncizq + 1));
		// pasamos a la izquierda los registros correspondientes
		for(int i = ncizq +2; i<= antncizq; i++) {
			reg.clave(i - ncizq - 1, izq.clave(i));
			reg.enlace(i - ncizq -1, izq.enlace(i));
		}
		izq.cardinal(ncizq);
		reg.clave(antncizq - ncizq,  padre.clave(posizq + 2));
		int pos1 = antncizq - ncizq;
		reg.enlace(pos1, der.enlace(0));
		// pasamos a la derecha los registros correspondientes
		for(int i = pos1 +1; i<= ncreg; i++) {
			reg.clave(i, der.clave(i - pos1));
			reg.enlace(i, der.enlace(i - pos1));
		}
		int ncpas = antncder - ncder;
		padre.clave(posizq +2,  der.clave(ncpas));
		der.enlace(0, der.enlace(ncpas));
		// desplazamos las claves y enlaces derechos
		for(int i = ncpas + 1; i <= antncder; i++) {
			der.clave(i - ncpas, der.clave(i));
			der.enlace(i - ncpas, der.enlace(i));
		}
		der.cardinal(ncder); // actualizamos la cardinalidad
		// escribimos en el fichero
		escribir(izq);
		escribir(reg);
		escribir(der);
	}
	
	/**
	 * Metodo para hacer una rotacion hacia la derecha,situacion hermano derecho con menos m-1 de valores de clave
	 * @param padre nodo padre del nodo sobrecargado
	 * @param posizq posicion del nodo izquierdo
	 * @param izq nodo izquierdo sobrecargado
	 * @param der nodo derecho con menos de m-1 valores clave
	 */
	private void rotaciónizqder(Nodo padre, int posizq, Nodo izq, Nodo der) {
		int clavesRepartir = izq.cardinal() + der.cardinal();
		// fijamos el numero de claves
		int ncizq = (clavesRepartir) / 2;
		int ncder = clavesRepartir - ncizq;
		int ncpas = ncder - der.cardinal();
		int antncder = der.cardinal();
		der.cardinal(ncder);
		// pasamos a la derecha tanto las claves como enlaces correspondientes
		for(int i = antncder; i >= 1; i--) {
			der.clave(i + ncpas,  der.clave(i));
			der.enlace(i + ncpas,  der.enlace(i));
		}
		der.enlace(ncpas, der.enlace(0));
		der.clave(ncpas, padre.clave(posizq + 1));
		// pasamos a la izquierda tanto las claves como enlaces correspondientes
		for(int i = ncizq + 2; i<= izq.cardinal(); i++) {
			der.clave(i - (ncizq +1), izq.clave(i));
			der.enlace(i - (ncizq +1), izq.enlace(i));
		}
		der.enlace(0, izq.enlace(ncizq +1));
		padre.clave(posizq + 1, izq.clave(ncizq + 1));
		izq.cardinal(ncizq); // actualizamos la cardinalidad
		// escribimos en el fichero
		escribir(padre);
		escribir(izq);
		escribir(der);
	}
	
	/**
	 * Metodo para hacer una rotacion hacia la izquierda,situacion hermano izquierdo con menos de m-1 valores clave
	 * @param padre nodo padre del nodo sorecargado
	 * @param posizq posicion del nodo de la izquierda
	 * @param izq nodo izquierdo con menos de m-1 valores clave
	 * @param der nodo derecho sobrecargado
	 */
	private void rotaciónderizq(Nodo padre, int posizq, Nodo izq, Nodo der) {
		int clavesRepartir = izq.cardinal() + der.cardinal();
		// fijamos el numero de claves
		int ncder = (clavesRepartir) / 2;
		int ncizq = clavesRepartir - ncder;
		int ncpas = der.cardinal() - ncder;
		int antncizq = izq.cardinal();
		izq.cardinal(ncizq);
		izq.clave(antncizq + 1, padre.clave(posizq + 1));
		izq.enlace(antncizq + 1, der.enlace(0));
		// pasamos a la izquierda tanto las claves como enlaces correspondientes
		for(int i = 1; i < ncpas; i++) {
			izq.clave(antncizq + 1 + i, der.clave(i));
			izq.enlace(antncizq + 1 + i, der.enlace(i));
		}
		padre.clave(posizq + 1, der.clave(ncpas));
		der.enlace(0, der.enlace(ncpas));
		// pasamos a la derecha tanto las claves como enlaces correspondientes
		for(int i = 1; i <= ncder; i++) {
			der.clave(i, der.clave(i + ncpas));
			der.enlace(i, der.enlace(i + ncpas));
		}
		der.cardinal(ncder); // actualizamos la cardinalidad
		// escribimos en el fichero
		escribir(padre);
		escribir(izq);
		escribir(der);
	}
	
	/**
	 * Metodo para hacer una recombinacion 2/1, situacion no tiene hermano derecho y su hermano izq tiene (m/2)-1 valores clave
	 * @param padre nodo padre del registro bajo minmo
	 * @param posizq posicion del nodo de la izquierda
	 * @param izq nodo izquierdo del registro bajo minimo
	 * @param der nodo derecho del registro  bajo minimo
	 */
	private void recombinación_2_1(Nodo padre, int posizq, Nodo izq, Nodo der) {
		int antncizq = izq.cardinal();
		izq.cardinal(izq.cardinal() + 1 + der.cardinal());
		izq.clave(antncizq + 1, padre.clave(posizq + 1));
		izq.enlace(antncizq + 1, der.enlace(0));
		// pasamos a la izquierda tanto las claves como enlaces correspondientes
		for(int i = 1; i <= der.cardinal(); i++) {
			izq.clave(antncizq + 1 + i, der.clave(i));
			izq.enlace(antncizq + 1 + i, der.enlace(i));
		}
		padre.extraer(posizq + 1);
		// escribimos en el fichero
		escribir(izq);
		// liberamos una pagina de la recombinacion
		fichero.liberarPágina(der.dirección());
	}
	
	/**
	 * Metodo para hacer una recombinacion 3/2, situacion nodo bajo minimo y ningun hermano para hacer rotacion
	 * @param padre nodo padre del registro bajo minimo
	 * @param posReg posicion del registro bajo minimo
	 * @param izq hermano izquierdo del registro
	 * @param reg registro que se encuentra bajo minimo
	 * @param der hermano derecho del registro
	 */
	private void recombinación_3_2(Nodo padre, int posReg, Nodo izq, Nodo reg, Nodo der) {
		int aRepartir = izq.cardinal() + reg.cardinal() + der.cardinal() + 1;
		// fijamos el numero de claves
		int ncder = aRepartir / 2;
		int ncizq = aRepartir - ncder;
		int antncizq = izq.cardinal();
		int antncder = der.cardinal();
		izq.cardinal(ncizq);
		izq.clave(antncizq + 1,  padre.clave(posReg));
		izq.enlace(antncizq + 1, reg.enlace(0));
		// pasamos a la izquierda tanto las claves como enlaces correspondientes
		for(int i = antncizq + 2; i<= ncizq; i++) {
			izq.clave(i, reg.clave(i - antncizq - 1));
			izq.enlace(i, reg.enlace(i - antncizq -1));
		}
		der.cardinal(ncder);
		int ncpas = ncder - antncder;
		// pasamos a la derecha tanto las claves como enlaces correspondientes		
		for(int i = antncder; i>=1;i--) {
			der.clave(i + ncpas,  der.clave(i));
			der.enlace(i + ncpas,  der.enlace(i));
		}
		der.enlace(ncpas, der.enlace(0));
		der.clave(ncpas, padre.clave(posReg + 1));
		for(int i = ncpas -1; i>=1;i--) {
			der.clave(i, reg.clave(reg.cardinal()+i-ncpas +1));
			der.enlace(i, reg.enlace(reg.cardinal()+i-ncpas+1));
		}
		der.enlace(0, reg.enlace(reg.cardinal() - ncpas +1));
		// liberamos una pagina de la recombinacion
		fichero.liberarPágina(reg.dirección());
		// escribimos en el fichero
		escribir(izq);
		escribir(der);
		padre.extraer(posReg);
		padre.clave(posReg,  reg.clave(reg.cardinal() - ncpas +1));
	}
}