
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author franc
 */
interface Nodo {
    void mostrarDetalles();
}

class Archivo implements Nodo {
    private String nombre;
    public Archivo(String n) { this.nombre = n; }
    public void mostrarDetalles() { System.out.println("Archivo: " + nombre); }
}

class Carpeta implements Nodo {
    private List<Nodo> hijos = new ArrayList<>();
    public void agregar(Nodo n) { hijos.add(n); }
    
    public void mostrarDetalles() {
        for (Nodo n : hijos) n.mostrarDetalles();
    }
}
