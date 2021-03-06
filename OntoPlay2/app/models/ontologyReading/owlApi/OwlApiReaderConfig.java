package models.ontologyReading.owlApi;

import java.util.HashMap;

public class OwlApiReaderConfig {
	private boolean ignorePropsWithNoDomain;
	private String localFolderPath;
	
	public OwlApiReaderConfig useLocalFolder(String localFolderPath) {
		this.localFolderPath = localFolderPath;
		return this;
	}
	
	public OwlApiReaderConfig ignorePropertiesWithUnspecifiedDomain(){
		ignorePropsWithNoDomain = true;
		return this;
	}

	public boolean isIgnorePropsWithNoDomain(){
		return ignorePropsWithNoDomain;
	}

	public String getLocalFolderPath() {
		return localFolderPath;
	}
}
