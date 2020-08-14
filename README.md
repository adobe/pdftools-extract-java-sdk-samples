# Samples for the Adobe PDFTools Extract Java SDK

This sample project helps you get started with the PDFTools extract SDK.

The sample classes illustrate how to perform PDF-related extraction (extracting content of PDF in user friendly 
structured format) using the SDK.

## Prerequisites
The sample application has the following requirements:
* Java JDK : Version 8 or above.
* Build Tool: The application requires Maven to be installed. Maven installation instructions can be found 
[here](https://maven.apache.org/install.html).


## Authentication Setup

The api credentials file and corresponding private key file for the samples is ```pdftools-api-credentials.json``` and ```private.key``` 
respectively. Before the samples can be run, replace both the files with the ones present in the zip file received 
via [Beta Program Access](https://opensource.adobe.com/pdftools-sdk-docs/beta/extract/#beta-program-access) workflow.

The SDK also supports providing the authentication credentials at runtime, without storing them in a config file. Please
refer this [section](#extract-text-elements-by-providing-in-memory-authentication-credentials) to 
know more.

## Build with maven

Run the following command to build the project:
```$xslt
mvn clean install
```

Note that the PDFTools Extract SDK is listed as a dependency in the pom.xml and will be downloaded automatically.

## A Note on Logging
For logging, this SDK uses the [slf4j API](https://www.slf4j.org/) with a log4j2-slf4j binding. The logging configurations 
are provided in ```src/main/resources/log4j2.properties```. Alternate bindings, if required, can be specified in pom.xml.

## Structured Information Output Format
The output of SDK extract operation is Zip package. The Zip package consists of following:

* The structuredData.json file with the extracted content & PDF element structure. See the [JSON schema](https://opensource.adobe.com/pdftools-sdk-docs/shared/extractJSONOutputSchema.json). 
* A renditions folder(s) containing renditions for each element type selected as input. 
  The folder name is either “tables” or “figures” depending on your specified element type. 
  Each folder contains renditions with filenames that correspond to the element information in the JSON file. 


## Running the samples

The following sub-sections describe how to run the samples. Prior to running the samples, check that the credentials 
file is set up as described above and that the project has been built.

The code itself is in the ```com.adobe.platform.operation.samples.extractpdf``` package under the ```src/main/java/``` folder. Test 
files used by the samples can be found in ```src/main/resources/```. When executed, all samples create an ```output``` 
child folder under the working directory to store their results.

### Extract PDF Elements from PDF Document
These samples illustrate how to extract PDF elements from PDF. Refer to the documentation of [ExtractPDFOperation.java](https://opensource.adobe.com/pdftools-extract-java-sdk-samples/apidocs/com/adobe/platform/operation/pdfops/ExtractPDFOperation.html)
to see the list of inputs.

#### Extract Text Elements

The sample class ExtractTextInfoFromPDF.java extracts text elements from PDF Document.

```$xslt
mvn -f pom.xml exec:java -Dexec.mainClass=com.adobe.platform.operation.samples.extractpdf.ExtractTextInfoFromPDF
```

#### Extract Text, Table Elements

The sample class ExtractTextTableInfoFromPDF extracts text, table elements from PDF Document. 

```$xslt
mvn -f pom.xml exec:java -Dexec.mainClass=com.adobe.platform.operation.samples.extractpdf.ExtractTextTableInfoFromPDF
```
#### Extract Text, Table Elements with Renditions of Table Elements

The sample class ExtractTextTableInfoWithRenditionsFromPDF extracts text, table elements along with table renditions
from PDF Document. Note that the output is a zip containing the structured information along with renditions as described
in [section](#structured-information-output-format).

```$xslt
mvn -f pom.xml exec:java -Dexec.mainClass=com.adobe.platform.operation.samples.extractpdf.ExtractTextTableInfoWithRenditionsFromPDF
```
#### Extract Text, Table Elements with Renditions of Figure, Table Elements

The sample class ExtractTextTableInfoWithFiguresTablesRenditionsFromPDF extracts text, table elements along with figure 
and table element's renditions from PDF Document. Note that the output is a zip containing the structured information 
along with renditions as described in [section](#structured-information-output-format).

```$xslt
mvn -f pom.xml exec:java -Dexec.mainClass=com.adobe.platform.operation.samples.extractpdf.ExtractTextTableInfoWithFiguresTablesRenditionsFromPDF
```

#### Extract Text Elements (By providing in-memory Authentication credentials)

The sample class ExtractTextInfoFromPDFWithInMemoryAuthCredentials.java extracts text elements from PDF Document. 
This sample highlights how to provide in-memory auth credentials for performing an operation. 
This enables the clients to fetch the credentials from a secret server during runtime, instead of storing them in a file.

```$xslt
mvn -f pom.xml exec:java -Dexec.mainClass=com.adobe.platform.operation.samples.extractpdf.ExtractTextInfoFromPDFWithInMemoryAuthCredentials
```

### Contributing

Contributions are welcome! Read the [Contributing Guide](.github/CONTRIBUTING.md) for more information.

### Licensing

This project is licensed under the MIT License. See [LICENSE](LICENSE.md) for more information.
