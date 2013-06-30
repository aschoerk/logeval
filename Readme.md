# logeval

Small Swing Tool to design and do logfile evaluations

## What can be done with logeval 

Using logeval a configuration-File can be created that can be used to repeated extract data from textual logfiles and put the extracted data into a sql-database or export it into a csv-file.
The program is ordered in multiple tabs:

![the tabs](https://github.com/aschoerk/logeval/raw/master/doc/images/logeval_tabs.png)

* **Config** allows to manage the different config files
* **LogFiles** allows to define the directory where the logfiles are which are to be evaluated
* **Extraction** in this Tab the extraction can be defined.
* **Extraction to Db** this Tab allows to control the extraction into sql-database-tables
* **Extraction to File** this Tab allows to define and control the extraction to a csv-file

## How to define a specifig configuration
All entries which are done are saved into a configuration-file. Using this tab you can 

* decide where this files can be found (**ConfigDir**) you can enter the directory directly or you can use the >-Button to navigate there using a dialog.
* decide what the name of the current file is (**ConfigName**)
* select one of your previously defined files that can be found in **ConfigDir** and load it.
* save your changes of your current configuration.

![the config-tabs](https://github.com/aschoerk/logeval/raw/master/doc/images/logeval_config.png)

## How to define the extraction done by a configuration
