package edu.unc.moya.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Garaje {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  idGaraje;
    private String ubicacion;
    private int capacidad;

	@ManyToOne
	@JoinColumn(name = "id_agencia")
	private Agencia agencia;

	@OneToMany(mappedBy = "garaje")
	@JsonIgnore
	private List<Vehiculo> vehiculos;

}
