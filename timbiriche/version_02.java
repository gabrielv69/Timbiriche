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
public class version_02 {

    Object[][] matriz = {{".", "-", ".", "-", "."}, {"|", "", "|", "", "|"}, {".", "-", ".", "-", "."}, {"|", "", "|", "", "|"}, {".", "-", ".", "-", "."}};
    Object[][] matrizvacia = {{".", 1, ".", 2, "."}, {3, "", "|", "", 4}, {".", 5, ".", 6, "."}, {7, "", 8, "", 9}, {".", 10, ".", 11, "."}};
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

    public int ingreso() {

        try {
            String numero = s.nextLine();
            a = Integer.parseInt(numero);

        } catch (Exception e) {
            System.out.println("Eso no es un número");
            System.out.println("Ingrese de nuevo ");
            ingreso();
        }
        return a;

    }

    public int ingresarmov() {
        System.out.println("Ingrese su movimiento");
        int f = ingreso();
        if (a <= 11 && a > 0) {
        } else {
            System.out.println("No existe esa posición");
            ingresarmov();
        }
        return f;

    }

    public void prueba() {
        int q = ingresarmov();
        Object[] y = compararnumeros(matrizvacia, q);
        String w = cambio(q);
        int i = (int) y[1];
        int j = (int) y[2];
        posicionmatriz(i, j, matrizvacia, w);
        imprimirmatriz(matrizvacia);

    }

    public String cambio(int pos) {
        String t;
        if (pos == 1 || pos == 2 || pos == 5 || pos == 6 || pos == 10 || pos == 11) {
            t = "-";
        } else {
            t = "|";
        }
        return t;
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
        if (posi.equals("1") || posi.equals("2") || posi.equals("5") || posi.equals("6") || posi.equals("10") || posi.equals("11")) {
            nuevamatriz[i][j] = "-";
        
        }          
        
        if (posi.equals("3") || posi.equals("4") || posi.equals("7") || posi.equals("8") || posi.equals("9"
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

    public void armarniveles(Nodotimb padre) {
        List<Nodotimb> hijosar = padre.getHijos();
        for (int i = 0; i < hijosar.size(); i++) {
            Nodotimb hijo = hijosar.get(i);
            hijo.setHijos(gethijos(buscarhijos(hijo.getEstado())));
                       

            imprimirarbol(hijo);
//            System.out.println("lista hijos de hijos");
//            System.out.println(" Fin lista hijos de hijos");
            for (int j = 0; j < hijosar.size(); j++) {
                Nodotimb get = hijosar.get(j);
                armarniveles(get);

            }
        }
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
        armarniveles(raiz);
    }
         } else {
            System.out.println("Ya llegaron al estado final");
        }
}
}
