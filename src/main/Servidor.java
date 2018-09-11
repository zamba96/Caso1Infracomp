package main;

public class Servidor extends Thread{

	private Buffer buffer;
	
	private int id;
	
	public Servidor(Buffer buffer, int id) {
		
		this.buffer = buffer;
		this.id = id;
	}
	
	public void run() {
		System.out.println("Inicia Servidor: " + id);
		while(true) {
			Mensaje mensaje = null;
			//mensaje = buffer.darMensaje();
			while(mensaje == null) {
				yield();
				//mensaje = buffer.darMensaje();
			}
			mensaje.setRespuesta("respuesta");
			mensaje.notify();
		}
	}
	
	
}
