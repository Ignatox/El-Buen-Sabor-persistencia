package com.utn.EBS.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cocinero")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Cocinero extends Usuario{

}