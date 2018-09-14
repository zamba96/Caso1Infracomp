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
		boolean termina = false;
		while(!termina) {
			Mensaje mensaje = null;
			mensaje = buffer.atender();

			while(mensaje == null && !termina) {
				try {
					sleep(2L); //Para que no exiga la CPU al 100% si no se est� usando lol

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				yield();
				//System.out.println("Servidor yield: " + id);
				mensaje = buffer.atender();
				termina = buffer.termina();
			}
			if(!termina) {
				mensaje.setRespuesta("respuesta a pregunta: " + mensaje.getPregunta() + "\npor el servidor: " + id + "\n=============");
				synchronized (mensaje) {
					mensaje.notify();
				}
				termina = buffer.termina();
			}
			termina = buffer.termina();

		}
		System.out.println("===================================\nCierra el servidor: " + id + "\n==================================");
	}


}
