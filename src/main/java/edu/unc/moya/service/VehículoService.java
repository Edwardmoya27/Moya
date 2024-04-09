package edu.unc.moya.service;

import java.util.List;
import java.util.Optional;
import edu.unc.moya.domain.Vehiculo;

public interface VehículoService {
	List<Vehiculo>listarVehículo();
	Optional<Vehiculo> buscarPorIdVehículo(Long idVehículo);
	Vehiculo grabarVehículo(Vehiculo vehiculo);
	void eliminarVehículo(long idVehículo);
}
