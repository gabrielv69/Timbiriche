/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timbiriche;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author
 */
public class version3{

    Object[][] matriz = {{".", "-", ".", "-", "."}, {"|", "", "|", "", "|"}, {".", "-", ".", "-", "."}, {"|", "", "|", "", "|"}, {".", "-", ".", "-", "."}};
   Object[][] matrizvacia = {{".", "A", ".", "B", "."}, {"C", "", "|", "", "D"}, {".", "E", ".", "F", "."}, {"G", "", "H", "", "I"}, {".", "J", ".", "K", "."}};
    Scanner s = new Scanner(System.in);
    int a = 0;
    List<Object[][]> estados = new ArrayList<>();
    boolean encfinal = false;

    public void imprimirmatriz(Object[][] estadotras) {
        for (int i = 0; i < estadotras.length; i++) {
            for (int j = 0; j < estadotras.length; j++) {
                System.out.print("  " + estadotras[i][j]);
            }
            System.out.println("  ");
        }
    }

    public Object[] compararnumeros(Object[][] vacio, int numero) {
        Object[] g = new Object[3];
        boolean a = false;
        for (int i = 0; i < vacio.length; i++) {
            for (int j = 0; j < vacio.length; j++) {
                if (vacio[i][j].equals(numero)) {
                    a = true;
                    g[0] = a;
                    g[1] = i;
                    g[2] = j;
                }
            }
        }
        return g;
    }

    public int ingreso(List<Nodotimb> hijosar) {
    System.out.println("Ingrese su movimiento");
        try {
            String numero = s.nextLine();
            a = Integer.parseInt(numero);
            if(a>hijosar.size()){
                System.out.println("Ingrese de nuevo ");
                System.out.println("Solo entre 0 y "+hijosar.size());
                ingreso(hijosar);
            }
                
        } catch (Exception e) {
            System.out.println("Eso no es un número");
            System.out.println("Ingrese de nuevo ");
            ingreso(hijosar);
        }
        return a;

    }

   

    
    

    public List<Object[][]> buscarhijos(Object[][] estadoinicial) {
        List<Object[][]> hijosencontrados = new ArrayList<>();
        System.out.println("Nodo padre");
        hijosencontrados = hijosposibles(estadoinicial);
        return hijosencontrados;
    }

    public Object[][] hijonuevo(Object[][] m) {
        Object[][] nuevamatriz = new Object[5][5];
        for (int k = 0; k < m.length; k++) {
            for (int l = 0; l < m.length; l++) {
                nuevamatriz[k][l] = m[k][l];
            }
        }
        return nuevamatriz;
    }

    public List<Object[][]> hijosposibles(Object[][] m) {
        List<Object[][]> hijos = new ArrayList<>();
        String p = "";
        boolean h = false;
        boolean f = false;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                Object g = m[i][j]; 
                try {
                    p = g.toString();
                    Object[][] k = condicioncambio(p, m, i, j);
                    f = compararfinal(estados);
                    h = comparar(k, estados);
                    if (f == false) {
                        if (h == false) {
                            hijos.add(k);
                            estados.add(k);
                            
                        }
                    }

                } catch (Exception e) {
                    System.out.println("catch");
                }
            }

        }
        return hijos;
    }

    public void imprlis(List<Object[][]> elementos) {
        for (int i = 0; i < elementos.size(); i++) {
            Object[][] get = elementos.get(i);
            imprimirEstado(get);

        }
    }

    public void imprimirEstado(Object[][] estadotras) {
        for (int i = 0; i < estadotras.length; i++) {
            for (int j = 0; j < estadotras.length; j++) {
                System.out.print("  " + estadotras[i][j]);
            }
            System.out.println(" ");
        }
    }

  public Object[][] condicioncambio(String posi, Object[][] arre, int i, int j) {
        Object[][] nuevamatriz = hijonuevo(arre);
        if (posi.equals("A") || posi.equals("B") || posi.equals("E") || posi.equals("F") || posi.equals("J") || posi.equals("K")) {
            nuevamatriz[i][j] = "-";

        }

        if (posi.equals("C") || posi.equals("D") || posi.equals("G") || posi.equals("H") || posi.equals("I"
        )) {
            nuevamatriz[i][j] = "|";
        }
        return nuevamatriz;

    }

    public void posicionmatriz(int i, int j, Object[][] matriz, String e) {
        matriz[i][j] = e;
    }

    public boolean compararfinal(List<Object[][]> mat) {
        boolean comparador = false;
        for (int i = 0; i < mat.size(); i++) {
            Object[][] get = mat.get(i);
            comparador = Arrays.deepEquals(get, matriz);
            if (comparador == true) {
            }
        }
        return comparador;
    }

    public boolean comparar(Object[][] a, List< Object[][]> mat) {
        boolean repetidos = false;
        for (int i = 0; i < mat.size(); i++) {
            Object[][] get = mat.get(i);
            if (Arrays.deepEquals(a, get)) {
                repetidos = true;
                break;
            }
        }
        return repetidos;

    }

    public void imprimirarbol(Nodotimb raiz) {
        imprimirEstado(raiz.getEstado());
        List<Nodotimb> hijos = raiz.getHijos();
        for (int i = 0; i < hijos.size(); i++) {
            Nodotimb hijo = hijos.get(i);
            System.out.println("Condicion" + (i + 1));
            imprimirEstado(hijo.getEstado());
        }
    }
 public Nodotimb  usuario(Nodotimb padre) {    
       List<Nodotimb> hijosar = padre.getHijos();
        int q = ingreso(hijosar);
           Nodotimb hijo = hijosar.get(q-1);
     return hijo;
 }
 
    public void armarniveles(Nodotimb hijo) {
            hijo.setHijos(gethijos(buscarhijos(hijo.getEstado())));
            imprimirarbol(hijo);    
    }

    public List<Nodotimb> gethijos(List<Object[][]> hijos) {
        List<Nodotimb> listahijos = new ArrayList<>();
        for (int i = 0; i < hijos.size(); i++) {
            Object[][] estado = hijos.get(i);
            Nodotimb nh = new Nodotimb(new ArrayList<>(), estado);
            listahijos.add(nh);
        }
        return listahijos;
    }

    public void armararbolo() {
        Nodotimb raiz = new Nodotimb();
        raiz.setEstado(matrizvacia);
        estados.add(matrizvacia);
        boolean finalsito=compararfinal(estados);
         if (finalsito==false) {
        List<Object[][]> hijos = buscarhijos(raiz.getEstado());
        finalsito=compararfinal(estados);
        if (finalsito==false) {
        raiz.setHijos(gethijos(hijos));
        finalsito=compararfinal(estados);
        imprimirarbol(raiz);
        }
        if (finalsito==false) {
                finalsito=compararfinal(estados);
                recursividad(raiz);
    }
         } else {
            System.out.println("Ya llegaron al estado final");
           
        }
}
    public void recursividad(Nodotimb raiz){
                
                System.out.println("Primer jugador");
                Nodotimb siguiente = usuario(raiz);
                armarniveles(siguiente);
                System.out.println("Segundo jugador");
                Nodotimb next = usuario(siguiente);
                armarniveles(next);
                raiz=next;
                recursividad(raiz);
    }
}
