package com.redpup.ccm.gui.panels;

import com.redpup.ccm.gui.GuiPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;

/**
 *
 */
public final class UnimplementedPanel extends GuiPanel {
	public UnimplementedPanel(String name) {
		setLayout(new BorderLayout());
		add(new JLabel(name), BorderLayout.CENTER);
	}
}

