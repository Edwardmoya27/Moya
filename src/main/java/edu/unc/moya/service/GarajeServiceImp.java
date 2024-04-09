package edu.unc.moya.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import edu.unc.moya.domain.Agencia;
import edu.unc.moya.domain.Vehiculo;
import edu.unc.moya.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unc.moya.domain.Garaje;
import edu.unc.moya.repositories.GarajeRepository;
@Service
public class GarajeServiceImp implements GarajeService {

	@Autowired
	private GarajeRepository GarajeRep;

	@Autowired
	private VehiculoRepository vehiculoRepository;

	@Override
	public List<Garaje> listarGaraje() {
		return (List<Garaje>)GarajeRep.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Garaje> buscarPorIdGaraje(Long idGaraje) {
		
		return GarajeRep.findById(idGaraje);
	}

	@Override
	public Garaje grabarGaraje(Garaje garaje) {
		return GarajeRep.save(garaje);
	}

	@Override
	public void eliminarGaraje(long idGaraje) {
		GarajeRep.deleteById(idGaraje);
	}

	@Override
	public void agregarVehiculoAGaraje(long idGaraje, long idVehiculo) {
		Optional<Garaje> garajeOptional = GarajeRep.findById(idGaraje);
		Optional<Vehiculo> vehiculoOptional = vehiculoRepository.findById(idVehiculo);
		if (garajeOptional.isPresent() && vehiculoOptional.isPresent()) {
			Garaje garaje = garajeOptional.get();
			Vehiculo vehiculo = vehiculoOptional.get();
			vehiculo.setGaraje(garaje);
			vehiculoRepository.save(vehiculo);
		} else {
			throw new NoSuchElementException("El garaje o el vehiculo no existen");
		}
	}


}
