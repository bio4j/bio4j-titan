# Importing Titan Bio4j


## Source files for the import

The **XML/TXT** files from the different data sources used for this specific import were downloaded on the date **12/03/2014** and can be retrieved from the following [requester pays](http://docs.aws.amazon.com/AmazonS3/latest/dev/RequesterPaysBuckets.html) bucket:

- `s3://eu-west-1.raw.bio4j.com/`


## Steps for importing Titan Bio4j

_If you are not using AWS please go directly to the **Step 3**_.

1. Launch a new AWS EC2 instance

  Preferably `hi1.4xlarge` or similar.

2. Format and mount the ephemeral storage devices

  ```bash
  $ mkfs -t ext4 /dev/sdb
  $ mkdir -p /mnt/sources
  $ mount /dev/sdb /mnt/sources

  $ mkfs -t ext4 /dev/sdc
  $ mkdir -p /mnt/bio4jtitan
  $ mount /dev/sdc /mnt/bio4jtitan
  ```

3. Download and install official Java 8 JDK

  If you you can use yum for that:

  ```bash
  yum -y remove java-1.7.0-openjdk
  yum -y install java-1.8.0-openjdk
  ```

  Otherwise, here's the link to the official website for downloading [Java JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).

4. Download bio4j-titan jars and configuration files

  You need the executable jar for the current release and some `xml` and `properties` files. For bio4j-titan `0.4.0` these are

  - [`bio4j-titan-0.4.0-fat.jar`](https://s3-eu-west-1.amazonaws.com/releases.era7.com/bio4j/bio4j-titan/0.4.0/bio4j-titan-0.4.0-fat.jar)  
  The jar that you will need to run
  - [`executionsBio4jTitan.xml`](https://raw.githubusercontent.com/bio4j/bio4j-titan/v0.4.0/executionsBio4jTitan.xml)  
  This file contains the mapping between raw data and the corresponding modules; it can be changed in order to import only a subset of the available data.
  - [`uniprotData.xml`](https://raw.githubusercontent.com/bio4j/bio4j-titan/v0.4.0/uniprotData.xml)  
  This file will only be used in the case where you want to import Uniprot module. (Set the boolean flags included in the XML file to true/false depending on your choice of data you want to import from Uniprot)
  - [property files](https://github.com/bio4j/bio4j-titan/tree/v0.4.0/properties_files)  
  All the `.properties` files under this folder; you will need the ones corresponding to the modules you want to import.

  For the `xml` and `properties` files, you can just clone the bio4j/bio4j-titan repo and checkout the `0.4.0` tag.

5. Get the raw input data

  Download and execute (from `/mnt/sources/`) the following bash script:

  - [DownloadAndPrepareBio4jSources.sh](https://github.com/bio4j/bio4j-titan/blob/v0.4.0/DownloadAndPrepareBio4jSources.sh)

  It will download and decompress all the raw data needed to build a full Bio4j distribution (Swissprot, TrEMBL, GO, etc..).
  Once the script has finished, make sure that the final file names coincide with those specified in your XML file `executionsBio4jTitan.xml`.

  Since we are only interested in some of the data sources, it wouldn't make much sense to download the ones we were not going to include. We can avoid that simply removing the lines we don't need from this shell script.

6. _(Optional)_ Customize data to be imported

  **Bio4j is divided in modules** and so is the importing process, that way you don't have to import the whole thing in the case where you are interested only in some of the data sources ( **Gene Ontology**, **NCBI taxonomy tree**, etc). However you must be coherent when importing a set modules, that's to say, for example it's not possible to import the **Uniref clusters** without previously importing **Uniprot KB** - otherwise there wouldn't be proteins to link to in the clusters!

  In order to customize the modules that will be imported you have to modify the file **executionsBio4jTitan.xml**.
  Let's imagine that we want a database including only the Gene Ontology, NCBI taxonomy tree and Uniprot KB (only Swiss-prot entries).
  The corresponding `executionsBio4jTitan.xml` file should look like this:

  ``` xml
  <scheduled_executions>
    <execution>
      <class_full_name>com.bio4j.titan.model.go.programs.ImportGOTitan</class_full_name>
      <arguments>
        <argument>/mnt/sources/go.xml</argument>
        <argument>/mnt/bio4jtitan/bio4j</argument>
      </arguments>
    </execution>
    <execution>
      <class_full_name>com.bio4j.titan.model.uniprot.programs.ImportUniprotTitan</class_full_name>
      <arguments>
        <argument>/mnt/sources/uniprot_sprot.xml</argument>
        <argument>/mnt/bio4jtitan/bio4j</argument>
        <argument>/mnt/sources/uniprot_data.xml</argument>
      </arguments>
    </execution>
    <execution>
      <class_full_name>com.bio4j.titan.model.uniprot.programs.ImportUniprotTitan</class_full_name>
      <arguments>
        <argument>/mnt/sources/uniprot_trembl.xml</argument>
        <argument>/mnt/bio4jtitan/bio4j</argument>
        <argument>/mnt/sources/uniprot_data.xml</argument>
      </arguments>
    </execution>
    <execution>
      <class_full_name>com.bio4j.titan.model.uniprot_go.programs.ImportUniprotGoTitan</class_full_name>
      <arguments>
        <argument>/mnt/sources/uniprot_sprot.xml</argument>
        <argument>/mnt/bio4jtitan/bio4j</argument>
      </arguments>
    </execution>
    <execution>
      <class_full_name>com.bio4j.titan.model.uniprot_go.programs.ImportUniprotGoTitan</class_full_name>
      <arguments>
        <argument>/mnt/sources/uniprot_trembl.xml</argument>
        <argument>/mnt/bio4jtitan/bio4j</argument>
      </arguments>
    </execution>
    <execution>
      <class_full_name>com.bio4j.titan.model.ncbiTaxonomy.programs.ImportNCBITaxonomyTitan</class_full_name>
      <arguments>
        <argument>/mnt/sources/nodes.dmp</argument>
        <argument>/mnt/sources/names.dmp</argument>
        <argument>/mnt/sources/merged.dmp</argument>
        <argument>/mnt/bio4jtitan/bio4j</argument>
      </arguments>
    </execution>
    <execution>
      <class_full_name>com.bio4j.titan.model.uniprot_ncbiTaxonomy.programs.ImportUniprotNCBITaxonomyTitan</class_full_name>
      <arguments>
        <argument>/mnt/sources/uniprot_sprot.xml</argument>
        <argument>/mnt/bio4jtitan/bio4j</argument>
      </arguments>
    </execution>
    <execution>
      <class_full_name>com.bio4j.titan.model.uniprot_ncbiTaxonomy.programs.ImportUniprotNCBITaxonomyTitan</class_full_name>
      <arguments>
        <argument>/mnt/sources/uniprot_trembl.xml</argument>
        <argument>/mnt/bio4jtitan/bio4j</argument>
      </arguments>
    </execution>
  </scheduled_executions>
  ```

7. Launch importing process

  For example:

  ```  bash
  java -d64 -Xmx40G -jar bio4j-titan-0.4.0-fat.jar executionsBio4jTitan.xml &
  ```

  Different log files will be created at the jar folder level.

### How long will it take?

> All these tests were performed on a **hi1.4xlarge** instance using 40GB of memory for the Java process. The only configuration value that was changed was `"autotype" = "none"`


| Module                           |        Time |
|:---------------------------------|------------:|
| Gene Ontology                    |      1m 14s |
| Enzyme DB                        |          3s |
| NCBI Taxonomy                    |      8m 13s |
| Uniprot (SwissProt)              |  2h 22m 22s |
| Uniprot (TrEMBL)                 |           - |
| UniRef                           | 10h 37m 21s |
| Protein Interactions (SwissProt) |      8m 53s |
| Protein Interactions (TrEMBL)    |     17h 20m |

#### SwissProt/TrEMBL times for combined modules

Time spent by the following programs when using SwissProt or TrEMBL XML as source file:

|                     |  SwissProt |    TrEMBL |
|:--------------------|-----------:|----------:|
| UniprotGo           | 2h 20m 35s |    2d 12h |
| UniprotEnzymeDB     |     6m 28s |   12h 40m |
| UniprotNCBITaxonomy |     6m 32s | 1d 8h 40m |
|                     |            |           |
| **Total time**      | 2h 33m 35s | 4d 1h 20m |
