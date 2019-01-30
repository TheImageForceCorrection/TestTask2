package compositelaunchconfiguration.ui;

import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.debug.ui.ILaunchConfigurationTabGroup;
import compositelaunchconfiguration.ui.CompositeLaunchConfigurationTab;


public class CompositeLaunchConfigurationTabGroup implements ILaunchConfigurationTabGroup
{
	
	private ILaunchConfigurationTab[] tabs = null;

	@Override
	public void createTabs(ILaunchConfigurationDialog dialog, String mode)
	{
		tabs = new ILaunchConfigurationTab[2];
		tabs[0] = new CompositeLaunchConfigurationTab("Launches");
		tabs[1] = new CommonTab();

	}

	@Override
	public ILaunchConfigurationTab[] getTabs()
	{
		return tabs;
	}

	@Override
	public void dispose()
	{
		
	}

	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration)
	{
		for(ILaunchConfigurationTab curTab:tabs)
			curTab.setDefaults(configuration);
	}

	@Override
	public void initializeFrom(ILaunchConfiguration configuration)
	{
		for(ILaunchConfigurationTab curTab:tabs)
			curTab.initializeFrom(configuration);
	}

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration)
	{
		for(ILaunchConfigurationTab curTab:tabs)
			curTab.performApply(configuration);
	}

	@Override
	public void launched(ILaunch launch)
	{
		
	}

}
