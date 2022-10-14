public class historial_mercado {

    item pieza;
    survivor comprador;
    survivor vendedor;

    public historial_mercado(item pieza, survivor comprador, survivor vendedor) {
        this.pieza = pieza;
        this.comprador = comprador;
        this.vendedor = vendedor;
    }

    public historial_mercado(item pieza, survivor comprador) {
        this.pieza = pieza;
        this.comprador = comprador;
    }

    public historial_mercado() {
    }
}
