package IA.ProbIA5;

import IA.Bicing.Estacion;
import IA.Bicing.Estaciones;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import static java.lang.Math.*;

public class ProbIA5Board {    
   public class Ruta {
        private Estacion estacionIni;
        private Estacion estacionFi1;
        private Estacion estacionFi2;
        private int nbicisRecogidas;
        private int nbicisDejadas1;
        private int nbicisDejadas2;

        private int costeRuta;

        public Ruta(Estacion e1, Estacion e2, Estacion e3, int n1, int n2, int n3) {
            this.estacionIni = e1;
            this.estacionFi1 = e2;
            this.estacionFi2 = e3;
            this.nbicisRecogidas = n1;
            this.nbicisDejadas1 = n2;
            this.nbicisDejadas2 = n3;
            costeRuta = 0;
        }

        public Ruta(Estacion e1, Estacion e2, int n1, int n2) {
            this.estacionIni = e1;
            this.estacionFi1 = e2;
            this.estacionFi2 = null;
            this.nbicisRecogidas = n1;
            this.nbicisDejadas1 = n2;
            this.nbicisDejadas2 = 0;
        }

        public Estacion getEstacionInicial() {
            return estacionIni;
        }

        public Estacion getEstacionFinal1() {
            return estacionFi1;
        }

        public Estacion getEstacionFinal2() {
            return estacionFi2;
        }

        public int getNBicis() {
            return nbicisRecogidas;
        }

        public int getBicisRecogidas() {return nbicisRecogidas;}



        public int getBicisDejadas1() {
            return nbicisDejadas1;
        }

       public int getBicisDejadas2() {
           return nbicisDejadas2;
       }

        public int getCosteRuta() {
           return costeRuta;
       }

       public void setCosteRuta(int costeRuta) {
           this.costeRuta = costeRuta;
       }

       public void setEstacionIni(Estacion e1) {
            estacionIni = e1;
       }

       public void setEstacionFi1(Estacion e2) {
           estacionFi1 = e2;
       }

       public void setEstacionFi2(Estacion e3) {
            estacionFi2 = e3;
       }

       public void setNbicisDejadas2(int b) {
            nbicisDejadas2 = b;
       }
   }


    /*******************************/
    /***** ATRIBUTOS DE ESTADO *****/
    /*******************************/

    private static int nbicis;
    private static int nestaciones;
    private static Estaciones estaciones;
    private int nfurgos;
    private ArrayList<Ruta> Rutas;
    private ArrayList<Integer> bicisNext; //numero de bicicletas que habra en una estacion teniendo en cuenta nuestros desplazamientos
    private float coste;


    /**************************/
    /******* CREADORAS ********/
    /**************************/
    public ProbIA5Board(Estaciones e, int nb, int nf) {
        estaciones = e;
        nbicis = nb;
        nestaciones = e.size();
        nfurgos = nf;
        Rutas = new ArrayList<>();
        bicisNext = new ArrayList<>(e.size());
        for (int i = 0; i < e.size(); ++i) {
            bicisNext.add(e.get(i).getNumBicicletasNext());
        }
        coste = 0;
    }

    public ProbIA5Board(Estaciones e, int nb, int nf, ArrayList<Ruta> r, ArrayList<Integer> bN, float c) {
        estaciones = e;
        nbicis = nb;
        nestaciones = e.size();
        nfurgos = nf;

        Rutas = new ArrayList<>(r.size());
        for (int i = 0; i < r.size(); ++i) {
            Rutas.add(copiarRuta(r.get(i)));
        }
        bicisNext = new ArrayList<>(e.size());
        for (int i = 0; i < e.size(); ++i) {
            bicisNext.add(bN.get(i));
        }
        coste = c;
    }

    //Copia del pare amb totes les copies de rutes, excepte una que no s'afageix
    /*public ProbIA5Board(Estaciones e, int nb, int nf, ArrayList<Ruta> r, float c, Ruta noAñadir) {
        estaciones = e;
        nbicis = nb;
        nestaciones = e.size();
        nfurgos = nf;

        Rutas = new ArrayList<>(r.size());
        for (int i = 0; i < r.size(); ++i) {
            Ruta ruta = r.get(i);
            if (!ruta.equals(noAñadir)) Rutas.add(copiarRuta(ruta));
        }
        coste = c;
    }*/

