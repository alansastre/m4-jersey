package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.example.demo.domain.SmartPhone;
import com.example.demo.domain.pieces.Battery;
import com.example.demo.domain.pieces.CPU;
import com.example.demo.domain.pieces.Camera;
import com.example.demo.domain.pieces.RAM;
import com.example.demo.service.SmartPhoneService;
import com.example.demo.service.SmartPhoneServiceImpl;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SmartPhoneServiceImplTest {

	SmartPhoneService sut;
	
	@BeforeEach
	void setUp() throws Exception {
		sut = new SmartPhoneServiceImpl();
		this.demoData();

	}
	
	/**
	 * Resetea el mapa
	 */
	private void demoData() {
		sut.deleteAll();
		SmartPhone phone1 = new SmartPhone(1L, "One plus 9", 
				new RAM(1L, "DDR4", 8),
				new Battery(1L, 4500.0),
				new CPU(1L, 4),
				false,
				new Camera(1L, "front camera", 12.5));
		
		SmartPhone phone2 = new SmartPhone(2L, "IPhone X", 
				new RAM(2L, "DDR3", 4),
				new Battery(2L, 3500.0),
				new CPU(2L, 2),
				true,
				new Camera(2L, "front camera", 8.5));
		
		SmartPhone phone3 = new SmartPhone(3L, "Samsung Galaxy 54", 
				new RAM(3L, "DDR5", 32),
				new Battery(3L, 9500.0),
				new CPU(3L, 16),
				true,
				new Camera(3L, "back camera", 58.5));
		sut.save(phone1);
		sut.save(phone2);
		sut.save(phone3);
	}

	@Order(1)
	@Test
	void testCount() {
		
		Integer numPhones = sut.count();
		assertEquals(3, numPhones);
		
		sut.delete(1L);
		
		numPhones = sut.count();
		assertEquals(2, numPhones);
		
		sut.delete(9999L);
		
		numPhones = sut.count();
		assertEquals(2, numPhones);
		
		sut.deleteAll();
		
		numPhones = sut.count();
		assertEquals(0, numPhones);
	}


	
	@Order(2)
	@Test
	void testFindAll() {
		List<SmartPhone> phones = sut.findAll();
		assertEquals(3, phones.size());
		
		sut.delete(1L);
		
		phones = sut.findAll();
		assertEquals(2, phones.size());
		
		for (SmartPhone smartPhone : phones) {
			assertNotNull(smartPhone.getId());
		}
		
	}
	

	@Test
	void testSaveCreate() {
		SmartPhone phone1 = new SmartPhone(0L, "IPhone X", 
				new RAM(2L, "DDR3", 4),
				new Battery(2L, 3500.0),
				new CPU(2L, 2),
				true,
				new Camera(2L, "front camera", 8.5));
		
		List<SmartPhone> phones = sut.findAll();
		SmartPhone result = sut.save(phone1);
		assertNotEquals(0, result.getId());
		assertEquals(phones.size() + 1, sut.findAll().size());

	}
	
	@Test
	void testSaveEdit() {
		SmartPhone phone1 = new SmartPhone(3L, "IPhone X editado", 
				new RAM(2L, "DDR3", 4),
				new Battery(2L, 3500.0),
				new CPU(2L, 2),
				true,
				new Camera(2L, "front camera", 8.5));
		
		List<SmartPhone> phones = sut.findAll();
		SmartPhone result = sut.save(phone1);
		assertNotEquals(3L, result.getId());
		assertEquals(phones.size(), sut.findAll().size());
	}


	@Test
	void testFindByWifiTrue() {
		List<SmartPhone> phones = sut.findByWifi(true);
		assertEquals(2, phones.size());
	}
	
	@Test
	void testFindByWifiFalse() {
		List<SmartPhone> phones = sut.findByWifi(false);
		assertEquals(1, phones.size());
	}

}
