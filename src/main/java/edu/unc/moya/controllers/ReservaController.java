package edu.unc.moya.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import edu.unc.moya.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.unc.moya.domain.Reserva;
import edu.unc.moya.service.ReservaService;

@RestController
@RequestMapping("api/v1/reservas")
public class ReservaController {
	
		@Autowired
		private ReservaService reservaService;
		
		@GetMapping
		public List<Reserva> ListarReserva(){
			return reservaService.listarReserva();
		}
		@GetMapping("/{id}")
		public ResponseEntity<?> listarPorReserva(@PathVariable Long id){
			Optional<Reserva> reservaOptional = reservaService.buscarPorIdReserva(id);
			if(reservaOptional.isPresent()) {
				Reserva reserva = reservaOptional.get();
				Cliente clienteReservante = reserva.getClienteReservante();
				Cliente clienteAval = reserva.getClienteAval();
				Map<String, Object> response = new HashMap<>();
				response.put("reserva", reserva);
				response.put("clienteReservante", clienteReservante);
				response.put("clienteAval", clienteAval);
				return ResponseEntity.ok(response);
			}
			return ResponseEntity.notFound().build();
		}
		@PostMapping
		public ResponseEntity<?> crearReserva(@RequestBody Reserva reserva){
			return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.grabarReserva(reserva));
		}
		
		@PutMapping("/{id}")
		public ResponseEntity<?> editarReserva(@PathVariable Long id,@RequestBody Reserva reserva){
			Optional<Reserva> o = reservaService.buscarPorIdReserva(id);
			if(o.isPresent()) {
				Reserva ReservaDB = o.get();
				ReservaDB.setFechaInicio(reserva.getFechaInicio());
				ReservaDB.setFechaFin(reserva.getFechaFin());
				ReservaDB.setEstadoEntrega(reserva.isEstadoEntrega());
				ReservaDB.setPrecioTotal(reserva.getPrecioTotal());
				return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.grabarReserva(ReservaDB));
			}
			return ResponseEntity.notFound().build(); 
		}
		
		@DeleteMapping ("/{id}")
		public ResponseEntity<?> eliminarReserva(@PathVariable Long id){
			Optional<Reserva> o = reservaService.buscarPorIdReserva(id);
			if(o.isPresent()) {
				reservaService.eliminarReserva(id);
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build(); 
		}
	
}
