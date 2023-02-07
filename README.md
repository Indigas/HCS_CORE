[![CircleCI](https://circleci.com/gh/Indigas/HCS_CORE/tree/master.svg?style=svg)](https://circleci.com/gh/Indigas/HCS_CORE/tree/master)

# Health care system

This is the core of Health Care System. Most of the application logic happens here.

Core module is built on Spring Framework 5.

### In this module you can find:
* Repositories build on Spring Crud repositories and connected Services
* Annotation processor, which creates generated classes to work with immutable entities
* Service manager as layer between repositories and entities
* Implemented Flyway database migration plugin to maven
* JMS entity listeners to communicate between microservices and process messages

### Testing
* DB layer - actually tested 92% of classes with 56% line covered
* Tested DB layer

###Integration testing
CircleCi is used for integration tests and simulate real world environment.
Used in CircleCi
* Docker with maven and mysql image

Issues
* Maven and Mysql image is running with root privileges. It means that it does not simulate real world environment at 100%