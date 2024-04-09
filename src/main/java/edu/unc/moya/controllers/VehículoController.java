package edu.unc.moya.controllers;

import java.util.List;
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

import edu.unc.moya.domain.Vehiculo;
import edu.unc.moya.service.VehículoService;

@RestController
@RequestMapping("api/v1/vehiculos")
public class VehículoController {
	
		@Autowired
		private VehículoService vehiculoService;
		
		@GetMapping
		public List<Vehiculo> ListarVehículo(){
			return vehiculoService.listarVehículo();
		}
		@GetMapping("/{id}")
		public ResponseEntity<?> listarPorVehículo(@PathVariable Long id){
			Optional<Vehiculo> VehículoOptional = vehiculoService.buscarPorIdVehículo(id);
			if(VehículoOptional.isPresent()) {
				return ResponseEntity.ok(VehículoOptional.get());
			}
			return ResponseEntity.notFound().build();
		}
		@PostMapping
		public ResponseEntity<?> crearVehículo(@RequestBody Vehiculo vehiculo){
			return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoService.grabarVehículo(vehiculo));
			
		}
		
		@PutMapping("/{id}")
		public ResponseEntity<?> editarVehículo(@PathVariable Long id,@RequestBody Vehiculo vehiculo){
			Optional<Vehiculo> o = vehiculoService.buscarPorIdVehículo(id);
			if(o.isPresent()) {
				Vehiculo vehiculoDB = o.get();
				vehiculoDB.setModelo(vehiculo.getModelo());
				vehiculoDB.setMarca(vehiculo.getMarca());
				vehiculoDB.setColor(vehiculo.getColor());
				return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoService.grabarVehículo(vehiculoDB));
			}
			return ResponseEntity.notFound().build(); 
		}
		
		@DeleteMapping ("/{id}")
		public ResponseEntity<?> eliminarVehículo(@PathVariable Long id){
			Optional<Vehiculo> o = vehiculoService.buscarPorIdVehículo(id);
			if(o.isPresent()) {
				vehiculoService.eliminarVehículo(id);
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build(); 
		}
	
}
