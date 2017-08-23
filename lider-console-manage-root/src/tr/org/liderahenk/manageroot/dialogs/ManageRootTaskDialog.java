package tr.org.liderahenk.manageroot.dialogs;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.org.liderahenk.manageroot.constants.ManageRootConstant;
import tr.org.liderahenk.manageroot.i18n.Messages;
import tr.org.liderahenk.liderconsole.core.dialogs.DefaultTaskDialog;
import tr.org.liderahenk.liderconsole.core.exceptions.ValidationException;

/**
 * Task execution dialog for manage-root plugin.
 * 
 */
public class ManageRootTaskDialog extends DefaultTaskDialog {
	
	private static final Logger logger = LoggerFactory.getLogger(ManageRootTaskDialog.class);
	private Text textRootPassword;
	
	// TODO do not forget to change this constructor if SingleSelectionHandler is used!
	public ManageRootTaskDialog(Shell parentShell, Set<String> dnSet) {
		super(parentShell, dnSet,true,true);
	}

	@Override
	public String createTitle() {
		return Messages.getString("TITLE");
	}

	@Override
	public Control createTaskDialogArea(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		GridData  gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		 gd.widthHint = SWT.DEFAULT;
		 gd.heightHint = SWT.DEFAULT;
		composite.setLayoutData( gd);
		
		
		Label lblRootPassword = new Label(composite, SWT.NONE);
		
		lblRootPassword.setText(Messages.getString("SET_ROOT_PASS"));
		
		textRootPassword = new Text(composite, SWT.BORDER);
		GridData gd_RootPassword = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_RootPassword.widthHint = 135;
		textRootPassword.setLayoutData(gd_RootPassword);
		return composite;
	}

	@Override
	public void validateBeforeExecution() throws ValidationException {
		if(textRootPassword.getText().equals("")) throw new ValidationException(Messages.getString("FILL_FIELDS"));
	}
	
	@Override
	public Map<String, Object> getParameterMap() {
	Map<String, Object> map = new HashMap<>();
		
		String pass = textRootPassword.getText();
		
		map.put("RootPassword", pass );
		
		
		return map;
	}

	@Override
	public String getCommandId() {
		return "SET_ROOT_PASSWORD";
	}

	@Override
	public String getPluginName() {
		return ManageRootConstant.PLUGIN_NAME;
	}

	@Override
	public String getPluginVersion() {
		return ManageRootConstant.PLUGIN_VERSION;
	}
	
	@Override
	public String getMailSubject() {
		
		return "Root Şifresi";
	}

	@Override
	public String getMailContent() {
		
		return "cn={ahenk} ahenkde tanımlamış olduğunuz root şifresi {date} tarihinde başarı ile değiştirilmiştir.";
	}
	
}
