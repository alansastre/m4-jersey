package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.SmartPhone;

public interface SmartPhoneService {

	/**
	 * It returns total number of smartphones
	 * @return
	 */
    Integer count();

    /**
     * It retrieves all smartphones
     */
    List<SmartPhone> findAll();

    SmartPhone findOne(Long id);
    
    // Filtros
     List<SmartPhone> findByWifi(Boolean wifi);
     
    // List<SmartPhone> findByManufacturer(String manufacturer);

    SmartPhone save(SmartPhone smartphone);

    boolean delete(Long id);

    void deleteAll();
}
