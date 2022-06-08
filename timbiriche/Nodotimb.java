/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timbiriche;

import java.util.List;

/**
 *
 * @author eliic
 */
public class Nodotimb {
     List<Nodotimb>hijos;
    Object [][] estado;

    public Nodotimb(List<Nodotimb> hijos, Object[][] estado) {
        this.hijos = hijos;
        this.estado = estado;
    }

    public Nodotimb() {
    }


    public List<Nodotimb> getHijos() {
        return hijos;
    }

    public void setHijos(List<Nodotimb> hijos) {
        this.hijos = hijos;
    }

    public Object[][] getEstado() {
        return estado;
    }

    public void setEstado(Object[][] estado) {
        this.estado = estado;
    }
    
}
