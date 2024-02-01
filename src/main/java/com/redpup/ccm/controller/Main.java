package com.redpup.ccm.controller;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.redpup.ccm.controller.service.CCMServiceModule;
import com.redpup.ccm.gui.proto.GuiArgsProtos.GuiArgs;
import com.redpup.ccm.gui.proto.GuiArgsProtos.PanelArgs;

/**
 *
 */
public class Main {

	private static final GuiArgs GUI_ARGS = GuiArgs.newBuilder()
			.addPanel(PanelArgs.newBuilder().setName("Gallery"))
			.addPanel(PanelArgs.newBuilder().setName("Templates"))
			.addPanel(PanelArgs.newBuilder().setName("Icons"))
			.addPanel(PanelArgs.newBuilder().setName("Images"))
			.addPanel(PanelArgs.newBuilder().setName("Content"))
			.build();

	public static void main(String[] args) {
		Guice.createInjector(new CCMServiceModule())
				.getInstance(Main.class)
				.run();
	}

	@Inject
	Main() {}

	private void run() {

	}
}