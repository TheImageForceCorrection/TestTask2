package compositelaunchconfiguration.launcher;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.debug.core.model.IProcess;
import compositelaunchconfiguration.CompositeLaunchConfiguration;
import compositelaunchconfiguration.SelectedLaunchConfigurations;

public class CompositeLaunchConfigurationDelegate implements ILaunchConfigurationDelegate
{
	
	public CompositeLaunchConfigurationDelegate()
	{
		if (CompositeLaunchConfiguration.selectedLaunchConfigs == null)
			CompositeLaunchConfiguration.selectedLaunchConfigs = new SelectedLaunchConfigurations();
	}
	
	@Override
	public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor)
			throws CoreException
	{
		for (ILaunchConfiguration curConfiguration:CompositeLaunchConfiguration.
											selectedLaunchConfigs.getLaunchConfigurations())
		{
			ILaunch curLaunch = curConfiguration.launch(mode, monitor);
			IDebugTarget[] curTargets = curLaunch.getDebugTargets();
			IProcess[] curProcesses = curLaunch.getProcesses();
			
			for (IDebugTarget curTarget:curTargets)
				launch.addDebugTarget(curTarget);
			
			for (IProcess curProcess:curProcesses)
				launch.addProcess(curProcess);
		}
	}

}