    private Ruta copiarRuta(Ruta r) {
        Ruta nuevaRuta = new Ruta(r.getEstacionInicial(), r.getEstacionFinal1(), r.getEstacionFinal2(),
                r.getBicisRecogidas(), r.getBicisDejadas1(), r.getBicisDejadas2());
        return nuevaRuta;
    }


    /*********************/
    /****** GETTERS ******/
    /*********************/


    public ArrayList<Integer> getBicisNext() {return bicisNext;}

    //Devuelve en que posicion del vector estaciones se encuentra la estación con coordenadas x e y

    public int getNBicis() { return nbicis; }

    public int getNEstaciones() { return nestaciones; }

    public int getNFurgos() { return nfurgos; }

    public Estaciones getEstaciones() { return estaciones; }

    public ArrayList<Ruta> getRutas(){ return Rutas; }
    public float getCoste(){ return coste; }

    public int getNRutas() { return Rutas.size(); };

    public int getBicisNext(int x, int y) {
        int i = getEstacion(x, y);
        return bicisNext.get(i);
    }

    public int getEstacion(int x, int y) {
        for (int i = 0; i < estaciones.size(); ++i) {
            if (estaciones.get(i).getCoordX() == x && estaciones.get(i).getCoordY() == y) return i;
        }

        System.out.println("");
        System.out.println("GET ESTACION HA FALLAT");
        System.out.println("");

        return 0;
    }

    /*
    //Torna una estacio random diferent a e1 i e2
    public Estacion getEstacionRandom(Estacion e1, Estacion e2) {
        Random random = new Random();

        // Para generar un número en un rango específico, puedes usar el método nextInt con argumentos
        int i = random.nextInt(nestaciones);

        Estacion randomE = getEstaciones().get(i);
        System.out.println(coste);
        if (!randomE.equals(e1) && !randomE.equals(e2)) return randomE;
        else return getEstacionRandom(e1, e2);

    }*/


    /********************************/
    /****** ESTADOS INICIALES *******/
    /********************************/

    public boolean estadoInicial1() {
        Rutas = new ArrayList<Ruta>();
        return true;
    }
    public boolean estadoInicial2() {
        ordenarEstacionesPorDiferencia(estaciones);
        //List<Ruta> rutas = new ArrayList<>();
        Rutas = new ArrayList<>();

        for (int i = 0; i < estaciones.size() - 1; i++) {
            Estacion estacionFinal = estaciones.get(i);
            Estacion estacionInicial = estaciones.get(estaciones.size() - 1);
            int diferencia = estacionFinal.getNumBicicletasNext() - estacionFinal.getDemanda();
            if (diferencia > 0) {
                Ruta ruta = new Ruta(estacionInicial, estacionFinal, diferencia, diferencia);
                Rutas.add(ruta);
                nfurgos += 1;
            }
        }
        return false;
    }


    /*Funció auxiliar Esther*/
    public static void ordenarEstacionesPorDiferencia(List<Estacion> estaciones) {
        Collections.sort(estaciones, new Comparator<Estacion>() {
            @Override
            public int compare(Estacion estacion1, Estacion estacion2) {
                int diferencia1 = estacion1.getNumBicicletasNext() - estacion1.getDemanda();
                int diferencia2 = estacion2.getNumBicicletasNext() - estacion2.getDemanda();
                return Integer.compare(diferencia1, diferencia2);
            }
        });
    }

    /*************************/
    /****** OPERADORES *******/
    /*************************/

    //Nova ruta. De momento solo puede tener una estación final
    //Bicis recogidas <= nbicis en e1 && Bicis recogidas <= 30
    public void añadirFurgoneta(Estacion e1, Estacion e2, Estacion e3, int bicisRecogidas, int bicisDejadas) {
        //Ruta rutaNueva = new Ruta(e1, e2, e3, bicisRecogidas, bicisDejadas, bicisRecogidas - bicisDejadas);
        Ruta rutaNueva = new Ruta(e1, e2, bicisRecogidas, bicisDejadas);
        //System.out.println("Coste antes de la nueva ruta: " + coste);
        modificarBicisNext(e1.getCoordX(), e1.getCoordY(), -1*bicisRecogidas);
        modificarBicisNext(e2.getCoordX(), e2.getCoordY(), bicisDejadas);
        if (e3 != null) modificarBicisNext(e3.getCoordX(), e3.getCoordY(), bicisRecogidas - bicisDejadas);
        modificarCoste(rutaNueva);

        Rutas.add(rutaNueva);
        //System.out.println("Nuevo coste: " + coste);
    }

