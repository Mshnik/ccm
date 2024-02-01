package com.redpup.ccm.gui.panels;

import com.redpup.ccm.gui.GuiPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;

/**
 *
 */
public final class UnimplementedPanel extends GuiPanel {
	private final String name;

	public UnimplementedPanel(String name) {
		this.name = name;
		add(new JLabel(name + " Unimplemented"), BorderLayout.CENTER);
	}

	@Override
	public String getName() {
		return name;
	}
}

