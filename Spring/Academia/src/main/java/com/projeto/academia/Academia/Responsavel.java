package com.projeto.academia.Academia;

public @interface Responsavel {
	
	public enum QuemFez {
		
		GEOVANI, HENRIQUE, GABRIEL, GEISO
	};

	QuemFez quemFez() default QuemFez.GEOVANI;
	//Até porque eu quem fiz tudo isso :v
}
