package compositelaunchconfiguration.controller;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationListener;

import compositelaunchconfiguration.errors.CompositeLaunchConfigurationException;
import compositelaunchconfiguration.ui.AvailableLaunchConfigurationsList;

public class AllLaunchConfigurationsListener implements ILaunchConfigurationListener
{

	private AvailableLaunchConfigurationsList availableLaunchConfigs;

	
	public AllLaunchConfigurationsListener(AvailableLaunchConfigurationsList availableLaunchConfigs)
																	throws CompositeLaunchConfigurationException
	{
		if (availableLaunchConfigs==null)
			throw new CompositeLaunchConfigurationException(
									"argument AvailableLaunchConfigurationsList availableLaunchConfigs = null in"+
									"public AllLaunchConfigurationsListener::AllLaunchConfigurationsListener(" +
									"AvailableLaunchConfigurationsList availableLaunchConfigs)");

		this.availableLaunchConfigs = availableLaunchConfigs;
	}
	
	@Override
	public void launchConfigurationAdded(ILaunchConfiguration configuration)
	{
		try
		{
			availableLaunchConfigs.refresh();
		}
		catch(CompositeLaunchConfigurationException ex)
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
	public void launchConfigurationChanged(ILaunchConfiguration configuration)
	{
		try
		{
			availableLaunchConfigs.refresh();
		}
		catch(CompositeLaunchConfigurationException ex)
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
	public void launchConfigurationRemoved(ILaunchConfiguration configuration)
	{
		try
		{
			availableLaunchConfigs.refresh();
		}
		catch(CompositeLaunchConfigurationException ex)
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
