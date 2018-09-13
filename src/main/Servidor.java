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
			mensaje = buffer.atender();
			while(mensaje == null) {
				try {
					sleep(200L); //Para que no exiga la CPU al 100% si no se está usando lol
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				yield();
				mensaje = buffer.atender();
			}
			mensaje.setRespuesta("respuesta a pregunta: " + mensaje.getPregunta() + "\npor el servidor: " + id);
			System.out.println(mensaje.getRespuesta());
			
			synchronized (mensaje) {
				mensaje.notify();
			}
			
		}
	}
	
	
}
