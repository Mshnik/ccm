package com.redpup.ccm.gui;

import com.redpup.ccm.gui.panels.templates.TemplatesPanel;
import com.redpup.ccm.gui.panels.UnimplementedPanel;
import com.redpup.ccm.gui.proto.GuiArgsProtos.PanelArgs;

/**
 *
 */
final class GuiPanels {
	private GuiPanels () {}

	static GuiPanel create(PanelArgs args) {
		switch (args.getName()) {
			case "Templates":
				return new TemplatesPanel();
			default:
				return new UnimplementedPanel(args.getName());
		}
	}

}
