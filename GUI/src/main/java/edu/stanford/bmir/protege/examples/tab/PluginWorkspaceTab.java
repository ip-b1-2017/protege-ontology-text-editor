package edu.stanford.bmir.protege.examples.tab;

import org.protege.editor.owl.ui.OWLWorkspaceViewsTab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PluginWorkspaceTab extends OWLWorkspaceViewsTab {

	private static final Logger log = LoggerFactory.getLogger(PluginWorkspaceTab.class);

	public PluginWorkspaceTab() {
		setToolTipText("Tool tip text");
	}

    @Override
	public void initialise() {
		super.initialise();
		log.info("Tab initialized");
	}

	@Override
	public void dispose() {
		super.dispose();
		log.info("Tab disposed");
	}
}
