## Pre-built Bio4j releases in AWS

This is the best way to go if you don't have the resources for importing Bio4j in your cluster or you simply think that you will be getting a better deal by using it as a service.

There isn't any kind of fee required for using Bio4j in AWS, you will just be paying for the data transfer _(and obviously the instances, volumes, deployed by yourself)_
If you have any doubt please refer to this section on **[Requester pays buckets](http://docs.aws.amazon.com/AmazonS3/latest/dev/RequesterPaysBuckets.html)**.

We provide two different options:

### Lite version

This database weighs **~460 GB** and includes the following modules:

- UniProt (both SwissProt and TrEMBL) 
- Gene Ontology (GO)
- NCBI taxonomy
- Enzyme DB
- UniProtGO
- UniProtEnzymeDB
- UniProtNCBITaxonomy
- UniProtInteractions
- UniProtIsoforms

### Whole version

This version includes all modules and weighs **~1.2 TB**. Apart from the aforementioned you the following modules are available :

- UniRef
- UniProtUniRef
- GenInfo
- NCBITaxonomyGenInfo
