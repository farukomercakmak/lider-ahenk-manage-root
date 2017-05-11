package tr.org.liderahenk.manageroot.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.org.liderahenk.manageroot.constants.manage-rootConstants;
import tr.org.liderahenk.manageroot.dialogs.manage-rootProfileDialog;
import tr.org.liderahenk.manageroot.i18n.Messages;
import tr.org.liderahenk.liderconsole.core.constants.LiderConstants;
import tr.org.liderahenk.liderconsole.core.editorinput.ProfileEditorInput;

/**
 * Profile definition handler for manage-root plugin.
 *
 */
public class manage-rootProfileHandler extends AbstractHandler {

	private Logger logger = LoggerFactory.getLogger(manage-rootProfileHandler.class);

	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
        IWorkbenchPage page = window.getActivePage();
        
        try {
			// Here we open default profile editor implementation so that all
			// profiles can be handled by Lider Console Core.
			// We also pass our profile dialog implementation as parameter to
			// allow the editor use it dynamically.
			page.openEditor(new ProfileEditorInput(Messages.getString("manage-root"), manage-rootConstants.PLUGIN_NAME, 
					manage-rootConstants.PLUGIN_VERSION, new manage-rootProfileDialog()), 
					LiderConstants.EDITORS.PROFILE_EDITOR);
		} catch (PartInitException e) {
			logger.error(e.getMessage(), e);
		}

        return null;
	}

}
