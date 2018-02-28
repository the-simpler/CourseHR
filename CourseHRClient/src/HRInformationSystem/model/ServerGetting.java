package HRInformationSystem.model;

import java.util.HashMap;
import java.util.Map;

public class ServerGetting {
    private boolean error;
    private String message;
    private String schema;
    private Object model;
   // private Map<String, Object> attributes;

    public ServerGetting(){
     //model = new Object();
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
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

    // public void setAttribute(String key, Object value){
       // attributes.put(key, value);
  //  }

   // public Object getParameter(String key){
    //    return attributes.get(key);
   // }





}
