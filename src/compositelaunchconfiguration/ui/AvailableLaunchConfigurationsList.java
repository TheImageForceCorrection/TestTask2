package compositelaunchconfiguration.ui;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

import compositelaunchconfiguration.CompositeLaunchConfiguration;
import compositelaunchconfiguration.errors.CompositeLaunchConfigurationException;


public class AvailableLaunchConfigurationsList extends List {
	
	public AvailableLaunchConfigurationsList(Composite parent, int style)
	{
		super(parent, style);
	}
	
	@Override
	protected void checkSubclass()
	{
		
	}
	
	public ILaunchConfiguration[] getSelectedLaunchConfigurations()
									throws CoreException, CompositeLaunchConfigurationException
	{
		int selectionCount = getSelectionCount();
		int[] indices = getSelectionIndices();
		ILaunchConfiguration[] selectedConfigs = new ILaunchConfiguration[selectionCount];
		
		for (int i=0;i<selectionCount;i++)
				selectedConfigs[i] = CompositeLaunchConfiguration.FindLaunchConfiguration(
						getItem(indices[i]));
		
		return selectedConfigs;
	}
	
	public ILaunchConfiguration[] getLaunchConfigurations() throws CoreException, CompositeLaunchConfigurationException
	{
		ArrayList<ILaunchConfiguration> configsList = new ArrayList<ILaunchConfiguration>();
		ILaunchManager curLaunchManager = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfigurationType compositeLaunchConfigType = curLaunchManager.getLaunchConfigurationType(
										CompositeLaunchConfiguration.compositeLaunchConfigurationType);

		if (compositeLaunchConfigType == null)
			throw new CompositeLaunchConfigurationException("compositeLaunchConfigurationType not found in "+
					"public ILaunchConfiguration[] AvailableLaunchConfigurationsList::getLaunchConfigurations()");
		
		ILaunchConfigurationType[] configTypes = curLaunchManager.getLaunchConfigurationTypes();
		
		for (int i=0;i<getItemCount();i++)
			for (ILaunchConfigurationType curConfigType:configTypes)
			{
				ILaunchConfiguration[] launchConfigs = curLaunchManager.getLaunchConfigurations(curConfigType);
				if (!compositeLaunchConfigType.equals(curConfigType))
					for (ILaunchConfiguration curLaunchConfig:launchConfigs)
						if (curLaunchConfig.getName().equals(getItem(i)))
								configsList.add(curLaunchConfig);
			}

		ILaunchConfiguration[] configsArray = new ILaunchConfiguration[configsList.size()];		
		configsArray = configsList.toArray(configsArray);

		return configsArray;
	}
	
	public void refresh() throws CoreException, CompositeLaunchConfigurationException
	{
		removeAll();
		ILaunchManager curLaunchManager = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfigurationType compositeLaunchConfigType = curLaunchManager.getLaunchConfigurationType(
											CompositeLaunchConfiguration.compositeLaunchConfigurationType);

		if (compositeLaunchConfigType == null)
			throw new CompositeLaunchConfigurationException("compositeLaunchConfigurationType not found in "+
		"public void AvailableLaunchConfigurationsList::refresh()");
		
		ILaunchConfigurationType[] configTypes = curLaunchManager.getLaunchConfigurationTypes();
		for (ILaunchConfigurationType curConfigType:configTypes)
		{
			ILaunchConfiguration[] launchConfigs = curLaunchManager.getLaunchConfigurations(curConfigType);
			if (!compositeLaunchConfigType.equals(curConfigType))
				for (ILaunchConfiguration curLaunchConfig:launchConfigs)
					add(curLaunchConfig.getName());
		}
	}

}
