package main;

import java.util.ArrayList;

public class Buffer {
	/**
	 * Variables privadas del buffer
	 */
	private int tamanoBuffer;
	private ArrayList<Mensaje> buffer;
	private int clientes;
	private Object semaforo;
	/**
	 * Metodo constructor de la clase Buffer
	 * @param n tamaño maximo del buffer
	 */
	public Buffer(int n){
		tamanoBuffer=n;
		buffer= new ArrayList<>();
		clientes=0;
		semaforo= new Object();
	}
	/**
	 * Metodo el cual pone los mensajes del cliente en el buffer
	 * @param n mensaje a encolar en el buffer
	 * @return true si se encolo el mensaje, false de lo contrario
	 */
	

	public synchronized boolean enviar(Mensaje n){
		if(buffer.size()==tamanoBuffer){
			return false;
		}else{
				buffer.add(n);
				clientes++;
				//semaforo.notify();
				return true;
		}
	}
	/**
	 * Metodo el cual le da el mensaje al  servidor para que lo pueda atender
	 * @return mensaje el cual debe atender el cliente
	 */
	
	public Mensaje atender(){
		synchronized(this){
			if(buffer.isEmpty()){
				return null;
			}
			return buffer.remove(0);
		}
	}
	/**
	 * metodo que me dice si se puede meter mensajes al buffer
	 * @return true si se puede meter, false de lo contrario
	 */
	
	public synchronized boolean dartamanoBuffer(){
		if(buffer.size()==tamanoBuffer){
			return false;
		}
		else{
			return true;
		}
	}
	

}
