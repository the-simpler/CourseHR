package HRInformationSystem.model;

import java.util.HashMap;
import java.util.Map;

public class ServerSending {
    private String command;
    private String schema;
    private Object model;
    //private Map<String, Object> attributes;

    public ServerSending(){
    //    attributes = new HashMap<>();
    }

    public String getCommand(){
        return command;
    }

    public void setCommand(String command){
        this.command = command;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }

    //public void setAttribute(String key, Object value){
   //     attributes.put(key, value);
    //}

    //public Object getParameter(String key){
    //    return attributes.get(key);
    //}
}
