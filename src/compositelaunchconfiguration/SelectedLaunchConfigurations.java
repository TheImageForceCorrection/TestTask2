package compositelaunchconfiguration;

import java.util.ArrayList;

import org.eclipse.debug.core.ILaunchConfiguration;

import compositelaunchconfiguration.errors.CompositeLaunchConfigurationException;

public class SelectedLaunchConfigurations
{
	
	private ArrayList<ILaunchConfiguration> configsList;
	
	public SelectedLaunchConfigurations()
	{
		configsList = new ArrayList<ILaunchConfiguration>();
	}
	
	public ILaunchConfiguration[] getLaunchConfigurations()
	{
		ILaunchConfiguration[] configsArray = new ILaunchConfiguration[configsList.size()];
		configsArray = configsList.toArray(configsArray);
		
		return configsArray;
	}
	
	public void refresh(ILaunchConfiguration[] newLaunchConfigs) throws CompositeLaunchConfigurationException
	{
		if (newLaunchConfigs == null)
			throw new CompositeLaunchConfigurationException(
					"argument ILaunchConfiguration[] newLaunchConfigs = null in"+
					"public void SelectedLaunchConfigurations::refresh(ILaunchConfiguration[] newLaunchConfigs)");

		configsList.clear();
		
		for (ILaunchConfiguration curLaunchConfig:newLaunchConfigs)
			configsList.add(curLaunchConfig);
	}
	
}
