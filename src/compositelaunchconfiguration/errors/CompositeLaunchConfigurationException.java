package compositelaunchconfiguration.errors;

public class CompositeLaunchConfigurationException extends Exception
{
	
	private static final long serialVersionUID = 1L;
	private String errMsg;
	
	public CompositeLaunchConfigurationException(String errMsg)
	{
		this.errMsg = errMsg;
	}
	
	public String what()
	{
		return errMsg;	
	}
	
}
