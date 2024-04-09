package edu.unc.moya.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  idReserva;

    @ManyToOne
    @JoinColumn(name = "id_cliente_reservante")

    private Cliente clienteReservante;

    @ManyToOne
    @JoinColumn(name = "id_cliente_aval")

    private Cliente clienteAval;

    @ManyToMany
    @JoinTable(
            name = "reserva_vehiculo",
            joinColumns = @JoinColumn(name = "id_reserva"),
            inverseJoinColumns = @JoinColumn(name = "id_vehiculo")
    )
    private List<Vehiculo> vehiculos;

    private Date fechaInicio;
    private Date fechaFin;
    private double precioTotal;
    private boolean estadoEntrega;

}
