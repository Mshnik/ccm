package com.redpup.ccm.gui;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.redpup.ccm.gui.proto.GuiArgsProtos.GuiArgs;
import com.redpup.ccm.gui.proto.GuiArgsProtos.PanelArgs;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

/**
 *
 */
@AutoValue
public abstract class Gui {

	/**
	 * Creates and shows GUI.
	 */
	@CanIgnoreReturnValue
	public static Gui create(GuiArgs guiArgs) {
		Gui.Builder builder = builder().setJFrame(new JFrame());
		builder.jFrame().getContentPane().setLayout(new BorderLayout());

		// Create tab panel on west.
		builder.jFrame().getContentPane()
				.add(createTabPanel(builder, guiArgs.getPanelList()),
						BorderLayout.WEST);

		// Create main panels.
		createGuiPanels(builder, guiArgs.getPanelList());

		// Build gui.
		Gui gui = builder.build();

		// Hook panel buttons up to panels.
		gui.panelButtonsMap().forEach((name, button) -> button.addActionListener(
				e ->
						SwingUtilities.invokeLater(() ->
								gui.setVisiblePanel(name))));

		// Set initial panel.
		gui.setVisiblePanel(guiArgs.getPanelList().get(0).getName());

		// Set basic settings and make visible.
		gui.jFrame().setMinimumSize(new Dimension(500, 500));
		gui.jFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.jFrame().pack();
		gui.jFrame().setLocationRelativeTo(null);
		gui.jFrame().setVisible(true);

		return gui;
	}

	/**
	 * Creates the tab panel and adds buttons to {@code builder}.
	 */
	private static JPanel createTabPanel(Gui.Builder builder,
			List<PanelArgs> panelList) {
		Color panelColor = new Color(198, 207, 208, 255);
		JPanel panel = new JPanel();
		panel.setBackground(panelColor);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new LineBorder(panelColor.darker(), 2));

		for (PanelArgs panelArgs : panelList) {
			JButton jButton = new JButton(panelArgs.getName());
			panel.add(jButton);
			builder.panelButtonsMapBuilder().put(panelArgs.getName(), jButton);
		}

		return panel;
	}

	/**
	 * Creates the all gui panels and adds them to {@code builder}.
	 */
	private static void createGuiPanels(Builder builder,
			List<PanelArgs> panelList) {
		for (PanelArgs panelArgs : panelList) {
			GuiPanel guiPanel = GuiPanels.create(panelArgs);
			builder.panelsMapBuilder().put(panelArgs.getName(), guiPanel);
		}
	}

	/**
	 * Creates a new {@link Builder}.
	 */
	private static Builder builder() {
		return new AutoValue_Gui.Builder()
				.setPanelsMap(ImmutableMap.of())
				.setPanelButtonsMap(ImmutableMap.of());
	}

	/**
	 * Builder for {@link Gui}.
	 */
	@AutoValue.Builder
	abstract static class Builder {

		Builder() {
		}

		/**
		 * Sets {@link #jFrame()}.
		 */
		abstract Builder setJFrame(JFrame jFrame);

		/**
		 * Returns {@link Gui#jFrame()}.
		 */
		abstract JFrame jFrame();

		/**
		 * Sets {@link #panelButtonsMap()}.
		 */
		abstract Builder setPanelButtonsMap(
				ImmutableMap<String, JButton> panelButtonsMap);

		/**
		 * Returns build access to {@link #panelButtonsMap()}.
		 */
		abstract ImmutableMap.Builder<String, JButton> panelButtonsMapBuilder();

		/**
		 * Sets {@link #panelsMap()}.
		 */
		abstract Builder setPanelsMap(ImmutableMap<String, GuiPanel> panelsMap);

		/**
		 * Returns build access to {@link #panelsMap()}.
		 */
		abstract ImmutableMap.Builder<String, GuiPanel> panelsMapBuilder();

		/**
		 * Builds this into a {@link Gui}.
		 */
		abstract Gui build();
	}

	/**
	 * The {@link JFrame} shown by this GUI.
	 */
	abstract JFrame jFrame();

	/**
	 * The {@link JButton}s that control what panel is showing.
	 */
	abstract ImmutableMap<String, JButton> panelButtonsMap();

	/**
	 * The {@link GuiPanel}s that can be shown on this GUI.
	 */
	abstract ImmutableMap<String, GuiPanel> panelsMap();

	/**
	 * The currently visible panel.
	 */
	private GuiPanel currentlyVisiblePanel;

	Gui() {
	}

	/**
	 * Sets the panel with the given {@code name} to be visible.
	 */
	private void setVisiblePanel(String name) {
		GuiPanel panel = checkNotNull(panelsMap().get(name));

		if (panel == currentlyVisiblePanel) {
			return;
		}

		if (currentlyVisiblePanel != null) {
			jFrame().getContentPane().remove(currentlyVisiblePanel);
		}

		jFrame().getContentPane().add(panel, BorderLayout.CENTER);
		currentlyVisiblePanel = panel;
		jFrame().getContentPane().revalidate();
	}
}
