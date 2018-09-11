package main;

import java.io.*;

public class Main {

	static int tamanoBuffer;
	
	static int numServidores;
	
	static int numClientes;
	
	static int numConsultasClientes;
	
	
	/*
	 * Formato del archivo:
	 * <tamanoBuffer>,<numServidores(threads)>,<numClientes>,<numConsultas>
	 */
	public static final String rutaArchivo = "./data/test1.txt";
	
	public static void main(String[] args) {
		
		File archivo = new File(rutaArchivo);
		try {
			BufferedReader in = new BufferedReader(new FileReader(archivo));
			String[] lineas = in.readLine().split(",");
			tamanoBuffer = Integer.parseInt(lineas[0]);
			numServidores = Integer.parseInt(lineas[1]);
			numClientes = Integer.parseInt(lineas[2]);
			numConsultasClientes = Integer.parseInt(lineas[3]);
			System.out.println("Inicia el programa con:");
			System.out.println("    Buffer: " + tamanoBuffer);
			System.out.println("    Servidores: " + numServidores);
			System.out.println("    Clientes: " + numClientes);
			System.out.println("    Consultas por clientes: " + numConsultasClientes);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

		
	}

}
