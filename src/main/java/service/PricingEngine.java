package service;

public class PricingEngine {

    private static final double PRECIO_DIA = 40000;
    private static final double PRECIO_MES = 400000;
    private static final double PRECIO_TEMPORADA = 1600000;

    private static final double EXTRA_SOMBRILLA = 0.10; 
    private static final double EXTRA_VISTA_MAR = 0.15; 

    public static double calcularPrecio(String tipo, boolean sombrilla, boolean vistaMar) {
        double base = 0;

        if (tipo.equalsIgnoreCase("DIA")) {
            base = PRECIO_DIA;
        } else if (tipo.equalsIgnoreCase("SEMANA") || tipo.equalsIgnoreCase("MES")) {
            base = PRECIO_MES;
        } else if (tipo.equalsIgnoreCase("TEMPORADA")) {
            base = PRECIO_TEMPORADA;
        }

        double total = base;
        if (sombrilla) total += base * EXTRA_SOMBRILLA;
        if (vistaMar) total += base * EXTRA_VISTA_MAR;

        return total;
    }
}
