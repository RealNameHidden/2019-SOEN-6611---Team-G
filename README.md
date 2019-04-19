# Software Measurement Project - Team G

## Team members
- Sucheta Vijayakumar Sudhakumari 
- Dheeraj Ashok Shobha
- Sarthak Arora 
- Sohrab Singh Chawla
- Shaina Ali

## Project directory structure
1.	`/Correlation/` Contains the correlation data for all the metrics
	`/Correlation/ Scatter Plots/` Contains the scatter plots with R values.

2.	`/DataCollection/` Contains the raw data collected for all the metrics

3.	`/Scripts/` Contains all the scripts that were used for the project

 
 
# Usage instructions for the scripts

## I Design Details about the script developed to Mine JIRA.

 Github location : `/Scripts/Purpose-MineIssueTracker_JIRA/`
 
 Language : JAVA
 Packages Used :
 - Selenium (Version : 3.141.59).
 - testng(Version : 6.8).
- Apache POI (Version : 4.0.1)
    
Include all the remaining dependencies as present in the pom.xml file.
Infrastructure required to replicate :
1. chromedriver.exe should be located in the root folder.
2. Google Chrome should be installed.
3. Java should be installed.
4. Maven should be installed.
5. A folder names DataCollection , with the  should be present in the root folder. 

#### Directions to replicate 

>Step 1: Open testing.xml and corresponding to the <include> tag within <methods> tag , give any of the following methods.

- ProjectList – Fetch total defect count for all of the projects.
- DefectwithoutVersion  - Fetch the number of defects with version as `:No` Version for all of the projects.
- UnspecifiedVersion -  Fetch the number of defects with version as `:Unspecified`  for all of the projects. 
			The excel file will have only those projects for which such defects are present.
- IdentifyProjects – Fetch the release wise defect count for released versions of the selected projects.

	note:Each method has been declared as individual Tests and should be run individually.

>Step 2:	Open cmd and navigate to the root folder where pom.xml is present. Run the command : `mvn test`

  * Class Description :
  1.	DefectDensity : Class file with the mining functions.
  
  2.	ProjectLoadException, VersionLoadException, DefectCountLoad : Custom Exception Classes.
  
  3.	DriverFactory : Singleton Class to initialise chromedriver .
  

## II Design Details about the script developed to Combine CSV Files:

Github location: `/Scripts/Purpose-CombineCSVFiles/`
Language : Python(3.7.1).
Packages Used :

	1.Pandas (0.24.2).

Infrastructure required to replicate :

	1.Python should be installed.

### Directions to replicate :

> Step 1: Set variable ‘root’ to the path of directory where the files to be combined are placed.

> Step 2: Column names should be same in all the files to be combined.

## III Instructions to run churn script

Github location: `/Scripts/Purpose-ChurnCalculation/`
Type of file: bash script

### Directions to replicate
> Step 1: Clone the project which you want to find churn for
	`$: git clone`
	
> Step 2: Cd into the master folder of the project

> Step 3: Run the below commands by changing the location according to the location of the bash scripts on your computer
`source "C:\Users\14387\Downloads\churn-master\git-commit-churn.sh" `
` source "C:\Users\14387\churn-master\git-simple-prompt.sh"`

> Step 4: Run
  `PROMPT_COMMAND=git_simple_prompt`
  
> Step 5: Run he below command to generate the churn data based on the time period.
 `git_line_churn --after=2016-02-05 --before=2017-12-09`
 
> Step 6: To output the data to a csv file add 
 `git_line_churn --after=2016-02-05 --before=2017-12-09 > filename.csv`

Note: 
- the original readme.md can be found in the directory
- Github link from where the code was borrowed: https://github.com/dmillett/churn

## IV Instructions to run the script to generate spearman correlation values and scatter plot graphs

Github location:
`/Scripts/Purpose-SpearmanCorrelation/`
Language: Python
Packages needed:
- `scipy.stats`
- `matplotlib`

### Directions to run the script
 > Step 1: Put the **excel** file location that you need to read in the line mentioned in the comment
 
 > Step 2: Update the variable with the respective column names for x and y that must be read from the excel file
 
 > Step 3: Run the program and enter the sheet name in the excel file. The scatter plot will be generated based on the sheet name.
 
 > Step 4: the correlation value will be displayed in the console and also a scatter plot will be generated
 
## V Instruction for generating Jacoco reports (Coverage and complexity)

- Changes have to be made in the pom.xml file of the respective project
- The below configuration must be added under <plugins> section of the pom.xml
```xml
<plugin>

<groupId>org.jacoco</groupId>

<artifactId>jacoco-maven-plugin</artifactId>

<version>0.8.3</version>


<executions>


<execution>


<goals>

<goal>prepare-agent</goal>

</goals>

</execution>

<!-- attached to Maven test phase -->



<execution>

<id>report-aggregate</id>

<phase>test</phase>


<goals>

<goal>report</goal>

</goals>

</execution>

</executions>

</plugin>
```
> Run `mvn test` to get the reports generated in the target folder

## VI Instructions for generating Pit reports (Mutation coverage)
- Changes have to be made in the pom.xml file of the respective project
- The below configuration must be added under <plugins> section of the pom.xml
```xml
<plugin>
        <groupId>org.pitest</groupId>
        <artifactId>pitest-maven</artifactId>
        <version>1.4.1</version>
        <executions>
                <execution>
                    <id>pit-report</id>
                    <phase>test</phase>
                    <goals>
                    <goal>mutationCoverage</goal>
                     </goals>
                    </execution>
                </executions>
                <configuration>
                    <targetClasses>
                        <param>org.apache.deltaspike.*</param>
                    </targetClasses>
                    <targetTests>
                        <param>*.*Test</param>
                    </targetTests>
                </configuration>
            </plugin>
```

- run `mvn test` in the command line to get the reports in the target folder

#### Note: Data for cyclomatic complexity was collected using MetricsReloaded plugin as well apart from Jacoco, but we decided to go with the data from jacoco as it was easier to compute class level cyclomatic complexity and coverage in Jacoco. So the Jacoco data can be found in the path '2019-SOEN-6611---Team-G\DataCollection\Metric1&2&4' and the data from Metrics reloaded is in the path '2019-SOEN-6611---Team-G\DataCollection\Metric4'
