package rs.gui;

import java.util.Random;

import javax.swing.SwingWorker;

import rs.controlador.Coordinador;

public class ConsultasHilo extends SwingWorker<Void, Void> {

	private int baseSleepTime;
	private Coordinador coordinador;
	private UsuariosFormConsultas usuariosFormConsultas;

	public ConsultasHilo(int baseSleepTime, Coordinador coordinador, UsuariosFormConsultas usuariosFormConsultas) {
		super();
		this.baseSleepTime = baseSleepTime;
		this.coordinador = coordinador;
		this.usuariosFormConsultas=usuariosFormConsultas;
	}

	/**
	 * Main task. Executed in background thread.
	 */
	@Override
	public Void doInBackground() {
		Random random = new Random();
		int progress = 0;
		// Initialize progress property.
		setProgress(0);
		while (progress < 100 && !isCancelled()) {
			// Sleep for up to one second.
			try {
				Thread.sleep(random.nextInt(1000) + baseSleepTime);
			} catch (InterruptedException ignore) {
			}
			// Make random progress.
			progress += random.nextInt(10);
			setProgress(Math.min(progress, 100));
		}

		return null;
	}

	/**
	 * Executed in event dispatching thread
	 */
	@Override
	public void done() {
		if (!isCancelled())
			coordinador.realizarConsulta(usuariosFormConsultas);

	}
}
