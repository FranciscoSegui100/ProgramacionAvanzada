/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author franc
 */
public class Computadora {
    private String procesador;
    private int ram;
    private boolean tieneGPU;
    private Computadora(Builder builder){
        this.procesador=builder.procesador;
        this.ram=builder.ram;
        this.tieneGPU=builder.tieneGPU;
    }
    
    public static class Builder{
        private String procesador;
        private int ram=8;
        private boolean tieneGPU=false;
        public Builder(String procesador){this.procesador=procesador;}
        public Builder setRam(int ram){this.ram=ram;return this;}
        public Builder setGPU(boolean tiene) { this.tieneGPU = tiene; return this; }
        public Computadora build() { return new Computadora(this); }
    }
}
