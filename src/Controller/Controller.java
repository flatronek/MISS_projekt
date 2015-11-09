/*
 * authors: Dominik Juraszek, Konrad Chmiel, Sebastian Brandys
 */
package Controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import graph.Graf;

public class Controller extends Thread {

	/**
	 * Dane zebrane z interfejsu, niezbedne do dzialania algorytmu genetycznego
	 */
	private Graf graf = null; // klasa przechowuje informacje na temat grafu
								// (jego reprezentacja)

	private GraphVisualizationAndButtonsStateActualizer aktualizatorGrafu; // graph
																			// actualizer

	private boolean paused = true; // flaga do wstrzymania watku wykonujacego
									// algorytm
	private boolean finished = true; // flaga ustawiana w momencie zakonczenia
										// algorytmu
	private boolean next = true; // flaga ustawiana w momencie kiedy algorytm ma
									// zaczac przeszukiwanie dla zwiekszonej
									// liczby wierzch.

	/**
	 * 
	 *
	 * @return true jezeli algorytm jest zakonczony
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * Setter, ustawia aktualizator grafu
	 *
	 * @param aktualizator
	 *            grafu
	 * 
	 */
	public void setAktualizator(
			GraphVisualizationAndButtonsStateActualizer aktualizatorGrafu) {
		this.aktualizatorGrafu = aktualizatorGrafu;

	}

	/**
	 * Setter ustawia graf
	 *
	 * @param graf
	 */
	public void setGraf(Graf graf) {
		this.graf = graf;
	}

	

	/**
	 * konczy algorytm
	 */
	public void stopSolving() {
		finished = true;
	}

	/**
	 * wstrzymuje algorytm
	 */
	public void pauseSolving() {
		paused = true;
	}

	/**
	 * wznawia algorytm
	 */
	public synchronized void resumeSolving() {
		paused = false;
		notify();
	}

	@Override
	public void run() {
		while (true) {
			if (!paused) {
				runAlgorithm();
				pauseSolving();
			}
			synchronized (this) {
				while (paused) {
					try {
						wait();
					} catch (InterruptedException ex) {
						Logger.getLogger(
								GraphVisualizationAndButtonsStateActualizer.class
										.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		}
	}

	/**
	 * glowna petla algorytmu
	 */
	public void runAlgorithm() {
	

	}

	/**
	 * Getter
	 *
	 * @return graf
	 */
	public Graf getGraf() {
		return graf;
	}

	
}
