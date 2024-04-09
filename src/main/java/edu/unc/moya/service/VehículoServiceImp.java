package edu.unc.moya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unc.moya.domain.Vehiculo;
import edu.unc.moya.repositories.VehiculoRepository;
@Service
public class VehículoServiceImp implements VehículoService {

	@Autowired
	private VehiculoRepository VehículoRep;

	@Override
	public List<Vehiculo> listarVehículo() {
		return (List<Vehiculo>)VehículoRep.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Vehiculo> buscarPorIdVehículo(Long idVehículo) {
		
		return VehículoRep.findById(idVehículo);
	}

	@Override
	public Vehiculo grabarVehículo(Vehiculo vehiculo) {
		return VehículoRep.save(vehiculo);
	}

	@Override
	public void eliminarVehículo(long idVehículo) {
		VehículoRep.deleteById(idVehículo);
	}

}
