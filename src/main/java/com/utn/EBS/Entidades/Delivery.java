package com.utn.EBS.Entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="delivery")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Delivery extends Usuario{

}
