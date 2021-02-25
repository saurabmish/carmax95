# carmaxx

Counterfeit used-car retailer.

## Execution

To keep dedicated directory for Maven cache, a `settings.xml` file is [defined][1] as per [XDG base directory][2] specification implemented.

`./mvnw -gs "$XDG_CONFIG_HOME"/maven/settings.xml spring-boot:run`

## Endpoint Testing

At this stage, API requests and responses are stored in-memory. So `POST` is done before `GET`. 

`./src/test/java/com/carmaxx/rental/endpoints.sh`



[1]: https://maven.apache.org/settings.html#Simple_Values
[2]: https://wiki.archlinux.org/index.php/XDG_Base_Directory
