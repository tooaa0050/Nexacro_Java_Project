package example.nexacro.uiadapter.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EtcProperty {
	private Logger logger = LoggerFactory.getLogger(EtcProperty.class);
    
    //nexacro:
	//	column-case: #camel(whoAreYou), kebab(who-are-you), snake(who_are_you),upper(WHO_ARE_YOU)
    private String columnCase;

    public EtcProperty(String columnCase) {
        this.columnCase = columnCase;
    }

    public String getColumnCase() {
        return columnCase;
    }    
    public void setColumnCase(String columnCase) {
        this.columnCase = columnCase;
    }    
    public EtcProperty() {

    }

	String returnString = "";
    public String toString() {
    	returnString = getColumnCase();
    	logger.debug("[★ EtcProperty.columnCase="+returnString+"]");
    	return returnString;
    }
}
