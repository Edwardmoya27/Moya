package edu.unc.moya.service;

import java.util.List;
import java.util.Optional;
import edu.unc.moya.domain.Reserva;

public interface ReservaService {
	List<Reserva>listarReserva();
	Optional<Reserva> buscarPorIdReserva(Long idReserva);
	Reserva grabarReserva(Reserva reserva);
	void eliminarReserva(long idReserva);
}
