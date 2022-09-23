package com.projeto.academia.Academia.model;

import java.util.ArrayList;

public class MaturidadeNivel3 {

	ArrayList<ItemNivel3> links;

	public MaturidadeNivel3() {
		this.links = new ArrayList<>();
	}

	public MaturidadeNivel3(ArrayList<ItemNivel3> links) {
		super();
		this.links = links;
	}

	public ArrayList<ItemNivel3> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<ItemNivel3> links) {
		this.links = links;
	}
	
}
