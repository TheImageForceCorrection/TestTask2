package compositelaunchconfiguration.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;

import compositelaunchconfiguration.controller.AllLaunchConfigurationsListener;
import compositelaunchconfiguration.controller.SelectedLaunchConfigurationsListener;
import compositelaunchconfiguration.errors.CompositeLaunchConfigurationException;


public class CompositeLaunchConfigurationTab extends AbstractLaunchConfigurationTab
{
	
	private AvailableLaunchConfigurationsList availableLaunchConfigs;
	private Label listLabel;
	private String name;
	private SelectedLaunchConfigurationsListener curSelectionListener;
	private AllLaunchConfigurationsListener allLaunchConfigsListner; 
	
	public CompositeLaunchConfigurationTab(String name)
	{
		this.name = name;
	}

	@Override
	public void createControl(Composite parent)
	{
		Composite comp = new Group(parent, SWT.BORDER);
		setControl(comp);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(comp);
		listLabel = new Label(comp, SWT.NONE);
		listLabel.setText("Choose Launching Projects (press Ctrl for multiple selection):");
		GridDataFactory.fillDefaults().grab(true, false).applyTo(listLabel);		
		availableLaunchConfigs = new AvailableLaunchConfigurationsList(comp, SWT.MULTI);
		
		try
		{
			availableLaunchConfigs.refresh();
		}
		catch (CompositeLaunchConfigurationException ex) 
		{
			ex.printStackTrace();
			System.err.println(ex.what());
		}
		catch (CoreException ex) 
		{
			ex.printStackTrace();
		}
		
		GridDataFactory.fillDefaults().grab(true, false).applyTo(availableLaunchConfigs);
		
		try
		{
			curSelectionListener = new SelectedLaunchConfigurationsListener(availableLaunchConfigs);
			allLaunchConfigsListner = new AllLaunchConfigurationsListener(availableLaunchConfigs);
		}
		catch (CompositeLaunchConfigurationException ex)
		{
			ex.printStackTrace();
			System.err.println(ex.what());
		}
		
		availableLaunchConfigs.addSelectionListener(curSelectionListener);
		getLaunchManager().addLaunchConfigurationListener(allLaunchConfigsListner);
	}

	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration)
	{

	}

	@Override
	public void initializeFrom(ILaunchConfiguration configuration)
	{

	}

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration)
	{

	}

	@Override
	public String getName()
	{
		return name;
	}

}
