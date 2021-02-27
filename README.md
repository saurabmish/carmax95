[![Build Status](https://travis-ci.com/saurabmish/carmax95.svg?branch=master)](https://travis-ci.com/saurabmish/carmax95)

# Carmax95

Counterfeit used-car retailer.


## Setup

+ Install OpenJDK, maven, cURL, and jq (command-line JSON processor)

  ```
  brew install openjdk
  brew install maven
  brew install curl
  brew install jq
  ```

+ For the system Java wrappers to find this JDK, symlink it with Java in `Library/`

  `sudo ln -sfn /usr/local/opt/openjdk/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk.jdk`

+ Set `JAVA_HOME`

  `export JAVA_HOME="/Library/Java/JavaVirtualMachines/openjdk.jdk/Contents/Home"`

+ In `$XDG_CONFIG_HOME/maven/`, create a settings.xml [file][1] with the following:

  ```
  <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd">
    <localRepository>${env.XDG_CACHE_HOME}/maven/repository</localRepository>
    <interactiveMode>true</interactiveMode>
    <offline>false</offline>
    <pluginGroups/>
    <servers/>
    <mirrors/>
    <proxies/>
    <profiles/>
    <activeProfiles/>
  </settings>
  ```

+ To keep `$HOME` directory clean using [XDG base directory specifications][2], set the below in shell configuration:

  ```
  alias mvn="mvn -gs $XDG_CONFIG_HOME/maven/settings.xml"
  export GRADLE_USER_HOME="$XDG_DATA_HOME/gradle"
  ```


## Initiation

+ Create a Spring application using [Spring Initializr][3]

+ Technical Specifications:

  + Project: Maven

  + Snapshot: 2.4.0

  + Project Metadata:

    1. Packaging: JAR

    2. Java Version: 15

    3. Dependencies: Spring Web


## Execution

+ Verify Maven build environment

  `mvn clean verify`

+ Install project dependencies

  `mvn clean install`

+ Run tests

  `mvn test`

+ Start Spring Boot application

  `mvn spring-boot:run`

+ In a new tab execute: 
 
  (**NOTE**: At this stage, API requests and responses are stored in-memory)

  `./src/test/java/com/carmaxx/rental/endpoints.sh`



[1]: https://maven.apache.org/settings.html#Simple_Values
[2]: https://wiki.archlinux.org/index.php/XDG_Base_Directory
[3]: https://start.spring.io/
