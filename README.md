TECHNICAL DESIGN DOCUMENT

  Design Details about the script developed to Mine JIRA.
  
  Language : JAVA
  
  Packages Used :
	 
  1.Selenium (Version : 3.141.59).
    
  2.testng(Version : 6.8).
    
 3.Apache POI (Version : 4.0.1)
    
Include all the remaining dependencies as present in the pom.xml file.

Infrastructure required to replicate :

1.	chromedriver.exe should be located in the root folder.

2.	Google Chrome should be installed.

3.	Java should be installed.

4.	Maven should be installed.

5.	A folder names DataCollection , with the  should be present in the root folder. 

Directions to replicate 

1.	Open testing.xml and corresponding to the <include> tag within <methods> tag , give any of the following methods.

  ProjectList – Fetch total defect count for all of the projects.
  
  DefectwithoutVersion  - Fetch the number of defects with version as :No Version for all of the projects.
  
  UnspecifiedVersion -  Fetch the number of defects with version as :Unspecified  for all of the projects. The excel file will have only    those projects for which such defects are present.
  
  IdentifyProjects – Fetch the release wise defect count for released versions of the selected projects.

Each method has been declared as individual Tests and should be run individually.

2.	Open cmd and navigate to the root folder where pom.xml is present. Run the command : mvn test.

Class Description :
  1.	DefectDensity : Class file with the mining functions.
  
  2.	ProjectLoadException, VersionLoadException, DefectCountLoad : Custom Exception Classes.
  
  3.	DriverFactory : Singleton Class to initialise chromedriver .
  
Design Details about the script developed to Combine CSV Files:

Language : Python(3.7.1).

Packages Used :

	1.Pandas (0.24.2).
  
Infrastructure required to replicate :

1.	Python should be installed.

Directions to replicate :

1.Set variable ‘root’ to the path of directory where the files to be combined are placed.

2.Column names should be same in all the files to be combined.

