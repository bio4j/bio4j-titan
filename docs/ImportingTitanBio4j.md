## Steps for importing Titan Bio4j

_If you are not using AWS please go directly to step 4_

#### 1. Launch a new AWS instance (preferably `hi1.4xlarge`)

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

#### 5. Download bio4j-titan jars and configuration files

You need the executable jar for the current release and some `xml` and `properties` files. For bio4j-titan `0.4.0-RC3` these are

- [bio4j-titan-0.4.0-RC3-fat.jar](https://s3-eu-west-1.amazonaws.com/releases.era7.com/bio4j/bio4j-titan/0.4.0-RC3/bio4j-titan-0.4.0-RC3-fat.jar) the jar that you will need to run
- [executionsBio4jTitan.xml](https://raw.githubusercontent.com/bio4j/bio4j-titan/v0.4.0-RC3/executionsBio4jTitan.xml) this file contains the mapping between raw data and the corresponding modules; it can be changed in order to import only a subset of the available data.
- [uniprotData.xml](https://raw.githubusercontent.com/bio4j/bio4j-titan/v0.4.0-RC3/uniprotData.xml) This file will only be used in the case where you want to import Uniprot module. (Set the boolean flags included in the XML file to true/false depending on your choice of data you want to import from Uniprot)
- [property files](https://github.com/bio4j/bio4j-titan/tree/v0.4.0-RC3/properties_files) All the `.properties` files under this folder; you will need the ones corresponding to the modules you want to import.

For the `xml` and `properties` files, you can just clone the bio4j/bio4j-titan repo and checkout the `0.4.0-RC3` tag.

#### 6. Get the raw input data

Download and execute the following bash script:

- [DownloadAndPrepareBio4jSources.sh](https://github.com/bio4j/bio4j-titan/blob/v0.4.0-RC3/DownloadAndPrepareBio4jSources.sh)

It will download and decompress all the raw data needed to build a full Bio4j distribution (Swissprot, TrEMBL, GO, etc..).
Once the script has finished, make sure that the final file names coincide with those specified in your XML file `executionsBio4jTitan.xml`.

Since we are only interested in some of the data sources, it wouldn't make much sense to download the ones we were not going to include. We can avoid that simply removing the lines we don't need from this shell script.

#### 7. (Optional) Customize data to be imported

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

#### 8. Launch importing process in background

For example:

```  bash
java -d64 -Xmx40G -jar bio4j-titan-0.4.0-SNAPSHOT-fat.jar executionsBio4jTitan.xml &
```

Different log files will be created at the jar folder level.

### How long will it take?

> All these tests were performed on a **hi1.4xlarge** instance using 40G of memory for the Java process. The only configuration value that was changed was `"autotype" = "none"`

| Gene Ontology | Enzyme DB | NCBI Taxonomy | Uniprot (SwissProt) | Uniprot (TrEMBL) | UniRef      | Protein Interactions (SwissProt) | Protein Interactions (TrEMBL) |
|:--------------|:----------|:--------------|:--------------------|:-----------------|:------------|:---------------------------------|:------------------------------|
| 1m 14s        | 3s        | 8m 13s        | 2h 22m 22s          | -                | 10h 37m 21s | 8m 53s                           | 17h 20m                       |

#### SwissProt times for combined modules

Time spent by the following programs when using SwissProt XML file as source file.

| UniprotGo  | UniprotEnzymeDB | UniprotNCBITaxonomy | Total time |
|:-----------|:----------------|:--------------------|:-----------|
| 2h 20m 35s | 6m 28s          | 6m 32s              | 2h 33m 35s |

#### TrEMBL times for combined modules

Time spent by the following programs when using TrEMBL XML file as source file.

| UniprotGo | UniprotEnzymeDB | UniprotNCBITaxonomy | Total time |
|:----------|:----------------|:--------------------|:-----------|
| 2d 12h    | 12h 40m         | 1d 8h 40m           | 4d 1h 20m  |
