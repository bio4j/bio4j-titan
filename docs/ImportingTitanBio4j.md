## Steps for importing Titan Bio4j 

_If you are not using AWS please go directly to step 6_

#### 1. Launch a new AWS instance _(preferably **hi1.4xlarge**)_

#### 2. Create two folders under `/mnt`

``` bash
mkdir /mnt/sources
mkdir /mnt/bio4jtitan
```

#### 3. Format and mount the ephemeral storage devices

```  bash
mkfs -t ext4 /dev/sdb
mount /dev/sdb /mnt/sources
mkfs -t ext4 /dev/sdc
mount /dev/sdc /mnt/bio4jtitan
```

#### 4. Download and install official Java 8 JDK
Here's the link to the official website for downloading [Java JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

#### 7. Get these files:
- **bio4j-titan-0.4.0-SNAPSHOT-fat.jar** _(s3://snapshots.era7.com/bio4j/bio4j-titan/0.4.0-SNAPSHOT/bio4j-titan-0.4.0-SNAPSHOT-fat.jar)_
- [executionsBio4j.xml](https://github.com/bio4j/bio4j-titan/blob/master/executionsBio4jTitan.xml) _(this file can be customized in order to just import a sub-set of the data available)_
- [uniprotData.xml](https://github.com/bio4j/bio4j-titan/blob/master/uniprotData.xml) _This file will only be used in the case where you want to import Uniprot module. (Set the boolean flags included in the XML file to true/false depending on your choice of data you want to import from Uniprot)_

#### 8. Import the data!

+ Download and execute the following bash script:
  - [DownloadAndPrepareBio4jSources.sh](/DownloadAndPrepareBio4jSources.sh)

  This script downloads and decompresses all the sources needed to build a full Bio4j DB (Swissprot, TrEMBL, GO, etc..).
  (Once the script has finished, make sure that the final file names coincide with those specified in your XML file executionsBio4jTitan.xml).

+ Tuning executions.xml file

  **Bio4j is divided in modules** and so it is the importing process, that way you don't have to import the whole thing in the case where you are interested only in some of the data sources _( **Gene Ontology**, **NCBI taxonomy tree**, etc...)_. However you must be coherent when importing a set modules, that's to say, for example it's not possible to import the **Uniref clusters** without previously importing **Uniprot KB** - otherwise there wouldn't be proteins to link to in the clusters!

  In order to customize the modules that will be imported you have to modify the file **executionsBio4jTitan.xml**.
  Let's imagine that we want a database including only the Gene Ontology, NCBI taxonomy tree and Uniprot KB (only Swiss-prot entries). 
  The corresponding executions.xml file should look like this:

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
+ One more thing, since we are only interested in some of the data sources, it wouldn't make much sense to download the ones we were not going to include. We can avoid that simply removing the lines we don't need from the shell script _DownloadAndPrepareBio4jSources.sh_. For our example it should look something like this:

  ``` bash
  curl 'ftp://ftp.uniprot.org/pub/databases/uniprot/current_release/knowledgebase/complete/uniprot_sprot.xml.gz' -o uniprot_sprot.xml.gz
  curl 'http://archive.geneontology.org/latest-termdb/go_daily-termdb.obo-xml.gz' -o go.xml.gz
  curl 'ftp://ftp.expasy.org/databases/enzyme/enzyme.dat' -o enzyme.dat
  gzip -d *.gz
  curl 'ftp://ftp.ncbi.nih.gov/pub/taxonomy/taxdump.tar.gz' -o taxdump.tar.gz
  tar -xvf taxdump.tar.gz
  ```

#### 9. Launch importing process in background

```  bash
java -d64 -Xmx40G -jar bio4j-titan-0.4.0-SNAPSHOT-fat.jar executionsBio4jTitan.xml &
```

Different log files will be created at jar folder level regarding the progress of the data importation

### Importing process expected time

> All these tests were performed on a **hi1.4xlarge** instance using 40G of memory for the Java process. The only configuration value that was changed was "autotype" = "none"

Gene Ontology | Enzyme DB | NCBI Taxonomy | Uniprot (SwissProt) | Uniprot (TrEMBL) |  UniRef | Protein Interactions (SwissProt) | Protein Interactions (TrEMBL) |
--- | --- | --- | --- | --- | --- | --- | --- | 
1m 14s | 3s | 8m 13s | 2h 22m 22s | - | 10h 37m 21s | 6m 28s | - | 

#### SwissProt times for combined modules

_Time spent by the following programs when using SwissProt XML file as source file._

UniprotGo | UniprotEnzymeDB | UniprotNCBITaxonomy | Total time |
--- | --- | --- | --- |
2h 20m 35s | 6m 28s | 6m 32s | 2h 33m 35s |

#### TrEMBL times for combined modules

_Time spent by the following programs when using TrEMBL XML file as source file._

UniprotGo | UniprotEnzymeDB | UniprotNCBITaxonomy | Total time |
--- | --- | --- | --- |
- | - | - | - |

#### Combined modules that are not imported using Uniprot XML files

_None of the following programs use in any way any neither SwissProt nor TrEMBL XML files._

UniprotUniRef | GenInfoNCBITaxonIndex | IsoformSequences | Total time |
--- | --- | --- | --- |
-| - | - | - |
