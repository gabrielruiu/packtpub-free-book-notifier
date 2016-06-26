**packtpub-free-book-notifier** is a Spring Boot application that periodically (by default once per day) checks 
the [Packtpub](https://www.packtpub.com/) website for the free title they offer each day, and afterwards
sends an email with the scraped title and description of the book.

## A. Configuration

The application is configurable by injecting certain property values and needs to now **where** to send the email notification, and **how**.
Check out the [Spring Boot documentation](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-external-config) on this topic
on how to find the best way to inject the values that fits for you.

I will be using command-line arguments and environment variables to demonstrate.

### Recipient and sender information
Specifying the **mail.recipient** and **mail.from** properties will tell the app to whom to send the email notification.

 Example:
 ```
 java -jar packtpub-free-book-notifier.jar --mail.recipient=your-email@email.com --mail.from="Packtpub Notifier <your-email@email.com>" ... other necessary properties
 ```

### Email sending
There are 2 ways of sending the emails:

1. through an email server
2. configuring the REST client to call the [Mailgun API](https://www.mailgun.com/)

Each of these two methods can be activated by setting the **spring.profiles.active** property.

#### 1. Email server
In order to tell the application to use an email server, **smtp** must be set as the spring active profile.

In this case, Spring will prepare an instance of [JavaMailSender](http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/mail/javamail/JavaMailSender.html)
 that will relay the email through an email server.
 Access to the email server is configurable through the properties defined in the [MailProperties](https://github.com/spring-projects/spring-boot/blob/v1.3.5.RELEASE/spring-boot-autoconfigure/src/main/java/org/springframework/boot/autoconfigure/mail/MailProperties.java) configuration class.
 
 Example:
 ```
 java -jar packtpub-free-book-notifier.jar --spring.profiles.active=smtp --mail.recipient=your-email@email.com --mail.from="Packtpub Notifier <your-email@email.com>" --spring.mail.host=my-email-host.com --spring.mail.port=465 --spring.mail.username=my-username --spring.mail.password=my-password
 ```

#### 2. Mailgun API client
In order to tell the application to invoke the Mailgun API, **mailgun** must be set as the spring active profile.

In this case, Spring will prepare a REST client that will invoke the Mailgun API.
In order to use the Mailgun API, you need to have an account created, and a [domain configured to be able to send emails](https://documentation.mailgun.com/quickstart-sending.html#add-sending-tracking-dns-records).

After you've successfully setup your account and domain, you will need **the base url for the API**, **the API key**, **the name of your domain** to setup the REST client.

Example
 ```
java -jar packtpub-free-book-notifier.jar --spring.profiles.active=mailgun --mail.recipient=your-email@email.com --mail.from="Packtpub Notifier <your-email@email.com>" --mailgun.url=https://api.mailgun.net/v3/ --mailgun.key=your-key --mailgun.domain=your-domain
 ```

**NOTE**: remember to add a '/' (slash) after the mailgun.url property.


## B. Using Docker image
Configuring the Docker container that is running the app is similar to the process described above, except environment variables are used instead of command-line arguments.

After pulling the image
```
docker pull gabrielruiu/packtpub-free-book-notifier
```
you can run the container using the same set of properties, but injected through environment variables.

Example:

1. using SMTP server
```
docker run --rm -e SPRING_PROFILES_ACTIVE=smtp -e MAIL_RECIPIENT=your-email@email.com -e MAIL_FROM="Packtpub Notifier <your-email@email.com>" -e SPRING_MAIL_HOST=my-email-host.com -e SPRING_MAIL_PORT=465 -e SPRING_MAIL_USERNAME=my-username -e SPRING_MAIL_PASSWORD=my-password --name packtpub gabrielruiu/packtpub-free-book-notifier
```

2. using the Mailgun client
```
docker run --rm -e SPRING_PROFILES_ACTIVE=mailgun -e MAIL_RECIPIENT=your-email@email.com -e MAIL_FROM="Packtpub Notifier <your-email@email.com>" -e MAILGUN_URL=https://api.mailgun.net/v3/ -e MAILGUN_KEY=your-key -e MAILGUN_DOMAIN=your-domain --name packtpub gabrielruiu/packtpub-free-book-notifier
```

