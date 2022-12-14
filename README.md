# seleniumtest (for VS Code)
Selenium test cases project for page [TestQA](https://demoqa.com/automation-practice-form) where it's needed to find bugs.
## Test cases identified (7)
  - Happy Path (TC_001)
  - Check empty fields validation (TC_002)
  - Check empty mandatory fields validation (TC_003)
  - Check for birth date validation (TC_004)
  - Check for wrong name validation (TC_005)
  - Check for wrong last name validation (TC_006)
  - Check to erase all fields (TC_007)
## Using this Repo with VS Code
It is mandatory to install [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) for VS Code.
### Prerequisites
To succesfully run this test case, is mandatory to have installed the dependencies below.
#### Apache Maven
Can be downloaded from [here](https://maven.apache.org/download.cgi). Please read documentation and follow [install](https://maven.apache.org/install.html) instruction according to your OS.
#### Java JDK 1.8 or superior
You can download it from [here](https://adoptium.net/es/). In this case, we are using OpenJDK 17.  
#### Git
You can download it from [here](https://git-scm.com/). Or you can get it through Winget with the following command:
  ```PowerShell
  winget install -e --id Git.Git
  ```
** NOTE: For Windows 10 OS you may need to install Winget for the command to run **