package com.utn.EBS.Entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="administrador")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Administrador extends Usuario{

}