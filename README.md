# Práctica de Búsqueda Local (IA-UPC)
## Guía para la ejecución y replicación de resultados
### por David Benedicto, Pau Martinez, Oriol Ramos  y Esther Fanyanàs

El objetivo de este documento es facilitar al usuario la creación de ejecuciones para la correcta ejecución práctica.

## Tabla de contenidos
- [Creación de ejecuciones simples](#creación-de-ejecuciones-simples)
- [Modificación de otros elementos del problema](#modificación-de-otros-elementos-del-problema)

## Creación de ejecuciones simples

Para poder realizar una ejecución del problema, es tan sencillo como ejecutar el fichero `Main.java` desde la terminal:

```
$ java Main
```

Otra forma de hacerlo es desde un editor siempre y cuando tengs una opción de ejecutar un archivo abierto (e.g. Visual Studio Code).

Es imprescindible que este fichero se encuentre en el mismo directorio que todos los archivos `.java` del proyecto y que los archivos `AIMA.jar`.

Una vez ejecutado, irán apareciendo por la terminal de manera secuencial las diferentes acciones que harán los algoritmos para la heurística elegida y el coste final.


## Modificación de otros elementos del problema

De cara a poder explorar y verificar ciertos resultados de la práctica, podéis modificar algunos de los elementos del problema. A continuación se especifican cuales son y la forma en que se pueden modificar:
- **Parámetros del algoritmo del problema:** se pueden modificar dichos parámetros desde el fichero `Main.java` pudiendose modificar tanto el numero de bicicletas, furgonetas, seed y demanda.
- **Heuristicos del problema:** Al igual que el resto de parametros, se puede modificar la linea de heuristico en `Main.java` pudiendo elegir así entre 0 y 1 el que se quiera usar.
