package edu.unc.moya.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Random;

@Entity
@Data
public class Vehiculo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  idVehiculo;
    private String modelo;
    private String marca;
    private String color;
    private String placa;

    @ManyToOne
    @JoinColumn(name = "id_garaje")
    private Garaje garaje;

    @PrePersist
    public void inicializarPlaca() {
        // Generar la placa del vehículo
        this.placa = generarPlaca();
    }

    private String generarPlaca() {
        return String.valueOf(generarLetraAleatoria()) +
                generarNumeroAleatorio() +
                generarLetraAleatoria() +
                "-" +
                generarNumeroAleatorio() +
                generarNumeroAleatorio() +
                generarNumeroAleatorio();
    }

    public static char generarLetraAleatoria() {
        Random random = new Random();
        return (char) (random.nextInt(26) + 'A');
    }
    public static int generarNumeroAleatorio() {
        Random random = new Random();
        return random.nextInt(10);
    }

}
