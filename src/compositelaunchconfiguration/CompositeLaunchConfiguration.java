package compositelaunchconfiguration;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchManager;

import compositelaunchconfiguration.errors.CompositeLaunchConfigurationException;

public final class CompositeLaunchConfiguration
{
		
	public static SelectedLaunchConfigurations selectedLaunchConfigs = new SelectedLaunchConfigurations();

	public static final String compositeLaunchConfigurationType = new String("composite.launchconfiguration.type");

	
	private CompositeLaunchConfiguration()
	{
		
	}
	
	public static ILaunchConfiguration FindLaunchConfiguration(String name)
												throws CoreException, CompositeLaunchConfigurationException
	{
		if (name == null)
			throw new CompositeLaunchConfigurationException("argument String name = null in"+
			"public static ILaunchConfiguration CompositeLaunchConfiguration::FindLaunchConfiguration(String name)");

		ILaunchManager curLaunchManager = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfigurationType compositeLaunchConfigType =
						curLaunchManager.getLaunchConfigurationType(compositeLaunchConfigurationType);

		if (compositeLaunchConfigType == null)
			throw new CompositeLaunchConfigurationException("compositeLaunchConfigurationType not found in "+
		"public static ILaunchConfiguration CompositeLaunchConfiguration::FindLaunchConfiguration(String name)");
		
		ILaunchConfigurationType[] configTypes = DebugPlugin.getDefault().getLaunchManager().
														getLaunchConfigurationTypes();
		
		for (ILaunchConfigurationType curConfigType:configTypes)
		{
			ILaunchConfiguration[] launchConfigs = curLaunchManager.getLaunchConfigurations(curConfigType);
			if (!compositeLaunchConfigType.equals(curConfigType))
				for (ILaunchConfiguration curLaunchConfig:launchConfigs)
					if (curLaunchConfig.getName().equals(name))
						return curLaunchConfig;
		}
		
		return null;
	}	

}
