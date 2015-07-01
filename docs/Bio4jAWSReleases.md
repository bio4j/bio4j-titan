# Pre-built Bio4j releases in AWS

> This is the recommended way of using bio4j-titan. For this you need a working Amazon Web Services (AWS) account.

We offer two pre-imported bio4j-titan distributions:

- **bio4j lite** the size of the binaries is approximately `500GB`. It includes the following modules:  
    - UniProt (both SwissProt and TrEMBL)
    - Gene Ontology (GO)
    - NCBI taxonomy
    - Enzyme DB
    - UniProtGO
    - UniProtEnzymeDB
    - UniProtNCBITaxonomy
    - UniProtInteractions
    - UniProtIsoforms
- **bio4j full** the size of the binaries is approximately `1.2 TB`. This version includes all modules:
    - UniProt (both SwissProt and TrEMBL)
    - Gene Ontology (GO)
    - NCBI taxonomy
    - Enzyme DB
    - UniProtGO
    - UniProtEnzymeDB
    - UniProtNCBITaxonomy
    - UniProtInteractions
    - UniProtIsoforms
    - UniRef
    - UniProtUniRef
    - GenInfo
    - NCBITaxonomyGenInfo

They are available from S3 through a [requester pays](http://docs.aws.amazon.com/AmazonS3/latest/dev/RequesterPaysBuckets.html) bucket `s3://eu-west-1.releases.bio4j.com/`, in the `eu-west-1` (Ireland) region. The object addresses are

- **bio4j lite** `s3://eu-west-1.releases.bio4j.com/2014_12_03/bio4j_all_but_uniref_and_gi_index.tar`
- **bio4j full** `s3://eu-west-1.releases.bio4j.com/2014_12_03/bio4j_all_plus_isoforms.tar`

The way this is expected to be used is:

1. launch an EC2 instance in the `eu-west-1` region
2. download the binary files for either bio4j lite or bio4j full.
3. enjoy!

#### IMPORTANT: AWS cost and fees

AWS charges fees for downloading S3 objects: [AWS S3 pricing - data transfer](https://aws.amazon.com/s3/pricing/#Data_Transfer_Pricing). However, this is free _if you download it from an EC2 instance within the same region_. Thus, you won't incur in any data transfer cost if you download bio4j from an EC2 instance in the `eu-west-1` region. Your AWS costs would be in this case just those associated to the compute inrastructure: the EC2 instance/s and, if using them, EBS volumes.

**IMPORTANT:** If you download it from your local computer you will incur in sizable costs: around **50$** for bio4j lite and **120$** for bio4j full.

#### IAM user configuration

You need to grant permissions to the user/role which you will use to download the bio4j distribution: read access to `s3://eu-west-1.releases.bio4j.com/` is enough. The following is an IAM policy which is more than sufficient for that:

``` json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "Stmt1434711865000",
            "Effect": "Allow",
            "Action": [
                "s3:*"
            ],
            "Resource": [
                "arn:aws:s3:::eu-west-1.releases.bio4j.com"
            ]
        },
        {
            "Sid": "Stmt1434711990000",
            "Effect": "Allow",
            "Action": [
                "s3:*"
            ],
            "Resource": [
                "arn:aws:s3:::eu-west-1.releases.bio4j.com/*"
            ]
        }
    ]
}
```
