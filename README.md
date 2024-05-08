<h3 align="center">DiscordWebhookBuilder</h3>

<h3 align="center"> A Simple Discord Webhook builder for java that allows you to add everything such as Embeds, Fields, etc </h3>


## 📖 How to add it to your project
You can easily add the Builder to your project by importing, here is how you can do it

**Maven**:
```
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
```
	<dependency>
	    <groupId>com.github.NimxDev</groupId>
	    <artifactId>DiscordWebhookBuilder</artifactId>
	    <version>f97af014a4</version>
	</dependency>
```

**Gradle**:
```
dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```
```
dependencies {
	        implementation 'com.github.NimxDev:DiscordWebhookBuilder:f97af014a4'
	}
```

If you dont want to import it you can copy the files into your project and add the dependencies below, but is not recomended.

## 📕 Dependencies
If you want to install manually the Builder you must have to add this dependencies to your project

**Maven**:
```
<dependencies>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.30</version>
        </dependency>
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
            <version>4.5.13</version>
        </dependency>
        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20240303</version>
        </dependency>
</dependencies>
```
**Gradle**:
```
dependencies {
    implementation 'org.projectlombok:lombok:1.18.30'
    implementation 'org.apache.httpcomponents:httpclient:4.5.13'
    implementation 'org.json:json:20240303'
}
```

## ❓ How to use

You can easly send a webhook with the next code:

```
new WebhookBuilder("WEBHOOK_URL")
                .setContent("This is a test!")
                .setUsername("WebhookBuilder Test")
                .send();
```

You can also set an avatar to the Webhook with the next code:
```
 .setAvatar("AVATAR_URL")
```

And if you want to add an embed to the Webhook you can use the **EmbedBuilder** like this:

```
new WebhookBuilder("WEBHOOK_URL")
                .setContent("This is a test!")
                .setUsername("WebhookBuilder Test")
                .addEmbed(new EmbedBuilder()
                        .setAuthor("ElNimo", "LOGO_URL")
                        .setTitle("Embed Test")
                        .setColor(Color.GREEN)
                        .setDescription("This is an embed test!")
                        .setFooter("Footer Test", ZonedDateTime.now())
                .send();
```

And you can also add fields to the embed with the **FieldsBuilder**

```
new WebhookBuilder("WEBHOOK_URL")
                .setContent("This is a test!")
                .setUsername("WebhookBuilder Test")
                .addEmbed(new EmbedBuilder()
                        .setAuthor("ElNimo", "LOGO_URL")
                        .setTitle("Embed Test")
                        .setColor(Color.GREEN)
                        .setDescription("This is an embed test!")
                        .setFooter("Footer Test", ZonedDateTime.now())
                        .addFields(new FieldsBuilder().setTitle("Field Test").setDescription("This is a Field Test").setInLine(true))
                .send();
```

## 💻 More Information

If you have any question you can contact me trought discord: @nimovt
Or you can also see the documentaion inside the classes.

Also if you have any suggestion or problem feel free to contact me.
