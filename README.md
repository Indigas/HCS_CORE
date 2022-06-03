[![CircleCI](https://circleci.com/gh/Indigas/HCS_CORE/tree/master.svg?style=svg)](https://circleci.com/gh/Indigas/HCS_CORE/tree/master)

# Health care system

This is the core of Health care system. Most of the logic of the application is happening here.

Core module is built with Spring Framework 5.

###In this module you can find:
* Repositories build on Spring Crud repositories
* Annotation processor, which create generated classes to work with immutable entities
* Own Entity manager, which use Entity container and Service container - everything written by myself
* Implemented Flyway database migration plugin to maven
* Workers, which are used together with services/repositories to communicate with DB concurrently

###Testing
* DB layer - actually tested 92% of classes with 56% line covered
* Tested DB layer

###Integration testing
CircleCi is used for integration tests and simulate real world environment.
Used in CircleCi
* Docker with maven and mysql image
Issues
* Maven and Mysql image is running with root privileges. It means that it does not simulate real world environment at 100%