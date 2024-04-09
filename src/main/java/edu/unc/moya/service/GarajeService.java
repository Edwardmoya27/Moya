package edu.unc.moya.service;

import java.util.List;
import java.util.Optional;
import edu.unc.moya.domain.Garaje;

public interface GarajeService {
	List<Garaje>listarGaraje();
	Optional<Garaje> buscarPorIdGaraje(Long idGaraje);
	Garaje grabarGaraje(Garaje garaje);
	void eliminarGaraje(long idGaraje);

	void agregarVehiculoAGaraje(long idGaraje, long idVehiculo);

}
