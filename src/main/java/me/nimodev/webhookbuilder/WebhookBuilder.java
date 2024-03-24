package me.nimodev.webhookbuilder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.json.JSONArray;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class WebhookBuilder {

    private String url;
    private String content;
    private List<EmbedBuilder> embeds;
    private String username;
    private String avatarURL;

    public WebhookBuilder(String url) {
        embeds = new ArrayList<>();
        this.url = url;
    }
    /**
     * set the content of the webhook
     * @param content content as String
     */
    public WebhookBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * add embeds to the webhook
     * @param embedBuilder create a new instance of EmbedBuilder
     */
    public WebhookBuilder addEmbed(EmbedBuilder embedBuilder){
        this.embeds.add(embedBuilder);
        return this;
    }
    /**
     * set the username of the webhook
     * @param username username as String
     */
    public WebhookBuilder setUsername(String username){
        this.username = username;
        return this;
    }
    /**
     * set the avatar to the webhook
     * @param url Image url as String, if it does not contain https:// it will be added automatically
     */
    public WebhookBuilder setAvatar(String url){
        if(!url.contains("https://") && !url.contains("http://")){
            url = "https://"+url;
        }
        this.avatarURL = url;
        return this;
    }

    public void send(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", content);

        JSONArray jsonArray = new JSONArray();
        if(embeds.isEmpty()){
            jsonObject.put("embeds", JSONObject.NULL);
        }else {
            for(EmbedBuilder embedBuilder : embeds){
                LinkedHashMap<String, Object> embed = new LinkedHashMap<>();
                embed.put("title", embedBuilder.getTitle());
                embed.put("description", embedBuilder.getDescription());
                embed.put("url", embedBuilder.getUrl());
                embed.put("color", embedBuilder.getColor());

                if(!embedBuilder.getFields().isEmpty()){
                    JSONArray fields = new JSONArray();

                    for(FieldsBuilder fieldsBuilder : embedBuilder.getFields()){
                        LinkedHashMap<String, Object> field = new LinkedHashMap<>();
                        field.put("name", fieldsBuilder.getTitle());
                        field.put("value", fieldsBuilder.getDescription());
                        field.put("inline", fieldsBuilder.isInLine());
                        fields.put(field);
                    }
                    embed.put("fields",fields);
                }
                LinkedHashMap<String, Object> author = new LinkedHashMap<>();
                author.put("name", embedBuilder.getAuthorName());
                author.put("icon_url", embedBuilder.getAuthorLogo());
                embed.put("author",author);

                LinkedHashMap<String, Object> footer = new LinkedHashMap<>();
                footer.put("text", embedBuilder.getFooter());
                footer.put("icon_url", embedBuilder.getIconURL());
                if(embedBuilder.getFooter() != null || embedBuilder.getIconURL() != null){
                    embed.put("footer", footer);
                }
                if(embedBuilder.getTime() != null){
                    embed.put("timestamp", embedBuilder.getTime());
                }
                LinkedHashMap<String, Object> image = new LinkedHashMap<>();
                image.put("url", embedBuilder.getImage());
                if(embedBuilder.getImage() != null){
                    embed.put("image", image);
                }

                LinkedHashMap<String, Object> thumb = new LinkedHashMap<>();
                thumb.put("url", embedBuilder.getThumbnail());
                if(embedBuilder.getThumbnail() != null){
                    embed.put("thumbnail", thumb);
                }
                jsonArray.put(embed);
            }
            jsonObject.put("embeds", jsonArray);
        }

        jsonObject.put("username", username);
        jsonObject.put("avatar_url", avatarURL);
        jsonObject.put("attachments", new JSONArray());

        System.out.println(jsonObject.toString());
        try{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        StringEntity params = new StringEntity(jsonObject.toString());
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.setEntity(params);


        httpClient.execute(httpPost);

        httpClient.close();
    } catch (IOException e) {
        e.printStackTrace();
    }

    }

}