    public void cambiarEstacionInicial(Ruta ruta, Estacion newInicial) {
        //Ruta rutaNueva = new Ruta(e1, e2, e3, bicisRecogidas, bicisDejadas, bicisRecogidas - bicisDejadas);
        Ruta rutaNueva = new Ruta(newInicial, ruta.getEstacionFinal1(), ruta.getEstacionFinal2(), ruta.getBicisRecogidas(), ruta.getBicisDejadas1(), ruta.getBicisDejadas2());
        //System.out.println("Coste antes de la nueva ruta: " + coste);
        modificarCoste(rutaNueva);

        Rutas.add(rutaNueva);
        //System.out.println("Nuevo coste: " + coste);
    }

    /**************************/
    /****** MODIFICADORAS *****/
    /**************************/

    //Actualiza el nombre de bicicletas que habra en una estacion nuevas
    //Nuevas puede ser positivo si dejamos bicis o negativo si nos llevamos
    public void modificarBicisNext(int x, int y, int nuevas) {
        int i = getEstacion(x, y);
        bicisNext.set(i, bicisNext.get(i) + nuevas);
    }

    public void modificarCoste(Ruta ruta) {
        coste -= ruta.getCosteRuta();
        ruta.setCosteRuta(0);

        int c = 0;

        //Suma al coste los kilometros de la ruta ponderados por el numero de bicis transportado
        //c += distanciaRecorrida(ruta)*((ruta.getNBicis() + 9)/10);

        //Nos beneficia dejar una bici en una estacion, mientras no se supere la demanda de bicis necesaria
        c -= min(ruta.getBicisDejadas1(), bicisNecesarias(ruta.getEstacionFinal1()));

        if (ruta.getEstacionFinal2() != null) c -= min(ruta.getBicisDejadas2(), bicisNecesarias(ruta.getEstacionFinal2()));
        //Incrementa el coste por cada bici que recojamos por debajo de la demanda
        c += max(0, ruta.getBicisRecogidas() - bicisSobrantes(ruta.getEstacionInicial()));

        coste += c;
        ruta.setCosteRuta(c);
    }


    /*************************/
    /****** AUXILIARES *******/
    /*************************/

    public boolean rutaIniciaEnEstacion(Estacion estacion) {
        for (Ruta ruta : Rutas) {
            if (ruta.getEstacionInicial().equals(estacion)) {
                return true; // La ruta ya inicia en esta estación
            }
        }
        return false; // La estación no se usa como punto de inicio de ninguna ruta
    }

    //Bicis que faltan en una estacion
    public int bicisNecesarias(Estacion e) {
        return max(0, e.getDemanda() - getBicisNext(e.getCoordX(), e.getCoordY()));
    }

    //Bicis que sobran en una estacion
    public int bicisSobrantes(Estacion e) {
        return max(0, getBicisNext(e.getCoordX(), e.getCoordY()) - e.getDemanda());
    }

    //Distancia entre la estacion inicial y la estacion final 1
    public int distanciaRecorrida(final Ruta ruta) {
        int dis = distanciaEstaciones(ruta.getEstacionInicial(), ruta.getEstacionFinal1());
        if (ruta.getEstacionFinal2() != null) dis += distanciaEstaciones(ruta.getEstacionFinal1(), ruta.getEstacionFinal2());
        return dis;
    }

    //Devuelve la distancia entre las dos estaciones
    public int distanciaEstaciones(Estacion e1, Estacion e2) {
        return abs(e1.getCoordX() - e2.getCoordX()) + abs(e1.getCoordY() - e2.getCoordY());

    }

    public int getBicisMal() {
        int valor = 0;
        for (Ruta r1: Rutas) {
            int aux = r1.getEstacionFinal1().getDemanda();
            int aux2 = r1.nbicisDejadas1;
            valor += aux2 - aux;
        }
        return min(0, valor);
    }
}