package me.nimodev.webhookbuilder;
import lombok.Getter;

@Getter
public class FieldsBuilder {

    private String title;
    private String description;
    private boolean inLine = false;

    /**
     * Set the Title of the field
     *
     */
    public FieldsBuilder setTitle(String title){
        this.title = title;
        return this;
    }

    /**
     * Set the description of the field
     * @param description use %newline% or \n to separate the lines
     */
    public FieldsBuilder setDescription(String description){
        this.description = description.replace("%newline%", "\n");
        return this;
    }

    /**
     *   Set the description of the field
     *  @param description use "", "" to separate the lines
     **/
    public FieldsBuilder setDescription(String... description){
        StringBuilder sb = new StringBuilder();
        for(String desc : description){
            sb.append(desc);
            sb.append("\n");
        }
        sb = new StringBuilder(sb.reverse().toString().replaceFirst("\n", ""));
        this.description = sb.reverse().toString();
        return this;
    }

    /**
    *   Set the field to inline or not
    *   false for default
    **/
    public FieldsBuilder setInLine(boolean inLine){
        this.inLine = inLine;
        return this;
    }
}
