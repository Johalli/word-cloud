import java.io.*;

public class Generador2 extends Generador{
	protected String[] listaNegra;


	public Generador2(File texto, File listaNegra, int limite) {
		super(File texto, int limite);
		this.listaNegra=archivoATexto(listaNegra);
	}


	@Override
	public void llenarArreglo(){ //aqui tambien se van generando las labels y se guardan en el estado de cada palabra, gracias a la agregacion se pueden recuperar con getLabel y usar en el UI en esta clase
		//importante ordenar el arreglo de palabra mas usada a menos usada
		String[] textoSeparado = texto.toLowerCase().split("\\W+"); //pasa todo a minusculas y despues busca todas las no palabras con W y con + junta los que estan seguidos para separador, ver http://regexr.com/3dcpk

		if (palabras==null) {
			//agregar el primer elemento manualmente
			Label etiqueta = new Label(textoSeparado[0]);
			palabras = new Palabra[1];
			palabras[0] = new Palabra(textoSeparado[0], etiqueta);
		}

			for (int i=1; i<textoSeparado.length; i++) { //importante el uno para no contar la primera palabra dos veces
				String buscar= textoSeparado[i];
				if (!checarListaNegra(buscar)) { //solo se hace la operacion por cada palabra si no esta en la lista negra
					boolean yaExiste = false;
					for (Palabra palabraActual : palabras) {
						if (palabraActual.getContenido().equals(buscar)) {
							yaExiste = true;
							palabraActual.actualizarFrecuencia();
						}
					}
					if (!yaExiste) {
						//no se encontro la palabra (String buscar) en el gran arreglo de palabras entonces hay que crearla
						agregarPalabra(buscar);
					}
				}
			}
		}

		ordenarArreglo();

	}

	public boolean checarListaNegra(String buscar) {
		boolean coincidencia=false;
		for (String palabra : listaNegra) {
			if(palabra.equals(buscar)) {
				coincidencia=true;
			}
		}
		return coincidencia;
	}

}
