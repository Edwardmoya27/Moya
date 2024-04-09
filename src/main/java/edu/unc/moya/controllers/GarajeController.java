package edu.unc.moya.controllers;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

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

import edu.unc.moya.domain.Garaje;
import edu.unc.moya.service.GarajeService;

@RestController
@RequestMapping("api/v1/garajes")
public class GarajeController {
	
		@Autowired
		private GarajeService garajeService;
		
		@GetMapping
		public List<Garaje> ListarGaraje(){
			return garajeService.listarGaraje();
		}
		@GetMapping("/{id}")
		public ResponseEntity<?> listarPorGaraje(@PathVariable Long id){
			Optional<Garaje> GarajeOptional = garajeService.buscarPorIdGaraje(id);
			if(GarajeOptional.isPresent()) {
				return ResponseEntity.ok(GarajeOptional.get());
			}
			return ResponseEntity.notFound().build();
		}
		@PostMapping
		public ResponseEntity<?> crearGaraje(@RequestBody Garaje garaje){
			return ResponseEntity.status(HttpStatus.CREATED).body(garajeService.grabarGaraje(garaje));
			
		}
		
		@PutMapping("/{id}")
		public ResponseEntity<?> editarGaraje(@PathVariable Long id,@RequestBody Garaje garaje){
			Optional<Garaje> o = garajeService.buscarPorIdGaraje(id);
			if(o.isPresent()) {
				Garaje GarajeDB = o.get();
				GarajeDB.setUbicacion(garaje.getUbicacion());
				GarajeDB.setCapacidad(garaje.getCapacidad());
				return ResponseEntity.status(HttpStatus.CREATED).body(garajeService.grabarGaraje(GarajeDB));
			}
			return ResponseEntity.notFound().build(); 
		}
		
		@DeleteMapping ("/{id}")
		public ResponseEntity<?> eliminarGaraje(@PathVariable Long id){
			Optional<Garaje> o = garajeService.buscarPorIdGaraje(id);
			if(o.isPresent()) {
				garajeService.eliminarGaraje(id);
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build(); 
		}

	@PostMapping("/{idGaraje}/vehiculos/{idVehiculo}")
	public ResponseEntity<?> agregarVehiculoAGaraje(@PathVariable Long idGaraje, @PathVariable Long idVehiculo) {
		try {
			garajeService.agregarVehiculoAGaraje(idGaraje, idVehiculo);
			return ResponseEntity.ok().body(Map.of("status", "ok", "message", "Vehiculo agregado exitosamente al garaje"));
		} catch (NoSuchElementException e) {
			return ResponseEntity.ok().body(Map.of("status", "error", "message", "Ha ocurrido un error al agregar el vehiculo"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al agregar el garaje a la agencia.");
		}
	}
	
}
