package edu.unc.moya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unc.moya.domain.Reserva;
import edu.unc.moya.repositories.ReservaRepository;
@Service
public class ReservaServiceImp implements ReservaService {

	@Autowired
	private ReservaRepository ReservaRep;

	@Override
	public List<Reserva> listarReserva() {
		return (List<Reserva>)ReservaRep.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Reserva> buscarPorIdReserva(Long idReserva) {
		
		return ReservaRep.findById(idReserva);
	}

	@Override
	public Reserva grabarReserva(Reserva reserva) {
		return ReservaRep.save(reserva);
	}

	@Override
	public void eliminarReserva(long idReserva) {
		ReservaRep.deleteById(idReserva);
	}

}
