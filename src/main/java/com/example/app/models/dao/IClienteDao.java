package com.example.app.models.dao;

import java.util.List;

import com.example.app.models.Cliente;

public interface IClienteDao {

	public List<Cliente> findAll();
	
	public void save(Cliente Cliente);
	
	
	public Cliente findOne(long id);
}
