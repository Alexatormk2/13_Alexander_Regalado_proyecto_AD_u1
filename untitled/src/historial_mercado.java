public class historial_mercado {

    item pieza;
    item[] expositor;
    survivor comprador;
    survivor vendedor;


    public historial_mercado(item pieza, survivor comprador, survivor vendedor) {
        this.pieza = pieza;
        this.comprador = comprador;
        this.vendedor = vendedor;
    }

    public item[] getExpositor() {
        return expositor;
    }

    public void setExpositor(item[] expositor) {
        this.expositor = expositor;
    }

    public historial_mercado(item pieza, survivor comprador) {
        this.pieza = pieza;
        this.comprador = comprador;
    }

    public historial_mercado() {
    }

    public item getPieza() {
        return pieza;
    }

    public void setPieza(item pieza) {
        this.pieza = pieza;
    }

    public survivor getComprador() {
        return comprador;
    }

    public void setComprador(survivor comprador) {
        this.comprador = comprador;
    }

    public survivor getVendedor() {
        return vendedor;
    }

    public void setVendedor(survivor vendedor) {
        this.vendedor = vendedor;
    }


}
