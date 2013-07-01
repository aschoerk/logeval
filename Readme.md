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
* 

# Overall GUI
As shown they different design and extraction steps can be defined in separate tabs. Sometimes information is 
given about the results of the definition. This information is output in a protocol-area under the tab-area.
This protocol-area also can be edited and most importantly cleared.
There all also some tool-tips defined which should help to explain the various functions of the GUI-elements.

## How to define a specifig configuration
All entries which are done are saved into a configuration-file. Using this tab you can 

![the config-tab](https://github.com/aschoerk/logeval/raw/master/doc/images/logeval_config.png)

* decide where this files can be found (**ConfigDir**) you can enter the directory directly or you can use the >-Button to navigate there using a dialog.
* decide what the name of the current file is (**ConfigName**)
* select one of your previously defined files that can be found in **ConfigDir** and load it.
* save your changes of your current configuration.


## How to define the files to be extracted

![the files-tab](https://github.com/aschoerk/logeval/raw/master/doc/images/logeval_logfiles.png)

* **Directory** the directory where to search for the Logfiles
* **Node Recognizer** this can be used to define a special field named node to separate between different cluster-nodes
* **Log Recognizer** a regular expression. If a file found under Directory fits to this, it will get extracted

The files can be gzipped or text. If gzipped they naturally will be expanded before extraction.
Using the Button **showFiles** will print out in the protocol-area the paths of all files that will be extracted.


## How to define the extraction done by a configuration
![the files-tab](https://github.com/aschoerk/logeval/raw/master/doc/images/logeval_extraction.png)
Using one configuration single lines of the logfiles can be extracted into single records of data. These 
records are composed of fields having names, types and values. This tab allows to define these three information parts.
Each line in the area defines an extraction rule. A extraction rule is composed of 

* **Name** which will be the name of the field (attribute) in the csv-file or sql-table
* **Pattern** which will allow to recognize the value of the field in the logfile-line. This is a regular expression containing groups. 
These groups can be used in the subsequent field to define what part of the regular expression shall be used.  
* **Rep..** defines hwo the value of the field is created. each group index is to be exclosed into **<** and **>** (not regex standard, I know)
* **Type** allows to define how the type should be defined in the sql-database. It can be selected as one of 
varchar, number, timestamp, date, time
* **Length** will be used together with varchar to define the attribute length in the sql-table
* **Line recognized by** defines a regular expression defining whether data of a specific line in the logfile will be extracted by this rule at all. 
If no rule fits because not once the line is recognized by the corresponding regular expression, it will not produce an extraction result.

At the bottom of the tab sample-lines can be used together with sample extraction regexes to build up the next rules.
Input the sample-line as **Line:** the sample Regex as **Regex:** and set the result in the **Replace:** field. Pressing **Test Regex** 
will produce the result in the protocol-area. If this result is ok, a new extraction Rule can be created by pressing the button **newExtractionRule**.
The **Times** - Button can be used to find out how costly the defined rules are. The results will be printed beside the Length-Field of the extraction rules.

## Extraction to a sql-database
![the files-tab](https://github.com/aschoerk/logeval/raw/master/doc/images/logeval_extraction_to_db.png)

Using *logval* it is possible to extract data directly and fast into a sql-database. Provided a suitable jdbc-driver is put into the lib-directory of logval, 
here the creation of a jdbc-connection can be defined. **DbUrl**, **Jdbc-Class**, **Db User** and **Db Password** 
must be entered accordingly. The connection can be tested using the button **Test Connection**.

The further information is used to defined how the data is input into the database. 
* **Allow Create** means, if the table is not found it may be created. 
* **Truncate if Exists** means that all data may be removed before inserting new data
* **Force Create** leads to an unconditionally executed *Create Table...* - statement.

If only **Allow Create** is checked, it is expected, that the table exists and data will be added to it.

* **Tablename** allows it to enter the name of the table to be created or added to.
* **Transaction Size** allows it to define the maximum number of records to be inserted in one transaction
* **Batch Size** allows it to define how many records are sent to the database-server in on Batch.
* **Max Varchar Size** allows it to define how the varchars in the *Create Table ...* - statements are defined maximum.

Pressing **Extract to Db** leads to the extraction of the data from the files previously defined in tab *LogFiles* 
using the rules defined in *Extraction* to the table entered here in *Tablename* which might be emptied or 
newly created or added to.

