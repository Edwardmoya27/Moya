package edu.unc.moya.service;

import java.util.List;
import java.util.Optional;
import edu.unc.moya.domain.Agencia;

public interface AgenciaService {
	List<Agencia>listarAgencia();
	Optional<Agencia> buscarPorIdAgencia(Long idAgencia);
	Agencia grabarAgencia(Agencia agencia);
	void eliminarAgencia(long idAgencia);
	void agregarGarajeAAgencia(long idAgencia, long idGaraje);

}
