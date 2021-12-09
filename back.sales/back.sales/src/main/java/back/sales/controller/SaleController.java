package back.sales.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.DuplicateKeyException;

import back.sales.model.Sales;
import back.sales.repository.SaleRepository;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class SaleController {

	@Autowired
	SaleRepository saleRepository;
	
	@GetMapping("/sales/consecutive")
	public ResponseEntity<Long> getSaleConsecutive() {
		try {
		ArrayList<Sales> aux = (ArrayList<Sales>) saleRepository.findAll();
		long mayor = 0;
		for (Sales v : aux) {
			if (v.getSalecode() > mayor) {
				mayor = v.getSalecode();
			}
		}
		if (aux.isEmpty()) {
			mayor=1;
		}
		
			return new ResponseEntity<>(mayor, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/sales")
	public ResponseEntity<List<Sales>> getAllSales() {
		try {
			List<Sales> ventas = new ArrayList<Sales>();

			saleRepository.findAll().forEach(ventas::add);

			if (ventas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(ventas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/sales/id/{id}")
	public ResponseEntity<Sales> getSalesById(@PathVariable("id") String id) {
		Optional<Sales> ventaData = saleRepository.findById(id);

		if (ventaData.isPresent()) {
			return new ResponseEntity<>(ventaData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/sales/code/{code}")
	public ResponseEntity<Sales> getSalesBySaleCode(@PathVariable("code") Long code) {
		Sales aux = saleRepository.findBySaleCode(code).get(0);
		Optional<Sales> ventaData = Optional.of(aux);

		if (ventaData.isPresent()) {
			return new ResponseEntity<>(ventaData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/sales/identification/{identification}")
	public ResponseEntity<ArrayList<Sales>> getSalesByIdentification(@PathVariable("identification") Long identification) {
		ArrayList<Sales> aux = (ArrayList<Sales>) saleRepository.findByIdentification(identification);
		Optional<ArrayList<Sales>> ventaData = Optional.of(aux);

		if (ventaData.isPresent()) {
			return new ResponseEntity<>(ventaData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/sales")
	public ResponseEntity<Sales> createSale(@RequestBody Sales sale) {
		try {
			Sales _venta = saleRepository.save(new Sales(sale.getIdentification(), sale.getSalecode(),
					sale.getSaledetail(), sale.getIvasale(), sale.getTotalsale(), sale.getSalevalue()));
			return new ResponseEntity<>(_venta, HttpStatus.CREATED);
		} catch (DuplicateKeyException e) {
			return new ResponseEntity<>(null, HttpStatus.IM_USED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// RECOMENDACIÓN, ENVIAR JSON SIN ID PERO EL SI ES OBLIGATORIO EN LA URL
	@PutMapping("/sales/id/{id}")
	public ResponseEntity<Sales> updateSaleById(@PathVariable("id") String id, @RequestBody Sales sale) {
		Optional<Sales> ventaData = saleRepository.findById(id);

		if (ventaData.isPresent()) {
			Sales _venta = ventaData.get();
			_venta.setIdentification(sale.getIdentification());
			_venta.setSalecode(sale.getSalecode());
			_venta.setSaledetail(sale.getSaledetail());
			_venta.setIvasale(sale.getIvasale());
			_venta.setTotalsale(sale.getTotalsale());
			_venta.setSalevalue(sale.getSalevalue());

			return new ResponseEntity<>(saleRepository.save(_venta), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/sales/code/{code}")
	public ResponseEntity<Sales> updateSalesBySaleCode(@PathVariable("code") long code,
			@RequestBody Sales sale) {
		Sales aux = saleRepository.findBySaleCode(code).get(0);
		Optional<Sales> ventaData = Optional.of(aux);

		if (ventaData.isPresent()) {
			Sales _venta = ventaData.get();
			_venta.setIdentification(sale.getIdentification());
			_venta.setSalecode(sale.getSalecode());
			_venta.setSaledetail(sale.getSaledetail());
			_venta.setIvasale(sale.getIvasale());
			_venta.setTotalsale(sale.getTotalsale());
			_venta.setSalevalue(sale.getSalevalue());

			return new ResponseEntity<>(saleRepository.save(_venta), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/sales/id/{id}")
	public ResponseEntity<HttpStatus> deleteSalesById(@PathVariable("id") String id) {
		try {
			saleRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/sales/identification/{identification}")
	public ResponseEntity<HttpStatus> deleteVentasByCedulacliente(@PathVariable("identification") long identification) {
		try {
			saleRepository.deleteBySaleCode(identification);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/sales/code/{code}")
	public ResponseEntity<HttpStatus> deleteSalesByName(@PathVariable("code") Long code) {
		try {
			saleRepository.deleteBySaleCode(code);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/sales")
	public ResponseEntity<HttpStatus> deleteAllVentas() {
		try {
			saleRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/sales/{identification}")
	public ResponseEntity<List<Sales>> findByCedulacliente(@PathVariable("identification") Long identification) {
		try {
			List<Sales> ventas = saleRepository.findByIdentification(identification);

			if (ventas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(ventas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
