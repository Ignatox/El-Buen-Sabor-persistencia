package com.utn.EBS.Entidades;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.utn.EBS.Enumeraciones.UnidadMedida;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ingrediente")
//Usamos la siguiente etiqueta para hacer un update de sql al atributo "DELETED" a TRUE
@SQLDelete(sql = "UPDATE ingrediente SET deleted = true WHERE id=?")
//Siempre que busquemos entidades, no van a hacer incluidas las que tengan su atributo deleted= true
@Where(clause = "deleted=false")

public class Ingrediente extends BaseEntidad{
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "costo", nullable = false)
    private double costo;

    @Column(name = "stock_actual", nullable = false)
    private int stockActual;

    @Column(name = "stock_minimo", nullable = false)
    private int stockMinimo;

    @Column(name = "foto")
    private String foto;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidad_medida")
    private UnidadMedida unidadMedida;

    @JsonManagedReference(value = "ingrediente-producto-ingrediente")
    @OneToMany(mappedBy = "ingrediente")
    private List<ProductoIngrediente> productos;

    @JsonManagedReference(value="ingrediente-rubro")
    @ManyToOne
    @JoinColumn(name = "rubro_id")
    private  Rubro rubro;
}
