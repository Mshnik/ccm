package com.redpup.ccm.gui;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 */
public final class Gui {

	public static Gui create() {
		JFrame jFrame = new JFrame();
		jFrame.setMinimumSize(new Dimension(100, 100));
		jFrame.pack();
		jFrame.setVisible(true);
		return new Gui(jFrame);
	}

	private final JFrame jFrame;

	private Gui(JFrame jFrame) {
		this.jFrame = jFrame;
	}
}
