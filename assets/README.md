# Readme - Comparación de Inserciones en Estructuras de Datos 📊📉

En la carpeta doc tenemos un informe que proporciona una breve descripción de las observaciones y conclusiones obtenidas al analizar las inserciones en diferentes estructuras de datos: lista encadenada, vector ordenado y árbol binario de búsqueda.

## Lista Encadenada 🧐

Al observar las gráficas, se destaca que la inserción en una lista encadenada es la más lenta. Esto se debe a que las búsquedas del elemento a insertar se realizan de manera secuencial, comparando valor a valor. Este enfoque lineal resulta en un mayor tiempo de ejecución, especialmente a medida que aumenta el tamaño del contenedor.

## Vector Ordenado 🚀

En el caso del vector ordenado, se experimenta una reducción significativa en el tiempo de inserción. Esto se debe a que las búsquedas se realizan de manera dicotómica, lo que implica una búsqueda más eficiente al tratar menos elementos para encontrar el valor deseado. Sin embargo, es importante tener en cuenta que este contenedor requiere que los datos estén ordenados, lo que puede ser un inconveniente.

## Árbol Binario de Búsqueda 🌳

La inserción en un árbol binario de búsqueda muestra tiempos considerablemente más bajos en comparación con la práctica anterior. La eficiencia se debe a que no es necesario realizar desplazamientos de elementos, lo que resulta en un menor gasto computacional. Sin embargo, es importante tener precaución, ya que la falta de operaciones de reequilibrado puede llevar a que el árbol se degrade a una estructura similar a la lista encadenada si no se gestiona adecuadamente.

## Gráficas 📈

Se adjuntan gráficas que representan el tiempo de ejecución en milisegundos para cada mil operaciones de inserción en función del tamaño del contenedor. Se incluyen comparativas entre las prácticas anteriores (PRCT 1 y PRCT 2) y las estructuras de datos utilizadas (PRCT 2, PRCT 3 y PRCT 4).

Este análisis ofrece una visión general de la eficiencia de las diferentes estructuras de datos en términos de operaciones de inserción, proporcionando información valiosa para la selección de la estructura más adecuada según los requisitos del sistema. 🤖
