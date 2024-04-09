package edu.unc.moya.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Agencia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  idAgencia;
    private String nombre;
    private String ubicacion;

	@OneToMany(mappedBy = "agencia")
	@JsonIgnore
	private List<Garaje> garajes;

}
