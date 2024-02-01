package com.redpup.ccm.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 */
public abstract class GuiPanel extends JPanel {

	public GuiPanel() {
		setLayout(new BorderLayout());
	}

	/** Returns the name of this panel. */
	public abstract String getName();

}
