/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author franc
 */
public class Strategy {
    // 1. La interfaz estrategia
    interface MetodoPago {
        void pagar(int monto);
    }

    // 2. Implementaciones concretas
    class PagoTarjeta implements MetodoPago {
        public void pagar(int monto) { System.out.println("Pagando " + monto + " con Tarjeta."); }
    }

    class PagoCrypto implements MetodoPago {
        public void pagar(int monto) { System.out.println("Pagando " + monto + " con Bitcoin."); }
    }

    // 3. El contexto
    class CarritoCompras {
        private MetodoPago metodo;
        public void setMetodo(MetodoPago m) { this.metodo = m; }
        public void realizarCompra(int total) { metodo.pagar(total); }
    }
    
}
