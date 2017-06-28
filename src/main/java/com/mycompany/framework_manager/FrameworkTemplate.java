package com.mycompany.framework_manager;

/**
 * An object consisting of all necessary information about a framework to be put in the database.
 *
 * @author Martin
 */
public class FrameworkTemplate {

    private String id;
    private String name;
    private String type;
    private String language;
    private String intrusive;
    private String openSource;

    public FrameworkTemplate(String id, String name, String type, String language, String intrusive, String openSource) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.language = language;
        this.intrusive = intrusive;
        this.openSource = openSource;
    }
    
    public String getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public String getType(){
        return type;
    }
    
    public String getLanguage(){
        return language;
    }
    
    public String getIntrusive(){
        return intrusive;
    }
    
    public String getOpenSource(){
        return openSource;
    }
}
