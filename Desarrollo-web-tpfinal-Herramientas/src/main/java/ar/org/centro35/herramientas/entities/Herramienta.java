package ar.org.centro35.herramientas.entities;

import ar.org.centro35.herramientas.enums.HerramientaEstado;
import ar.org.centro35.herramientas.enums.HerramientaTipo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


 @Entity
 @Table(name="herramientas")
public class Herramienta implements Comparable<Herramienta>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;
    private String codigo_articulo;
    private String marca;
    @Column(name = "tipo")
    @Enumerated(value = EnumType.STRING)
    private HerramientaTipo herramienta_tipo;
    private String descripcion;
    @Column(name = "estado")
    @Enumerated(value = EnumType.STRING)
    private HerramientaEstado herramienta_estado;
    //@Transient
    private String observaciones;

    public Herramienta() {
    }

    public Herramienta(String codigo_articulo, String marca, HerramientaTipo herramienta_tipo, String descripcion,
            HerramientaEstado herramienta_estado, String observaciones) {
        this.codigo_articulo = codigo_articulo;
        this.marca = marca;
        this.herramienta_tipo = herramienta_tipo;
        this.descripcion = descripcion;
        this.herramienta_estado = herramienta_estado;
        this.observaciones = observaciones;
    }

    public Herramienta(int id, String codigo_articulo, String marca, HerramientaTipo herramienta_tipo,
            String descripcion, HerramientaEstado herramienta_estado, String observaciones) {
        this.id = id;
        this.codigo_articulo = codigo_articulo;
        this.marca = marca;
        this.herramienta_tipo = herramienta_tipo;
        this.descripcion = descripcion;
        this.herramienta_estado = herramienta_estado;
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Herramienta [id=" + id + ", codigo_articulo=" + codigo_articulo + ", marca=" + marca
                + ", herramienta_tipo=" + herramienta_tipo + ", descripcion=" + descripcion + ", herramienta_estado="
                + herramienta_estado + ", observaciones=" + observaciones + "]";
    }

    @Override
    public int compareTo(Herramienta para) {
        return this.toString().compareTo(para.toString());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo_articulo() {
        return codigo_articulo;
    }

    public void setCodigo_articulo(String codigo_articulo) {
        this.codigo_articulo = codigo_articulo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public HerramientaTipo getHerramienta_tipo() {
        return herramienta_tipo;
    }

    public void setHerramienta_tipo(HerramientaTipo herramienta_tipo) {
        this.herramienta_tipo = herramienta_tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public HerramientaEstado getHerramienta_estado() {
        return herramienta_estado;
    }

    public void setHerramienta_estado(HerramientaEstado herramienta_estado) {
        this.herramienta_estado = herramienta_estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}
