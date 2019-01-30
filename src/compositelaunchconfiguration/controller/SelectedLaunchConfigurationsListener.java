package compositelaunchconfiguration.controller;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import compositelaunchconfiguration.CompositeLaunchConfiguration;
import compositelaunchconfiguration.errors.CompositeLaunchConfigurationException;
import compositelaunchconfiguration.ui.AvailableLaunchConfigurationsList;

public class SelectedLaunchConfigurationsListener implements SelectionListener
{
	
	private AvailableLaunchConfigurationsList availableLaunchConfigs;
	
	public SelectedLaunchConfigurationsListener(AvailableLaunchConfigurationsList availableLaunchConfigs)
																		throws CompositeLaunchConfigurationException
	{
		if (availableLaunchConfigs==null)
			throw new CompositeLaunchConfigurationException(
							"argument AvailableLaunchConfigurationsList availableLaunchConfigs = null in"+
							"public SelectedLaunchConfigurationsListener:: SelectedLaunchConfigurationsListener(" +
							"AvailableLaunchConfigurationsList availableLaunchConfigs)");
		
		this.availableLaunchConfigs = availableLaunchConfigs;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e)
	{
		try
		{
			CompositeLaunchConfiguration.selectedLaunchConfigs.refresh(
						availableLaunchConfigs.getSelectedLaunchConfigurations());
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
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e)
	{
		try
		{

		CompositeLaunchConfiguration.selectedLaunchConfigs.refresh(
							availableLaunchConfigs.getSelectedLaunchConfigurations());
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
	}

}
