# CuteBedwars
**[English](https://github.com/gdrfgdrf/CuteBedwars/blob/dev/README.md)** | [简体中文](https://github.com/gdrfgdrf/CuteBedwars/blob/dev/README_chinese.md) 

A Minecraft Bed Wars plugin,
based on version 1.12.2
and using [multi-module-mediator](https://github.com/gdrfgdrf/multi-module-mediator) for modularity.  
⚠ Still in development, not usable ⚠

For specific information on the math and geometry modules, please visit the [Math README](https://github.com/gdrfgdrf/CuteBedwars/blob/dev/math/math-readme.md).

## Features

- **Modular Design**: Decoupled architecture for maintainability.
- **Area System**: Supports multiple games within a single area, sharing common properties.
- **Team System**: Games can include multiple teams for competition.
- **Item Generators**: Generate items, and auto-upgrade.
- **Template System**: Allows duplication of game data for quick setup and sharing templates.
- **Database Abstraction**: Custom database implementations supported, with default SQLite and MySQL.

## Data Info

- **Areas**: Host multiple games with shared settings like a common game world.
- **Games**: Include teams, item generators, waiting rooms and etc..
- **Teams**: Include villagers, item generators etc..

## Development

Clone the project and run `dependency/install-to-maven.bat` or execute:
```shell
mvn install:install-file -Dfile=multi-module-mediator-0.1.0.jar -DgroupId=io.github.gdrfgdrf.multimodulemediator -DartifactId=multi-module-mediator -Dversion=0.1.0 -Dpackaging=jar
```
May need to use IntelliJ IDEA's Build → Rebuild Project once to enable normal Maven packaging.

## Contribute

Welcome contributions, including code, bug reports, and documentation improvements.

Module List
-----------------

| Module Name                              | Function                                                                                                                                                                                                                                  |
|------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| cute-bedwars-core                        | Plugin entry, used to start various services                                                                                                                                                                                              |  
| cute-bedwars-beans                       | All data classes                                                                                                                                                                                                                          |  
| cute-bedwars-database                    | Abstraction of the database                                                                                                                                                                                                               |
| cute-bedwars-database-default-impl       | Default SQLite database implementation                                                                                                                                                                                                    |
| cute-bedwars-database-default-impl-beans | All data classes for the default SQLite database                                                                                                                                                                                          |
| cute-bedwars-commons                     | Common module                                                                                                                                                                                                                             |
| cute-bedwars-locale                      | Localization, providing different language information based on settings                                                                                                                                                                  |
| cute-bedwars-languages                   | All language strings                                                                                                                                                                                                                      |
| cute-bedwars-commands                    | All command implementations                                                                                                                                                                                                               |
| cute-bedwars-requests                    | Request module, performs operations within a specified time, otherwise times out. For example, some dangerous operations require secondary confirmation, and if they time out after a certain number of seconds, this module must be used |
| cute-bedwars-game-management             | Game management                                                                                                                                                                                                                           |
| cute-bedwars-notification                | Player notification module                                                                                                                                                                                                                |
| cute-bedwars-tasks                       | Task module, synchronous and asynchronous tasks                                                                                                                                                                                           |
| cute-bedwars-chat-page                   | Chat page module, can paginate multiple lines of text, supports caching                                                                                                                                                                   |
| cute-bedwars-finder                      | Query module, used to query various data, if not found or conditions are not met, it will output a prompt information                                                                                                                     |
| cute-bedwars-information                 | Translates data into a viewable version                                                                                                                                                                                                   |
| cute-bedwars-editing                     | Editing module, edits data using Git's design                                                                                                                                                                                             |
| cute-bedwars-storage                     | Storage module, actually stores data, this storage does not use a database, but directly stores to a file                                                                                                                                 |
| cute-bedwars-items                       | Item module, wraps game item operations                                                                                                                                                                                                   |
| cute-bedwars-events                      | Event module, stores some event classes and event listeners                                                                                                                                                                               |
| cute-bedwars-protobuf                    | All Protobuf classes                                                                                                                                                                                                                      |
| cute-bedwars-selection                   | Selection module, displays through particle effects, and adds auxiliary lines through calculation to check each position                                                                                                                  |
| cute-bedwars-particles                   | Particle effects module, abstracts particle effects to a certain extent                                                                                                                                                                   |
| cute-bedwars-frequency-tasks             | Frequency tasks, perform a task at a certain frequency                                                                                                                                                                                    |
| cute-bedwars-math                        | Math module, performs various mathematical calculations                                                                                                                                                                                   |
| cute-bedwars-geometry                    | Geometry module, under the math module                                                                                                                                                                                                    |
| cute-bedwars-enums                       | All enumeration classes                                                                                                                                                                                                                   |
| cute-bedwars-utils                       | Utility module                                                                                                                                                                                                                            |
| cute-bedwars-abstracts                   | Decoupling module, decouples all the above modules, contains a large number of interfaces, and communicates through mediators                                                                                                             |
| cute-bedwars-packer                      | Packaging module, packages all the above modules into a Jar package                                                                                                                                                                       |