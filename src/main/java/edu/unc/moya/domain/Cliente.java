package edu.unc.moya.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  idCliente;
	private String dni;
    private String nombre;
    private String direccion;
    private String telefono;
    private String codigoUnico;

    public Cliente() {
        this.codigoUnico = generarCodigoUnico();
    }

    private String generarCodigoUnico() {
        return UUID.randomUUID().toString();
    }

    @OneToMany(mappedBy = "clienteReservante", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reserva> reservasClienteReservante;

    @OneToMany(mappedBy = "clienteAval", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reserva> reservasClienteAval;


}
