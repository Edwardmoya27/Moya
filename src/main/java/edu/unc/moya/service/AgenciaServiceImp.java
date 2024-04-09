package edu.unc.moya.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import edu.unc.moya.domain.Garaje;
import edu.unc.moya.repositories.GarajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.unc.moya.domain.Agencia;
import edu.unc.moya.repositories.AgenciaRepository;
@Service
public class 	AgenciaServiceImp implements AgenciaService {
	@Autowired
	private AgenciaRepository AgenciaRep;

	@Autowired
	private GarajeRepository garajeRepository;

	@Override
	public List<Agencia> listarAgencia() {
		return (List<Agencia>)AgenciaRep.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Agencia> buscarPorIdAgencia(Long idAgencia) {
		
		return AgenciaRep.findById(idAgencia);
	}

	@Override
	public Agencia grabarAgencia(Agencia agencia) {
		return AgenciaRep.save(agencia);
	}

	@Override
	public void eliminarAgencia(long idAgencia) {
		AgenciaRep.deleteById(idAgencia);
		
	}

	@Override
	public void agregarGarajeAAgencia(long idAgencia, long idGaraje) {
		Optional<Agencia> agenciaOptional = AgenciaRep.findById(idAgencia);
		Optional<Garaje> garajeOptional = garajeRepository.findById(idGaraje);
		if (agenciaOptional.isPresent() && garajeOptional.isPresent()) {
			Agencia agencia = agenciaOptional.get();
			Garaje garaje = garajeOptional.get();
			garaje.setAgencia(agencia);
			garajeRepository.save(garaje);
		} else {
			throw new NoSuchElementException("La agencia o el garaje no existen");
		}
	}

}
