package com.empresa.service;

import java.util.ArrayList;

public abstract interface AbstractServiceCRUD<C,D, I> {
	
	public void create(C classs);
	
	public D readById(I id);
	
	public void update(D classDTO);
	
	public ArrayList<D> read();
	
	public void delete(I id);
	
}