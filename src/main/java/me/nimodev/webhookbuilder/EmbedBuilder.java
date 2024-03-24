import lombok.Getter;

import java.awt.Color;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@Getter
public class EmbedBuilder {

    private String title;
    private String description;
    private String url;
    private int color = 16777215;
    private ArrayList<FieldsBuilder> fields;
    private String authorName;
    private String authorLogo;
    private String iconURL;
    private String footer;
    private String time;
    private String image;
    private String thumbnail;

    public EmbedBuilder(){
        fields = new ArrayList<>();
    }
    public EmbedBuilder setTitle(String title){
        this.title = title;
        return this;
    }

    /**
     * Set the description of the embed by various strings
     * @param description use "", "" to separate lines
     */
    public EmbedBuilder setDescription(String... description){
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
     * Set the description of the embed
     * @param description use %newline% or \n to separate lines
     */
    public EmbedBuilder setDescription(String description){
        this.description = description.replace("%newline%", "\n");
        return this;
    }

    /**
     * Set the url of the Title, you can use https:// or not
     * @param url if the url does not contain https:// it will be added automatically
     */
    public EmbedBuilder setUrl(String url){
        if(!url.contains("https://") && !url.contains("http://")){
            url = "https://"+url;
        }
        this.url = url;
        return this;

    }

    /**
     * Set the color of the embed
     * @param color Use java colors
     */
    public EmbedBuilder setColor(Color color){
        int rgb = (color.getRed() * 256 * 256) + (color.getGreen() * 256) + (color.getBlue());
        System.out.println(rgb);
        this.color = rgb;
        return this;
    }


    /**
     * Add fields to the embed
     * @param fieldsBuilder create a new instance of FieldsBuilder
     */
    public EmbedBuilder addFields(FieldsBuilder fieldsBuilder){
        this.fields.add(fieldsBuilder);
        return this;
    }

    /**
     * Set the author of the embed, this is not the author of the webhook message, only for the embed
     * @param name Authors name as String
     * @param logo Authors logo as String, if it does not contain https:// it will be added automatically
     */
    public EmbedBuilder setAuthor(String name, String logo){
        if(!logo.contains("https://") && !logo.contains("http://")){
            logo = "https://"+logo;
        }
        this.authorName = name;
        this.authorLogo = logo;
        return this;
    }

    /**
     * Set the footer of the embed with an icon
     * @param footer Footer title as String
     * @param time Footer time as ZonedDateTime, you can use ZonedDateTime.now() to make the Footer Time to be te actual time
     * @param iconURL Footer icon as String, if it does not contain https:// it will be added automatically
     */
    public EmbedBuilder setFooter(String footer, ZonedDateTime time, String iconURL){
        if(!iconURL.contains("https://") && !iconURL.contains("http://")){
            iconURL = "https://"+iconURL;
        }
        this.iconURL = iconURL;
        this.footer = footer;
        this.time = time.toOffsetDateTime().toString();
        return this;
    }

    /**
     * Set the footer of the embed without an icon
     * @param footer Footer title as String
     * @param time Footer time as ZonedDateTime, you can use ZonedDateTime.now() to make the Footer Time to be te actual time
     */
    public EmbedBuilder setFooter(String footer, ZonedDateTime time){
        this.footer = footer;
        this.time = time.toOffsetDateTime().toString();
        return this;
    }

    /**
     * add an image to the embed
     * @param url Image url as String, if it does not contain https:// it will be added automatically
     */
    public EmbedBuilder addImage(String url){
        if(!url.contains("https://") && !url.contains("http://")){
            url = "https://"+url;
        }
        image = url;
        return this;
    }
    /**
     * set the thumbnail of the embed
     * @param thumbnail Image url as String, if it does not contain https:// it will be added automatically
     */
    public EmbedBuilder setThumbnail(String thumbnail){
        if(!thumbnail.contains("https://") && !thumbnail.contains("http://")){
            thumbnail = "https://"+thumbnail;
        }
        this.thumbnail = thumbnail;
        return this;
    }
}
