package com.backend.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;

    @Column(name = "precio_compra")
    private Double precioCompra;

    @Column(name = "precio_venta")
    private Double precioVenta;

    private String imagen;
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private MarcaEntity marca;

    @Column(name = "fecha_entrada")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fehaEntrada;

    @PrePersist
    public void onCreate() {
        this.fehaEntrada = LocalDate.now();
    }
}
